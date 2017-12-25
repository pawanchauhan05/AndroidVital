package com.pawan.androidvital.fragment.ChooseImage;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pawan.androidvital.R;
import com.pawan.androidvital.utilities.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseImageFragment extends Fragment implements View.OnClickListener {
    private Button buttonFromCamera, buttonFromGallery, buttonFromCameraCrop, buttonFromGalleryCrop;

    public static final int CHOICE_AVATAR_FROM_CAMERA_CROP = 3;
    public static final int MY_PERMISSIONS_REQUEST_READ_PHOTO = 4;
    public static final int MY_PERMISSIONS_REQUEST_CLICK_PHOTO = 5;

    private Uri fileUri;
    private ImageView imageView;
    private boolean isForCrop;


    public ChooseImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_image, container, false);
        buttonFromCamera = (Button) view.findViewById(R.id.button_from_camera);
        buttonFromGallery = (Button) view.findViewById(R.id.button_from_gallery);
        buttonFromCameraCrop = (Button) view.findViewById(R.id.button_from_camera_crop);
        buttonFromGalleryCrop = (Button) view.findViewById(R.id.button_from_gallery_crop);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        buttonFromCamera.setOnClickListener(this);
        buttonFromGallery.setOnClickListener(this);
        buttonFromCameraCrop.setOnClickListener(this);
        buttonFromGalleryCrop.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.button_from_gallery:
                isForCrop = false;
                if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissionsForGallery();
                }
                break;
            case R.id.button_from_camera:
                isForCrop = false;
                if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissionsForCamera();
                }
                break;

            case R.id.button_from_camera_crop:
                isForCrop = true;
                if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissionsForCamera();
                }
                break;

            case R.id.button_from_gallery_crop:
                isForCrop = true;
                if (Build.VERSION.SDK_INT >= 23) {
                    requestPermissionsForGallery();
                }

                break;
        }
    }

    private void requestPermissionsForGallery() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_PHOTO);
        } else {
            galleryIntent(600);
        }
    }

    private void requestPermissionsForCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CLICK_PHOTO);
        } else {
            cameraIntent(500);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHOTO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent(600);
                } else {
                    Toast.makeText(getContext(), "Please allow permission", Toast.LENGTH_SHORT).show();
                }
            }

            case MY_PERMISSIONS_REQUEST_CLICK_PHOTO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraIntent(500);
                } else {
                    Toast.makeText(getContext(), "Please allow permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private Intent getCropIntent(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        return intent;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == 500) {
                    Log.e("Pawan", "onActivityResult: camera");

                    String split[] = getFileUri().getPath().split("/");
                    if (split.length > 0) {
                        String imageName = split[split.length - 1];
                        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constants.IMAGE_DIRECTORY_NAME);
                        File file = new File(mediaStorageDir.getPath() + File.separator + imageName);
                        Uri resizedUri = resizeBitmap(Uri.fromFile(file), Constants.MAX_IMAGE_RESOLUTION, getActivity());
                        if (resizedUri == null)
                            resizedUri = Uri.fromFile(file);

                        if (isForCrop) {
                            performCrop(resizedUri);
                        } else {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), resizedUri);
                                Bitmap rotatedBitmap = imageOrientationValidator(bitmap, file.getPath());
                                imageView.setImageBitmap(rotatedBitmap);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    }


                } else if (requestCode == 600) {
                    Log.e("Pawan", "onActivityResult: gallery");
                    if (data != null) {
                        if (isForCrop) {
                            Uri resizedUri = resizeBitmap(data.getData(), Constants.MAX_IMAGE_RESOLUTION, getActivity());
                            performCrop(resizedUri);
                        } else {
                            Uri resizedUri = resizeBitmap(data.getData(), Constants.MAX_IMAGE_RESOLUTION, getActivity());
                            imageView.setImageURI(resizedUri);
                        }
                    }
                } else if (requestCode == CHOICE_AVATAR_FROM_CAMERA_CROP) {
                    if (data != null) {
                        Uri resizedUri = resizeBitmap(data.getData(), Constants.MAX_IMAGE_RESOLUTION, getActivity());
                        imageView.setImageURI(resizedUri);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Pawan", "onActivityResult: " + e.getMessage());
        }
    }

    public void cameraIntent(int cameraRequestCode) {
        setFileUri(getOutputMediaFileUri(getActivity(), MEDIA_TYPE_IMAGE));

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getFileUri());
        startActivityForResult(intent, cameraRequestCode);

    }

    public void galleryIntent(int galleryRequestCode) {
        final String[] ACCEPT_MIME_TYPES = {
                "image/*"
        };
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), galleryRequestCode);
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public void setFileUri(Uri fileUri) {
        this.fileUri = fileUri;
    }

    public static Uri getOutputMediaFileUri(Context context, int type) {
        try {
            Uri photoURI = FileProvider.getUriForFile(context, Constants.APPLICATION_ID + ".provider", getOutputMediaFile(type));
            return photoURI;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constants.IMAGE_DIRECTORY_NAME);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("Rahul", "Oops! Failed create " + Constants.IMAGE_DIRECTORY_NAME + " directory");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    public static Uri resizeBitmap(Uri photoUri, int maxSize, Context context) {
        try {
            String name = getFileName(photoUri, context);
            Log.e("rahul before resize", photoUri.getEncodedPath());
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), photoUri);
            if (bitmap == null)
                Log.e("rahul scale down", "bitmap is null");
            else
                Log.e("rahul scale down", "bitmap is not null");
            Bitmap scaledBitmap = scaleDown(bitmap, maxSize, false);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), scaledBitmap, name, null);
            return Uri.parse(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFileName(Uri uri, Context context) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public Bitmap imageOrientationValidator(Bitmap bitmap, String path) {
        ExifInterface ei;
        try {
            ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bitmap = rotateImage(bitmap, 270);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Bitmap rotateImage(Bitmap source, float angle) {

        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bitmap;
    }

    private void performCrop(Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties here
            cropIntent.putExtra("crop", true);
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CHOICE_AVATAR_FROM_CAMERA_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}

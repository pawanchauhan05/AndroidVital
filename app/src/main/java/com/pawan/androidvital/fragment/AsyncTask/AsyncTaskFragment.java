package com.pawan.androidvital.fragment.AsyncTask;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pawan.androidvital.R;
import com.pawan.androidvital.app.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsyncTaskFragment extends Fragment implements View.OnClickListener {

    private TextView textViewName, textViewEmail, textViewDesignation;
    private Button buttonDownloadAudio, buttonShowData;
    public static final String URL = "";


    public AsyncTaskFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_async_task, container, false);
        textViewName = (TextView) view.findViewById(R.id.text_view_name);
        textViewEmail = (TextView) view.findViewById(R.id.text_view_email);
        textViewDesignation = (TextView) view.findViewById(R.id.text_view_designation);
        buttonDownloadAudio = (Button) view.findViewById(R.id.button_download_audio);
        buttonShowData = (Button) view.findViewById(R.id.button_load_data);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_download_audio) {
            new DownloadMusicFile().execute(URL);
        } else if(id == R.id.button_load_data) {
            new LoadData().execute("");
        }
    }

    class DownloadMusicFile extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... musicURL) {
            int count;
            try {
                URL url = new URL(musicURL[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "filename.mp3");
                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];

                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Utils.generateToast(getContext(), "Music Download complete.", true);
        }
    }

    class LoadData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... musicURL) {
            return inputStreamToString(musicURL[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                textViewName.setText(jsonObject.getString("name"));
                textViewEmail.setText(jsonObject.getString("email"));
                textViewDesignation.setText(jsonObject.getString("designation"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String inputStreamToString(String serverURL) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String data;

            try {
                URL url = new URL(serverURL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null)
                    return null;
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0)
                    return null;
                data = buffer.toString();
                return data;
            } catch (IOException e) {
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                    }
                }
            }
        }

    }

}

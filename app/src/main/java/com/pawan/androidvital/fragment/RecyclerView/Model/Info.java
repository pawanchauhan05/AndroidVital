package com.pawan.androidvital.fragment.RecyclerView.Model;

/**
 * Created by divya on 13/01/17.
 */

public class Info {

    private String name;
    private String institute;


    public Info() {
    }

    public Info(String name, String institute) {
        this.name = name;
        this.institute = institute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}

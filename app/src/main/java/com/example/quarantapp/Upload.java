package com.example.quarantapp;
// Modelo de clase para la receta.
public class Upload {
    private String mName;
    private String mShortDescription;
    private String mSteps;
    private String mImageUrl;
    private String mUserID;



    public Upload() {
        // Constructor Vacio Necesario para Firebase
    }

    public Upload(String mName, String mShortDescription, String mSteps, String mImageUrl, String mUserID) {
        if (mName.trim().equals("")){
            mName = "No name";
        }

        this.mName = mName;
        this.mShortDescription = mShortDescription;
        this.mSteps = mSteps;
        this.mImageUrl = mImageUrl;
        this.mUserID = mUserID;
    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmSteps() {
        return mSteps;
    }

    public void setmSteps(String mSteps) {
        this.mSteps = mSteps;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}

package com.marialijideveloper.cakeslist_waracle;

import android.os.Parcelable;

/**
 * Created by marialijideveloper on 15/3/18.
 */

public abstract class CakeAdapter implements Parcelable {

    public String ImageURL;
    public String ImageTitle;
    public String ImageDesc;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        this.ImageURL = imageURL;

    }

    public String getImageTitle() {
        return ImageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.ImageTitle = imageTitle;
    }

    public String getImageDesc() {
        return ImageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.ImageDesc = imageDesc;
    }




}

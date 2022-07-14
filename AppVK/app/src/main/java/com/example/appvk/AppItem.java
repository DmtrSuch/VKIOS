package com.example.appvk;

public class AppItem {
    private String mImageUrl;
    private String name;
    private String description;
    private String link;

    public AppItem(String mImageUrl, String name, String description, String link) {
        this.mImageUrl = mImageUrl;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}

package com.example.application_animation;

public class Model {
    String title,description,urlToImage,url;


    public Model(){

    }

    public Model(String title, String description, String urlToImage,String url) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url=url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


}

package com.example.marvel_arnau;

import java.util.List;

public class Film {
        String authorlink;
        String authorname;
        String contact;
        String title;
        String description;
        String url;
        String urlToImage;
        String publishedAt;
        String content;
        String[] media;
        String urlvideo;
        String[] comments;

    public String[] getMedia() {
        return media;
    }

    public String getMediaS() {
        String value = "";
        for (int i = 0; i < media.length; i++){
            if (i == 0){
                value = media[i];
            } else {
                value = value + ", " + media[i];
            }
        }
        return value;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public Film(){}

    public Film(String authorlink, String authorname, String contact, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.authorlink = authorlink;
        this.authorname = authorname;
        this.contact = contact;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthorlink() {
        return authorlink;
    }

    public void setAuthorlink(String authorlink) {
        this.authorlink = authorlink;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

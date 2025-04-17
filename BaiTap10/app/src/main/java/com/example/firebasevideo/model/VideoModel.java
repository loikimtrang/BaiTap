package com.example.firebasevideo.model;

public class VideoModel {
    public String videoUrl;
    public String title;
    public String userId;

    public VideoModel() {
    }

    public VideoModel(String videoUrl, String title, String userId) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.userId = userId;
    }
}


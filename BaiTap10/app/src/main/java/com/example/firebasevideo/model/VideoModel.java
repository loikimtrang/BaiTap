package com.example.firebasevideo.model;

import java.util.Map;

public class VideoModel {
    public String videoId;
    public String videoUrl;
    public String title;
    public String userId;
    public Map<String, Boolean> likes;
    public VideoModel() {
    }

    public VideoModel(String videoUrl, String title, String userId) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.userId = userId;
    }
    public int getLikesCount() {
        return likes != null ? likes.size() : 0;
    }
}


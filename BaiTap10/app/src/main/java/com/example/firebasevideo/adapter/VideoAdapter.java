package com.example.firebasevideo.adapter;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasevideo.R;
import com.example.firebasevideo.model.VideoModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<VideoModel> videoList;
    private FirebaseAuth mAuth;
    private DatabaseReference videosRef;

    public VideoAdapter(List<VideoModel> videoList) {
        this.videoList = videoList;
        this.mAuth = FirebaseAuth.getInstance();
        this.videosRef = FirebaseDatabase.getInstance().getReference("videos");
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel video = videoList.get(position);

        holder.player = new SimpleExoPlayer.Builder(holder.itemView.getContext()).build();
        holder.playerView.setPlayer(holder.player);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(video.videoUrl));
        holder.player.setMediaItem(mediaItem);
        holder.player.prepare();
        holder.player.setPlayWhenReady(true);

        holder.countHeart.setText(String.valueOf(video.getLikesCount()));

        boolean isLiked = video.likes != null &&
                video.likes.containsKey(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        holder.heart.setImageResource(isLiked ? R.drawable.heart_check : R.drawable.heart);

        holder.heart.setOnClickListener(v -> {
            if (mAuth.getCurrentUser() != null) {
                toggleLike(video, holder);
            }
        });

        if (holder.titleText != null && video.title != null) {
            holder.titleText.setText(video.title);
        }
    }

    @Override
    public int getItemCount() {
        return videoList != null ? videoList.size() : 0;
    }

    private void toggleLike(VideoModel video, VideoViewHolder holder) {
        String userId = mAuth.getCurrentUser().getUid();
        if (video.likes == null) {
            video.likes = new HashMap<>();
        }

        DatabaseReference videoRef = videosRef.child(video.videoId);

        if (video.likes.containsKey(userId)) {
            video.likes.remove(userId);
            holder.heart.setImageResource(R.drawable.heart);
        } else {
            video.likes.put(userId, true);
            holder.heart.setImageResource(R.drawable.heart_check);
        }

        holder.countHeart.setText(String.valueOf(video.getLikesCount()));
        videoRef.child("likes").setValue(video.likes);
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        SimpleExoPlayer player;
        ImageView heart;
        TextView countHeart;
        TextView titleText;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.player_view);
            heart = itemView.findViewById(R.id.heart);
            countHeart = itemView.findViewById(R.id.count_heart);
            titleText = itemView.findViewById(R.id.video_title);
        }

        public void releasePlayer() {
            if (player != null) {
                player.release();
                player = null;
            }
        }
    }
}

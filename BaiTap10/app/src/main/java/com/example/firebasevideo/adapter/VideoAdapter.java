package com.example.firebasevideo.adapter;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasevideo.R;
import com.example.firebasevideo.model.VideoModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoModel> videoList;

    public VideoAdapter(List<VideoModel> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
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
    }

    @Override
    public void onViewRecycled(@NonNull VideoViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder.player != null) {
            holder.player.release();
            holder.player = null;
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        SimpleExoPlayer player;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.player_view);
        }
    }
}



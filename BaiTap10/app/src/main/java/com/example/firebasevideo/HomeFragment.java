package com.example.firebasevideo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasevideo.adapter.VideoAdapter;
import com.example.firebasevideo.model.VideoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private VideoAdapter adapter;
    private DatabaseReference videoRef;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        tvEmpty = view.findViewById(R.id.tvEmpty);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        videoRef = FirebaseDatabase.getInstance().getReference("videos");
        mAuth = FirebaseAuth.getInstance();

        loadVideos();
        return view;
    }

    private void loadVideos() {
        progressBar.setVisibility(View.VISIBLE);
        List<VideoModel> videos = new ArrayList<>();

        videoRef.orderByChild("userId").equalTo(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot item : snapshot.getChildren()) {
                            VideoModel video = item.getValue(VideoModel.class);
                            video.videoId = item.getKey();
                            videos.add(video);
                        }

                        progressBar.setVisibility(View.GONE);

                        if (videos.isEmpty()) {
                            tvEmpty.setVisibility(View.VISIBLE);
                        } else {
                            adapter = new VideoAdapter(videos);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Lỗi: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}

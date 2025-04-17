package com.example.firebasevideo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasevideo.model.VideoModel;
import com.example.firebasevideo.util.CloudinaryHelper;
import com.example.firebasevideo.util.FileUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {
    private static final int PICK_VIDEO_REQUEST = 1;
    private Uri videoUri;
    private TextView tvFileName;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference videoRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Button btnSelect = findViewById(R.id.btnSelectVideo);
        Button btnUpload = findViewById(R.id.btnUpload);
        tvFileName = findViewById(R.id.tvFileName);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        videoRef = FirebaseDatabase.getInstance().getReference("videos");

        btnSelect.setOnClickListener(v -> openFilePicker());
        btnUpload.setOnClickListener(v -> {
            if (videoUri != null) {
                uploadVideoToCloudinary(videoUri);
            } else {
                Toast.makeText(this, "Hãy chọn video trước", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn Video"), PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null) {
            videoUri = data.getData();
            String fileName = FileUtils.getFileName(this, videoUri);
            tvFileName.setText(fileName != null ? fileName : "Video đã chọn");
        }
    }

    private void uploadVideoToCloudinary(Uri uri) {
        progressBar.setVisibility(View.VISIBLE);
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            new Thread(() -> {
                String videoUrl = CloudinaryHelper.uploadVideo(inputStream);
                runOnUiThread(() -> {
                    if (videoUrl != null) {
                        String uid = mAuth.getUid();
                        String videoId = videoRef.push().getKey();
                        VideoModel model = new VideoModel(videoUrl, "Video của tôi", uid);
                        model.videoId = videoId;

                        videoRef.child(videoId).setValue(model)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Upload thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Lỗi: " + e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        Toast.makeText(this, "Lỗi khi upload lên Cloudinary",
                                Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
            }).start();
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Lỗi khi đọc video", Toast.LENGTH_SHORT).show();
        }
    }
}

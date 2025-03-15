package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvSongs;
    SongAdapter songAdapter;
    List<SongModel> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSongs = findViewById(R.id.rv_songs);

        songList = new ArrayList<>();
        songList.add(new SongModel("S001", "Bài Ca Tình Yêu", "Lời ca em viết...", "Hà Anh Tuấn"));
        songList.add(new SongModel("S002", "Nắng Ấm Xa Dần", "Một chiều nắng phai...", "Sơn Tùng MTP"));
        songList.add(new SongModel("S003", "Lạc Trôi", "Lạc trôi về đâu...", "Sơn Tùng MTP"));
        songList.add(new SongModel("S004", "Đi Để Trở Về", "Khi ta bước đi...", "Soobin Hoàng Sơn"));
        songList.add(new SongModel("S005", "Ngày Mai Em Đi", "Ngày mai em đi, còn anh ở lại...", "Bùi Anh Tuấn"));
        songList.add(new SongModel("S006", "Em Gái Mưa", "Mưa đã rơi, em đã đi...", "Hương Tràm"));
        songList.add(new SongModel("S007", "Chạy Ngay Đi", "Cứ để em đi một mình...", "Sơn Tùng MTP"));
        songList.add(new SongModel("S008", "Phía Sau Một Cô Gái", "Đôi mắt em hờ hững...", "Soobin Hoàng Sơn"));
        songList.add(new SongModel("S009", "Cảm Ơn Vì Tất Cả", "Cảm ơn anh đã đến bên đời em...", "Minh Vương M4U"));
        songList.add(new SongModel("S010", "Mùa Ta Đã Yêu", "Dưới ánh trăng, chúng ta yêu nhau...", "Hồ Ngọc Hà"));

        songAdapter = new SongAdapter(this);
        rvSongs.setAdapter(songAdapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        songAdapter.setData(songList);
    }
}

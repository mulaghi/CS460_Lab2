package com.example.swipevideo_lab2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPage);
        //Data pulling from database
        List<VideoItem> videoItemList = new ArrayList<>();
        VideoItem videoRoo = new VideoItem();
        videoRoo.videoURL = "https://firebasestorage.googleapis.com/v0/b/swipeapp-a2756.appspot.com/o/export_1650435664625.MP4?alt=media&token=22a8f91c-61a3-4a22-b6d3-b02e6f00e6d0";
        videoRoo.videoTitle = "Roo Girls 1st Snow Day";
        videoRoo.videoID = "ID: 019769";
        videoRoo.videoDescription = "Roo was experiencing her 1st time in snow while she was a puppy";
        videoItemList.add(videoRoo);

        VideoItem videoYoshi = new VideoItem();
        videoYoshi.videoURL = "https://firebasestorage.googleapis.com/v0/b/swipeapp-a2756.appspot.com/o/iGPmN5ggNo-uywBNu.mp4?alt=media&token=9f7a0f36-6bfb-441b-933d-521ef9a1863f";
        videoYoshi.videoTitle = "Extended Bad Breath";
        videoYoshi.videoID = "ID: 739249";
        videoYoshi.videoDescription = "A fun clip of me and my friend playing tekken 8!";
        videoItemList.add(videoYoshi);

        VideoItem videoRooDay = new VideoItem();
        videoRooDay.videoURL = "https://firebasestorage.googleapis.com/v0/b/swipeapp-a2756.appspot.com/o/IMG_7350.mp4?alt=media&token=0244092d-b3fd-4f56-bcce-5f58471d6972";
        videoRooDay.videoTitle = "Roo Daytime Snow!";
        videoRooDay.videoID = "ID: 258765";
        videoRooDay.videoDescription = "A fun clip of roo in the snow during the day time!";
        videoItemList.add(videoRooDay);

        videoViewPager.setAdapter(new VideoAdapter(videoItemList));

    }


}
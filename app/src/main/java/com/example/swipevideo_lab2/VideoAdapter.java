package com.example.swipevideo_lab2;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private List<VideoItem> videoItems;

    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }


    /**
     * Creates and returns a VideoViewHolder that holds the view for each video item.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new view (not used here as all items are of the same type).
     * @return A new VideoViewHolder that holds the view for a video item.
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent , false)
        );
    }

    /**
     * Binds the video data to the ViewHolder for the video at the specified position in the list.
     *
     * @param holder The VideoViewHolder to bind data to.
     * @param position The position of the video item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * Returns the total number of video items in the list.
     *
     * @return The number of video items in the list.
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }


    /**
     *
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder{
        TextView textVideoTitle1, textVideoDescription1, textVideoID;
        VideoView videoView;
        ProgressBar videoProgressBar;

        /**
         * Constructs a VideoViewHolder, initializing the views that will display video information.
         *
         * @param itemView The view representing a single video item.
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle1);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription1);
            textVideoID = itemView.findViewById(R.id.textVideoID);
            videoProgressBar = itemView.findViewById(R.id.videoProgressBar);
        }
        /**
         * Binds the data from a VideoItem to the views in the ViewHolder.
         * It sets the video URL to the VideoView and handles video playback.
         *
         * @param videoItem The VideoItem containing the data to be displayed.
         */
        void setVideoData(VideoItem videoItem ){
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            textVideoID.setText(videoItem.videoID);
            videoView.setVideoPath(videoItem.videoURL);


            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Show a progress bar until the video is ready to play
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    videoProgressBar.setVisibility(View.GONE);
                    mediaPlayer.start();

                    float videoRatio = mediaPlayer.getVideoWidth() / (float) mediaPlayer.getVideoHeight();
                    float screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;

                    if(scale >= 1) {
                        videoView.setScaleX(scale);
                    }else{
                        videoView.setScaleY(1f / scale);
                    }

                }
            });

            // Loop the video when it finishes playing
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }

    }

}

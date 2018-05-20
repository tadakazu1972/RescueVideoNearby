package osakacityfire.rescuevideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by tadakazu on 2017/09/29.
 */

public class VideoActivity extends AppCompatActivity {
    protected VideoActivity mActivity;
    protected View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        mView = this.getWindow().getDecorView();

        setContentView(R.layout.activity_video);

        //MainActivityのボタンから押されたビデオのindexを受け取り
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer index = bundle.getInt("index");
        Uri videoPath = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.adult);
        if ( index == 1 ){
            videoPath = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.adult);
        }
        if ( index == 2 ){
            videoPath = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.child);
        }
        if ( index == 3 ){
            videoPath = Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.baby);
        }
        final VideoView videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(mActivity));
        videoView.setVideoURI(videoPath);

        //準備が完了したら再生
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp){
                videoView.start();
            }
        });

        videoView.start();
        //再生完了通知リスナー
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp){
                videoView.seekTo(0);
                videoView.start();
            }
        });
    }
}

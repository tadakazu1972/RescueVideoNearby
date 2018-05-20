package osakacityfire.rescuevideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.Nearby;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    protected MainActivity mActivity;
    protected View mView;
    protected MessageListener mMessageListener;
    protected Message mMessage;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        mView = this.getWindow().getDecorView();

        setContentView(R.layout.activity_main);

        //ボタン初期化
        initButtons();

        //Nearby Messages API
        mMessageListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
                Log.d(TAG, "Found message: " + new String(message.getContent()));
            }

            @Override
            public void onLost(Message message) {
                Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
            }
        };

        mMessage = new Message("Hello World".getBytes());

    }

    @Override
    public void onStart(){
        super.onStart();

        Nearby.getMessagesClient(this).publish(mMessage);
        Nearby.getMessagesClient(this).subscribe(mMessageListener);
    }

    @Override
    public void onStop(){
        Nearby.getMessagesClient(this).unpublish(mMessage);
        Nearby.getMessagesClient(this).unsubscribe(mMessageListener);
        super.onStop();
    }

    //ボタン初期化
    private void initButtons(){
        //ビデオ１
        mView.findViewById(R.id.btnVideo1).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, VideoActivity.class);
                intent.putExtra("index", 1);
                startActivity(intent);
            }
        });

        //ビデオ２
        mView.findViewById(R.id.btnVideo2).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, VideoActivity.class);
                intent.putExtra("index", 2);
                startActivity(intent);
            }
        });

        //ビデオ３
        mView.findViewById(R.id.btnVideo3).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mActivity, VideoActivity.class);
                intent.putExtra("index", 3);
                startActivity(intent);
            }
        });
    }
}

package blehblu.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashAc extends AppCompatActivity {
    private ImageView igview;
    private TextView text;
    Animation aniImage,aniText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        text=findViewById(R.id.textView);
        igview=findViewById(R.id.imageView);

        aniImage= AnimationUtils.loadAnimation(this,R.anim.pic_ani);
        aniText=AnimationUtils.loadAnimation(this,R.anim.text_ani);
        igview.setAnimation(aniImage);
        text.setAnimation(aniText);

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
             startActivity(new Intent(splashAc.this,MainActivity.class));
             finish();
            }
        }.start();
    }
}
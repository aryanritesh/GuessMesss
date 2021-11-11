package blehblu.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
      private Button start;
      private RadioButton twoD,threeD,fourD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.Begin);
        twoD=findViewById(R.id.twoDigit);
        threeD=findViewById(R.id.threeDigit);
        fourD=findViewById(R.id.fourDigit);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game.class);
                if(!twoD.isChecked() && !threeD.isChecked() && !fourD.isChecked()){
                    Snackbar.make(view,"Select the number of digits",Snackbar.LENGTH_LONG).show();
                }
                else{
                    if(twoD.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if(threeD.isChecked()){
                        intent.putExtra("three",true);
                    }
                    if(fourD.isChecked()){
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
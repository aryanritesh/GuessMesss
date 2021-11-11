package blehblu.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    private TextView lastAns,guess1,hint;
    private Button check;
    private EditText input;
    boolean TwoDigits,ThreeDigits,FourDigits;
    Random r=new Random();
    int random;
    int lives=5;
    ArrayList<Integer> guessList=new ArrayList<>();
    int userAttempts=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        lastAns=findViewById(R.id.lastAns);
        guess1=findViewById(R.id.guess);
        hint=findViewById(R.id.hint);
        check=findViewById(R.id.confirm);
        input=findViewById(R.id.inputGuess);
        TwoDigits=getIntent().getBooleanExtra("two",false);
        ThreeDigits=getIntent().getBooleanExtra("three",false);
        FourDigits=getIntent().getBooleanExtra("four",false);

        if(TwoDigits){
            random=r.nextInt(90)+10;
        }
        if(ThreeDigits){
            random=r.nextInt(900)+100;
        }
        if(FourDigits){
            random=r.nextInt(9000)+1000;
        }
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess=input.getText().toString();
                if(guess.equals("")){
                    Toast.makeText(Game.this,"Enter a number",Toast.LENGTH_LONG).show();
                }
                else{
                    lastAns.setVisibility(view.VISIBLE);
                    guess1.setVisibility(view.VISIBLE);
                    hint.setVisibility(view.VISIBLE);
                    userAttempts++;
                    lives--;
                    int playerG=Integer.parseInt(guess);
                    guessList.add(playerG);
                    lastAns.setText("Your previous guess was :"+guess);
                    guess1.setText("Lives left : "+lives);

                    if(random==playerG){
                        AlertDialog.Builder builder= new AlertDialog.Builder(Game.this);
                        builder.setTitle("GuessMesss");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations! That is correct.\n\n It took you "+userAttempts+" attempts. \n\n Your guesses were "+guessList+
                                "\n\n Would you wish to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(Game.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    else if(random<playerG){
                        hint.setText("Your guess was higher than the number");

                    }
                    else if(random>playerG){
                        hint.setText("Your guess was lower than the number");

                    }
                    if(lives==0){
                        AlertDialog.Builder builder= new AlertDialog.Builder(Game.this);
                        builder.setTitle("GuessMesss");
                        builder.setCancelable(false);
                        builder.setMessage("Ooops. Seems like you have run out of attempts :("+"\n\n The correct number was "+random+
                                "\n\n It took you "+userAttempts+" attempts. \n\n Your guesses were "+guessList+
                                "\n\n Would you wish to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(Game.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    input.setText("");

                }
            }
        });
    }
}
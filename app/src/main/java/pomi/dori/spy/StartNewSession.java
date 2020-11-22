package pomi.dori.spy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartNewSession extends AppCompatActivity {
    int numberofplayers=6;
    int numberofspies=2;
    int timeinminutes=15;
    TextView NumOfPlayers;
    TextView NumOfSpies;
    TextView TimeInMinutes;
    SeekBar SeekBarNP, SeekBarNS, SeekBarTime;

    Button buttonBack, buttonCont;
    private Toast contToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_session);
        NumOfPlayers = (TextView)findViewById(R.id.NumOfPlayers);
        NumOfSpies = (TextView)findViewById(R.id.NumOfSpies);
        NumOfPlayers .setText(String.valueOf(numberofplayers));
        NumOfSpies.setText(String.valueOf(numberofspies));

        TimeInMinutes = (TextView)findViewById(R.id.minutes);
        TimeInMinutes.setText(String.valueOf(timeinminutes));
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonBack = (Button) findViewById(R.id.buttonBack2);
        buttonCont = (Button) findViewById(R.id.buttonCont);

        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try {
                    switch (view.getId()){
                        case R.id.buttonBack2:
                            intent = new Intent(StartNewSession.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case R.id.buttonCont:
                            if (numberofplayers==0 || numberofspies==0){
                                contToast = Toast.makeText(getBaseContext(), "Игра не может быть создана", Toast.LENGTH_SHORT);
                                contToast.show();
                            }else {
                                intent = new Intent(StartNewSession.this, EnterNames.class);
                                intent.putExtra("numofplayers", numberofplayers);
                                intent.putExtra("numofspies", numberofspies);
                                intent.putExtra("timeinminutes", timeinminutes);
                                startActivity(intent);
                                finish();
                            }
                            break;
                    }
                }catch (Exception e){
                    //
                }
            }
            };
        buttonCont.setOnClickListener(OnClickListener);
        buttonBack.setOnClickListener(OnClickListener);

        SeekBarNP = (SeekBar) findViewById(R.id.seekBarPN);
        SeekBarNS = (SeekBar) findViewById(R.id.seekBarSN);
        SeekBarTime = (SeekBar) findViewById(R.id.seekBarTime);

        SeekBarNP.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBarNS.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBarTime.setOnSeekBarChangeListener(seekBarChangeListener);

        SeekBarNP.setProgress(numberofplayers);
        SeekBarNS.setProgress(numberofspies);
        SeekBarTime.setProgress(timeinminutes-1);

        }
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()){
                case R.id.seekBarPN:
                    numberofplayers = seekBar.getProgress();
                    NumOfPlayers.setText(String.valueOf(numberofplayers));
                    SeekBarNS.setMax(numberofplayers/3);
                    break;
                case R.id.seekBarSN:
                    numberofspies = seekBar.getProgress();
                    NumOfSpies.setText(String.valueOf(numberofspies));
                    break;
                case R.id.seekBarTime:
                    timeinminutes = seekBar.getProgress()+1;
                    TimeInMinutes.setText(String.valueOf(timeinminutes));
                    break;
            }


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(StartNewSession.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }

    }
}







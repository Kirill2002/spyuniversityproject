package pomi.dori.spy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long backPressedTimer;
    private Toast backToast;
    Button buttonRules;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonRules = (Button)findViewById(R.id.buttonRules);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        View.OnClickListener OnClickListener = new View.OnClickListener(){
          @Override
          public void onClick(View view){
              Intent intent;
              try {
                  switch (view.getId()){
                      case R.id.buttonStart:
                          intent = new Intent(MainActivity.this, StartNewSession.class);
                          startActivity(intent);
                          break;
                      case R.id.buttonRules:
                          intent = new Intent(MainActivity.this, Rules.class);
                          startActivity(intent);
                          break;
                  }
                  finish();
              }catch (Exception e){
                  //
              }
          }
        };

        buttonRules.setOnClickListener(OnClickListener);
        buttonStart.setOnClickListener(OnClickListener);




    }

    @Override
    public void onBackPressed() {
        if (backPressedTimer + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTimer = System.currentTimeMillis();

    }
}
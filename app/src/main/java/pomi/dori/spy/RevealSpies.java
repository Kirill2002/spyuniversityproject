package pomi.dori.spy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RevealSpies extends AppCompatActivity {
    private long backPressedTimer;
    private Toast backToast;
    Button buttonNewGame;
    Session gameses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revealspies);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Bundle args = getIntent().getExtras();
//        if (args!=null){
//            gameses = (Session) args.getSerializable(Session.class.getSimpleName());
//        }
//        buttonNewGame = (Button)findViewById(R.id.newGame);
//        View.OnClickListener OnClickListener = new View.OnClickListener(){
//          @Override
//          public void onClick(View view){
//              Intent intent;
//              try {
//                  if (view.getId() == R.id.newGame) {
//                      intent = new Intent(RevealSpies.this, StartNewSession.class);
//                      startActivity(intent);
//                  }
//                  finish();
//              }catch (Exception e){
//                  //
//              }
//          }
//        };
//
//        buttonNewGame.setOnClickListener(OnClickListener);

//        showSpies();


    }

//    public void showSpies(){
//        LinearLayout container = findViewById(R.id.spiescont);
//        for (int i=0; i<gameses.NumOfPlayers; i++){
//            if (gameses.players[i].isSpy) {
//                String tmp = getString(R.string.player, i+1);
//                tmp += " " + gameses.players[i].Name;
//                TextView text = new TextView(this);
//                text.setText(tmp);
//                text.setTextSize(30);
//                text.setPadding(10, 10, 10, 10);
//                container.addView(text);
//            }
//        }
//    }


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
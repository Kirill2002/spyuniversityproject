package pomi.dori.spy;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {
    public Session gameses;
    public int top=1;
    int timeLeft;
    public boolean playflag = false;
    public boolean ContIsCreated = false;
    CountDownTimer countDown;

    View.OnClickListener OnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){

                if (top<=gameses.NumOfPlayers) {
                    if (view == findViewById(top)) {
                        Card card = findViewById(top);
                        if (card.FrontSide) {
                            card.Remove();
                            top++;
                            if (top>gameses.NumOfPlayers){
                                RelativeLayout container = findViewById(R.id.cardContainer);
                                RelativeLayout ln = findViewById(R.id.wholeContainer);
                                ln.removeView(container);
                                CreateTimer();
                            }
                        } else {
                            card.TurnOver();
                        }

                    }
                }else {
                    switch (view.getId()){
                        case R.id.play_btn:
                            if (playflag){
                                playflag = !playflag;
                                if (countDown!=null) {
                                    countDown.cancel();
                                }
                            }else{
                                playflag = !playflag;
                                startTimer(timeLeft);
                            }
                            break;
                        case R.id.reset:
                            timeLeft=gameses.Time;
                            TextView timer = findViewById(R.id.timer);
                            int mins = timeLeft/60;
                            int secs = timeLeft%60;
                            String m = String.valueOf(mins);
                            String s = String.valueOf(secs);
                            if (mins<10) m = "0" + m;
                            if (secs<10) s = "0" + s;
                            timer.setText(m + ":" +s);
                            if (countDown!=null) {
                                playflag = false;
                                countDown.cancel();
                            }
                            break;
                        case R.id.ContBtn:
                            if (countDown!=null) {
                                countDown.cancel();
                            }

                                Intent intent = new Intent(Game.this, RevealSpies.class);
                                intent.putExtra(Player.class.getSimpleName(), gameses.players);
                            try {
                                startActivity(intent);
                            }catch (Exception e){

                            }
                                finish();

                            break;
                    }

                }



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle args = getIntent().getExtras();
        if (args!=null){
            gameses = (Session) args.getSerializable(Session.class.getSimpleName());
            timeLeft = gameses.Time;
        }

        CreateCards();
        }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Game.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }

    }

    public void CreateCards(){
        RelativeLayout container = (RelativeLayout)findViewById(R.id.cardContainer);
        gameses.cards = new Card[gameses.NumOfPlayers];
        for (int i=gameses.NumOfPlayers-1; i>=0; i--){
            gameses.cards[i] = new Card(this);
            gameses.cards[i].Number=i+1;
            String Text = getString(R.string.player, gameses.players[i].Number) + "\n" + gameses.players[i].Name + "\n";
            if (gameses.players[i].isSpy){
                Text += getString(R.string.spy);
            }else {
                Text += getString(R.string.location) + gameses.Location + "\n" + getString(R.string.role) + gameses.players[i].Role;
            }
            gameses.cards[i].Text = Text;
            gameses.cards[i].drawCard();
            gameses.cards[i].setOnClickListener(OnClickListener);
            container.addView(gameses.cards[i]);
//            RelativeLayout card = new RelativeLayout(this);
//            RelativeLayout.LayoutParams lpView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//            lpView.setMargins(80, 200, 80, 200);
//            lpView.addRule(RelativeLayout.CENTER_HORIZONTAL);
//            lpView.addRule(RelativeLayout.CENTER_VERTICAL);
//            card.setLayoutParams(lpView);
//            card.setId(i+1);
//            ImageView img = new ImageView(this);
//            img.setBackgroundResource(R.drawable.card_backside);
//            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            card.addView(img);
//            TextView num = new TextView(this);
//            num.setText(getString(R.string.card, i+1));
//            num.setTextSize(30);
//            card.addView(num);
//            container.addView(card);
        }

    }
    public void CreateTimer(){

        ImageButton play = findViewById(R.id.play_btn);
        play.setOnClickListener(OnClickListener);
        ImageButton reset = findViewById(R.id.reset);
        reset.setOnClickListener(OnClickListener);

        TextView timer = findViewById(R.id.timer);
        int mins = gameses.Time/60;
        int secs = gameses.Time%60;
        String m = String.valueOf(mins);
        String s = String.valueOf(secs);
        if (mins<10) m = "0" + m;
        if (secs<10) s = "0" + s;
        timer.setText(m + ":" +s);
        CreateCont();



    }

    public void startTimer(final int t){
        countDown = new CountDownTimer(t*1000, 1000){
            TextView timer = findViewById(R.id.timer);
            int time = t;
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                int mins = time/60;
                int secs = time%60;
                String m = String.valueOf(mins);
                String s = String.valueOf(secs);
                if (mins<10) m = "0" + m;
                if (secs<10) s = "0" + s;
                timer.setText(m + ":" +s);
                timeLeft = time;
            }


            @Override
            public void onFinish() {
                timer.setText("Time Over");
            }
        }.start();
    }
    public void CreateCont(){
        if (!ContIsCreated) {
            LinearLayout.LayoutParams lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 20);

            LinearLayout lnforCont = new LinearLayout(this);
            lnforCont.setOrientation(LinearLayout.VERTICAL);
            lnforCont.setLayoutParams(lpview);

            lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 30);

            Space sp = new Space(this);
            sp.setLayoutParams(lpview);
            lnforCont.addView(sp);

            lpview = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);

            Button contButt = new Button(this);
            contButt.setId(R.id.ContBtn);
            contButt.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
            contButt.setLayoutParams(lpview);
            lpview.setMargins(50, 0, 50, 20);
            contButt.setText(R.string.revealspies);
            contButt.setTextSize(30);
            lnforCont.addView(contButt);
            contButt.setOnClickListener(OnClickListener);

            Space spForCont = findViewById(R.id.spaceForCont);
            LinearLayout timerContainer = findViewById(R.id.timerContainer);
            timerContainer.removeView(spForCont);
            timerContainer.addView(lnforCont);
            ContIsCreated = true;
        }

    }

}







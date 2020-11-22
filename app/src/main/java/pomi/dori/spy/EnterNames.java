package pomi.dori.spy;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterNames extends AppCompatActivity {
    int numberofplayers;
    int numberofspies;
    int timeinminutes;
    private Toast ToastNames;
    Button buttonBack, buttonCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_names);
        Bundle arguments = getIntent().getExtras();
        numberofplayers = arguments.getInt("numofplayers");
        numberofspies = arguments.getInt("numofspies");
        timeinminutes = arguments.getInt("timeinminutes");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonCont = (Button)findViewById(R.id.buttonCont2);
        View.OnClickListener OnClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent;
                try {
                    switch (view.getId()){
                        case R.id.buttonCont2:
                            String[] pls = new String[numberofplayers];
                            boolean flag=true;
                            for (int i=0; i<numberofplayers; i++){
                                EditText tmp = (EditText)findViewById(i);
                                pls[i] = tmp.getText().toString();

                                if (pls[i].equals("")){
                                    ToastNames = Toast.makeText(getBaseContext(), "Заполните все поля", Toast.LENGTH_SHORT);
                                    ToastNames.show();
                                    flag=false;
                                }

                            }
                            if (flag) {
                                Session gameses = new Session(timeinminutes*60, numberofplayers, numberofspies, pls);
                                intent = new Intent(EnterNames.this, Game.class);
                                intent.putExtra(Session.class.getSimpleName(), gameses);
                                startActivity(intent);
                                finish();
                            }
                            break;
                        case R.id.buttonBack:
                            intent = new Intent(EnterNames.this, StartNewSession.class);
                            startActivity(intent);
                            finish();
                            break;
                    }

                }catch (Exception e){

                }
            }
        };

        buttonBack.setOnClickListener(OnClickListener);
        buttonCont.setOnClickListener(OnClickListener);

        this.createTextboxes();

    }

    public void createTextboxes(){
        LinearLayout layout;
        layout = (LinearLayout) findViewById(R.id.namescontainer);

        for (int i=0; i<numberofplayers; i++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lpView);
            row.setPadding(20, 20, 20, 20);


            TextView text = new TextView(this);
            text.setText(getString(R.string.player, i+1));
            text.setTextSize(30);
            text.setPadding(10, 10, 10, 10);
            row.addView(text);
            EditText editText = new EditText(this);
            editText.setId(i);
            editText.setLayoutParams(lpView);
            editText.setHint(R.string.name);
            row.addView(editText);
            layout.addView(row);


        }
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(EnterNames.this, StartNewSession.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
}
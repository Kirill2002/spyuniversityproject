package pomi.dori.spy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EnterNames extends AppCompatActivity {
    int numberofplayers;
    int numberofspies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_names);
        Bundle arguments = getIntent().getExtras();
        numberofplayers = arguments.getInt("numofplayers");
        numberofspies = arguments.getInt("numofspies");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(EnterNames.this, StartNewSession.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }

        });
        this.createTextboxes();

    }

    public void createTextboxes(){
        LinearLayout layout;
        layout = (LinearLayout) findViewById(R.id.namescontainer);

        for (int i=0; i<numberofplayers; i++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(20, 20, 20, 20);
            TextView text = new TextView(this);

            text.setText(getString(R.string.player, i+1));
            text.setTextSize(30);
            text.setPadding(10, 10, 10, 10);
            row.addView(text);
            EditText editText = new EditText(this);
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
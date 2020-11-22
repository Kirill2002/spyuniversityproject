package pomi.dori.spy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;


public class Card extends androidx.appcompat.widget.AppCompatButton implements Serializable {
    public String Text;
    public int Number;
    public boolean FrontSide=false;
    public Context cont;

    public Card(Context context){
        super(context);
        cont = context;
    }
    public Card(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        cont = context;
    }

    public Card(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        cont = context;
    }
    public void drawCard(){
        this.setId(Number);
        RelativeLayout.LayoutParams lpView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lpView.setMargins(80, 200, 80, 200);
        lpView.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpView.addRule(RelativeLayout.CENTER_VERTICAL);
        this.setLayoutParams(lpView);
        this.setBackgroundResource(R.drawable.card_backside);
//        this.setText(cont.getString(R.string.card, Number));
//        this.setTextSize(20);
//        this.setPadding(20, 20, 0, 0);
//        this.setGravity(Gravity.START);
//        this.setTextAlignment(this.getGravity());
//        View.LayoutParams lpView = new View.LayoutParams(View.LayoutParams.MATCH_PARENT, View.LayoutParams.MATCH_PARENT);

//        this.setId(Number);
//        this.setClickable(true);
//        LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        lpView.setMargins(80, 200, 80, 200);
//        lpView.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        lpView.addRule(RelativeLayout.CENTER_VERTICAL);
//        this.setLayoutParams(lpView);
//        img = new ImageView(cont);
//        imgId = Number+20;
//        img.setId(imgId);
//        img.setBackgroundResource(R.drawable.card_backside);
//        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        this.addView(img);
//        num = new TextView(cont);
//        LayoutParams lpView2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        lpView2.setMargins(20, 20, 20, 20);
//        num.setLayoutParams(lpView2);
//
//        num.setText(cont.getString(R.string.card, Number));
//        num.setTextSize(30);
//        this.addView(num);
    }


    public void TurnOver(){
        this.setBackgroundResource(R.drawable.card_frontside);
        this.setText(this.Text);
        this.setPadding(0, 0, 0, 0);
        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.setGravity(Gravity.CENTER);
        this.FrontSide=true;
    }


    public void Remove(){
        RelativeLayout cardcont = (RelativeLayout) this.getParent();
        cardcont.removeView(this);
    }
}

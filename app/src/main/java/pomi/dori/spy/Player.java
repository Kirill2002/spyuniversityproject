package pomi.dori.spy;

import java.io.Serializable;

public class Player implements Serializable{
    public String Name;
    public boolean isSpy;
    public String Role;
    public int Number;
    Player(String N, boolean iS, String R, int Num){
        Name = N;
        isSpy = iS;
        Role = R;
        Number = Num;
    }
}

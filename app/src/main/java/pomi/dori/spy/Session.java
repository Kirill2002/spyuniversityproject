package pomi.dori.spy;

import java.io.Serializable;
import java.util.HashSet;

public class Session implements Serializable{
    public String Location;
    public int Time;
    public int NumOfPlayers;
    public int NumOfSpies;
    public Player[] players;
    Card[] cards;
    Session(int T, int NOP, int NOS, String[] PNames){
        Location="Баня";
        NumOfPlayers=NOP;
        NumOfSpies=NOS;
        players = new Player[NOP];
        Time=T;
        HashSet<Integer> spies = new HashSet<>();
        while(spies.size()<NOS){
            spies.add(1 + (int)(Math.random()*NOS));
        }
        for (int i=0; i<NOP; i++){
            players[i] = new Player(PNames[i], spies.contains(i), "Шлюха", i+1);
        }
    }
//    public void StartTimer(){
//        long start = System.currentTimeMillis();
//        while(System.currentTimeMillis()<start+Time){
//
//        }
//        this.OutOfTime();
//    }
//    public void CreateCards(){
//
//    }
//
//    public void OutOfTime(){
//        dosomething
//    }
//    public void HelpWithQuestion(){
//
//    }
}

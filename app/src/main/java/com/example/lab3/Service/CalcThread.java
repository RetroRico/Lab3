package com.example.lab3.Service;

import com.example.lab3.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class CalcThread extends Thread{
    private static List<Observer> observers = new ArrayList<>();
    private final double M;
    private final double p;
    private final double cstart;
    private final double cend;

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }


    CalcThread(double M, double p, double cstart, double cend){
        this.M = M;
        this.p = p;
        this.cstart = cstart;
        this.cend = cend;
    }
    @Override
    public void run(){
        double Sy = 12 * M;
        double Sc = p * Sy;
        double Ci = cstart + (10 * ((cend - cstart) / 12));
        double W = 0;
        for (int i = 1; i <= 12; i++) {
            W += (p * M) / Ci;
        }
        double Sh = W * cend;
        double Sl = Sy - Sc;
        double H = Sh + Sl;
        double r = H - Sy;
        Result result = new Result(Sy, Sc, W, Sh, Sl, H, r);
        System.out.println(Sy);
        for (Observer observer : observers) {
            observer.update(null, result);
        }
    }

}

package com.example.lab3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lab3.R;
import com.example.lab3.Result;
import com.example.lab3.Service.CalcService;
import com.example.lab3.Service.CalcThread;

import java.util.Observable;
import java.util.Observer;

public class ResultFr extends Fragment implements Observer {
    private TextView TSy,TSc,TW,TSh,TSl,TH,Tr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);

        TSy = view.findViewById(R.id.Sy_TextView);
        TSc = view.findViewById(R.id.Sc_TextView);
        TW = view.findViewById(R.id.W_TextView);
        TSh = view.findViewById(R.id.Sh_TextView);
        TSl = view.findViewById(R.id.Sl_TextView);
        TH = view.findViewById(R.id.H_TextView);
        Tr = view.findViewById(R.id.R_TextView);
        Button ok_button = view.findViewById(R.id.ok_button);

        CalcThread.addObserver(this);


        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFr mainFr = new MainFr();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, mainFr)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Result){
            Result result = (Result) arg;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TSy.setText(String.format("%.2f", result.getSy()));
                    TSc.setText(String.format("%.2f",result.getSc()));
                    TW.setText(String.format("%.2f",result.getW()));
                    TSh.setText(String.format("%.2f",result.getSh()));
                    TSl.setText(String.format("%.2f",result.getSl()));
                    TH.setText(String.format("%.2f",result.getH()));
                    Tr.setText(String.format("%.2f",result.getR()));
                }
            });
        }
    }
}

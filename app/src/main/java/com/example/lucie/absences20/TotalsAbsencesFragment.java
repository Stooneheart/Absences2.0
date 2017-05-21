package com.example.lucie.absences20;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class TotalsAbsencesFragment extends Fragment {


    private ListView maListViewPerso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_totals_absences, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        
    }





}

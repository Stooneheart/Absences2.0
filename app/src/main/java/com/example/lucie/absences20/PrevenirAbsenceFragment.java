package com.example.lucie.absences20;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Guillaume Rosenrib on 17/05/2017.
 */

public class PrevenirAbsenceFragment extends android.app.Fragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.prevenir_absence, container, false);
        return mView;
    }
}


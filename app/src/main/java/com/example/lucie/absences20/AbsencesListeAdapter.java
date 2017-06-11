package com.example.lucie.absences20;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lucie on 22/05/2017.
 */

public class AbsencesListeAdapter extends ArrayAdapter<InfosAbsences> {
    private static final String TAG = "AbsencesListeAdapter";

    private Context mContext;
    int mResource;

    public AbsencesListeAdapter(Context context, int resource, ArrayList<InfosAbsences> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String module = getItem(position).getModule();
        String date = getItem(position).getDate();
        String prenom_prof = getItem(position).getPrenom_prof();
        String nom_prof = getItem(position).getNom_prof();
        String statut = getItem(position).getStatut();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvmodule = (TextView) convertView.findViewById(R.id.tvmodule);
        TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
        TextView tvpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
        TextView tvnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
        TextView tvstatut = (TextView) convertView.findViewById(R.id.tvstatut);

        tvmodule.setText(module);
        tvdate.setText(date);
        tvpreprof.setText(prenom_prof);
        tvnomprof.setText(nom_prof);
        tvstatut.setText(statut);

        return convertView;

    }


}

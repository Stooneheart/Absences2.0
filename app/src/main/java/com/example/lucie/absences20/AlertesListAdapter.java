package com.example.lucie.absences20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucie on 09/06/2017.
 */

public class AlertesListAdapter extends ArrayAdapter<InfosAlertes> {

    private static final String TAG = "ClassementListeAdapter";

    private Context mContext;
    int mResource;

    public AlertesListAdapter(Context context, int resource, ArrayList<InfosAlertes> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String prenom = getItem(position).getPrenom_eleve();
        String nom = getItem(position).getNom_eleve();
        String promotion = getItem(position).getPromotion();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvprenom = (TextView) convertView.findViewById(R.id.tvprenom);
        TextView tvnom = (TextView) convertView.findViewById(R.id.tvnom);
        TextView tvnbrabsences = (TextView) convertView.findViewById(R.id.tvpromo);

        tvprenom.setText(prenom);
        tvnom.setText(nom);
        tvnbrabsences.setText(promotion);

        return convertView;

    }
}

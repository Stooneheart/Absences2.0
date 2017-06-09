package com.example.lucie.absences20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucie on 22/05/2017.
 */

public class ClassementPromoListeAdapter extends ArrayAdapter<InfosClassement> {
    private static final String TAG = "ClassementListeAdapter";

    private Context mContext;
    int mResource;

    public ClassementPromoListeAdapter(Context context, int resource, ArrayList<InfosClassement> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String prenom = getItem(position).getPrenom();
        String nom = getItem(position).getNom();
        String nbrAbsence = getItem(position).getNbr_absences();

        InfosClassement infoClassement = new InfosClassement(prenom,nom,nbrAbsence);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvprenom = (TextView) convertView.findViewById(R.id.tvprenom);
        TextView tvnom = (TextView) convertView.findViewById(R.id.tvnom);
        TextView tvnbrabsences = (TextView) convertView.findViewById(R.id.tvnbr);

        tvprenom.setText(prenom);
        tvnom.setText(nom);
        tvnbrabsences.setText(nbrAbsence);

        return convertView;

    }
}

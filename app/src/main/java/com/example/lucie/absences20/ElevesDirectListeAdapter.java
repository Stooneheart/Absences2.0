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

public class ElevesDirectListeAdapter extends ArrayAdapter<InfosElevesDirect> {
    private static final String TAG = "ElevesDirectListeAdapter";

    private Context mContext;
    int mResource;

    public ElevesDirectListeAdapter(Context context, int resource, ArrayList<InfosElevesDirect> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String prenom = getItem(position).getPrenom();
        String nom = getItem(position).getNom();
        boolean absent = getItem(position).isAbsent();

        InfosElevesDirect infoClassement = new InfosElevesDirect(prenom,nom,absent);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvprenom = (TextView) convertView.findViewById(R.id.tvprenom);
        TextView tvnom = (TextView) convertView.findViewById(R.id.tvnom);
        TextView tvabsent = (TextView) convertView.findViewById(R.id.tvabsent);

        tvprenom.setText(prenom);
        tvnom.setText(nom);
        if(absent)
            tvabsent.setText("absent");
        else
            tvabsent.setText("present");

        return convertView;

    }
}

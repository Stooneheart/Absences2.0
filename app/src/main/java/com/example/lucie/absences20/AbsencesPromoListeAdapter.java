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

public class AbsencesPromoListeAdapter extends ArrayAdapter<InfosAbsencesPromotion> {
    private static final String TAG = "AbsencesListeAdapter";

    private Context mContext;
    int mResource;

    public AbsencesPromoListeAdapter(Context context, int resource, ArrayList<InfosAbsencesPromotion> objects) {
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
        String prenom_eleve = getItem(position).getPrenom_eleve();
        String nom_eleve = getItem(position).getNom_eleve();
        String statut = getItem(position).getStatut();

        InfosAbsencesPromotion infoAbs = new InfosAbsencesPromotion(module,date,prenom_prof,nom_prof, prenom_eleve, nom_eleve, statut);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvmodule = (TextView) convertView.findViewById(R.id.tvmodule);
        TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
        TextView tvpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
        TextView tvnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
        TextView tvpreeleve = (TextView) convertView.findViewById(R.id.tvpreeleve);
        TextView tvnomeleve = (TextView) convertView.findViewById(R.id.tvnomeleve);
        TextView tvstatut = (TextView) convertView.findViewById(R.id.tvstatut);

        tvmodule.setText(module);
        tvdate.setText(date);
        tvpreprof.setText(prenom_prof);
        tvnomprof.setText(nom_prof);
        tvpreeleve.setText(prenom_eleve);
        tvnomeleve.setText(nom_eleve);
        tvstatut.setText(statut);

        return convertView;

    }
}

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
 * Created by lucie on 22/05/2017.
 */

public class AbsencesPromoListeAdapter extends BaseAdapter {
    private static final String TAG = "AbsencesListeAdapter";

    private ArrayList<InfosAbsencesPromotion> absListPromo;
    private ArrayList<InfosAbsencesPromotion> filterListPromo;
    private LayoutInflater inflater;
    private Context mContext;

    public AbsencesPromoListeAdapter(Context context, ArrayList<InfosAbsencesPromotion> objects) {
        mContext = context;
        absListPromo = objects;
    }

    @Override
    public int getCount() {
        if(filterListPromo == null) {
            return absListPromo.size();
        } else {
            return filterListPromo.size();
        }
    }

    public Object getItem(int i){
        return absListPromo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.affichage_absences_promo, null);
        }

        if(filterListPromo == null) {

            InfosAbsencesPromotion absence = absListPromo.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String prenom_eleve = absence.getPrenom_eleve();
            String nom_eleve = absence.getNom_eleve();
            String statut = absence.getStatut();

            TextView tvmodule = (TextView) convertView.findViewById(R.id.tvmodule);
            TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView tvpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
            TextView tvnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
            TextView tvpreeleve = (TextView) convertView.findViewById(R.id.tvprenomeleve);
            TextView tvnomeleve = (TextView) convertView.findViewById(R.id.tvnomeleve);
            TextView tvstatut = (TextView) convertView.findViewById(R.id.tvstatut);

            tvmodule.setText(module);
            tvdate.setText(date);
            tvpreprof.setText(prenom_prof);
            tvnomprof.setText(nom_prof);
            tvpreeleve.setText(prenom_eleve);
            tvnomeleve.setText(nom_eleve);
            tvstatut.setText(statut);
        } else {
            InfosAbsencesPromotion absence = filterListPromo.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String prenom_eleve = absence.getPrenom_eleve();
            String nom_eleve = absence.getNom_eleve();
            String statut = absence.getStatut();

            TextView tvmodule = (TextView) convertView.findViewById(R.id.tvmodule);
            TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView tvpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
            TextView tvnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
            TextView tvpreeleve = (TextView) convertView.findViewById(R.id.tvprenomeleve);
            TextView tvnomeleve = (TextView) convertView.findViewById(R.id.tvnomeleve);
            TextView tvstatut = (TextView) convertView.findViewById(R.id.tvstatut);

            tvmodule.setText(module);
            tvdate.setText(date);
            tvpreprof.setText(prenom_prof);
            tvnomprof.setText(nom_prof);
            tvpreeleve.setText(prenom_eleve);
            tvnomeleve.setText(nom_eleve);
            tvstatut.setText(statut);
        }
        return convertView;
    }

    public void getFilter(String message) {
        if (message != null && message.length() != 0) {
            filterListPromo = new ArrayList<>();
            filterListPromo.clear();
            for (int i = 0; i < absListPromo.size(); i++) {
                if ((absListPromo.get(i).getModule().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getDate().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getNom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getPrenom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getNom_eleve().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getPrenom_eleve().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListPromo.get(i).getStatut().toUpperCase())
                        .contains(message.toString().toUpperCase())) {
                    InfosAbsencesPromotion absence = new InfosAbsencesPromotion(absListPromo.get(i).getModule(), absListPromo.get(i).getDate(),
                            absListPromo.get(i).getPrenom_eleve(), absListPromo.get(i).getNom_eleve(), absListPromo.get(i).getPrenom_prof(),
                            absListPromo.get(i).getNom_prof(), absListPromo.get(i).getStatut());
                    filterListPromo.add(absence);
                }
            }
        } else {
            filterListPromo = absListPromo;
        }
    }

    public void getUnfilter(){
        filterListPromo.clear();
        filterListPromo = absListPromo;
    }
}

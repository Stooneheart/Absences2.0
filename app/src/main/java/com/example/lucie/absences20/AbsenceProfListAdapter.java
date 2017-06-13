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

public class AbsenceProfListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<InfosAbsencesProf> absListProf;
    private LayoutInflater inflater;
    private ArrayList<InfosAbsencesProf> filterListProf;


    public AbsenceProfListAdapter(Context context, ArrayList<InfosAbsencesProf> objects) {
        mContext = context;
        absListProf = objects;
    }
    @Override
    public int getCount() {
        if(filterListProf == null) {
            return absListProf.size();
        } else {
            return filterListProf.size();
        }
    }

    public Object getItem(int i){
        return absListProf.get(i);
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
            convertView = inflater.inflate(R.layout.affichage_absences_prof, null);
        }

        if(filterListProf == null) {

            InfosAbsencesProf absence = absListProf.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String statut = absence.getStatut();

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

        }else{
            InfosAbsencesProf absence = filterListProf.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String statut = absence.getStatut();

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
        }

        return convertView;

    }

    public void getFilter(String message) {
        if (message != null && message.length() != 0) {
            filterListProf = new ArrayList<>();
            filterListProf.clear();
            for (int i = 0; i < absListProf.size(); i++) {
                if ((absListProf.get(i).getModule().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListProf.get(i).getDate().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListProf.get(i).getNom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListProf.get(i).getPrenom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absListProf.get(i).getStatut().toUpperCase())
                        .contains(message.toString().toUpperCase())) {
                    InfosAbsencesProf absence = new InfosAbsencesProf(absListProf.get(i).getModule(), absListProf.get(i).getDate(), absListProf.get(i).getPrenom_prof(),
                            absListProf.get(i).getNom_prof(), absListProf.get(i).getStatut());
                    filterListProf.add(absence);
                }
            }
        } else {
            filterListProf = absListProf;
        }
    }

    public void getUnfilter(){
        filterListProf.clear();
        filterListProf = absListProf;
    }
}

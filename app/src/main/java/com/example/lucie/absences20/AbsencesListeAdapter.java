package com.example.lucie.absences20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucie on 22/05/2017.
 */

public class AbsencesListeAdapter extends BaseAdapter{
    private static final String TAG = "AbsencesListeAdapter";

    private Context mContext;
    private ArrayList<InfosAbsences> absList;
    private LayoutInflater inflater;
    private ArrayList<InfosAbsences> filterList;

    public AbsencesListeAdapter(Context context, ArrayList<InfosAbsences> objects) {
        mContext = context;
        this.absList = objects;
    }


    @Override
    public int getCount() {
        if(filterList == null) {
            return absList.size();
        } else {
            return filterList.size();
        }
    }

    public Object getItem(int i){
        return absList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position , View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.affichage_absences, null);
        }

        if(filterList == null) {

            InfosAbsences absence = absList.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String statut = absence.getStatut();

            TextView txtmodule = (TextView) convertView.findViewById(R.id.tvmodule);
            TextView txtdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView txtpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
            TextView txtnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
            TextView txtstatut = (TextView) convertView.findViewById(R.id.tvstatut);

            txtmodule.setText(module);
            txtdate.setText(date);
            txtpreprof.setText(prenom_prof);
            txtnomprof.setText(nom_prof);
            txtstatut.setText(statut);
        }else {
            InfosAbsences absence = filterList.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String statut = absence.getStatut();

            TextView txtmodule = (TextView) convertView.findViewById(R.id.tvmodule);
            TextView txtdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView txtpreprof = (TextView) convertView.findViewById(R.id.tvpreprof);
            TextView txtnomprof = (TextView) convertView.findViewById(R.id.tvnomprof);
            TextView txtstatut = (TextView) convertView.findViewById(R.id.tvstatut);

            txtmodule.setText(module);
            txtdate.setText(date);
            txtpreprof.setText(prenom_prof);
            txtnomprof.setText(nom_prof);
            txtstatut.setText(statut);
        }
        return convertView;
    }

    public void getFilter(String message) {
        if (message != null && message.length() != 0) {
            filterList = new ArrayList<>();
            filterList.clear();
            for (int i = 0; i < absList.size(); i++) {
                if ((absList.get(i).getModule().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absList.get(i).getDate().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absList.get(i).getNom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absList.get(i).getPrenom_prof().toUpperCase())
                        .contains(message.toString().toUpperCase()) || (absList.get(i).getStatut().toUpperCase())
                        .contains(message.toString().toUpperCase())) {
                    InfosAbsences absence = new InfosAbsences(absList.get(i).getModule(), absList.get(i).getDate(), absList.get(i).getPrenom_prof(),
                            absList.get(i).getNom_prof(), absList.get(i).getStatut());
                    filterList.add(absence);
                }
            }
        } else {
            filterList = absList;
        }
    }

    public void getUnfilter(){
        filterList.clear();
        filterList = absList;
    }

}

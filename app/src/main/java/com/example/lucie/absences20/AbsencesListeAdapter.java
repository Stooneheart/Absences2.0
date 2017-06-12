package com.example.lucie.absences20;

import android.content.Context;
import android.icu.text.IDNA;
import android.support.annotation.LayoutRes;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by lucie on 22/05/2017.
 */

public class AbsencesListeAdapter extends BaseAdapter implements Filterable{
    private static final String TAG = "AbsencesListeAdapter";

    private Context mContext;
    //int mResource;
    private List<InfosAbsences> absList;
    private LayoutInflater inflater;
    private List<InfosAbsences> mStringFilterList;
    private ValueFilter valueFilter;
    private int size;
    private ArrayList<InfosAbsences> filterList;

    public AbsencesListeAdapter(Context context, ArrayList<InfosAbsences> objects, int size) {
        mContext = context;
        //mResource = resource;
        this.absList = objects;
        mStringFilterList = objects;
        this.size = size;
    }


    @Override
    public int getCount() {
        return size;
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


            InfosAbsences absence = absList.get(position);
            String module = absence.getModule();
            String date = absence.getDate();
            String prenom_prof = absence.getPrenom_prof();
            String nom_prof = absence.getNom_prof();
            String statut = absence.getStatut();

            //LayoutInflater inflater = LayoutInflater.from(mContext);
            //convertView = inflater.inflate(mResource, parent, false);


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


        return convertView;

    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                filterList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if ((mStringFilterList.get(i).getModule().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || (mStringFilterList.get(i).getDate().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || (mStringFilterList.get(i).getNom_prof().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || (mStringFilterList.get(i).getPrenom_prof().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) || (mStringFilterList.get(i).getStatut().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        InfosAbsences absence = new InfosAbsences(mStringFilterList.get(i)
                                .getModule(), mStringFilterList.get(i)
                                .getDate(), mStringFilterList.get(i).getPrenom_prof(), mStringFilterList.get(i).getNom_prof(), mStringFilterList.get(i).getStatut());
                        filterList.add(absence);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = size;
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            absList = (ArrayList<InfosAbsences>) results.values;
            notifyDataSetChanged();
        }

    }
}

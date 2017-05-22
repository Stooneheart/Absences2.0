package com.example.lucie.absences20;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by lucie on 22/05/2017.
 */

public class TotalsAbsences extends ListActivity {

    private static String TAG="TotalsAbsences";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals_absences);
        mListView = (ListView) findViewById(R.id.theList);
        Log.d(TAG, "OnCreate : started. ");

        ArrayList<String> names = new ArrayList<>();
        names.add("Lucie");
        names.add("Guillaume");
        names.add("Alexis");
        names.add("Clément");
        names.add("Thomas");
        names.add("Olivier");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        mListView.setAdapter(adapter);

    }
/*
    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mes_absences) {
            Intent intent2 = new Intent(this, TotalsAbsences.class);
            startActivity(intent2);

            //TotalsAbsencesFragment absencesFragment = new TotalsAbsencesFragment();
            //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.fragment_container, absencesFragment).commit();

            //MesAbsencesFragment absencesFragment = new MesAbsencesFragment();
            //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.fragment_container, absencesFragment).commit();
        } else if (id == R.id.mes_statistiques) {
            Intent intent3 = new Intent(this, AccueilActivity.class);
            startActivity(intent3);

        } else if (id == R.id.prevenir_absence) {
            Intent intent4 = new Intent(this, AccueilActivity.class);
            startActivity(intent4);

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            this.finish();
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}

package com.example.android.horizontalpaging;

// Lähde: http://developer.android.com/training/basics/fragments/fragment-ui.html

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

// Huom. support-kirjasto (ei android.app...).
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class PiirustuksetOhjeetActivity extends FragmentActivity implements
        PiirustuksetOhjeetFragment.OnPiirustuksetOhjeetInteractionListener,
        PiirustusOhjeFragment.OnPiirustusOhjeInteractionListener{

    public final static String INSTRUCTION_MESSAGE =
            "com.example.android.horizontal.INSTRUCTION";
    public final static String DRAWING_MESSAGE =
            "com.example.android.horizontal.DRAWING";
    private PiirustuksetOhjeetFragment piirustuksetOhjeetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piirustukset_ohjeet);

        // Selvitetään haluttu työmaa, jolla on ohjeet ja piirustukset.
        //Worksite worksite =
                //(Worksite) intent.getSerializableExtra(MainActivity.DRAWINGS_INSTRUCTIONS_MESSAGE);



        // Tarkistetaan fragment xml-layoutin olemassaolo.
        //if (findViewById(R.id.piirustukset_ohjeet_main_linear_layout) != null) {


        if (savedInstanceState != null) {
            //return;
        }

        // Luodaan uusi fragmentti (ohjeet ja piirustukset).
        piirustuksetOhjeetFragment = new PiirustuksetOhjeetFragment();
        Bundle bundle = getIntent().getExtras();
        createFragment(piirustuksetOhjeetFragment, R.id.fragment_test, bundle);

       // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piirustukset_ohjeet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Käyttäjä valitsi piirustuksen matriisista (GridView).
    @Override
    public void onPiirustuksetInteraction(Drawing drawing) {
        //removeFragment(piirustuksetOhjeetFragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DRAWING_MESSAGE, drawing);
        //createFragment(new PiirustusOhjeFragment(), R.id.fragment_test, bundle);
        replaceFragment(new PiirustusOhjeFragment(), R.id.fragment_test, bundle);

    }

    // Käyttäjä valitsi ohjeen matriisista (GridView).
    @Override
    public void onOhjeetInteraction(Instruction instruction) {
        //removeFragment(piirustuksetOhjeetFragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable(INSTRUCTION_MESSAGE, instruction);
        //createFragment(new PiirustusOhjeFragment(), R.id.fragment_test, bundle);
        replaceFragment(new PiirustusOhjeFragment(), R.id.fragment_test, bundle);
    }

    // Poistetaan piirustuksia ja ohjeita sisältävä fragmentti.
    private void removeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(fragment).commit();
    }

    // Luodaan fragmentti.
    private void createFragment(Fragment fragment, Integer containerId, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        fragment.setArguments(bundle);

        transaction.add(
                R.id.fragment_test, fragment).addToBackStack(
                fragment.getClass().getName()).commit();
    }

    private void replaceFragment(Fragment fragment, Integer containerId, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        fragment.setArguments(bundle);

        transaction.replace(
                R.id.fragment_test, fragment).addToBackStack(
                fragment.getClass().getName()).commit();
    }

    // Yksittäisen piirustuksen/ohjeen tapahtumat (PiirustusOhjeFragment).
    @Override
    public void onOhjeInteraction() {


    }

    @Override
    public void onPiirustusInteraction() {

    }
}

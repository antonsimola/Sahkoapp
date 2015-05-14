package com.example.android.horizontalpaging;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.Locale;

// Pääactivity, joka sisältää viewpager + action bar -combon,
// joka näyttää sovelluksen neljä välilehteä

public class MainActivity extends FragmentActivity implements ActionBar.TabListener,
        TyomaaFragment.OnTyomaaInteractionListener, AdapterView.OnItemSelectedListener {
    private boolean loggedIn = false;
    private WorksiteControl worksiteControl;

    public final static String DRAWINGS_INSTRUCTIONS_MESSAGE =
            "com.example.android.horizontal.MESSAGE";

    // Pitää tallessa tab-fragmentit
    SectionsPagerAdapter mSectionsPagerAdapter;

    // Näyttää tab-fragmentit
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sample_main);
        // näyttää login - popupin
        if (!loggedIn) {
            View view = new View(this);
            showLoginDialog(view);
        }

        worksiteControl = new WorksiteControl(10);  // Luodaan 10 työmaata kontrollioliossa.

        //Asettaa näytön yläreunaan action barin, ja asetetaan se näyttämään tab-valikko
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //Adapter, joka sisältää ja suorittaa fragmenttien vaihdon
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //ViewPagerille Adapteri
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // swipe
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        // Tab-valikkoon napit
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter. Also
            // specify this Activity object, which implements the TabListener interface, as the
            // callback (listener) for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        //Sovellus käynnistyy uutiset näkymään
        mViewPager.setCurrentItem(1);

    }

    //Tämä reagoi nappien painallukseen tab-valikossa
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        mViewPager.setCurrentItem(tab.getPosition());
    }
    // Ei tarvita
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    // Ei tarvita
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Luo fragmentit jokaiselle sivulle, ja asettaa tarvittavia alkuarvoja niille
            switch (position){
                case 0:
                    TyotehtavatFragment tyotehtavat = new TyotehtavatFragment();

                    return tyotehtavat;
                case 1:
                    //Tässä laitetaan tuo viewPager olio messiin tuolle uutisetFragmentille
                    // ,jotta voidaan siirtyä uutisista työtehtäviin
                    UutisetFragment uutiset = new UutisetFragment();
                    uutiset.setViewPager(mViewPager);
                    return uutiset;
                case 2:
                    TyomaaFragment tyomaaFragment = new TyomaaFragment();

                    /* Täytyy asettaa erikseen heti olion luomisen jälkeen (ei onnistu alustajan
                     * kautta).
                     */
                    tyomaaFragment.setWorksiteControl(worksiteControl);

                    return tyomaaFragment;
                case 3:
                    return new AsetuksetFragment();
            }

            return null;
        }

        // Palauttaa tabien määrän
        @Override
        public int getCount() {

            return 4;
        }
        // Tab-otsikot
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }

    }

    // näyttää dialogit ajan valitsemiseen
    public void showDatePickerDialog(View v) {

        DatePickerFragment newFragment = new DatePickerFragment();
        // tarkastetaan, kumpi aika halutaan valita: true: aloitusaika, false: lopetus
        if (v.getId() == R.id.btn_setfinish) {
            newFragment.setAloitus(false);
        } else {
            newFragment.setAloitus(true);
        }
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void showToast(View v) {
        Context context = getApplicationContext();
        CharSequence text = "Raporttisi on lähetetty tarkastettavaksi!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        languageChangedToast(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void languageChangedToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Kieli on nyt "+text, duration);
        toast.show();

    }
    @Override
    public void onTyomaaGridSelected(int position) {
        // Käyttäjä valitsee Ohjeet-osiosta kansion, jolla on indeksi (>= 0).

        // Lähde: http://developer.android.com/training/basics/firstapp/starting-activity.html
        // ja: http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents

        Intent intent = new Intent(this, PiirustuksetOhjeetActivity.class);

        /* Tässä annetaan PiirustuksetOhjeetActivitylle referenssi yhteen työmaahan (jonka
         * perusteella rakennetaan fragmenttien sisältö).
         * Lähde:
         * http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
         */
        intent.putExtra(DRAWINGS_INSTRUCTIONS_MESSAGE, worksiteControl.getWorksites().get(position));

        startActivity(intent);
    }

    public void showLoginDialog(View v) {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getSupportFragmentManager(),"Login Dialog");
    }

    public void setLoggedIn(boolean b) {
        loggedIn = b;
    }
}

package com.example.android.horizontalpaging;

// Huom. käytössä support-kirjasto (ei android.app).
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import java.util.ArrayList;

/* Tekijä: Samuli
 * Viimeisin muokkaus: 14.5.2015
 * Käytetty lähdettä: http://developer.android.com/training/basics/fragments/fragment-ui.html
 *
 * Tämä luokka toteuttaa activityn, josta käyttäjä voi valita ohjeen tai piirustuksen katsomisen.
 * Piirustukselle ja ohjeelle on omat fragmenttinsä, jotka kuuluvat tälle tämän luokan kuvaamalle
 * activitylle.
 */
public class PiirustuksetOhjeetActivity extends FragmentActivity implements
        PiirustusOhjeFragment.OnPiirustusOhjeInteractionListener{

    private Worksite worksite;  // Yksittäinen työmaa.
    private GridView drawingsGridView, instructionsGridView;  // UI-matriisit.

    // Fragmenttit käyttävät (vakio)merkkijonoja avaimina hajautustaulussa.
    public final static String INSTRUCTION_MESSAGE =
            "com.example.android.horizontal.INSTRUCTION";
    public final static String DRAWING_MESSAGE =
            "com.example.android.horizontal.DRAWING";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piirustukset_ohjeet);

        if (savedInstanceState != null) {
            return;
        }

        // Kansiohaun toteutuksessa käytettävät hakupainikkeet.
        Button searchButton = (Button)findViewById(R.id.piirustukset_ohjeet_search_button);
        Button resetSearchButton = (Button)findViewById(
                R.id.piirustukset_ohjeet_reset_search_button);

        // UI-matriisit piirustuksille ja ohjeille.
        drawingsGridView = (GridView)findViewById(R.id.piirustukset_grid_view);
        instructionsGridView = (GridView)findViewById(R.id.ohjeet_grid_view);

        Bundle bundle = getIntent().getExtras();  // Activityn luonnissa tarjottu tietosisältö.

        // Haetaan activityn luonnissa annettu työmaa.
        worksite = (Worksite)bundle.getSerializable(MainActivity.DRAWINGS_INSTRUCTIONS_MESSAGE);

        // Asetetaan adapterit UI-matriiseille.
        drawingsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this, worksite, 1));
        instructionsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this, worksite, 2));

        // Tapahtumankuuntelija piirrustuksia sisältävän matriisin alkioille.
        drawingsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onPiirustuksetInteraction(worksite.getDrawings().get(position));
            }
        });

        // Tapahtumankuuntelija ohjeita sisältävän matriisin alkioille.
        instructionsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onOhjeetInteraction(worksite.getInstructions().get(position));
            }
        });

        // Tapahtumankuuntelija hakupainikkeelle (haetaan piirustus/ohje).
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView search =  (SearchView)findViewById(R.id.piirustukset_ohjeet_search_view);
                performSearch(search.getQuery().toString());

            }
        });

        // Tapahtumankuuntelija haun reset-painikkeelle (haettiin piirustus/ohje).
        resetSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView search =  (SearchView)findViewById(R.id.piirustukset_ohjeet_search_view);
                search.setQuery("", true);

                drawingsGridView.setAdapter(new PiirustuksetOhjeetAdapter(
                        v.getContext(), worksite, 1));
                instructionsGridView.setAdapter(new PiirustuksetOhjeetAdapter(
                        v.getContext(), worksite, 2));

            }
        });
    }

    // Ei varsinaista käyttöä.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piirustukset_ohjeet, menu);
        return true;
    }

    // Ei varsinaista käyttöä.
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

    // Suoritaan haku piirustusten ja ohjeiden suhteen.
    private void performSearch(String title) {

        // Hakua vastaavien piirustusten ja ohjeiden tietorakenteet.
        ArrayList<Drawing> foundDrawings = worksite.findDrawings(title);
        ArrayList<Instruction> foundInstructions = worksite.findInstructions(title);


        if(!foundDrawings.isEmpty()) {

            // Läydettiin hakua vastaavia piirustuksia. Asetetaan uusi XML-sisältö.
            drawingsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this,
                    new Worksite(worksite.getName(), worksite.getDate(),
                            worksite.getFileImage(), foundDrawings, null), 1));
            instructionsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this, new Worksite(worksite.getName(), worksite.getDate(),
                    worksite.getFileImage(), null, new ArrayList<Instruction>()), 2));
        }

        if(!foundInstructions.isEmpty()) {

            // Läydettiin hakua vastaavia ohjeita. Asetetaan uusi XML-sisältö.
            instructionsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this, new Worksite(worksite.getName(), worksite.getDate(),
                    worksite.getFileImage(), null, foundInstructions), 2));
            drawingsGridView.setAdapter(new PiirustuksetOhjeetAdapter(this, new Worksite(worksite.getName(), worksite.getDate(),
                    worksite.getFileImage(), new ArrayList<Drawing>(), null), 1));
        }
    }

    // Käyttäjä valitsi piirustuksen matriisista (GridView).
    public void onPiirustuksetInteraction(Drawing drawing) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DRAWING_MESSAGE, drawing);
        // Vaihdetaan nykyinen fragmentti valitun tiedon mukaiseen fragmenttiin.
        replaceFragment(new PiirustusOhjeFragment(), R.id.piirustukset_ohjeet_main_relative_layout, bundle);

    }

    // Käyttäjä valitsi ohjeen matriisista (GridView).
    public void onOhjeetInteraction(Instruction instruction) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(INSTRUCTION_MESSAGE, instruction);

        // Vaihdetaan nykyinen fragmentti valitun tiedon mukaiseen fragmenttiin.
        replaceFragment(new PiirustusOhjeFragment(), R.id.piirustukset_ohjeet_main_relative_layout, bundle);
    }

    // Vaihdetaan nykyinen fragmentti toiseen (annettu parametrina).
    private void replaceFragment(Fragment fragment, Integer containerId, Bundle bundle) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        fragment.setArguments(bundle);

        transaction.replace(
                R.id.piirustukset_ohjeet_main_relative_layout, fragment).addToBackStack(
                fragment.getClass().getName()).commit();
    }

    // Ei varsinaista käyttöä.
    @Override
    public void onOhjeInteraction() {


    }

    // Ei varsinaista käyttöä.
    @Override
    public void onPiirustusInteraction() {

    }
}
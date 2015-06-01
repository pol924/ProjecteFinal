package cat.copernic.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//import cat.copernic.projectefinal.FragmentUsuario.OnFragmentInteractionListener;


public class MainActivityDrawer extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        FragmentUsuario.OnFragmentInteractionListener,
        FragmentLuces.OnFragmentInteractionListener,
        FragmentTemperatura.OnFragmentInteractionListener,
        FragmentPersianas.OnFragmentInteractionListener{

    public static Usuario getUser() {
        return user;
    }

    public static Usuario user;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;


    private String[] titulos;
    private ListView list;
    private ArrayList<Img> arrayimg;
    private TypedArray icon;
    NavAdapter navadapter;

    private DrawerLayout navdraw;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        user = (Usuario)getIntent().getSerializableExtra("usuario");


       /* navdraw = (DrawerLayout) findViewById(R.id.drawer_layout);
        //lista

        list=(ListView) findViewById(R.id.lista);
        View head = getLayoutInflater().inflate(R.layout.header,null);

        list.addHeaderView(head);

        icon = getResources().obtainTypedArray(R.array.icon);

        titulos = getResources().getStringArray(R.array.title);

        arrayimg = new ArrayList<Img>();

        arrayimg.add(new Img(titulos[0],icon.getResourceId(0,-1)));
        arrayimg.add(new Img(titulos[1],icon.getResourceId(1,-1)));
        arrayimg.add(new Img(titulos[2],icon.getResourceId(2,-1)));
        arrayimg.add(new Img(titulos[3], icon.getResourceId(3, -1)));


        navadapter = new NavAdapter(this,arrayimg);
        list.setAdapter(navadapter);
*/
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        //cargamos fragmentos segun posicion
        switch (position){
            case 0:
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                FragmentManager fragmentManager0 = getSupportFragmentManager();
                fragmentManager0.beginTransaction()
                        .replace(R.id.container, FragmentLuces.newInstance(position+1))
                        .commit();


                break;
            case 1:FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.container, FragmentTemperatura.newInstance(position + 1))
                        .commit();
                break;
            case 2:FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction()
                        .replace(R.id.container, FragmentPersianas.newInstance(position + 1))
                        .commit();
                break;
            case 3: FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3.beginTransaction()
                        .replace(R.id.container, FragmentUsuario.newInstance(position + 1))
                        .commit();
                break;
        }




        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/



    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);

                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;


        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_activity2, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
*/
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        TextView prueba;

        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_activity2, container, false);


          //  prueba = (TextView) getView().findViewById(R.id.pruebauser);
           // prueba.setText(user.getNombre());


            return rootView;
        }



        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivityDrawer) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}

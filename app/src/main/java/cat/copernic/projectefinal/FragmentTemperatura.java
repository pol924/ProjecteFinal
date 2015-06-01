package cat.copernic.projectefinal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTemperatura.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTemperatura#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTemperatura extends Fragment {

    Switch calefaccion,aireAcondicionado;
    TextView TextCalefaccion, TextAire;
    Button BTMenosCalefaccion, BTMasCalefaccion, BTMenosAire, BTMasAire;
    int numCalefaccion=20, numAire = 20;

    private static final String ARG_SECTION_NUMBER = "section_number";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Usuario User = MainActivityDrawer.getUser();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FragmentTemperatura.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTemperatura newInstance(int sectionNumber) {
        FragmentTemperatura fragment = new FragmentTemperatura();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTemperatura() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_temperatura, container, false);

        //Enlazamos variables con layout
        calefaccion = (Switch) v.findViewById(R.id.switchCalefaccion);
        aireAcondicionado = (Switch) v.findViewById(R.id.switchAireAcondicionado);
        TextCalefaccion = (TextView) v.findViewById(R.id.textViewCalefaccion);
        TextAire = (TextView) v.findViewById(R.id.textViewAire);
        BTMenosCalefaccion = (Button) v.findViewById(R.id.BtMenosCalefaccion);
        BTMasCalefaccion = (Button) v.findViewById(R.id.BtMasCalefaccion);
        BTMenosAire = (Button) v.findViewById(R.id.BtMenosAire);
        BTMasAire = (Button) v.findViewById(R.id.BtMasAire);

        //desactivamos botones + y -
        BTMenosCalefaccion.setEnabled(false);
        BTMasCalefaccion.setEnabled(false);
        BTMenosAire.setEnabled(false);
        BTMasAire.setEnabled(false);

        //preguntamos si el usuario tiene permisos para usar aire i calefaccion, sino bloqueamos botones
        if(!User.isCalefaccion()){
            calefaccion.setEnabled(false);
        }
        if(!User.isAireAcondicionado()){
            aireAcondicionado.setEnabled(false);
        }

        //Switchs
        calefaccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    BTMenosCalefaccion.setEnabled(true);
                    BTMasCalefaccion.setEnabled(true);
                    aireAcondicionado.setEnabled(false);



                } else {

                    BTMenosCalefaccion.setEnabled(false);
                    BTMasCalefaccion.setEnabled(false);
                    if(User.isAireAcondicionado()){
                        aireAcondicionado.setEnabled(true);
                    }



                }
            }
        });

        aireAcondicionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    BTMenosAire.setEnabled(true);
                    BTMasAire.setEnabled(true);
                    calefaccion.setEnabled(false);



                } else {

                    BTMenosAire.setEnabled(false);
                    BTMasAire.setEnabled(false);
                    if(User.isCalefaccion()){
                        calefaccion.setEnabled(true);
                    }


                }
            }
        });

        //Botones + y -
        BTMenosCalefaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numCalefaccion>10) {
                    numCalefaccion--;
                    TextCalefaccion.setText(numCalefaccion + "\u00BAC");
                    // Toast.makeText(getActivity().getApplicationContext(),"Menos calefaccion",Toast.LENGTH_LONG).show();
                }
            }
        });
        BTMasCalefaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numCalefaccion<30) {
                    numCalefaccion++;
                    TextCalefaccion.setText(numCalefaccion + "\u00BAC");
                    //Toast.makeText(getActivity().getApplicationContext(),"Mas calefaccion",Toast.LENGTH_LONG).show();
                }
            }
        });
        BTMenosAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numAire>10) {
                    numAire--;
                    TextAire.setText(numAire + "\u00BAC");
                //Toast.makeText(getActivity().getApplicationContext(),"Menos Aire",Toast.LENGTH_LONG).show();
                }
            }
        });
        BTMasAire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numAire<30) {
                    numAire++;
                    TextAire.setText(numAire + "\u00BAC");
                    // Toast.makeText(getActivity().getApplicationContext(),"Mas Aire",Toast.LENGTH_LONG).show();
                }
            }
        });







        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        ((MainActivityDrawer) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }




/*
    //OnClick para todos los botones + y - , luego miramos cual es en un switch
    void OnClickBotonesTemperatura(View v){
        Button b = (Button)v;
        Toast.makeText(getActivity().getApplicationContext(),String.valueOf(v.getId()),Toast.LENGTH_LONG).show();

        switch(b.getId()){
            case R.id.BtMenosCalefaccion:

                   // Toast.makeText(getActivity().getApplicationContext(),"menos calefaccion",Toast.LENGTH_LONG).show();

                break;
            case R.id.BtMasCalefaccion:
               // Toast.makeText(getActivity().getApplicationContext(),"mas calefaccion",Toast.LENGTH_LONG).show();


                break;
            case R.id.BtMenosAire:
               // Toast.makeText(getActivity().getApplicationContext(),"menos aire",Toast.LENGTH_LONG).show();

                break;
            case R.id.BtMasAire:
              //  Toast.makeText(getActivity().getApplicationContext(),"mas aire",Toast.LENGTH_LONG).show();

                break;


        }

    } */

}

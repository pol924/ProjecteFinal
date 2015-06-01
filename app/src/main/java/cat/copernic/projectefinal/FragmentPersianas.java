package cat.copernic.projectefinal;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPersianas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPersianas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPersianas extends Fragment {

    TextView garaje,cocina,salita,comedor,dormitorio;
    SeekBar seekBarGaraje, seekBarCocina, seekBarSalita, seekBarComedor, seekBarDormitorio;
    Button subeGaraje, bajaGaraje, subeCocina, bajaCocina, subeSalita, bajaSalita, subeComedor, bajaComedor, subeDormitorio, bajaDormitorio;

    int tiempoTotal = 10,estadoGaraje=5, estadoCocina=5, estadoSalita=5, estadoComedor=5, estadoDormitorio=5;

    private static final String ARG_SECTION_NUMBER = "section_number";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Usuario user = MainActivityDrawer.getUser();
    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentPersianas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPersianas newInstance(int sectionNumber) {
        FragmentPersianas fragment = new FragmentPersianas();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentPersianas() {
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

        View v = inflater.inflate(R.layout.fragment_persianas, container, false);

        garaje = (TextView) v.findViewById(R.id.textViewGaraje);
        cocina = (TextView) v.findViewById(R.id.textViewCocina);
        salita = (TextView) v.findViewById(R.id.textViewSalita);
        comedor = (TextView) v.findViewById(R.id.textViewComedor);
        dormitorio = (TextView) v.findViewById(R.id.textViewDormitorio);

        seekBarGaraje = (SeekBar) v.findViewById(R.id.seekBarGaraje);
        seekBarCocina = (SeekBar) v.findViewById(R.id.seekBarCocina);
        seekBarSalita = (SeekBar) v.findViewById(R.id.seekBarSalita);
        seekBarComedor = (SeekBar) v.findViewById(R.id.seekBarComedor);
        seekBarDormitorio = (SeekBar) v.findViewById(R.id.seekBarDormitorio);

        subeCocina = (Button) v.findViewById(R.id.buttonSCocina);
        subeGaraje = (Button) v.findViewById(R.id.buttonSGaraje);
        subeSalita = (Button) v.findViewById(R.id.buttonSSalita);
        subeComedor = (Button) v.findViewById(R.id.buttonSComedor);
        subeDormitorio = (Button) v.findViewById(R.id.buttonSDormitorio);


        //desabilitar botones mientras se mueven


        subeGaraje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.isPersianaGaraje()) {
                    int barra = seekBarGaraje.getProgress();
                    int subirBajar = estadoGaraje - barra;
                    // Toast.makeText(getActivity(),String.valueOf(barra),Toast.LENGTH_SHORT).show();

                    if (subirBajar > 0) {//bajar
                        subeGaraje.setTextColor(Color.RED);
                        subeGaraje.setEnabled(false);
                        esperarSubeBaja(subeGaraje, subirBajar * 1000);
                        Toast.makeText(getActivity(), "Bajando...", Toast.LENGTH_SHORT).show();
                        estadoGaraje -= subirBajar;
                        garaje.setText("Garaje        [" + estadoGaraje + "]");
                    } else if (subirBajar < 0) {//subir
                        subeGaraje.setTextColor(Color.RED);
                        subeGaraje.setEnabled(false);
                        subirBajar *= -1;
                        estadoGaraje += subirBajar;
                        esperarSubeBaja(subeGaraje, subirBajar * 1000);
                        Toast.makeText(getActivity(), "Subiendo...", Toast.LENGTH_SHORT).show();
                        garaje.setText("Garaje        [" + estadoGaraje + "]");
                    }


                }

            }
        });

        subeCocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int barra =seekBarCocina.getProgress();
                int subirBajar=estadoCocina-barra;
                // Toast.makeText(getActivity(),String.valueOf(barra),Toast.LENGTH_SHORT).show();

                if(subirBajar>0) {//bajar
                    subeCocina.setTextColor(Color.RED);
                    subeCocina.setEnabled(false);
                    esperarSubeBaja(subeCocina, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Bajando...",Toast.LENGTH_SHORT).show();
                    estadoCocina-=subirBajar;
                    cocina.setText("Cocina       [" + estadoCocina + "]");
                }else if(subirBajar<0){//subir
                    subeCocina.setTextColor(Color.RED);
                    subeCocina.setEnabled(false);
                    subirBajar*=-1;
                    estadoCocina+=subirBajar;
                    esperarSubeBaja(subeCocina, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Subiendo...",Toast.LENGTH_SHORT).show();
                    cocina.setText("Cocina       [" + estadoCocina + "]");
                }

            }
        });

        subeSalita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int barra =seekBarSalita.getProgress();
                int subirBajar=estadoSalita-barra;
                // Toast.makeText(getActivity(),String.valueOf(barra),Toast.LENGTH_SHORT).show();

                if(subirBajar>0) {//bajar
                    subeSalita.setTextColor(Color.RED);
                    subeSalita.setEnabled(false);
                    esperarSubeBaja(subeSalita, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Bajando...",Toast.LENGTH_SHORT).show();
                    estadoSalita-=subirBajar;
                    salita.setText("Salita         [" + estadoSalita + "]");
                }else if(subirBajar<0){//subir
                    subeSalita.setTextColor(Color.RED);
                    subeSalita.setEnabled(false);
                    subirBajar*=-1;
                    estadoSalita+=subirBajar;
                    esperarSubeBaja(subeSalita, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Subiendo...",Toast.LENGTH_SHORT).show();
                    salita.setText("Salita         [" + estadoSalita + "]");
                }

            }
        });

        subeComedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int barra =seekBarComedor.getProgress();
                int subirBajar=estadoComedor-barra;
                // Toast.makeText(getActivity(),String.valueOf(barra),Toast.LENGTH_SHORT).show();

                if(subirBajar>0) {//bajar
                    subeComedor.setTextColor(Color.RED);
                    subeComedor.setEnabled(false);
                    esperarSubeBaja(subeComedor, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Bajando...",Toast.LENGTH_SHORT).show();
                    estadoComedor-=subirBajar;
                    comedor.setText("Comedor   [" + estadoComedor + "]");
                }else if(subirBajar<0){//subir
                    subeComedor.setTextColor(Color.RED);
                    subeComedor.setEnabled(false);
                    subirBajar*=-1;
                    estadoComedor+=subirBajar;
                    esperarSubeBaja(subeComedor, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Subiendo...",Toast.LENGTH_SHORT).show();
                    comedor.setText("Comedor   [" + estadoComedor + "]");
                }

            }
        });

        subeDormitorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int barra =seekBarDormitorio.getProgress();
                int subirBajar=estadoDormitorio-barra;
                // Toast.makeText(getActivity(),String.valueOf(barra),Toast.LENGTH_SHORT).show();

                if(subirBajar>0) {//bajar
                    subeDormitorio.setTextColor(Color.RED);
                    subeDormitorio.setEnabled(false);
                    esperarSubeBaja(subeDormitorio, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Bajando...",Toast.LENGTH_SHORT).show();
                    estadoDormitorio-=subirBajar;
                    dormitorio.setText("Dormitorio [" + estadoDormitorio + "]");
                }else if(subirBajar<0){//subir
                    subeDormitorio.setTextColor(Color.RED);
                    subeDormitorio.setEnabled(false);
                    subirBajar*=-1;
                    estadoDormitorio+=subirBajar;
                    esperarSubeBaja(subeDormitorio, subirBajar * 1000);
                    Toast.makeText(getActivity(),"Subiendo...",Toast.LENGTH_SHORT).show();
                    dormitorio.setText("Dormitorio [" + estadoDormitorio + "]");
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

    public void esperarSubeBaja(final Button sube, int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                sube.setTextColor(Color.BLACK);
                sube.setEnabled(true);


            }
        }, milisegundos);
    }





}
package cat.copernic.projectefinal;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentLuces.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentLuces#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLuces extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private Switch entrada,cocina,comedor,salita,habMatr,habD,habM,wc,garaje,patio,escalera,trastero;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity m = new MainActivity();
    String confirm = null;
    String s = null;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentLuces.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLuces newInstance(int sectionNumber) {
        FragmentLuces fragment = new FragmentLuces();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentLuces() {
        // Required empty public constructor
    }



    public void Enviar(Socket client,String luz){
        try {
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            String s2 = "accion";
            writer.println(s2);
            writer.flush();
            writer.println(luz);
            writer.flush();

            //s = "okk";

        }catch(Exception e2){
            e2.printStackTrace();
        }
    }

    public void Recibir(Socket client){
        try {
            BufferedReader reader = new BufferedReader((new InputStreamReader(client.getInputStream())));
            String estado = reader.readLine();
            s = estado;
        }catch(Exception e3){
            e3.printStackTrace();
            s="sin readline";
        }

    }


    public void EncenderLuz(final String luz){
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                confirm = "novalido";

                String host = (String) params[0];
                Integer port = (Integer) params[1];

                try {
                    Socket client = new Socket(host, port);

                    //ENVIAR
                    Enviar(client,luz);


                    //RECIBIR
                    Recibir(client);

                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    s = "sin conexion";
                }


                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                boolean b = Boolean.parseBoolean(s);

                if(b) {
                    Toast.makeText(getActivity().getApplicationContext(), "Luz ON", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(m.LOCALHOST_PC, m.PORT);
    }


    public void ApagarLuz(final String luz){
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                confirm = "novalido";

                String host = (String) params[0];
                Integer port = (Integer) params[1];

                try {
                    Socket client = new Socket(host, port);

                    //ENVIAR
                    Enviar(client,luz);


                    //RECIBIR
                    Recibir(client);

                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    s = "sin conexion";
                }


                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                boolean b = Boolean.parseBoolean(s);

                if(b) {
                    Toast.makeText(getActivity().getApplicationContext(), "Luz OFF", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(m.LOCALHOST_PC, m.PORT);
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
                             Bundle savedInstanceState) {View v = inflater.inflate(R.layout.fragment_luces, container, false);

        entrada = (Switch) v.findViewById(R.id.switchEntrada);
        cocina= (Switch) v.findViewById(R.id.switchCocina);
        comedor= (Switch) v.findViewById(R.id.switchComedor);
        salita= (Switch) v.findViewById(R.id.switchSalita);
        habMatr= (Switch) v.findViewById(R.id.switchHabitacionMatr);
        habD= (Switch) v.findViewById(R.id.switchHabitacionD);
        habM= (Switch) v.findViewById(R.id.switchHabitacionM);
        wc= (Switch) v.findViewById(R.id.switchWC);
        garaje= (Switch) v.findViewById(R.id.switchGaraje);
        patio= (Switch) v.findViewById(R.id.switchPatio);
        escalera= (Switch) v.findViewById(R.id.switchEscalera);
        trastero= (Switch) v.findViewById(R.id.switchTrastero);


        entrada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz entrada ON");
                } else {
                    ApagarLuz("Luz entrada OFF");
                }
            }
        });

        cocina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz cocina ON");
                } else {
                    ApagarLuz("Luz cocina OFF");
                }
            }
        });

        comedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz comedor ON");
                } else {
                    ApagarLuz("Luz comedor OFF");
                }
            }
        });

        salita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz salita ON");
                } else {
                    ApagarLuz("Luz salita OFF");
                }
            }
        });

        habMatr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz habitacion ON");
                } else {
                    ApagarLuz("Luz habitacion OFF");
                }
            }
        });

        habD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz habitacion ON");
                } else {
                    ApagarLuz("Luz habitacion OFF");
                }
            }
        });


        habM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz habitacion ON");
                } else {
                    ApagarLuz("Luz habitacion OFF");
                }
            }
        });

        wc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz WC ON");
                } else {
                    ApagarLuz("Luz WC OFF");
                }
            }
        });

        garaje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    EncenderLuz("Luz garaje ON");
                } else {
                    ApagarLuz("Luz garaje OFF");
                }
            }
        });

        patio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz patio ON");
                } else {
                    ApagarLuz("Luz patio OFF");
                }
            }
        });
        escalera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz escalera ON");
                } else {
                    ApagarLuz("Luz escalera OFF");
                }
            }
        });
        trastero.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    EncenderLuz("Luz trastero ON");
                } else {
                    ApagarLuz("Luz trastero OFF");
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

}

package cat.copernic.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentUsuario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUsuario extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    Usuario user;
    TextView nombre,modificarCrear, persianaGaraje,calefaccion,aireAcondicionado;
    Button modificar,crear;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FragmentUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUsuario newInstance(int sectionNumber) {
        FragmentUsuario fragment = new FragmentUsuario();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUsuario() {
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

        // Definimos la vista "View l" para poder asignar las variables con el layout

        View l = inflater.inflate(R.layout.fragment_usuario, container, false);

        //asignamos variables con layout
        nombre = (TextView) l.findViewById(R.id.nombreUsuario);
        modificarCrear = (TextView) l.findViewById(R.id.modificar_crear);
        persianaGaraje  =(TextView) l.findViewById(R.id.persianaGaraje);
        calefaccion = (TextView) l.findViewById(R.id.calefaccion);
        aireAcondicionado = (TextView) l.findViewById(R.id.aireAcondicionado);

        modificar = (Button) l.findViewById(R.id.buttonModificar);
        crear = (Button) l.findViewById(R.id.buttonCrear);


        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isModificar()) {
                    Intent i = new Intent(getActivity(),ActivityModificar.class);
                    i.putExtra("usuario", user);
                    startActivity(i);


                }else{
                    Toast.makeText(getActivity(),"No tienes acceso",Toast.LENGTH_LONG).show();
                }
            }
        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isCrear()){
                    Intent i = new Intent(getActivity(),ActivityCrear.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getActivity(),"No tienes acceso",Toast.LENGTH_LONG).show();
                }
            }
        });

        //cargamos informaciÃ³n
        cargarUsuario();

        return l;//retornamos la vista
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


    void cargarUsuario(){

        user=MainActivityDrawer.getUser();
        nombre.setText(user.getNombre());


        if (user.isModificar()) {
            if(user.isCrear()){
                modificarCrear.setText("Si"+" / "+"Si");}else{
                modificarCrear.setText("Si"+" / "+"No");
            }
        }else{
            if(user.isCrear()){
                modificarCrear.setText("No"+" / "+"Si");
            }else{
                modificarCrear.setText("No"+" / "+"No");
            }
        }


        if(user.isPersianaGaraje()){
            persianaGaraje.setText("Si");
        }else{
            persianaGaraje.setText("No");
        }

        if(user.isCalefaccion()){ calefaccion.setText("Si");}else{calefaccion.setText("No");
        }
        if(user.isAireAcondicionado()){aireAcondicionado.setText("Si");}else{aireAcondicionado.setText("No");}

    }

}
package cr.ac.itcr.examenict;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DB.AnimalRepository;
import DB.IBD;
import Entity.Animal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnimalRegister.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnimalRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalRegister extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AnimalRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimalRegister newInstance(String param1, String param2) {
        AnimalRegister fragment = new AnimalRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        /**
         *como es un fragment no se puede obtener los id directamente por ello debemos crear una variable que haga referencia
         * al fragment que estamos modificando
         */
        final View view = inflater.inflate(R.layout.fragment_animal_register, container, false);
        //Inicialización de boton
        final Button addA = (Button)view.findViewById(R.id.btnAddAnimal);

        //acciones a realizar en el momento de activar el boton
        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicialización de la variables contenedoras de los datos a registrar
                TextView txtname = (TextView)view.findViewById(R.id.txtAnimalName);
                TextView txtweight = (TextView)view.findViewById(R.id.txtAnimalWeight);
                TextView txtcategory = (TextView)view.findViewById(R.id.txtCategory);
                TextView txtpopulation = (TextView)view.findViewById(R.id.txtpopulation);

                //comprueba que los campos no esten vaciós
                if(txtname.getText().toString().equals("") || txtweight.getText().toString().equals("") || txtcategory.getText().toString().equals("") || txtpopulation.getText().toString().equals("")){
                    Toast.makeText(getContext().getApplicationContext(), "Complete the Spaces", Toast.LENGTH_LONG).show();
                }
                else {
                    //Instancia  el Repositorio de Animale que contiene los métodos para BD
                    IBD repository = new AnimalRepository(getContext().getApplicationContext());

                    //instancia animal para crear un objeto con los datos que se van a enviar a almacenar a la BD
                    Animal animal = new Animal();

                    //captura los datos a almcenar
                    animal.setName(txtname.getText().toString());
                    animal.setCategory(txtcategory.getText().toString());
                    animal.setPopulation(Integer.parseInt(txtpopulation.getText().toString()));
                    animal.setWeight(Integer.parseInt(txtweight.getText().toString()));

                    //envia a almacenar
                    repository.Save(animal);
                    //limpiar campos
                    txtcategory.setText("");
                    txtname.setText("");
                    txtpopulation.setText("");
                    txtweight.setText("");
                    Toast.makeText(getContext().getApplicationContext(),"Registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        void onFragmentInteraction(Uri uri);
    }
}

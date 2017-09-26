package company.juancho.congresoFP;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment {


    private RadioGroup grupoInstitucion;
    private Button buttonFinalizar, buttonModificar;
    private RadioButton campoPublica, campoPrivada;
    private TextInputLayout tilLocalidad, tilID, tilCargaHoraria, tilGasto;

    View view;

    private void inicarElementos() {
        grupoInstitucion = (RadioGroup) view.findViewById(R.id.opciones_institucion);

        campoPublica = (RadioButton) view.findViewById(R.id.radio_publica);
        campoPrivada = (RadioButton) view.findViewById(R.id.radio_privada);

        tilID = (TextInputLayout) view.findViewById(R.id.til_ID);
        tilCargaHoraria = (TextInputLayout) view.findViewById(R.id.til_cargaHoraria);

        buttonFinalizar = (Button) view.findViewById(R.id.button_finalizar);
        buttonModificar = (Button) view.findViewById(R.id.button_modificar);

    }

    public FormularioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_formulario, container, false);


        return view;
    }


}
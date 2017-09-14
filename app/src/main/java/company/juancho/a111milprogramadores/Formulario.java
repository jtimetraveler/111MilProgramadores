package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Formulario extends AppCompatActivity {

    //region Parametros
    private int n = 0, m = 0;
    private EditText campoNombre;
    private EditText campoApellido, campoMail, campoTelefono;

    private EditText campoDNI;
    private EditText campoInstitucion;
    private EditText campoLocalidad, campoCargaHoraria, campoID;
    //private EditText campoID, campoViatico, campoTraslado;


    private Usuario usuario;

    private TextInputLayout tilNombre, tilApellido, tilMail, tilTelefono;
    private TextInputLayout tilDNI;
    private TextInputLayout tilIntitucion;
    private TextInputLayout tilLocalidad, tilID, tilCargaHoraria, tilGasto;


    private RadioGroup grupoLicencia, grupoVehiculo, grupoViatico;
    private RadioGroup grupoInstitucion;
    private RadioButton campoLicenciaSi, campoLicenciaNo, campoPublica, campoPrivada, campoViatico, campoColectivo;

    private Button buttonAgregarLicencia, buttonFinalizar, buttonModificar;

    private LinearLayout layoutFormulario, layoutLicencias;
    private LinearLayout.LayoutParams layoutParams;

    private ArrayList<TextInputLayout> listaID = new ArrayList<TextInputLayout>();
    private ArrayList<TextView> listaTextLicencias = new ArrayList<TextView>();
    private ArrayList<ImageButton> listaBotonesLic = new ArrayList<ImageButton>();


    private ArrayList<Licencia> licencias = new ArrayList<Licencia>();

    //endregion


    private void iniciarElementos() {
        buttonAgregarLicencia = (Button) findViewById(R.id.button_agregarLic);
        buttonFinalizar = (Button) findViewById(R.id.button_finalizar);
        buttonModificar = (Button) findViewById(R.id.button_modificar);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoDNI = (EditText) findViewById(R.id.campo_DNI);
        campoInstitucion = (EditText) findViewById(R.id.campo_Institucion);
        campoLocalidad = (EditText) findViewById(R.id.campo_localidad);
        campoMail = (EditText) findViewById(R.id.campo_mail);
        campoApellido = (EditText) findViewById(R.id.campo_apellido);
        campoTelefono = (EditText) findViewById(R.id.campo_telefono);


        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilDNI = (TextInputLayout) findViewById(R.id.til_DNI);
        tilIntitucion = (TextInputLayout) findViewById(R.id.til_Institucion);
        tilLocalidad = (TextInputLayout) findViewById(R.id.til_localidad);
        tilApellido = (TextInputLayout) findViewById(R.id.til_apellido);
        tilMail = (TextInputLayout) findViewById(R.id.til_mail);
        tilTelefono = (TextInputLayout) findViewById(R.id.til_telefono);

        tilID = (TextInputLayout) findViewById(R.id.til_ID);
        tilCargaHoraria = (TextInputLayout) findViewById(R.id.til_cargaHoraria);
        tilGasto = (TextInputLayout) findViewById(R.id.til_transposteKM);


        grupoVehiculo = (RadioGroup) findViewById(R.id.opciones_transporte);
        grupoLicencia = (RadioGroup) findViewById(R.id.opciones_licencia);
        grupoViatico = (RadioGroup) findViewById(R.id.opciones_viatico);
        grupoInstitucion = (RadioGroup) findViewById(R.id.opciones_institucion);


        campoLicenciaSi = (RadioButton) findViewById(R.id.radio_si);
        campoLicenciaNo = (RadioButton) findViewById(R.id.radio_no);
        campoPublica = (RadioButton) findViewById(R.id.radio_publica);
        campoPrivada = (RadioButton) findViewById(R.id.radio_privada);
        campoViatico = (RadioButton) findViewById(R.id.radio_viaticoSI);
        campoColectivo = (RadioButton) findViewById(R.id.radio_colectivo);


        campoID = (EditText) findViewById(R.id.campoID);
        campoCargaHoraria = (EditText) findViewById(R.id.campo_cargaHoraria);


        layoutFormulario = (LinearLayout) findViewById(R.id.layout_formulario);
        layoutLicencias = (LinearLayout) findViewById(R.id.layout_licencias);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        assert layoutFormulario != null;


        listaID.add((TextInputLayout) findViewById(R.id.til_ID));

        habilitarLicencia(false);

        habilitarTraslado(false);
        grupoVehiculo.check(R.id.radio_trasladoNo);
        grupoLicencia.check(R.id.radio_viaticoNO);

        campoLicenciaNo.setChecked(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.iniciarElementos();


        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pressBoton();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarLicencia(guardarLicencia(n), n);
                buttonModificar.setVisibility(View.INVISIBLE);
                buttonAgregarLicencia.setVisibility(View.VISIBLE);
                habitarBonetes(true);
                buttonFinalizar.setEnabled(true);
            }
        });

        buttonAgregarLicencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarLicencia(guardarLicencia());
            }
        });

        grupoViatico.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_viaticoSI:
                        grupoVehiculo.getChildAt(1).setEnabled(false);
                        grupoVehiculo.check(R.id.radio_colectivo);
                        habilitarTraslado(true);
                        break;
                    case R.id.radio_viaticoNO:
                        grupoVehiculo.getChildAt(1).setEnabled(true);
                        grupoVehiculo.check(R.id.radio_trasladoNo);
                        habilitarTraslado(false);
                        break;
                }
            }
        });

        grupoVehiculo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_trasladoNo:
                        habilitarTraslado(false);
                        break;
                    case R.id.radio_colectivo:
                        habilitarTraslado(true);
                        tilGasto.setHint("Precio del pasaje");
                        break;
                    case R.id.radio_auto:
                        habilitarTraslado(true);
                        tilGasto.setHint("Gasto promedio en combustible y peajes");
                        break;
                }
            }
        });

        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esCorreoValido(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoDNI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDNI.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        campoTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void pressBoton() {

        boolean c = validarDatos();

        mostrarMensaje("Esta todo bien con Validar");
/*
        if (validarDatos()) {
            // OK, se pasa a la siguiente acción
            guardarDatos();

            //new Insertar(this, usuario).execute();

            Toast.makeText(this, "Se ha realizado el registro, espere confirmación", Toast.LENGTH_LONG).show();
            //lanzarMainActivity();
        }*/


    }




    //region Métodos de valiudación del formulario

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30 || nombre.isEmpty()) {
            tilNombre.setError("Nombre inválido");
            tilNombre.requestFocus();
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    private boolean esDNIValido(String password) {

        if (!(password.length() == 8)) {
            tilDNI.setError("Número de DNI inválido");
            tilDNI.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilMail.setError("Correo electrónico inválido");
            return false;
        } else {
            tilMail.setError(null);
        }

        return true;
    }

    private boolean esIDValido(String id){
        if ((id.length()>6 )) {
            tilID.setError("Número de ID inválido");
            tilID.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean esTelefonoValido(String tel){

        if ( !(tel.length()==10)) {
            tilTelefono.setError("Número de Teléfono inválido");
            tilTelefono.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    private boolean validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String DNI = tilDNI.getEditText().getText().toString();


        boolean aux = false;


        if (esFull()) {

            boolean a = esNombreValido(nombre);
            boolean b = esDNIValido(DNI);
            boolean c = true;
            boolean d = esTelefonoValido(tilTelefono.getEditText().getText().toString());

            if(campoLicenciaSi.isChecked()){
                c = esIDValido(campoID.getText().toString());
            }


            if (a && b && c) {
                aux = true;
            }

        }


        return aux;
    }


    private boolean esFull() {
        boolean respuesta = false;


        if ( estaCompleto(tilApellido) && estaCompleto(tilNombre) && estaCompleto(tilDNI) && estaCompleto(tilIntitucion)
                && estaCompleto(tilLocalidad) && estaCompleto(tilMail) && estaCompleto(tilTelefono)) {
            if (campoLicenciaSi.isChecked()) {
                if (estaCompleto(tilID)) {
                    respuesta = true;
                }
            } else {
                respuesta = true;
            }

        }
        return respuesta;
    }

    //endregion


    //region Guardar Datos en del Usuario


    private void guardarDatos() {
        usuario = new Usuario(campoNombre.getText().toString(), campoInstitucion.getText().toString(), campoLocalidad.getText().toString(), Integer.parseInt(campoDNI.getText().toString()));

        usuario.setApellido(tilApellido.getEditText().getText().toString());
        usuario.setMail(campoMail.getText().toString());
        usuario.setTelefono(campoTelefono.getText().toString());

        usuario.setListaLicencias(licencias);
        /*if(licencias.size()==0){
            usuario.setPublica(campoPublica.isChecked());

        }*/
        usuario.setViatico(campoViatico.isChecked());
        if(usuario.isViatico()){
            usuario.setGasto(tilGasto.getEditText().getText().toString());
            if(campoColectivo.isChecked()){
                usuario.setTransporte("Colectivo");
            } else {
                usuario.setTransporte("Auto");
            }

        }


    }

    private Licencia guardarLicencia() {
        Licencia lic = new Licencia();
        lic.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText().toString()));
        lic.setID(Integer.parseInt(campoID.getText().toString()));
        lic.setPublica(campoPublica.isChecked());
        licencias.add(lic);
        limpiarCamposLicencia();
        return lic;
    }

    private Licencia guardarLicencia(int index) {
        Licencia lic = new Licencia();
        lic.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText().toString()));
        lic.setID(Integer.parseInt(campoID.getText().toString()));
        lic.setPublica(campoPublica.isChecked());
        licencias.set(index, lic);
        limpiarCamposLicencia();
        return lic;
    }


    //endregion


    //region Métodos auxiliares
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, "Está todo ok con "+mensaje, Toast.LENGTH_LONG).show();
    }


    private boolean estaCompleto(TextInputLayout til) {
        String text = til.getEditText().getText().toString();
        if (text.equals("")) {
            til.setError("Campo incompleto");
            til.getEditText().requestFocus();
            return false;
        } else {
            return true;
        }

    }


    private void limpiarTIL(TextInputLayout til) {
        til.setError(null);
        til.getEditText().setText("");
    }


    private void limpiarCamposLicencia() {

        this.limpiarTIL(tilCargaHoraria);
        this.limpiarTIL(tilID);
        campoLicenciaNo.setChecked(true);
        habilitarLicencia(false);
    }


    public void agragarTil(View v) {
        TextInputLayout titleWrapper = new TextInputLayout(this);
        titleWrapper.setLayoutParams(layoutParams);
        titleWrapper.setHint("Nuevo ID");
        titleWrapper.setCounterEnabled(true);
        titleWrapper.setCounterMaxLength(6);

        layoutFormulario.addView(titleWrapper);

        EditText title = new EditText(this);
        title.setLayoutParams(layoutParams);
        title.setInputType(2);

        titleWrapper.addView(title);

        listaID.add(titleWrapper);
    }


    private void habitarBonetes(boolean si) {

        for (int i = 0; i < listaBotonesLic.size(); i++) {
            listaBotonesLic.get(i).setEnabled(si);
        }

    }



    private void habilitarTraslado(boolean parametro) {


        tilGasto.setEnabled(parametro);

        tilGasto.setHint("");

    }


    public void habilitarLicencia(boolean parametro) {
        tilCargaHoraria.setEnabled(parametro);
        tilID.setEnabled(parametro);
        buttonAgregarLicencia.setEnabled(parametro);
        for (int i = 0; i < grupoInstitucion.getChildCount(); i++) {
            grupoInstitucion.getChildAt(i).setEnabled(parametro);

        }

        /*
        if (parametro) {
            buttonAgregarLicencia.setVisibility(View.VISIBLE);
        } else {
            buttonAgregarLicencia.setVisibility(View.INVISIBLE);
        }*/

    }

    //endregion


    //region Métodos de eventos


    public void editarLicencia(View v) {

        for (int i = 0; i < listaBotonesLic.size(); i++) {
            if (v.getId() == listaBotonesLic.get(i).getId()) {
                n = i;
                campoID.setText(String.valueOf(licencias.get(i).getID()));
                campoCargaHoraria.setText(String.valueOf(licencias.get(i).getCargaHoraria()));
                if (licencias.get(i).isPublica()) {
                    campoPublica.setChecked(true);
                } else {
                    campoPrivada.setChecked(true);
                }

                campoLicenciaSi.setChecked(true);
                habilitarLicencia(true);
                //mostrarMensaje();
                buttonModificar.setVisibility(View.VISIBLE);
                //buttonAgregarLicencia.setVisibility(View.INVISIBLE);
            }
        }

    }


    public void mostrarLicencia(Licencia licencia) {
        //added LInearLayout
        LinearLayout linearLayout = new LinearLayout(this);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //linearLayout.setVerticalGravity(3);

        layoutLicencias.addView(linearLayout);
        //add textView

        LinearLayout.LayoutParams layoutParamsGrav = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);

        layoutParamsGrav.gravity = Gravity.CENTER_VERTICAL;
        layoutParamsGrav.setMargins(10, 0, 0, 0);
        TextView textView = new TextView(this);

        //textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText("ID: " + licencia.getID() + " - Carga Horaria: " + licencia.getCargaHoraria() + " (" + licencia.getPublica() + ")");


        // added Button
        final ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.ic_menu_edit);
        //button.setLayoutParams(params);
        button.setId(m);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editarLicencia(view);
                habitarBonetes(false);
                buttonFinalizar.setEnabled(false);
                buttonAgregarLicencia.setEnabled(false);
                buttonAgregarLicencia.setVisibility(View.INVISIBLE);
                campoLicenciaSi.requestFocus();
            }
        });
        textView.requestFocus();

        //added the textView and the Button to LinearLayout
        linearLayout.addView(textView, layoutParamsGrav);
        linearLayout.addView(button);

        listaBotonesLic.add(button);
        listaTextLicencias.add(textView);
        m++;

    }



    private void mostrarLicencia(Licencia licencia, int i) {
        listaTextLicencias.get(i).setText("ID: " + licencia.getID() + " - Carga Horaria: " + licencia.getCargaHoraria() + " (" + licencia.getPublica() + ")");
    }


    public void lanzarMainActivity() {
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }


    public void onRadioButtonClicked(View view) {
        boolean marcado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_no:
                if (marcado) {
                    this.habilitarLicencia(false);
                }
                break;

            case R.id.radio_si:
                if (marcado) {
                    this.habilitarLicencia(true);
                }
                break;
        }
    }





    //endregion


    //region Basura (?
    /*
    protected void sendEmail(String mail) {
        new MailJob("prog111mil@hotmail.com", "curso111mil").execute(
                new MailJob.Mail("prog111mil@hotmail.com", mail, "Inscripción ", "La Inscripción se ha realizado exitosamente. Gracias, vuelva pronto.")
        );
    }*/


    //endregion

}

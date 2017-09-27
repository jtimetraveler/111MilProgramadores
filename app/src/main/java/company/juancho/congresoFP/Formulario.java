package company.juancho.congresoFP;

import android.content.Context;
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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
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

import company.juancho.congresoFP.tools.Insertar;
import company.juancho.congresoFP.tools.MailJob;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Formulario extends AppCompatActivity {

    //region Parametros
    private int n = 0, m = 0;
    private EditText campoNombre;
    private EditText campoApellido, campoMail, campoTelefono;

    private EditText campoDNI;
    private EditText campoInstitucion;
    private EditText campoLocalidad, campoCargaHoraria, campoID;
    private EditText campoPeaje;
    //private EditText campoID, campoViatico, campoTraslado;


    private Usuario usuario;

    private TextInputLayout tilNombre, tilApellido, tilMail, tilTelefono;
    private TextInputLayout tilDNI;
    private TextInputLayout tilIntitucion;
    private TextInputLayout tilLocalidad, tilID, tilCargaHoraria, tilGasto, tilPeaje;


    private RadioGroup grupoLicencia, grupoVehiculo, grupoViatico, grupoTraslado, grupoMenu;
    private RadioGroup grupoInstitucion;
    private RadioButton campoLicenciaSi, campoLicenciaNo, campoPublica, campoPrivada, campoViatico, campoColectivo, campoTrasladoSi;

    private Button buttonAgregarLicencia, buttonFinalizar, buttonModificar;

    private LinearLayout layoutFormulario, layoutLicencias;
    private LinearLayout.LayoutParams layoutParams;

    private ArrayList<TextInputLayout> listaID = new ArrayList<TextInputLayout>();
    private ArrayList<TextView> listaTextLicencias = new ArrayList<TextView>();
    private ArrayList<ImageButton> listaBotonesLic = new ArrayList<ImageButton>();


    private ArrayList<Licencia> licencias = new ArrayList<Licencia>();

    private LinearLayout lBotonesLicencia;

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

        campoPeaje = (EditText) findViewById(R.id.campo_peaje);


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
        tilPeaje = (TextInputLayout) findViewById(R.id.til_peaje);


        grupoTraslado = (RadioGroup) findViewById(R.id.opciones_traslado);
        grupoVehiculo = (RadioGroup) findViewById(R.id.opciones_transporte);
        grupoLicencia = (RadioGroup) findViewById(R.id.opciones_licencia);
        grupoViatico = (RadioGroup) findViewById(R.id.opciones_viatico);
        grupoInstitucion = (RadioGroup) findViewById(R.id.opciones_institucion);
        grupoMenu = (RadioGroup) findViewById(R.id.opciones_menu);



        campoLicenciaSi = (RadioButton) findViewById(R.id.radio_si);
        campoLicenciaNo = (RadioButton) findViewById(R.id.radio_no);
        campoPublica = (RadioButton) findViewById(R.id.radio_publica);
        campoPrivada = (RadioButton) findViewById(R.id.radio_privada);
        campoViatico = (RadioButton) findViewById(R.id.radio_viaticoSI);
        campoColectivo = (RadioButton) findViewById(R.id.radio_colectivo);
        campoTrasladoSi = (RadioButton) findViewById(R.id.radio_trasladoSi);

        campoID = (EditText) findViewById(R.id.campoID);
        campoCargaHoraria = (EditText) findViewById(R.id.campo_cargaHoraria);


        layoutFormulario = (LinearLayout) findViewById(R.id.layout_formulario);
        layoutLicencias = (LinearLayout) findViewById(R.id.layout_licencias);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lBotonesLicencia = (LinearLayout) findViewById(R.id.layout_botones_licencia);
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

                pressBotonFinalizar();
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

        /*grupoViatico.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        });*/


        grupoTraslado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_trasladoNo:
                        habilitarTraslado(false);
                        break;
                    case R.id.radio_trasladoSi:
                        habilitarTraslado(true);
                        campoColectivo.setChecked(true);
                        tilGasto.setHint("Precio del pasaje");
                        break;
                }
            }
        });


        grupoVehiculo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_colectivo:
                        tilGasto.setHint("Precio del pasaje");
                        tilPeaje.setEnabled(false);
                        mostralLinearLayout(tilPeaje,false);

                        break;
                    case R.id.radio_auto:
                        tilGasto.setHint("Gasto promedio en combustible");
                        tilPeaje.setEnabled(true);
                        mostrarTIL(tilPeaje);
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
                tilApellido.setError(null);
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
                tilID.setError(null);
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
                tilTelefono.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void pressBotonFinalizar() {




        if (validarDatos()) {
            // OK, se pasa a la siguiente acción
            guardarDatos();
            //mostrarMensaje("Guardar"+usuario.getMenu()+usuario.getPeaje()+usuario.getTraslado());
            new Insertar(this, usuario).execute();
            sendEmail(usuario.getMail());
            Toast.makeText(this, "Se ha realizado el registro, espere confirmación", Toast.LENGTH_LONG).show();
            lanzarMainActivity();
        }


    }

    /*public void manejoTransporte(@IdRes int id) {
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
    }*/


    //region Métodos de valiudación del formulario

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+$");
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


            boolean c = true;
            boolean d = esTelefonoValido(tilTelefono.getEditText().getText().toString());
            boolean e = esCorreoValido(campoMail.getText().toString());
            boolean b = esDNIValido(DNI);
            boolean a = esNombreValido(nombre);

            if(campoLicenciaSi.isChecked()){
                c = esIDValido(campoID.getText().toString());
            }


            if (a && b && c && d && e) {
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
                if (estaCompleto(tilID ) && estaCompleto(tilCargaHoraria)) {
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


        usuario.setGasto(tilGasto.getEditText().getText().toString());
        usuario.setTraslado(campoTrasladoSi.isChecked());
        if(campoColectivo.isChecked()){
            usuario.setTransporte("Colectivo");
        } else {
            usuario.setTransporte("Auto");
            usuario.setPeaje(campoPeaje.getText().toString());
        }
        usuario.setViatico(campoViatico.isChecked());


        usuario.setMenu(this.getMenu(grupoMenu.getCheckedRadioButtonId()));


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

    private String getMenu(@IdRes int id){

        switch (id){
            case R.id.radio_menuVegetariano:
                return "Vegetariano";

            case R.id.radio_menuCeliaco:
                return "Celiado";

            case R.id.radio_menuHipertenso:
                return "Hipertenso";
            default:
                return "Ninguno";

        }



    }



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
        tilPeaje.setEnabled(parametro);
        for (int i = 0; i < grupoVehiculo.getChildCount(); i++) {
            grupoVehiculo.getChildAt(i).setEnabled(parametro);

        }
        mostralLinearLayout(grupoVehiculo,parametro);

        mostralLinearLayout(tilGasto,parametro);
        if(!campoColectivo.isChecked()){
            mostralLinearLayout(tilPeaje,parametro);
        }
        if(parametro){
            campoColectivo.requestFocus();
        }

    }


    public void habilitarLicencia(boolean parametro) {
        tilCargaHoraria.setEnabled(parametro);
        tilID.setEnabled(parametro);
        buttonAgregarLicencia.setEnabled(parametro);
        for (int i = 0; i < grupoInstitucion.getChildCount(); i++) {
            grupoInstitucion.getChildAt(i).setEnabled(parametro);

        }

        mostralLinearLayout(tilCargaHoraria,parametro);
        mostralLinearLayout(tilID,parametro);
        mostralLinearLayout(grupoInstitucion,parametro);
        mostralLinearLayout(lBotonesLicencia, parametro);
        if(parametro){
            tilID.requestFocus();
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
        button.setLayoutParams(params);
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

    protected void sendEmail(String mail) {
        new MailJob(this, "prog111mil@hotmail.com", "curso111mil").execute(
                new MailJob.Mail("prog111mil@hotmail.com", mail, "Inscripción ", "La Inscripción se ha realizado exitosamente. " +
                        "Necesitariamos una cuenta de correo oficial desde donde mandar los mail y todo lo que iría en ellos.")
        );
    }


    //endregion

    //region Animaciones
    private void mostralLinearLayout(LinearLayout l, boolean visible){
        if(visible && (l.getVisibility() == View.GONE)){
                animar(true);
                l.setVisibility(View.VISIBLE);
        } else if (l.getVisibility() == View.VISIBLE){
            animar(false);
            l.setVisibility(View.GONE);
        }
    }


    public void mostrarTIL(TextInputLayout l){

        if (l.getVisibility() == View.GONE)
        {
            animar(true);
            l.setVisibility(View.VISIBLE);
        }
    }



    public void ocultarTIL(TextInputLayout textInputLayout) {
        if (textInputLayout.getVisibility() == View.VISIBLE) {
            animar(false);
            textInputLayout.setVisibility(View.GONE);

        }
    }



    private void animar(boolean mostrar)
    {
        AnimationSet set = new AnimationSet(true);

        Animation animation = null;

        if (mostrar)
        {

            //desde la esquina inferior derecha a la superior izquierda

            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        } else

        {    //desde la esquina superior izquierda a la esquina inferior derecha

            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        }
    }
    //endregion
}

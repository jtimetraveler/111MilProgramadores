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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import company.juancho.congresoFP.tools.Insertar;
import company.juancho.congresoFP.tools.MailJob;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Formulario extends AppCompatActivity {

    //region Parametros
    private int n = 0, m = 0, positionSpinnerInstitucion =0;
    private EditText campoNombre;
    private EditText campoApellido, campoMail, campoTelefono;

    private EditText campoDNI;
    //private EditText campoInstitucion;
    private EditText campoLocalidad, campoCargaHoraria, campoID;
    private EditText campoPeaje;
    //private EditText campoID, campoViatico, campoTraslado;


    private Usuario usuario;

    private TextInputLayout tilNombre, tilApellido, tilMail, tilTelefono;
    private TextInputLayout tilDNI;
    //private TextInputLayout tilIntitucion;
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



    private Spinner spinnerMenu, spinnerProf, spinnerInstucion, spinnerNumInst;

    private ArrayAdapter<CharSequence> adapterNumInstitucion;

    private TextView textoNumIns, textFamiliaProf, textCursos, textCursosTitulo;
    //endregion


    private void iniciarElementos() {
        buttonAgregarLicencia = (Button) findViewById(R.id.button_agregarLic);
        buttonFinalizar = (Button) findViewById(R.id.button_finalizar);
        buttonModificar = (Button) findViewById(R.id.button_modificar);

        textoNumIns = (TextView) findViewById(R.id.text_num_institucion);
        textFamiliaProf = (TextView) findViewById(R.id.text_prof);
        textCursos = (TextView) findViewById(R.id.text_cursos_prof);
        textCursosTitulo = (TextView) findViewById(R.id.text_cursos);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoDNI = (EditText) findViewById(R.id.campo_DNI);
        //campoInstitucion = (EditText) findViewById(R.id.campo_Institucion);
        campoLocalidad = (EditText) findViewById(R.id.campo_localidad);
        campoMail = (EditText) findViewById(R.id.campo_mail);
        campoApellido = (EditText) findViewById(R.id.campo_apellido);
        campoTelefono = (EditText) findViewById(R.id.campo_telefono);

        campoPeaje = (EditText) findViewById(R.id.campo_peaje);


        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilDNI = (TextInputLayout) findViewById(R.id.til_DNI);
        //tilIntitucion = (TextInputLayout) findViewById(R.id.til_Institucion);
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
        //grupoMenu = (RadioGroup) findViewById(R.id.opciones_menu);



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

        spinnerMenu = (Spinner) findViewById(R.id.spinnerMenu);
        spinnerProf = (Spinner) findViewById(R.id.spinner_profesional);

        spinnerInstucion = (Spinner) findViewById(R.id.spinner_institucion);
        spinnerNumInst = (Spinner) findViewById(R.id.spinner_num_institucion);

        //ArrayAdapter para conectar el Spinner a nuestros recursos strings.xml
        ArrayAdapter<CharSequence> adapter, adapterProf, adapterInst;
        //Obtener instancia del GameSpinner


        //Asignas el origen de datos desde los recursos
        adapter = ArrayAdapter.createFromResource(this, R.array.Dieta,
                android.R.layout.simple_spinner_item);
        adapterProf = ArrayAdapter.createFromResource(this, R.array.FamiliaProfesional,
                android.R.layout.simple_spinner_item);

        adapterInst = ArrayAdapter.createFromResource(this, R.array.Institucion,
                android.R.layout.simple_spinner_item);

        /*adapterNumInstitucion = ArrayAdapter.createFromResource(this, R.array.CEA,
                android.R.layout.simple_spinner_item);*/

        //Asignas el layout a inflar para cada elemento
        //al momento de desplegar la lista
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterInst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapterNumInstitucion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seteas el adaptador
        spinnerMenu.setAdapter(adapter);

        spinnerProf.setAdapter(adapterProf);

        spinnerInstucion.setAdapter(adapterInst);
        //spinnerNumInst.setAdapter(adapterNumInstitucion);




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
                if(validarCamposID()) {
                    mostrarLicencia(guardarLicencia(n), n);
                    buttonModificar.setVisibility(View.INVISIBLE);
                    buttonAgregarLicencia.setVisibility(View.VISIBLE);
                    habitarBonetes(true);
                    buttonFinalizar.setEnabled(true);
                }
            }
        });

        buttonAgregarLicencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCamposID()){
                    mostrarLicencia(guardarLicencia());
                }

            }
        });


        spinnerInstucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                cambiarSpinnerNum(pos);
                if(!(pos== positionSpinnerInstitucion)){
                    mostrarMensaje("Verifique correctamente que no se encuentra en otra categoría de incripción.");
                    positionSpinnerInstitucion =pos;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


        spinnerProf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                comportamientoSpinnerFamiliaProf(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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


    private void comportamientoSpinnerFamiliaProf(int position){
        int aux=0;

        switch (position){
            case 0:
                aux = R.string.cursos18artistica;
                break;
            case 1:
                 aux = R.string.cursos00administracion;
                break;
            case 2:
                aux = R.string.cursos01agro;
                break;
            case 3:
                aux = R.string.cursos02apicultura;
                break;
            case 4:
                aux = R.string.cursos03Automotriz;
                break;
            case 5:
                aux = R.string.cursos04Construccion;
                break;
            case 6:
                aux = R.string.cursos05cuero;
                break;
            case 7:
                aux = R.string.cursos06electromecanica;
                break;
            case 8:
                aux = R.string.cursos07electronica;
                break;
            case 9:
                aux = R.string.cursos08electrica;
                break;
            case 10:
                aux = R.string.cursos09estetica;
                break;
            case 11:
                aux = R.string.cursos10forestal;
                break;
            case 12:
                aux = R.string.cursos11hoteleria;
                break;
            case 13:
                aux = R.string.cursos12alimenticia;
                break;
            case 14:
                aux = R.string.cursos13informatica;
                break;
            case 15:
                aux = R.string.cursos14mecanica;
                break;
            case 16:
                aux = R.string.cursos15artesanal;
                break;
            case 17:
                aux = R.string.cursos16servicios;
                break;
            case 18:
                aux = R.string.cursos17textil;
                break;
        }


        textCursos.setText(aux);
    }


    private void cambiarSpinnerNum(int pos){
        int aux=0;

        switch (pos){
            case 0:
                aux = R.array.talleristas;
                break;

            case 1:
                aux = R.array.Otros;
                break;
            case 2:
                aux = R.array.CEA;
                break;
            case 3:
                aux = R.array.CECLA;
                break;
            case 4:
                aux = R.array.CFP;
                break;
            case 5:
                aux = R.array.EESO;
                break;
            case 6:
                aux = R.array.EET;
                break;
            case 7:
                aux = R.array.EETP;
                break;
            case 8:
                aux = R.array.EPN;
                break;

        }
        if(pos<2){
            mostralView(spinnerProf, false);
            mostralView(textFamiliaProf,false);
            mostralView(textoNumIns,false);
            mostralView(textCursos,false);
            mostralView(textCursosTitulo,false);
        }else {
            mostralView(spinnerProf, true);
            mostralView(textFamiliaProf,true);
            mostralView(textoNumIns,true);
            mostralView(textCursos,true);
            mostralView(textCursosTitulo,true);
        }


        adapterNumInstitucion = ArrayAdapter.createFromResource(this, aux,
                android.R.layout.simple_spinner_item);
        adapterNumInstitucion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seteas el adaptador
        spinnerNumInst.setAdapter(adapterNumInstitucion);

    }

    private void pressBotonFinalizar() {




        if (validarDatos()) {
            // OK, se pasa a la siguiente acción
            guardarDatos();
            //mostrarMensaje("Guardar. Int: "+usuario.getInstitucion());
            new Insertar(this, usuario).execute();

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
            tilTelefono.setError("Número de Teléfono inválido. Ej: 3415111222");
            tilTelefono.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    private boolean validarCamposID(){
        if(this.estaCompleto(tilID)&&this.estaCompleto(tilCargaHoraria) && esIDValido(tilID.getEditText().getText().toString())){
            return true;
        } else {
            return false;
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


        if ( estaCompleto(tilApellido) && estaCompleto(tilNombre) && estaCompleto(tilDNI)
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
        usuario = new Usuario(campoNombre.getText().toString(), campoLocalidad.getText().toString(), Integer.parseInt(campoDNI.getText().toString()));

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


        usuario.setMenu(spinnerMenu.getItemAtPosition(spinnerMenu.getSelectedItemPosition()).toString());
        if(positionSpinnerInstitucion>1){
            usuario.setFamiliaProfesional(spinnerProf.getItemAtPosition(spinnerProf.getSelectedItemPosition()).toString());
        }

        usuario.setInstitucion(this.getInstitucion());
    }

    private String getInstitucion(){
        String aux1, aux2;
                aux1 =spinnerInstucion.getItemAtPosition(spinnerInstucion.getSelectedItemPosition()).toString();
                aux2 = spinnerNumInst.getItemAtPosition(spinnerNumInst.getSelectedItemPosition()).toString();
        return aux1+"-"+aux2;
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
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
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
        button.setImageResource(R.drawable.ic_edit_black_24dp);
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


    private void mostralView(Spinner l, boolean visible){
        if(visible && (l.getVisibility() == View.GONE)){
            animar(true);
            l.setVisibility(View.VISIBLE);

        } else if (l.getVisibility() == View.VISIBLE){
            animar(false);
            l.setVisibility(View.GONE);

        }
    }

    private void mostralView(TextView l, boolean visible){
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



    //region basura
    /*<RadioGroup
    android:id="@+id/opciones_menu"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    android:gravity="left"
    android:orientation="vertical">



                <RadioButton
    android:id="@+id/radio_menuNinguna"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginRight="30dp"
    android:checked="true"
    android:text="Ninguna" />

                <RadioButton
    android:id="@+id/radio_menuVegetariano"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginRight="30dp"
    android:checked="false"
    android:text="Vegetariano" />

                <RadioButton
    android:id="@+id/radio_menuCeliaco"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginRight="30dp"
    android:checked="false"
    android:text="Celiaco" />

                <RadioButton
    android:id="@+id/radio_menuHipertenso"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginRight="30dp"
    android:checked="false"
    android:text="Hipertenso" />

            </RadioGroup>*/

    //endregion
}

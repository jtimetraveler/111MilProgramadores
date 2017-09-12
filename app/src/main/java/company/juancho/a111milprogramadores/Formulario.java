package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import company.juancho.a111milprogramadores.tools.Insertar;
import company.juancho.a111milprogramadores.tools.MailJob;

public class Formulario extends AppCompatActivity {

    //region Parametros
    private int n = 0, m=0;
    private EditText campoNombre ;
    private EditText campoDNI;
    private EditText campoInstitucion;
    private EditText campoLocalidad, campoCargaHoraria, campoID;
    //private EditText campoID, campoViatico, campoTraslado;


    private Usuario usuario;

    private TextInputLayout tilNombre;
    private TextInputLayout tilDNI;
    private TextInputLayout tilIntitucion;
    private TextInputLayout tilLocalidad, tilID, tilCargaHoraria ;

    private RadioGroup grupoLicencia;
    private RadioButton campoLicenciaSi, campoLicenciaNo, campoPublica, campoPrivada;

    private Button buttonAgregarLicencia, buttonFinalizar, buttonModificar;

    private LinearLayout layoutFormulario;
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


        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilDNI = (TextInputLayout) findViewById(R.id.til_DNI);
        tilIntitucion = (TextInputLayout) findViewById(R.id.til_Institucion);
        tilLocalidad = (TextInputLayout) findViewById(R.id.til_localidad);
        tilID = (TextInputLayout) findViewById(R.id.til_ID);
        tilCargaHoraria = (TextInputLayout) findViewById(R.id.til_cargaHoraria);

        grupoLicencia = (RadioGroup) findViewById(R.id.opciones_licencia);

        campoLicenciaSi = (RadioButton) findViewById(R.id.radio_si);
        campoLicenciaNo = (RadioButton) findViewById(R.id.radio_no);
        campoPublica = (RadioButton) findViewById(R.id.radio_publica);
        campoPrivada = (RadioButton) findViewById(R.id.radio_privada);

        campoID = (EditText) findViewById(R.id.campoID);
        campoCargaHoraria = (EditText) findViewById(R.id.campo_cargaHoraria);


        layoutFormulario = (LinearLayout) findViewById(R.id.layout_formulario);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        assert layoutFormulario != null;





        listaID.add((TextInputLayout) findViewById(R.id.til_ID));
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

                mostrarLicencia(guardarLicencia(n),n);
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



    }


    private void pressBoton(){




        if(validarDatos()){
            // OK, se pasa a la siguiente acción
            guardarDatos();
            //Toast.makeText(this, "Está todo ok "+usuario.getDni()+" "+usuario.getWsID(), Toast.LENGTH_LONG).show();
            new Insertar(this, usuario).execute();

            Toast.makeText(this, "Se ha realizado el registro, espere confirmación", Toast.LENGTH_LONG).show();
            lanzarMainActivity();
        }



    }

    protected void sendEmail(String mail) {
        new MailJob("prog111mil@hotmail.com", "curso111mil").execute(
                new MailJob.Mail("prog111mil@hotmail.com", mail, "Inscripción ", "La Inscripción se ha realizado exitosamente. Gracias, vuelva pronto.")
        );
    }





    //region Métodos de valiudación del formulario

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30 || nombre.isEmpty()) {
            tilNombre.setError("Nombre inválido");

            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    private boolean esDNIValido(String password){

        if (!(password.length() == 8)){
            tilDNI.setError("Número de DNI inválido");
            return false;
        } else {
            return true;
        }

    }


    private boolean validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String DNI = tilDNI.getEditText().getText().toString();
        String institucion = tilIntitucion.getEditText().getText().toString();
        String localidad = tilLocalidad.getEditText().getText().toString();
        boolean aux =false;


        if(esFull()){

            boolean a = esNombreValido(nombre);
            boolean b = esDNIValido(DNI);


            if (a &&b ) {
                aux = true;
            }

        }


        return aux;
    }




    private boolean esFull(){
        boolean respuesta = false;



        if(estaCompleto(tilNombre) && estaCompleto(tilDNI) && estaCompleto(tilIntitucion) && estaCompleto(tilLocalidad)){
            if(campoLicenciaSi.isChecked()){
                if(estaCompleto(tilID)){
                    respuesta = true;
                }
            } else {
                respuesta = true;
            }
        }
        return respuesta;
    }

    private void guardarDatos(){
        usuario = new Usuario(campoNombre.getText().toString(),campoInstitucion.getText().toString(),campoLocalidad.getText().toString(),Integer.parseInt(campoDNI.getText().toString()));
        //usuario.setNombre();
        /*usuario.setInstitucion(campoInstitucion.getText().toString());
        usuario.setLocalidad(campoLocalidad.getText().toString());
        usuario.setDni(Integer.parseInt(campoDNI.getText().toString()));
        //usuario.setPublica(campoPublica.isChecked());
        //usuario.setLicencia(campoLicenciaSi.isChecked());*/


            usuario.setListaLicencias(licencias);


    }

    private Licencia guardarLicencia(){
        Licencia lic = new Licencia();
        lic.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText().toString()));
        lic.setID(Integer.parseInt(campoID.getText().toString()));
        lic.setPublica(campoPublica.isChecked());
        licencias.add(lic);
        limpiarCampos();
        return lic;
    }

    private Licencia guardarLicencia(int index){
        Licencia lic = new Licencia();
        lic.setCargaHoraria(Integer.parseInt(campoCargaHoraria.getText().toString()));
        lic.setID(Integer.parseInt(campoID.getText().toString()));
        lic.setPublica(campoPublica.isChecked());
        licencias.set(index, lic);
        limpiarCampos();
        return lic;
    }


    //endregion


    //region Métodos auxiliares
    private void mostrarMensaje(){
        Toast.makeText(this, "Está todo ok con el boton", Toast.LENGTH_LONG).show();
    }


    private boolean estaCompleto(TextInputLayout til){
        String text = til.getEditText().getText().toString();
        if( text.equals("")){
            til.setError("Campo incompleto");
            til.getEditText().requestFocus();
            return false;
        } else {
            return true;
        }

    }

    public void habilitarLicencia(boolean ver){
        tilCargaHoraria.setEnabled(ver);
        tilID.setEnabled(ver);
        buttonAgregarLicencia.setEnabled(ver);
    }

    private  void limpiarTIL(TextInputLayout til){
        til.setError(null);
        til.getEditText().setText("");
    }

    private void limpiarCampos(){

        this.limpiarTIL(tilCargaHoraria);
        this.limpiarTIL(tilID);
        campoLicenciaNo.setChecked(true);
        habilitarLicencia(false);
    }


    //endregion


    //region Métodos de eventos


    public  void editarLicencia(View v){

        for(int i=0;i<listaBotonesLic.size();i++){
            if(v.getId()==listaBotonesLic.get(i).getId()){
                n=i;
                campoID.setText(String.valueOf(licencias.get(i).getID()));
                campoCargaHoraria.setText(String.valueOf(licencias.get(i).getCargaHoraria()));
                if(licencias.get(i).isPublica()){
                    campoPublica.setChecked(true);
                } else {
                    campoPrivada.setChecked(true);
                }

                campoLicenciaSi.setChecked(true);
                habilitarLicencia(true);
                mostrarMensaje();
                buttonModificar.setVisibility(View.VISIBLE);
                buttonAgregarLicencia.setVisibility(View.INVISIBLE);
            }
        }

    }


    private void habitarBonetes(boolean si){

        for(int i=0;i<listaBotonesLic.size();i++){
            listaBotonesLic.get(i).setEnabled(si);
        }

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


    public void mostrarLicencia(Licencia licencia){
        //added LInearLayout
        LinearLayout linearLayout = new LinearLayout(this);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //linearLayout.setVerticalGravity(3);

        layoutFormulario.addView(linearLayout);
        //add textView

        LinearLayout.LayoutParams layoutParamsGrav = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);

        layoutParamsGrav.gravity=Gravity.CENTER_VERTICAL;
        layoutParamsGrav.setMargins(10,0,0,0);
        TextView textView = new TextView(this);

        //textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText("ID: "+licencia.getID()+" - Carga Horaria: "+licencia.getCargaHoraria()+" ("+licencia.getPublica()+")");



        // added Button
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.ic_menu_edit);
        //button.setLayoutParams(params);
        button.setId(m);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editarLicencia(view);
                habitarBonetes(false);
                buttonFinalizar.setEnabled(false);
                campoLicenciaSi.requestFocus();
            }
        });
        textView.requestFocus();

        //added the textView and the Button to LinearLayout
        linearLayout.addView(textView,layoutParamsGrav);
        linearLayout.addView(button);

        listaBotonesLic.add(button);
        listaTextLicencias.add(textView);
        m++;

    }

    private void mostrarLicencia(Licencia licencia, int i){
        listaTextLicencias.get(i).setText(licencia.getID()+" - "+licencia.getCargaHoraria()+" - "+licencia.getPublica());
    }


    public void lanzarMainActivity(){
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






}

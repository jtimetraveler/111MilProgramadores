package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.regex.Pattern;

import company.juancho.a111milprogramadores.tools.Insertar;
import company.juancho.a111milprogramadores.tools.MailJob;

public class Formulario extends AppCompatActivity {
    private int n = 10;
    private EditText campoNombre ;
    private EditText campoDNI;
    private EditText campoInstitucion;
    private EditText campoLocalidad;
    private EditText campoID, campoViatico, campoTraslado;


    private Usuario usuario;

    private TextInputLayout tilNombre;
    private TextInputLayout tilDNI;
    private TextInputLayout tilIntitucion;
    private TextInputLayout tilLocalidad, tilID;

    private RadioGroup grupoLicencia, grupoPublica;
    private RadioButton campoLicencia, campoPublica;

    private Button button;

    private LinearLayout layoutFormulario;
    private LinearLayout.LayoutParams layoutParams;

    private void iniciarElementos() {
        button = (Button) findViewById(R.id.button3);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoDNI = (EditText) findViewById(R.id.campo_DNI);
        campoInstitucion = (EditText) findViewById(R.id.campo_Institucion);
        campoLocalidad = (EditText) findViewById(R.id.campo_localidad);

        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilDNI = (TextInputLayout) findViewById(R.id.til_DNI);
        tilIntitucion = (TextInputLayout) findViewById(R.id.til_Institucion);
        tilLocalidad = (TextInputLayout) findViewById(R.id.til_localidad);

        grupoLicencia = (RadioGroup) findViewById(R.id.opciones_licencia);

        campoLicencia = (RadioButton) findViewById(R.id.radio_si);
        campoPublica = (RadioButton) findViewById(R.id.radio_publica);

        layoutFormulario = (LinearLayout) findViewById(R.id.layout_formulario);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        assert layoutFormulario != null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.iniciarElementos();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pressBoton();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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
            new Insertar(this, usuario).execute();
            //sendEmail(campoLocalidad.getText().toString());
            //Toast.makeText(this, "Se ha realizado el registro", Toast.LENGTH_LONG).show();
            lanzarActivity();
        }

    }

    protected void sendEmail(String mail) {
        new MailJob("prog111mil@hotmail.com", "curso111mil").execute(
                new MailJob.Mail("prog111mil@hotmail.com", mail, "Inscripción ", "La Inscripción se ha realizado exitosamente. Gracias, vuelva pronto.")
        );
    }







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


    public void agragarTil(View v) {
        TextInputLayout titleWrapper = new TextInputLayout(this);
        titleWrapper.setLayoutParams(layoutParams);
        titleWrapper.setHint("Assessment Title:");

        layoutFormulario.addView(titleWrapper);

        EditText title = new EditText(this);
        title.setLayoutParams(layoutParams);


        titleWrapper.addView(title);


    }


    private boolean esFull(){

        if(estaCompleto(tilNombre) && estaCompleto(tilDNI) && estaCompleto(tilIntitucion) && estaCompleto(tilLocalidad)){
            return true;
        } else {
            return false;
        }

    }







    private void guardarDatos(){
        usuario = new Usuario();
        usuario.setNombre(campoNombre.getText().toString());
        usuario.setInstitucion(campoInstitucion.getText().toString());
        usuario.setLocalidad(campoLocalidad.getText().toString());
        usuario.setDni(Integer.getInteger(campoDNI.getText().toString()));
        usuario.setLicencia(campoLicencia.isChecked());
        if (usuario.isLicencia()) {
            usuario.setIdLicencia(Integer.parseInt(campoID.getText().toString()));
        }

    }

    public void lanzarActivity(){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }








}

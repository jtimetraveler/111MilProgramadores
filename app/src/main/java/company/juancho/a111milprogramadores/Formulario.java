package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import company.juancho.a111milprogramadores.tools.Insertar;
import company.juancho.a111milprogramadores.tools.MailJob;

public class Formulario extends AppCompatActivity {

    private EditText campoNombre ;
    private EditText campoDNI;
    private EditText campoInstitucion;
    private EditText campoLocalidad;
    private Usuario usuario;

    private TextInputLayout tilNombre;
    private TextInputLayout tilDNI;
    private TextInputLayout tilIntitucion;
    private TextInputLayout tilLocalidad;

    private CheckBox checkLicencia;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoDNI = (EditText) findViewById(R.id.campo_DNI);
        campoInstitucion = (EditText) findViewById(R.id.campo_Institucion);
        campoLocalidad = (EditText) findViewById(R.id.campo_localidad);

        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilDNI = (TextInputLayout) findViewById(R.id.til_DNI);
        tilIntitucion = (TextInputLayout) findViewById(R.id.til_Institucion);
        tilLocalidad = (TextInputLayout) findViewById(R.id.til_localidad);

        checkLicencia = (CheckBox) findViewById(R.id.check_licencia);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pressBoton();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        campoLocalidad.addTextChangedListener(new TextWatcher() {
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

    public void guardarDatos(View v){

        if(campoDNI.getText().toString().equals(campoInstitucion.getText().toString()) & !(campoDNI.getText().toString().equals("")) ){
            validarDatos();
            /*
            esCorreoValido(campoLocalidad.getText().toString());


            if(usuario.getNombre().equals("")){
                mensaje.setText("Falta completar: Nombre y Apellido");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                campoNombre.requestFocus();
            } else if(usuario.getMail().equals("")) {
                mensaje.setText("Falta completar: E-mail");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                campoLocalidad.requestFocus();
            } else  {
                mensaje.setTextColor(Color.GRAY);
                mensaje.setText("Está todo OK, "+usuario.getNombre());
                mensaje.setVisibility(View.VISIBLE);
                Intent intent = new Intent(this,Confirmacion.class);
                intent.putExtra("EXTRA_NOMBRE",usuario.getNombre());
                startActivity(intent);
            }
            */



        }else{
            mostrarMensaje(v, "Las contraseñas no coinsiden" );
        }

    }


    private void mostrarMensaje(View view, String mensaje){
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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

        if (!(password.length() == 6)){
            tilDNI.setError("Número de DNI inválido");
            return false;
        } else {
            return true;
        }

    }


    private boolean esEqualPass(String password, String repPassword){
        if(password.equals(repPassword)){
            return true;
        } else{
            tilIntitucion.setError("Las contraseñas no coinciden");
            return false;
        }
    }




    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilLocalidad.setError("Correo electrónico inválido");
            return false;
        } else {
            tilLocalidad.setError(null);
        }

        return true;
    }

    private boolean validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String DNI = tilDNI.getEditText().getText().toString();
        String Institucion = tilIntitucion.getEditText().getText().toString();
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


    private boolean esFull(){
        /*boolean aux [] = {estaCompleto(tilNombre),estaCompleto(tilDNI),estaCompleto(tilIntitucion),estaCompleto(tilLocalidad)};
        boolean b = estaCompleto(tilDNI;
        boolean c = estaCompleto(tilIntitucion);
        boolean d = estaCompleto(tilLocalidad);*/
        if(estaCompleto(tilNombre) && estaCompleto(tilDNI) && estaCompleto(tilIntitucion) && estaCompleto(tilLocalidad)){
            return true;
        } else {
            return false;
        }

    }





    private void guardarDatos(){
        usuario = new Usuario();
        usuario.setNombre(campoNombre.getText().toString());
        usuario.setContrasenia(campoDNI.getText().toString());
        usuario.setMail(campoLocalidad.getText().toString());
    }

    public void lanzarActivity(){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }








}

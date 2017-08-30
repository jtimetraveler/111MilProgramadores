package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import company.juancho.a111milprogramadores.tools.MailJob;

public class Formulario extends AppCompatActivity {

    private EditText campoNombre ;
    private EditText campoPassord;
    private EditText campoRepPassword;
    private EditText campoMail ;
    private Usuario usuario;

    private TextInputLayout tilNombre;
    private TextInputLayout tilPassword;
    private TextInputLayout tilRepPassword;
    private TextInputLayout tilMail;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoPassord = (EditText) findViewById(R.id.campo_password);
        campoRepPassword = (EditText) findViewById(R.id.campo_repPassword);
        campoMail = (EditText) findViewById(R.id.campo_mail);

        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilPassword = (TextInputLayout) findViewById(R.id.til_passord);
        tilRepPassword = (TextInputLayout) findViewById(R.id.til_repPassword);
        tilMail = (TextInputLayout) findViewById(R.id.til_mail);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarDatos();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

    public void guardarDatos(View v){

        if(campoPassord.getText().toString().equals(campoRepPassword.getText().toString()) & !(campoPassord.getText().toString().equals("")) ){
            validarDatos();
            /*
            esCorreoValido(campoMail.getText().toString());


            if(usuario.getNombre().equals("")){
                mensaje.setText("Falta completar: Nombre y Apellido");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                campoNombre.requestFocus();
            } else if(usuario.getMail().equals("")) {
                mensaje.setText("Falta completar: E-mail");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                campoMail.requestFocus();
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

    private boolean esPasswordValida(String password){

        if (password.length() < 6){
            tilPassword.setError("La contraseña debe tener al menos 6 dígitos");
            return false;
        } else {
            return true;
        }

    }


    private boolean esEqualPass(String password, String repPassword){
        if(password.equals(repPassword)){
            return true;
        } else{
            tilRepPassword.setError("Las contraseñas no coinciden");
            return false;
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

    private void validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        String repPass = tilRepPassword.getEditText().getText().toString();
        String correo = tilMail.getEditText().getText().toString();



        if(esFull()){

            boolean a = esNombreValido(nombre);
            boolean b = esPasswordValida(password);
            boolean c = esCorreoValido(correo);
            boolean d = esEqualPass(password, repPass);
            if (a &&b && d && c) {
                // OK, se pasa a la siguiente acción
                guardarDatos();
                sendEmail(campoMail.getText().toString());
                Toast.makeText(this, "Se ha realizado el registro", Toast.LENGTH_LONG).show();
                lanzarActivity();
            }

        }


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
        /*boolean aux [] = {estaCompleto(tilNombre),estaCompleto(tilPassword),estaCompleto(tilRepPassword),estaCompleto(tilMail)};
        boolean b = estaCompleto(tilPassword;
        boolean c = estaCompleto(tilRepPassword);
        boolean d = estaCompleto(tilMail);*/
        if(estaCompleto(tilNombre) && estaCompleto(tilPassword) && estaCompleto(tilRepPassword) && estaCompleto(tilMail)){
            return true;
        } else {
            return false;
        }

    }





    private void guardarDatos(){
        usuario = new Usuario();
        usuario.setNombre(campoNombre.getText().toString());
        usuario.setContrasenia(campoPassord.getText().toString());
        usuario.setMail(campoMail.getText().toString());
    }

    private void lanzarActivity(){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }







    protected void sendEmail(String mail) {
        new MailJob("prog111mil@hotmail.com", "curso111mil").execute(
                new MailJob.Mail("prog111mil@hotmail.com", mail, "Inscripción ", "La Inscripción se ha realizado exitosamente. Gracias, vuelva pronto.")
        );
    }
}

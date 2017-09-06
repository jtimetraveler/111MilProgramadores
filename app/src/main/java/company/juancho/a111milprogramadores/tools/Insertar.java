package company.juancho.a111milprogramadores.tools;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import company.juancho.a111milprogramadores.Usuario;

/**
 * Created by Desktop on 31/08/2017.
 */

//AsyncTask para insertar Personas
public class Insertar extends AsyncTask<String,String,String> {

    private Activity context;
    private String nombre, dni, telefono, email;
    private Usuario usuario;


    public Insertar(Activity context, String nombre, String dni, String telefono, String email){

        this.context=context;
        this.nombre = nombre;
        this.dni =  dni;
        this.telefono =  telefono;
        this.email =  email;
        doInBackground();
    }

    public Insertar (Activity context, Usuario usuario){
        this.usuario = usuario;
        this.context = context;
        this.nombre = usuario.getNombre();
        this.dni = usuario.getContrasenia();
        this.email = usuario.getMail();
        this.telefono = usuario.getContrasenia();
    }
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        if(insertar(nombre, dni, telefono, email ))
            context.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "Persona insertada con éxito", Toast.LENGTH_LONG).show();

                }
            });
        else
            context.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "Persona no insertada con éxito", Toast.LENGTH_LONG).show();
                }
            });
        return null;
    }


    private boolean insertar(String dni, String nombre, String email, String telefono){
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient=new DefaultHttpClient();
        httppost= new HttpPost("http://institutosiris.com/ws/wservice.php"); // Url del Servidor
        //Añadimos nuestros datos
        nameValuePairs = new ArrayList<NameValuePair>(3);
        nameValuePairs.add(new BasicNameValuePair("nomape",nombre.trim()));
        nameValuePairs.add(new BasicNameValuePair("pass",dni.trim()));
        nameValuePairs.add(new BasicNameValuePair("mail",email.trim()));
        //nameValuePairs.add(new BasicNameValuePair("email",email.trim()));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
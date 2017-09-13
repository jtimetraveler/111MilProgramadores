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
public class Insertar extends AsyncTask<String, String, String> {

    private Activity context;

    private Usuario usuario;


    public Insertar(Activity context, Usuario usuario) {
        this.usuario = usuario;
        this.context = context;
        //Toast.makeText(context, "Usuario creado: "+usuario.getWsID(), Toast.LENGTH_LONG).show();



    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        if (insertar(usuario))
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "Persona insertada con éxito", Toast.LENGTH_LONG).show();

                }
            });
        else
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "Persona no insertada con éxito", Toast.LENGTH_LONG).show();
                }
            });
        return null;
    }


    private boolean insertar(Usuario usuario) {
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://institutosiris.com/ws/wservice.php"); // Url del Servidor
        //Añadimos nuestros datos
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("nomape",usuario.getNombre()));
        nameValuePairs.add(new BasicNameValuePair("dni", String.valueOf(usuario.getDni())));
        nameValuePairs.add(new BasicNameValuePair("institucion",usuario.getInstitucion()));
        nameValuePairs.add(new BasicNameValuePair("publica", usuario.getWsPublica()));
        nameValuePairs.add(new BasicNameValuePair("localidad",usuario.getLocalidad()));
        nameValuePairs.add(new BasicNameValuePair("licencia", usuario.getWsLicencia()));
        nameValuePairs.add(new BasicNameValuePair("id", usuario.getWsID()));
        nameValuePairs.add(new BasicNameValuePair("cargaHoraria", usuario.getWsCargaHoracia()));
        nameValuePairs.add(new BasicNameValuePair("viatico", usuario.getViatico()));
        nameValuePairs.add(new BasicNameValuePair("transporte", usuario.getTransporte()));
        nameValuePairs.add(new BasicNameValuePair("gasto", usuario.getGasto()));


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
package company.juancho.a111milprogramadores.tools;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import company.juancho.a111milprogramadores.Formulario;

/**
 * Created by Desktop on 31/08/2017.
 */

//AsyncTask para insertar Personas
class Insertar extends AsyncTask<String,String,String> {

    private Activity context;

    Insertar(Activity context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        if(insertar())
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


    private boolean insertar(){
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient=new DefaultHttpClient();
        httppost= new HttpPost("http://192.168.0.11/picarcodigo/insert.php"); // Url del Servidor
        //Añadimos nuestros datos
        nameValuePairs = new ArrayList<NameValuePair>(4);
        nameValuePairs.add(new BasicNameValuePair("dni",dni.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("nombre",nombre.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("telefono",telefono.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("email",email.getText().toString().trim()));

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
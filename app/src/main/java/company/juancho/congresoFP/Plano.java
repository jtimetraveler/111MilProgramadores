package company.juancho.congresoFP;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.text.DecimalFormat;

import company.juancho.congresoFP.tools.Save;
import company.juancho.congresoFP.tools.TouchImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Plano extends AppCompatActivity {

    private TouchImageView imagen;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        df = new DecimalFormat("#.##");
        imagen = (TouchImageView) findViewById(R.id.idDeVuestraImagen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        imagen.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View arg0) {

                guardarImagen();
                return true;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    private void guardarImagen(){
        //convertir imagen a bitmap
        imagen.buildDrawingCache();
        Bitmap bmap = imagen.getDrawingCache();

        //guardar imagen
        Save savefile = new Save();
        savefile.SaveImage(this, bmap);
    }


    /*// cambiando el nombre de vuestra clase y importando lo necesario dandole a control+shift+O y te importara todo lo que la clase necesite
    public class NombreDeVuestraClase extends Activity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_single_touchimageview);

            df = new DecimalFormat("#.##");
            imagen = (TouchImageView) findViewById(R.id.idDeVuestraImagen);

        }
    }*/

}

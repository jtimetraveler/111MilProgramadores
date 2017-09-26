package company.juancho.congresoFP;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by juancho on 25/09/17.
 */

public class Principal extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/bbook.ttf") //Fuente agregada en los assets dentro de la carpeta fonts
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}

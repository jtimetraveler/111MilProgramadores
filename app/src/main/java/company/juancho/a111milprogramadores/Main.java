package company.juancho.a111milprogramadores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_formulario) {
            this.lanzarFormulario();
        } else if (id == R.id.nav_grilla) {
            this.lanzarGrilla();

        } else if (id == R.id.nav_eventos) {
            this.lanzarWeb();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void lanzarWeb(){
        Uri webpage = Uri.parse("https://www.rosariocultura.gob.ar/agenda");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    private void lanzarGrilla(){
        Intent intent = new Intent(this, Grilla.class);
        startActivity(intent);
    }

    private void lanzarFormulario(){
        Intent intent = new Intent(this, Formulario.class);
        startActivity(intent);
    }



    public void lanzarMapa(View v){
        double lon = -60.669510, lat = -32.929058;



        Uri uri = Uri.parse("geo:"+lat+","+"lon"+"?z=17&q=" +
                lat+","+lon+"(Rosario)");
        startActivity( new Intent(Intent.ACTION_VIEW, uri));
        /*
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+_lat+","+_ltg+"&daddr="+destino_lat+","+destino_ltg)); //o la direccion/consulta que quiera "http://maps.google.com/maps?q="+ myLatitude  +"," + myLongitude +"("+ labLocation + ")&iwloc=A&hl=es"
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);


        https://www.google.com.ar/search?q=metropolitano+rosario&npsic=0&rflfq=1&rlha=0&rllag=-32928944,-60670131,72&tbm=lcl&ved=0ahUKEwi26fjG-_LVAhWJIJAKHTyKAoIQtgMIJg&tbs=lrf:!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:2&rldoc=1#rlfi=hd:;si:8224927032178275017;mv:!1m3!1d9557.227714970175!2d-60.65440825!3d-32.937335049999994!2m3!1f0!2f0!3f0!3m2!1i427!2i274!4f13.1
        */
    }




}

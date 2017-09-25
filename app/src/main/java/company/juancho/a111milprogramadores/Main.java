package company.juancho.a111milprogramadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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

        switch (item.getItemId()){
            case R.id.nav_formulario:
                this.lanzarFormulario();
                break;
            case R.id.nav_grilla:
                this.lanzarGrilla();
                break;
            case R.id.nav_eventos:
                this.lanzarWebCultura();

                break;
            case R.id.nav_map:
                this.lanzarMapa();
                break;
            case R.id.nav_emr:
                this.lanzarWebEMR();
                break;
            case R.id.nav_tur:
                this.lanzarWebTUR();
                break;
            case R.id.nav_plano:
                this.lanzarPlano();
                break;




        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void lanzarWebCultura(){
        Uri webpage = Uri.parse("https://www.rosariocultura.gob.ar/agenda");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    private void lanzarGrilla(){
        Intent intent = new Intent(this, Grilla.class);
        startActivity(intent);
    }


    private void lanzarPlano(){
        Intent intent = new Intent(this, Plano.class);
        startActivity(intent);
    }


    private void lanzarFormulario(){
        Intent intent = new Intent(this, Formulario.class);
        startActivity(intent);
    }


    private void lanzarNewActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }


    public void lanzarMapa(){
        double lon = -60.669510, lat = -32.929058;



        Uri uri = Uri.parse("geo:"+lat+","+"lon"+"?z=17&q=" +
                lat+","+lon+"(Rosario)");
        startActivity( new Intent(Intent.ACTION_VIEW, uri));

    }

    private void lanzarWebEMR(){
        Uri webpage = Uri.parse("http://www.etr.gov.ar/info_movete.php");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    private void lanzarWebTUR(){
        Uri webpage = Uri.parse("http://www.rosario.tur.ar/es/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }



}

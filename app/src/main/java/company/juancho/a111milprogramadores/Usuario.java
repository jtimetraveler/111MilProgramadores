package company.juancho.a111milprogramadores;

import java.util.ArrayList;

/**
 * Created by juancho on 11/08/17.
 */

public class Usuario {

    //region Parámetros
    private String nombre, institucion, localidad;

    private int dni ;
    private boolean licencia, publica;

    private String wsPublica ="", wsCargaHoracia="", wsID="";

    private ArrayList<Licencia> listaLicencias = new ArrayList<Licencia>();
    //endregion

    //private String viaticos, traslado;

    //region Constructores

    public Usuario() {
    }

    public Usuario(String nombre, String institucion, String localidad, int dni) {
        this.nombre = nombre;
        this.institucion = institucion;
        this.localidad = localidad;
        this.dni = dni;

    }



    //endregion


    //region Getters and Setters


    public ArrayList<Licencia> getListaLicencias() {
        return listaLicencias;
    }

    public void setListaLicencias(ArrayList<Licencia> listaLicencias) {
        this.listaLicencias = listaLicencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }


    public boolean isLicencia() {
        return licencia;
    }

    public void setLicencia(boolean licencia) {
        this.licencia = licencia;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public String getWsPublica() {
        for(int i=0;i<listaLicencias.size();i++){
            wsPublica += "-" + listaLicencias.get(i).getPublica();
        }
        return wsPublica;
    }

    public String getWsCargaHoracia() {
        for(int i=0;i<listaLicencias.size();i++){
            wsCargaHoracia += "-" + listaLicencias.get(i).getCargaHoraria();
        }

        return wsCargaHoracia;
    }

    public String getWsID() {
        for(int i=0;i<listaLicencias.size();i++){
            wsID += "-" + listaLicencias.get(i).getID();
        }
        return wsID;
    }

    public String getWsLicencia(){
        if(listaLicencias.size()==0){
            return "No";
        } else {
            return "Si";
        }
    }


    /*
    public String getViaticos() {
        return viaticos;
    }

    public void setViaticos(String viaticos) {
        this.viaticos = viaticos;
    }

    public String getTraslado() {
        return traslado;
    }

    public void setTraslado(String traslado) {
        this.traslado = traslado;
    }
    */

    //endregion
}

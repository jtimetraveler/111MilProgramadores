package company.juancho.a111milprogramadores;

import java.util.ArrayList;

/**
 * Created by juancho on 11/08/17.
 */

public class Usuario {

    //region Parámetros
    private String nombre, apellido, institucion, localidad;

    private int dni ;
    private boolean licencia, publica, viatico=false;

    private String gasto = "", transporte ="";

    private String wsPublica ="", wsCargaHoracia="", wsID="";

    private String mail, telefono;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public boolean isViatico() {
        return viatico;
    }

    public void setViatico(boolean viatico) {
        this.viatico = viatico;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }




    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public  String getViatico(){
        if(viatico){
            return "Si";
        } else {
            return "No";
        }
    }

    public String getTransporte(){
        return transporte;
    }

    public String getWsPublica() {
        if(!(listaLicencias.size()==0)) {
            wsPublica = listaLicencias.get(0).getPublica();
            for (int i = 1; i < listaLicencias.size(); i++) {
                wsPublica += "-" + listaLicencias.get(i).getPublica();
            }
        } else if (publica){
            wsPublica = "Pública";
        } else {
            wsPublica = "Privada";
        }
        return wsPublica;
    }

    public String getWsCargaHoracia() {
        if(!(listaLicencias.size()==0)) {
            wsCargaHoracia = String.valueOf(listaLicencias.get(0).getCargaHoraria());
            for (int i = 1; i < listaLicencias.size(); i++) {
                wsCargaHoracia += "-" + listaLicencias.get(i).getCargaHoraria();
            }

        }
        return wsCargaHoracia;
    }

    public String getWsID() {
        if(!(listaLicencias.size()==0)) {
            wsID = String.valueOf(listaLicencias.get(0).getID());

            for (int i = 1; i < listaLicencias.size(); i++) {
                wsID += "-" + listaLicencias.get(i).getID();
            }
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






    //endregion
}

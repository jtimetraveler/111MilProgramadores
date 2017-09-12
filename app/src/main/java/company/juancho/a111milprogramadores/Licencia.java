package company.juancho.a111milprogramadores;

/**
 * Created by juancho on 11/09/17.
 */

public class Licencia {

    //region Parametros
    //String institucion, localidad;
    int ID, cargaHoraria;
    boolean publica; // true: la institucion en pública, false la institucion es privada

    //endregion


    //region Contructores
    public Licencia() {
    }

    /*public Licencia(String institucion, String localidad) {
        this.institucion = institucion;
        this.localidad = localidad;
    }*/

    public Licencia(String institucion, int ID, int cargaHoraria, boolean publica) {
        //this.institucion = institucion;
        this.ID = ID;
        this.cargaHoraria = cargaHoraria;
        this.publica = publica;
    }
    //endregion

    //region Getters and Setters
    /*public String getInstitucion() {
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
*/
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public String getPublica(){
        if(publica){
            return "Pública";
        } else {
            return "Privada";
        }

    }

    //endregion
}

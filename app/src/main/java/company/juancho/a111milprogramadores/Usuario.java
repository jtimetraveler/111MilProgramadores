package company.juancho.a111milprogramadores;

/**
 * Created by juancho on 11/08/17.
 */

public class Usuario {

    private String nombre, institucion, localidad;

    private int dni, fechaDesde, fechaHasta, idLicencia;
    private boolean esLicencia, esPublica;
    private String viaticos, traslado;

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

    public int getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(int fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public int getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(int fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public boolean isEsLicencia() {
        return esLicencia;
    }

    public void setEsLicencia(boolean esLicencia) {
        this.esLicencia = esLicencia;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

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
}

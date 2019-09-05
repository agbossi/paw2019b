package ar.edu.itba.paw.model;


public class Doctor {
    private String nombre;
    private String especialidad;
    private String locacion;
    private long matricula;

    public Doctor(){
    }
    public Doctor(String nombre, String especialidad, String locacion, long matricula){
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.locacion = locacion;
        this.matricula = matricula;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }
}

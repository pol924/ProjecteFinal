package cat.copernic.projectefinal;

import java.io.Serializable;

/**
 * Created by Francisco on 19/05/2015.
 */
public class Usuario implements Serializable{



    String nombre;
    String contraseña;
    boolean modificar;
    boolean crear;
    boolean persianaGaraje;
    boolean calefaccion;
    boolean aireAcondicionado;


    public Usuario(String nombre, String contraseña, boolean modificar, boolean crear, boolean persianaGaraje, boolean calefaccion, boolean aireAcondicionado) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.modificar = modificar;
        this.crear = crear;
        this.persianaGaraje = persianaGaraje;
        this.calefaccion = calefaccion;
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isCrear() {
        return crear;
    }

    public void setCrear(boolean crear) {
        this.crear = crear;
    }

    public boolean isPersianaGaraje() {
        return persianaGaraje;
    }

    public void setPersianaGaraje(boolean persianaGaraje) {
        this.persianaGaraje = persianaGaraje;
    }



    public boolean isCalefaccion() {
        return calefaccion;
    }

    public void setCalefaccion(boolean calefaccion) {
        this.calefaccion = calefaccion;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contraseÃ±a='" + contraseña + '\'' +
                ", modificar=" + modificar +
                ", crear=" + crear +
                ", persianaGaraje=" + persianaGaraje +
                ", calefaccion=" + calefaccion +
                ", aireAcondicionado=" + aireAcondicionado +
                '}';
    }
}

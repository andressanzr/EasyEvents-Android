package es.easyevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Evento  implements Serializable {
    @SerializedName("codigo")
    @Expose
    private int codigo;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("anfitrion")
    @Expose
    private String anfitrion;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora")
    @Expose
    private String hora;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    public Evento() {
    }

    public Evento(int codigo, String imagen, String titulo, String descripcion, String anfitrion, String fecha, String hora, String direccion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anfitrion = anfitrion;
        this.imagen = imagen;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(String anfitrion) {
        this.anfitrion = anfitrion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    @Override
    public String toString() {
        return  "codigo: " + codigo +
                ", imagen: " + imagen + ", titulo: " + titulo+ ", descripcion: " + descripcion + ", anfitrion: " + anfitrion + ", fecha: " + fecha + ", hora: " + hora +
                ", direccion: " + direccion;
    }
}

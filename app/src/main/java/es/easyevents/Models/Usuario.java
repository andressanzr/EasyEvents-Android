package es.easyevents.Models;

public class Usuario {
    private static String name;
   // private String name;
    private int phone;
    private static String email;
    private static String password;
    private String imagen;

    public Usuario() {
    }

    public Usuario(String name, int phone, String email, String password, String imagen) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.imagen = imagen;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

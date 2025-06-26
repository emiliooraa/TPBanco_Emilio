package Logica;

public abstract class Usuario {
    private String nombre;
    private String mail;
    private String contrasenia;

    public Usuario(String nombre, String mail, String contrasenia) {
        this.nombre = nombre;
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public Usuario(String mail, String contrasenia) {
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail != null && !mail.trim().isEmpty()) {
            this.mail = mail;
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void Login(String mail, String contrasenia) {

    }

    public void Menu() {

    }

}

package Logica;

import javax.swing.JOptionPane;

import Extends.OpcionesAdmin;

public class Admin extends Usuario {
    private String sector;

    public Admin(String nombre, String mail, String contrasenia, String sector) {
        super(nombre, mail, contrasenia);
        this.sector = sector;
    }

    public Admin(String mail, String contrasenia) {
        super(mail, contrasenia);
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public void Login(String mail, String contrasenia) {
        if (mail == null || mail.trim().isEmpty())
            return;
        if (contrasenia == null || contrasenia.trim().isEmpty())
            return;
        if (this.getMail().equals(this.getMail()) && this.getContrasenia().equals(this.getContrasenia()))
            this.Menu(); else {
                JOptionPane.showMessageDialog(null,"Mail o contraseña incorrecto/s, vuelva a intentarlo","Error!",1);
            }
        {
        }

    }

    @Override
    public void Menu() {
        int algo1 = JOptionPane.showOptionDialog(null, "Elija una de las siguientes opciones", "Menu Admin", 0, 0,
                null, OpcionesAdmin.values(), OpcionesAdmin.values());
    }
    //Ver_Clientes, Eliminar_Cliente, Agregar_Cliente, Ver_Movimientos_de_una_Cuenta, Salir
    public void VerClientes () {

    }
}

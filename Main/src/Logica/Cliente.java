package Logica;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import Extends.OpcionesCliente;

public class Cliente extends Usuario {
    static LinkedList<Cliente> clientes = new LinkedList<Cliente>();
    private int nro;
    private static int indice = 0;

    public Cliente(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
        indice++;
        this.nro = indice;
    }

    public Cliente(String mail, String contrasenia) {
        super(mail, contrasenia);
        indice++;
        this.nro = indice;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }


    @Override
    public void Login(String mail, String contrasenia) {
        if (mail == null || mail.trim().isEmpty())
            return;
        if (contrasenia == null || contrasenia.trim().isEmpty())
            return;

        for (Cliente cliente : clientes) {
            if (cliente.getMail().equals(mail) && cliente.getContrasenia().equals(contrasenia)) {
                cliente.Menu();
               
            }else {
                JOptionPane.showMessageDialog(null,"Mail o contraseña incorrecto/s, vuelva a intentarlo","Error!",1);
            }
        }

    }

    @Override
    public void Menu() {
        int algo2 = JOptionPane.showOptionDialog(null, "Elija una de las siguientes opciones", "Menu cliente", 0, 0,
                null, OpcionesCliente.values(), OpcionesCliente.values());
    }
    public void Registrar (String mail, String contrasenia){
        JOptionPane.showMessageDialog(null, "Registro exitoso");
    }
}

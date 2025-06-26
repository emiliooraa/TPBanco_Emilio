package Interfaz;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extends.LoginCliente;
import Extends.Rol;
import Logica.Admin;
import Logica.Cliente;
import Logica.Usuario;

public class Main {
    public static void main(String[] args) throws Exception {
        Admin yo = new Admin("admin@gmail.com", "admin");
        int opcion;
        String mail, contrasenia;
        do {
            opcion = JOptionPane.showOptionDialog(null, "Elija el rol", "Bienvenido al Banco", 0,
                    JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/img/banco.png")), Rol.values(),
                    Rol.values());

            switch (opcion) {
                case 0: //Admin
                    mail = (String) JOptionPane.showInputDialog(null,"Ingrese mail","Login Admin",0, new ImageIcon(Main.class.getResource("/img/admin.png")),null,null);
                    contrasenia = (String) JOptionPane.showInputDialog(null,"Ingrese contrasenia","Login Admin",0, new ImageIcon(Main.class.getResource("/img/admin.png")),null,null);
                    yo.Login(mail, contrasenia);
                    break;

                case 1: //Cliente
                    int opcCliente;
                    
                    do {
                        opcCliente = JOptionPane.showOptionDialog(null, "Iniciar cuenta/ Registrarse", "Bienvenido Cliente", 0,0,null,LoginCliente.values(), LoginCliente.values());
                        switch (opcCliente) {
                            case 0: //Login
                            mail = (String) JOptionPane.showInputDialog(null,"Ingrese mail","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            contrasenia = (String) JOptionPane.showInputDialog(null,"Ingrese contrasenia","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            Cliente.Login(mail, contrasenia);
                            break;
                        
                            case 1: //Registrar
                            mail = (String) JOptionPane.showInputDialog(null,"Ingrese mail","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            contrasenia = (String) JOptionPane.showInputDialog(null,"Ingrese contrasenia","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            Cliente nuevo1 = new Cliente(mail, contrasenia);
                            
                            break;
                        }

                    } while (opcCliente!=2);
                    break;

                default:
                JOptionPane.showMessageDialog(null,"Nos vemos!","Adiós, ten piedad Ghami",JOptionPane.PLAIN_MESSAGE,new ImageIcon(Main.class.getResource("/img/adios.png")));
                    break;
            }

        } while (opcion != 2);
    }
}

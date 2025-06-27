package Interfaz;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Enum.LoginCliente;
import Enum.Rol;
import Logica.Admin;
import Logica.Cliente;


public class Main {
    public static void main(String[] args) throws Exception {
        /*Admin:
        mail: admin@gmail.com    --> Esta en la linea 79 de Admin.java
        contraseña: admin
        */
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
                    Admin admin = new Admin(mail,contrasenia);
                    admin.Login(mail, contrasenia);
                    break;

                case 1: //Cliente
                    do {
                        opcion = JOptionPane.showOptionDialog(null, "Login / Registrarse", "Bienvenido Cliente", opcion, opcion, new ImageIcon(Main.class.getResource("/img/cliente.png")), LoginCliente.values(), LoginCliente.values());
                        switch (opcion) {
                            case 0: //Login
                            mail = (String) JOptionPane.showInputDialog(null,"Ingrese mail","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            contrasenia = (String) JOptionPane.showInputDialog(null,"Ingrese contrasenia","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            Cliente nuevo = new Cliente(mail,contrasenia);
                            nuevo.Login(mail, contrasenia);
                            break;
                        
                            case 1: //Registrar
                            
					        mail = (String) JOptionPane.showInputDialog(null,"Ingrese mail","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            contrasenia = (String) JOptionPane.showInputDialog(null,"Ingrese contrasenia","Login Cliente",0, new ImageIcon(Main.class.getResource("/img/cliente.png")),null,null);
                            Cliente nuevoCliente = new Cliente(mail, contrasenia);
                            Cliente.getClientes().add(nuevoCliente);
                            JOptionPane.showMessageDialog(null, "Registro con exito. Ya puede iniciar sesión.");
                            break;
                        }
                    } while (opcion!=2);
                    break;

                default:
                JOptionPane.showMessageDialog(null,"Gracias por usar el sistema!","Adios, ten piedad Ghami",JOptionPane.PLAIN_MESSAGE,new ImageIcon(Main.class.getResource("/img/adios.png")));
                    break;
            }

        } while (opcion != 2);
    }
}

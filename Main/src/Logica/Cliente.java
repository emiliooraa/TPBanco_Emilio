package Logica;

import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Enum.OpcionesCliente;
import Enum.TipoCuenta;
import Interfaz.Main;

public class Cliente extends Usuario {
    
    static LinkedList<Cliente> clientes = new LinkedList<Cliente>(); 
    
    public static void setClientes(LinkedList<Cliente> clientes) {
        Cliente.clientes = clientes;
    }
    
    public static LinkedList<Cliente> getClientes() {
        return clientes;
    }
    
    private int nro;
    private static int indice = 0;
    
    private LinkedList<Cuenta> cuentas = new LinkedList<>(); 

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
    
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
    
    public LinkedList<Cuenta> getCuentas() {
        return cuentas;
    }

    @Override
    public void Login(String mail, String contrasenia) {
        while (mail == null || mail.trim().isEmpty()) {
            mail = (String) JOptionPane.showInputDialog(null, "El correo no puede estar vacio. Ingrese mail", "Login Cliente", 0, new ImageIcon(Main.class.getResource("/img/cliente.png")), null, null);
            if (mail == null) {
                return; 
            }
        }
        while (contrasenia == null || contrasenia.trim().isEmpty()) {
            contrasenia = (String) JOptionPane.showInputDialog(null, "El campo de contraseña no puede estar vacio", "Login Cliente", 0, new ImageIcon(Main.class.getResource("/img/cliente.png")), null, null);
            if (contrasenia == null) {
                return;
            }
        }
        boolean loginExitoso = false;
        for (Cliente cliente : clientes) {
            if (cliente.getMail().equals(mail) && cliente.getContrasenia().equals(contrasenia)) {
                cliente.Menu();
                loginExitoso = true;
                break; 
            }
        }
        if (!loginExitoso) {
            JOptionPane.showMessageDialog(null, "Mail o contraseña incorrecto/s. Vuelva a intentarlo.", "Error de inicio de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void Menu() {
        int opcionSeleccionada = 0;
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una de las siguientes opciones", "Menu Cliente", 0, 0, null, OpcionesCliente.values(), OpcionesCliente.values());
            
            if (opcionSeleccionada == JOptionPane.CLOSED_OPTION) {
                break;
            }
            
            switch (OpcionesCliente.values()[opcionSeleccionada]) {
                case Ver_Movimientos:
                    this.verMovimientos();
                    break;
                case Retirar:
                    this.realizarRetiro();
                    break;
                case Ingresar_Plata:
                    this.ingresarDinero();
                    break;
                case Crear_Cuenta:
                    this.crearCuenta();
                    break;
                case Salir:
                    JOptionPane.showMessageDialog(null, "Cerrando sesión. Hasta pronto!");
                    break;
                default:
                    break;
            }
        } while (OpcionesCliente.values()[opcionSeleccionada] != OpcionesCliente.Salir);
    }
    
    private void verMovimientos() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes cuentas asociadas. Cree una cuenta");
            return;
        }
        
        Cuenta cuentaSeleccionada = (Cuenta) JOptionPane.showInputDialog(
            null, 
            "Seleccione la cuenta para ver los movimientos:",
            "Ver Movimientos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            cuentas.toArray(), 
            cuentas.get(0)
        );

        if (cuentaSeleccionada != null) {
            String historial = "Historial de movimientos de la cuenta " + cuentaSeleccionada.getNroCuenta() + ":\n\n";
            
            if (cuentaSeleccionada.getHistorialTransacciones().isEmpty()) {
                historial += "No hay movimientos registrados.";
            } else {
                for (Transaccion transaccion : cuentaSeleccionada.getHistorialTransacciones()) {
                    historial += transaccion.getFecha() + " | " +
                                 transaccion.getTipo() + ": $" + transaccion.getMonto() +
                                 " (" + transaccion.getDetalle() + ")\n";
                }
            }
            JOptionPane.showMessageDialog(null, historial, "Historial de Movimientos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void realizarRetiro() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes cuentas asociadas. Cree una cuenta primero");
            return;
        }
        
        Cuenta cuentaSeleccionada = (Cuenta) JOptionPane.showInputDialog(
            null, 
            "Seleccione la cuenta para retirar:",
            "Retirar",
            JOptionPane.QUESTION_MESSAGE,
            null,
            cuentas.toArray(), 
            cuentas.get(0)
        );

        if (cuentaSeleccionada != null) {
            String montoStr = JOptionPane.showInputDialog("Ingrese el monto a retirar:");
            if (montoStr != null && !montoStr.trim().isEmpty()) {
                try {
                    double monto = Double.parseDouble(montoStr);
                    boolean exito = cuentaSeleccionada.retirar(monto); 
                    if (exito) {
                         JOptionPane.showMessageDialog(null, "Retiro de $" + monto + " realizado con éxito. Saldo actual: $" + cuentaSeleccionada.getSaldo());
                    } else {
                         JOptionPane.showMessageDialog(null, "Error: Saldo insuficiente o monto inválido.", "Error de Retiro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Monto inválido. Ingrese un número válido.");
                }
            }
        }
    }
    
    private void ingresarDinero() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes cuentas asociadas. Primero cree una cuenta!");
            return;
        }
        
        Cuenta cuentaSeleccionada = (Cuenta) JOptionPane.showInputDialog(
            null, 
            "Seleccione la cuenta para ingresar dinero:",
            "Ingresar Dinero",
            JOptionPane.QUESTION_MESSAGE,
            null,
            cuentas.toArray(), 
            cuentas.get(0)
        );

        if (cuentaSeleccionada != null) {
            String montoStr = JOptionPane.showInputDialog("Ingrese el monto a depositar:");
            if (montoStr != null && !montoStr.trim().isEmpty()) {
                try {
                    double monto = Double.parseDouble(montoStr);
                    if (monto <= 0) {
                        JOptionPane.showMessageDialog(null, "El monto a depositar debe ser un número positivo.", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
                    cuentaSeleccionada.depositar(monto);
                    JOptionPane.showMessageDialog(null, "Depósito de $" + monto + " realizado con exito. Saldo actual: $" + cuentaSeleccionada.getSaldo());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Monto invalido. Ingrese un numero valido.");
                }
            }
        }
    }

    
    private void crearCuenta() {
        TipoCuenta tipoSeleccionado = (TipoCuenta) JOptionPane.showInputDialog(
            null,
            "Seleccione el tipo de cuenta:",
            "Crear Cuenta",
            JOptionPane.QUESTION_MESSAGE,
            null,
            TipoCuenta.values(), 
            TipoCuenta.CAJA_DE_AHORRO
        );
        
        if (tipoSeleccionado == null) return; 

        String saldoInicialStr = JOptionPane.showInputDialog("Ingrese el saldo inicial para la nueva cuenta:");
        
        if (saldoInicialStr == null || saldoInicialStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingresó un saldo inicial. La cuenta no se creará.");
            return;
        }
        
        try {
            double saldoInicial = Double.parseDouble(saldoInicialStr);
            if (saldoInicial < 0) {
                JOptionPane.showMessageDialog(null, "El saldo inicial no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
         
            Cuenta nuevaCuenta = new Cuenta(this, tipoSeleccionado);
            this.agregarCuenta(nuevaCuenta);
            nuevaCuenta.depositar(saldoInicial);
            
            JOptionPane.showMessageDialog(null, "Cuenta " + nuevaCuenta.getNroCuenta() + " (" + tipoSeleccionado + ") creada con éxito. Saldo inicial: $" + saldoInicial);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto inválido. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @Override
    public String toString() {
        return "Cliente: " + this.getNombre() + " (Mail: " + this.getMail() + ")";
    }
}
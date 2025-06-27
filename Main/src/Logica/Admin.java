package Logica;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Enum.OpcionesAdmin;
import Interfaz.Main;

public class Admin extends Usuario {
    private String sector;

    public Admin(String nombre, String mail, String contrasenia) {
        super(nombre, mail, contrasenia);
    }
    
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
    public void Menu() {
        int opcionSeleccionada = 0;
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una de las siguientes opciones", "Menu de Administrador", 0, 0, null, OpcionesAdmin.values(), OpcionesAdmin.values()[0]);

            if (opcionSeleccionada == JOptionPane.CLOSED_OPTION) {
                break;
            }

            switch (OpcionesAdmin.values()[opcionSeleccionada]) {
                case Agregar_Cliente:
                    this.agregarCliente();
                    break;
                case Eliminar_Cliente:
                    this.eliminarCliente();
                    break;
                case Ver_Clientes:
                    this.verClientes();
                    break;
                case Ver_Movimientos_de_una_Cuenta:
                    this.verMovimientosDeCuenta();
                    break;
                case Salir:
                    JOptionPane.showMessageDialog(null, "Cerrando sesion de administrador.");
                    break;
                default:
                    break;
            }
        } while (OpcionesAdmin.values()[opcionSeleccionada] != OpcionesAdmin.Salir);
    }
    
    @Override
    public void Login(String mail, String contrasenia) {
        while (mail == null || mail.trim().isEmpty()) {
            mail = (String) JOptionPane.showInputDialog(null, "El correo no puede estar vacio. Ingrese mail", "Login Admin", 0, new ImageIcon(Main.class.getResource("/img/admin.png")), null, null);
            if (mail == null) {
                return; 
            }
        }
        while (contrasenia == null || contrasenia.trim().isEmpty()) {
            contrasenia = (String) JOptionPane.showInputDialog(null, "El campo de contraseña no puede estar vacio", "Login Admin", 0, new ImageIcon(Main.class.getResource("/img/admin.png")), null, null);
            if (contrasenia == null) {
                return;
            }
        }
        
        if (this.getMail().equals("admin@gmail.com") && this.getContrasenia().equals("admin")) {
            this.Menu(); 
        } else {
            JOptionPane.showMessageDialog(null, "Mail o contraseña incorrecto/s. Vuelva a intentarlo.", "Error de inicio de sesion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String mail = JOptionPane.showInputDialog("Ingrese el correo del cliente:");
        if (mail == null || mail.trim().isEmpty()) return;

        String contrasenia = JOptionPane.showInputDialog("Ingrese la contraseña del cliente:");
        if (contrasenia == null || contrasenia.trim().isEmpty()) return;

        Cliente nuevoCliente = new Cliente(nombre, mail, contrasenia);
        Cliente.getClientes().add(nuevoCliente);

        int crearCuenta = JOptionPane.showConfirmDialog(null, "Desea crear una cuenta para este cliente?", "Crear Cuenta", JOptionPane.YES_NO_OPTION);
        if (crearCuenta == JOptionPane.YES_OPTION) {
            this.crearCuentaParaCliente(nuevoCliente);
        }

        JOptionPane.showMessageDialog(null, "Cliente " + nombre + " agregado con exito.");
    }

    private void crearCuentaParaCliente(Cliente cliente) {
        String saldoInicialStr = JOptionPane.showInputDialog("Ingrese el saldo inicial de la cuenta para " + cliente.getNombre() + ":");
        if (saldoInicialStr == null || saldoInicialStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingresó saldo. La cuenta se creara con saldo cero.");
            saldoInicialStr = "0.0";
        }

        try {
            double saldoInicial = Double.parseDouble(saldoInicialStr);
            Cuenta nuevaCuenta = new Cuenta(cliente);
            cliente.agregarCuenta(nuevaCuenta);
            nuevaCuenta.depositar(saldoInicial);
            JOptionPane.showMessageDialog(null, "Cuenta " + nuevaCuenta.getNroCuenta() + " creada con exito para " + cliente.getNombre() + " con saldo inicial de $" + saldoInicial + ".");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto inválido. La cuenta no se pudo crear.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        if (Cliente.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados para eliminar.");
            return;
        }

        Cliente clienteAEliminar = (Cliente) JOptionPane.showInputDialog(null, "Seleccione el cliente a eliminar:", "Eliminar Cliente", JOptionPane.QUESTION_MESSAGE, null, Cliente.getClientes().toArray(), Cliente.getClientes().get(0));

        if (clienteAEliminar != null) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar a " + clienteAEliminar.getNombre() + "?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                Cliente.getClientes().remove(clienteAEliminar);
                JOptionPane.showMessageDialog(null, "Cliente " + clienteAEliminar.getNombre() + " eliminado con exito.");
            }
        }
    }

    private void verClientes() {
        if (Cliente.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }

        String listaClientes = "--- Lista de Clientes ---\n\n";
        for (Cliente cliente : Cliente.getClientes()) {
            listaClientes += "Nro: " + cliente.getNro() + "\n";
            listaClientes += "Nombre: " + cliente.getNombre() + "\n";
            listaClientes += "Mail: " + cliente.getMail() + "\n";
            listaClientes += "Cuentas: " + (cliente.getCuentas().isEmpty() ? "Ninguna" : cliente.getCuentas().size()) + "\n";
            listaClientes += "---------------------------\n";
        }
        JOptionPane.showMessageDialog(null, listaClientes, "Ver Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verMovimientosDeCuenta() {
        if (Cliente.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados para ver movimientos.");
            return;
        }

        Cliente clienteSeleccionado = (Cliente) JOptionPane.showInputDialog(null, "Seleccione el cliente:", "Ver Movimientos", JOptionPane.QUESTION_MESSAGE, null, Cliente.getClientes().toArray(), Cliente.getClientes().get(0));

        if (clienteSeleccionado != null) {
            if (clienteSeleccionado.getCuentas().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El cliente no tiene cuentas asociadas.");
                return;
            }

            Cuenta cuentaSeleccionada = (Cuenta) JOptionPane.showInputDialog(null, "Seleccione la cuenta para ver movimientos:", "Ver Movimientos", JOptionPane.QUESTION_MESSAGE, null, clienteSeleccionado.getCuentas().toArray(), clienteSeleccionado.getCuentas().get(0));

            if (cuentaSeleccionada != null) {
                String historial = "Historial de la cuenta " + cuentaSeleccionada.getNroCuenta() + ":\n\n";
                if (cuentaSeleccionada.getHistorialTransacciones().isEmpty()) {
                    historial += "No hay movimientos registrados en esta cuenta.";
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
    }
}
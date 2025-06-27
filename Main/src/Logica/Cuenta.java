package Logica;

import java.util.LinkedList;
import Enum.TipoTransaccion;
import Enum.TipoCuenta; 

public class Cuenta {
    private int nroCuenta;
    private double saldo;
    private Cliente titular;
    private TipoCuenta tipo;
    private LinkedList<Transaccion> historialTransacciones;
    
    private static int contadorCuentas = 1000;

  
    public Cuenta(Cliente titular, TipoCuenta tipo) {
        this.nroCuenta = contadorCuentas++;
        this.saldo = 0.0;
        this.titular = titular;
        this.tipo = tipo; 
        this.historialTransacciones = new LinkedList<>();
    }
    
   
    public Cuenta(Cliente titular) {
        this.nroCuenta = contadorCuentas++;
        this.saldo = 0.0;
        this.titular = titular;
        this.tipo = TipoCuenta.CAJA_DE_AHORRO; 
        this.historialTransacciones = new LinkedList<>();
    }
    
    public long getNroCuenta() {
        return nroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
    
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            Transaccion transaccion = new Transaccion(TipoTransaccion.DEPOSITO, monto, "Depósito de dinero", this);
            this.historialTransacciones.add(transaccion);
        }
    }
    
    public boolean retirar(double monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            Transaccion transaccion = new Transaccion(TipoTransaccion.RETIRO, monto, "Retiro de dinero", this);
            this.historialTransacciones.add(transaccion);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean Transferir(Cuenta cuentaDestino, double monto) {
        if (this.retirar(monto)) {
            cuentaDestino.depositar(monto);
            Transaccion transaccion = new Transaccion(TipoTransaccion.TRANSFERENCIA, monto, "Transferencia a " + cuentaDestino.getTitular().getNombre(), this, cuentaDestino);
            this.historialTransacciones.add(transaccion);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Cuenta Nro: " + nroCuenta + " (" + tipo + ") - Saldo: $" + saldo;
    }
}
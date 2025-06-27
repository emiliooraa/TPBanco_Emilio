package Logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Enum.TipoTransaccion;

public class Transaccion {
    private String idTransaccion;
    private TipoTransaccion tipo;
    private double monto;
    private LocalDateTime fecha;
    private String detalle;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    
    private static int contadorTransacciones = 0;

    public Transaccion(TipoTransaccion tipo, double monto, String detalle, Cuenta cuentaOrigen) {
        contadorTransacciones++;
        this.idTransaccion = "TXN-" + contadorTransacciones; 
        
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = null;
    }

    public Transaccion(TipoTransaccion tipo, double monto, String detalle, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        contadorTransacciones++;
        this.idTransaccion = "TXN-" + contadorTransacciones;
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
        this.fecha = LocalDateTime.now();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = this.fecha.format(formatter);
        
        String info = fechaFormateada + " | " + this.tipo + ": $" + this.monto + " (" + this.detalle + ")";
        
        if (this.tipo == TipoTransaccion.TRANSFERENCIA && this.cuentaDestino != null) {
            info += " -> a Cuenta " + this.cuentaDestino.getNroCuenta();
        }
        
        return info;
    }
}
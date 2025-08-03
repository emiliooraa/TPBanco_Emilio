package Logica;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CuentaInversion {
    private double saldo;
    private ArrayList<Double> historialRendimiento;
    private ArrayList<Double> historialTasas;

    public CuentaInversion(double montoInicial) {
        this.saldo = montoInicial;
        this.historialRendimiento = new ArrayList<>();
        this.historialTasas = new ArrayList<>();
        historialRendimiento.add(montoInicial);
    }

    public void simularDia() {
        double tasa = generarTasaInteres();
        saldo = saldo * (1 + tasa);
        historialTasas.add(tasa);
        historialRendimiento.add(saldo);
    }

    private double generarTasaInteres() {
        return -0.05 + (0.10 * Math.random());
    }

    public void mostrarResumen() {
        String resumen = "Resumen de inversión:\n\n";

        for (int i = 1; i < historialRendimiento.size(); i++) {
            double saldoAnterior = historialRendimiento.get(i - 1);
            double tasa = historialTasas.get(i - 1);
            double saldoActual = historialRendimiento.get(i);
            String tipo = (tasa >= 0) ? "alcista" : "bajista";
            String signo = (tasa >= 0) ? "+" : "-";

            resumen += "Día " + i + ":\n";
            resumen += "Tasa de interés generada: " + String.format("%.2f", tasa) + " (" + tipo + ")\n";
            resumen += "Saldo al final del día será: $" + String.format("%.2f", saldoAnterior) +
                       " * (1 " + signo + " " + String.format("%.2f", Math.abs(tasa)) + ") = $" +
                       String.format("%.2f", saldoActual) + "\n\n";
        }

        JOptionPane.showMessageDialog(null, resumen, "Resumen de Inversión", JOptionPane.INFORMATION_MESSAGE);
    }
}

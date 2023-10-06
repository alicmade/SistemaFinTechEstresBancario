package org.example;

import java.util.ArrayList;

public class Cuenta {
    private double saldo;

    public Cuenta(int saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    public void retirar(int cantidad) {
        saldo -= cantidad;
    }
    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }
    ArrayList<String> lista = new ArrayList<String>();

    public synchronized void depositar(double cantidad) {
        String mensajeDepositar = "Depositar " + cantidad + " euros.\n";
        lista.add(mensajeDepositar);
        saldo += cantidad;
    }

    public synchronized void retirar(double cantidad) {
        if (saldo >= cantidad) {
            String mensajeRetirar = "Retirar " + cantidad + " euros. \n";
            lista.add(mensajeRetirar);
            saldo -= cantidad;
        } else {
            String mensajeErrorRetirar = "Saldo insuficiente para el retiro de " + cantidad + " euros.\n";
            lista.add(mensajeErrorRetirar);
        }
    }

    public synchronized double obtenerSaldo() {
        return saldo;
    }
}

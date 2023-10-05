package org.example;

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

    public synchronized void depositar(double cantidad) {
        System.out.println("Depositar " + cantidad + " euros.");
        saldo += cantidad;
    }

    public synchronized void retirar(double cantidad) {
        if (saldo >= cantidad) {
            System.out.println("Retirar " + cantidad + " euros.");
            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente para el retiro de " + cantidad + " euros.");
        }
    }

    public synchronized double obtenerSaldo() {
        return saldo;
    }
}

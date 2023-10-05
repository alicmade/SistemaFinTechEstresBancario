package org.example;

public class HiloCliente implements Runnable  {
    private Cuenta cuenta;
    private double cantidad;
    private boolean esDeposito;

    public HiloCliente(Cuenta cuenta, double cantidad, boolean esDeposito) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esDeposito = esDeposito;
    }

    @Override
    public void run() {
        if (esDeposito) {
            cuenta.depositar(cantidad);
        } else {
            cuenta.retirar(cantidad);
        }
    }
}

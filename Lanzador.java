package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Lanzador {
    public void lanzdor(String[] args) {
        Cuenta cuenta = new Cuenta(10000);

        // Crear hilos para depósitos
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Crear hilos para depósitos
        for (int i = 0; i < 400; i++) {
            executor.execute(new HiloCliente(cuenta, 100, true));
        }
        for (int i = 0; i < 200; i++) {
            executor.execute(new HiloCliente(cuenta, 50, true));
        }
        for (int i = 0; i < 600; i++) {
            executor.execute(new HiloCliente(cuenta, 20, true));
        }

        // Crear hilos para retiros
        for (int i = 0; i < 400; i++) {
            executor.execute(new HiloCliente(cuenta, 100, false));
        }
        for (int i = 0; i < 200; i++) {
            executor.execute(new HiloCliente(cuenta, 50, false));
        }
        for (int i = 0; i < 600; i++) {
            executor.execute(new HiloCliente(cuenta, 20, false));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double saldoFinal = cuenta.obtenerSaldo();
        if (saldoFinal == 10000) {
            System.out.println("La simulación se ha completado correctamente. Saldo final: " + saldoFinal + " euros.");
        } else {
            System.out.println("La simulación ha fallado. Saldo final: " + saldoFinal + " euros.");
        }
    }
}

package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Lanzador {
    public void lanzdor(String[] args) {

        String mensaje = "";
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
            mensaje =  "La simulación se ha completado correctamente. Saldo final: " + saldoFinal + " euros.";
        } else {
            mensaje ="La simulación ha fallado. Saldo final: " + saldoFinal + " euros.";
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registro.txt", true))) {
            writer.write(mensaje + "\n"+ "Resgistro de operaciones: \n"+ cuenta.lista.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

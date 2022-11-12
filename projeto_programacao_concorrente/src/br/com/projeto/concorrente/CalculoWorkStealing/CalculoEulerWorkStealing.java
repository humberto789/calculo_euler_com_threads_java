package br.com.projeto.concorrente.CalculoWorkStealing;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.projeto.concorrente.CalculoFatorialThread;

public class CalculoEulerWorkStealing {
	public static void main(String[] args) {
		EulerWorkStealingReader reader = new EulerWorkStealingReader();

		ExecutorService threadPoolExecutor = Executors.newWorkStealingPool();

		ArrayList<Future<Double>> resultadosThreads = new ArrayList<Future<Double>>();

		for (int i = 0; i < reader.getTermos(); i++) {
			Callable<Double> calculoFatorial = new CalculoFatorialThread(i);

			Future<Double> resultado = threadPoolExecutor.submit(calculoFatorial);
			resultadosThreads.add(resultado);
		}

		double numeroEuler = 0;

		try {
			for (Future<Double> resultadosUnitarios : resultadosThreads) {
				numeroEuler += resultadosUnitarios.get();

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Threads Ativas: " + Thread.activeCount() + " (Incluindo a main)");
		System.out.println("Número de Euler: " + numeroEuler);
	
		threadPoolExecutor.shutdown();
	}
}

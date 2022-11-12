package br.com.projeto.concorrente;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculoEulerWorkStealing {
	public static void main(String[] args) {
		int n = 100;

		ExecutorService threadPoolExecutor = Executors.newWorkStealingPool();

		ArrayList<Future<Double>> resultadosThreads = new ArrayList<Future<Double>>();

		for (int i = 0; i < n; i++) {
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

		System.out.println("Threads Ativas:" + Thread.activeCount());

		System.out.println("Número de Euler: " + numeroEuler);
		System.out.println("Quantidades de threads: " + resultadosThreads.size());

		threadPoolExecutor.isShutdown();
	}
}

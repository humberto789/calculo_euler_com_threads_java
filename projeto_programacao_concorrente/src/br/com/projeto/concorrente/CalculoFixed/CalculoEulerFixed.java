package br.com.projeto.concorrente.CalculoFixed;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import br.com.projeto.concorrente.CalculoFatorialThread;

public class CalculoEulerFixed {
	 public static void main(String[] args) {
		CalculoEulerFixedReader reader = new  CalculoEulerFixedReader();
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(reader.getNumThreads());
	
		ArrayList<Future<Double>> resultadosThreads = new ArrayList<Future<Double>>();
		
		for(int i = 0; i < reader.getTermos(); i++) {
			Callable<Double> calculoFatorial = new CalculoFatorialThread(i);
			
			Future<Double> resultado = threadPoolExecutor.submit(calculoFatorial);
			resultadosThreads.add(resultado);
		}
		
		double numeroEuler = 0;
		
		try {
			for(Future<Double> resultadosUnitarios : resultadosThreads) {
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

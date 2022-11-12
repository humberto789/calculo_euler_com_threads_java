package br.com.projeto.concorrente;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CalculoEulerFixed {
	 public static void main(String[] args) {
		 
		int n = 1000;
		int quantidade_thread = 50;
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(quantidade_thread);
	
		ArrayList<Future<Double>> resultadosThreads = new ArrayList<Future<Double>>();
		
		for(int i = 0; i < n; i++) {
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
		
		System.out.println("Número de Euler: " + numeroEuler);
		System.out.println("Quantidades de threads: " + resultadosThreads.size());
		
		threadPoolExecutor.shutdown();
	 }
}

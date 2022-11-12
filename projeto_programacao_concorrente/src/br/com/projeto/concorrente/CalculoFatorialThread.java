package br.com.projeto.concorrente;

import java.util.concurrent.Callable;

public class CalculoFatorialThread implements Callable<Double>{

	private int index;
	
	public CalculoFatorialThread(int index) {
		this.index = index;
	}
	
	@Override
	public Double call() throws Exception {
		
		double resultadoFatorial = 1;
		
		for(int i = 2; i <= index; i++) {
			resultadoFatorial *= i;
		}
		
		double calculo = 1 / resultadoFatorial;
		
		return calculo;
	}

}

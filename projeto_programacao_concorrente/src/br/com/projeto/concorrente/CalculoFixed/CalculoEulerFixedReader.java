package br.com.projeto.concorrente.CalculoFixed;

import br.com.projeto.concorrente.InputReader;

public class CalculoEulerFixedReader extends InputReader{
	int numThreads;
	
	public CalculoEulerFixedReader() {
		super();
		
		System.out.print("Insira o número de Threads: ");
		this.numThreads = super.scanner.nextInt();
	}
	
	public int getNumThreads() {
		return this.numThreads;
	}
}

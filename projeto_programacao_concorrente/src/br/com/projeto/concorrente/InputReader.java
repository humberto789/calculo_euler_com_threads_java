package br.com.projeto.concorrente;

import java.util.Scanner;

public abstract class InputReader {
	protected Scanner scanner;
	private int termos;
	
	protected InputReader(){
		System.out.print("Insira a quantidade de termos a ser calculados: ");
		this.scanner = new Scanner(System.in);
		this.termos = scanner.nextInt();
	}
	
	public int getTermos() {
		return this.termos;
	}
	
}

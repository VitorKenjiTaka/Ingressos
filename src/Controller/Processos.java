package Controller;

import java.util.concurrent.Semaphore;

public class Processos extends Thread{
	private int threadId;
	private Semaphore semaforo;
	private static int qnt_ing = 100;
	private static int vendidos = 0;
	
	
	public Processos(int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
	}

	public void run() {
		
		login();
		int ing = ingressos();
		compra(ing);
		validacao(ing);
	}

	private int ingressos() {
		int ingressos = (int) ((Math.random()* 4)+ 1);
		return ingressos;
	}
	
	private void login() {
		
		System.out.println("Pessoa #" + threadId + " efetuando login");
		int tempo = (int) ((Math.random()* 1951)+ 50);
		
		if (tempo > 1000) {
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Pessoa #"+ threadId +"Tempo limite de login excedido, timeout.");
			return;
		}
		else{
			
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Login efetuado com sucesso.");
		}
	}
	
	private void compra(int ing) {
		
		System.out.println("Pessoa #"+ threadId +" esta comprando "+ ing +" ingressos.");
		int tempo = (int) ((Math.random()* 2001)+ 1000);
		
		
		if (tempo > 2500) {
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Pessoa #"+ threadId +"Tempo limite de compra excedido, timeout.");
			return;
		}else {
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Pessoa #" +threadId+ " Comprou " +ing + " ingressos.");
		}
	}
	
	private void validacao(int ing) {
		int total = 100; 
		
		try {
			semaforo.acquire();
			vendidos =+ ing; 
			qnt_ing = qnt_ing - vendidos;
			
			System.out.println("Pessoa #"+threadId+": esta validando a compra.");
			
			if (total < ing) {
				
				System.out.println("Pessoa #"+threadId+": Sem locais disponiveis, cancelando compra.");
				return;
			}
			
			if(qnt_ing <= 0) {
				System.out.println("Pessoa #"+threadId+": Sem vagas diponiveis.");
				return;
			}else {
				System.out.println("Pessoa #"+threadId+": Validação completa.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
			System.out.println("numeros de ingressos restante: " + qnt_ing);
		}
	}
}
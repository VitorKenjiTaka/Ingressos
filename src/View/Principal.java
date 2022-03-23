package View;
import java.util.concurrent.Semaphore;

import Controller.*;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		for (int threadId = 0; threadId< 300 ; threadId++) {
			Thread thread = new Processos(threadId, semaforo);
			thread.start();
		}
	}
}
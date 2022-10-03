/* Algoritmos Y Programación III
 * 
 * Facultad de Ingeniería - Universidad de Buenos Aires
 * 
 * 2do cuatrimestre de 2022
 * 
 * Cátedra Corsi - Essaya - Maraggi
 * 
 * Ejemplo de Diseño Orientado a Objetos: hangman (juego del ahorcado)
 * 
 * */

package application;

import java.io.InputStream;
import java.util.Scanner;

import controller.HangmanController;
import model.HangmanEngine;
// import view.IHangmanDisplay;
import view.TextInterface;

public class MainHangman {

	public static void main(String[] args) {
		
		TextInterface textView = new TextInterface(System.out);
		HangmanEngine engine = new HangmanEngine(textView);
		
		Scanner scanner = new Scanner(System.in);
		
		HangmanController controller = new HangmanController(engine, scanner, textView);
		
		while (!controller.hasProgramToTerminate()) {
			controller.processUserInput();
		}		
	}
}

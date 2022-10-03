package view;

import java.io.PrintStream;

import view.IHangmanDisplay;


public class TextInterface implements IHangmanDisplay { // interfaz de aplicación (GUI/Web/Consola) >< interfaz de OOP >< interfaz de aplicación (API) 
	
	// note: example inspired by https://gist.github.com/SedaKunda/79e1d9ddc798aec3a366919f0c14a078
	
	private PrintStream printStream;
	
	public TextInterface(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public void goodbye() {
		this.printStream.println("Thank you for playing OOP Hangman! Goodbye.");
	}
	
	public void askForSecretWord() {
		this.printStream.println("Type your secret word (type 'quit' to exit... no, quit can't be your secret word!):");
	}
	
	public void printWelcome() {
		this.printStream.println("Welcome to Hangman. Try to guess the word without being hanged...");
		this.printStream.println("Type 'quit' to quit");
		this.printStream.println("Type any other word to set it as the secret word...");
	}
	
	public void congratulations() {
		this.printStream.println("Congratulations, you were not hanged...");
	}
	
	public void reportError(String message) {
		this.printStream.println(message);
	}
	
	private void updatePartialWord(String partialWord) { 
		
		// docuementación online! mucho muy importante, con OOP o sin OOP. Estándar de cada grupo de trabajo o proyecto
		
		// partialWord is expected to contain underscores for undiscovered letters and the already discovered letters into their position
		this.printStream.println();
		this.printStream.println();
		this.printStream.println("So far, the word is...");
		this.printStream.println(partialWord);
	}
	
	public void updateView(int lives, String partialWord) {
		if (lives == 7) {
			this.printStream.println("You are about to be hanged: guess the secret word!");
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
		}
		if (lives == 6) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println();
			this.printStream.println("___|___");
		}
		if (lives == 5) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("___|___");
		}
		if (lives == 4) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println("   ____________");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   | ");
			this.printStream.println("___|___");
		}
		if (lives == 3) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println("   ____________");
			this.printStream.println("   |          _|_");
			this.printStream.println("   |         /   \\");
			this.printStream.println("   |        |     |");
			this.printStream.println("   |         \\_ _/");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("   |");
			this.printStream.println("___|___");
		}
		if (lives == 2) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println("   ____________");
			this.printStream.println("   |          _|_");
			this.printStream.println("   |         /   \\");
			this.printStream.println("   |        |     |");
			this.printStream.println("   |         \\_ _/");
			this.printStream.println("   |           |");
			this.printStream.println("   |           |");
			this.printStream.println("   |");
			this.printStream.println("___|___");
		}
		if (lives == 1) {
			this.printStream.println("Wrong guess, try again");
			this.printStream.println("   ____________");
			this.printStream.println("   |          _|_");
			this.printStream.println("   |         /   \\");
			this.printStream.println("   |        |     |");
			this.printStream.println("   |         \\_ _/");
			this.printStream.println("   |           |");
			this.printStream.println("   |           |");
			this.printStream.println("   |          / \\ ");
			this.printStream.println("___|___      /   \\");
		}
		if (lives == 0) {
			this.printStream.println("GAME OVER!"); // acá ya sabemos que es "game over"... sin embargo, como vista dejamos que la iniciativa la mantenga el controlador/motor
			this.printStream.println("   ____________");
			this.printStream.println("   |          _|_");
			this.printStream.println("   |         /   \\");
			this.printStream.println("   |        |     |");
			this.printStream.println("   |         \\_ _/");
			this.printStream.println("   |          _|_");
			this.printStream.println("   |         / | \\");
			this.printStream.println("   |          / \\ ");
			this.printStream.println("___|___      /   \\");
		}
		
		this.updatePartialWord(partialWord);
	}
	
	public void gameOver(String secretWord) { // en esta llamada específica podemos pedir más datos, como por ejemplo cuál era la palabra secreta
		
		this.printStream.println("GAME OVER! The word was " + secretWord);
	}
}

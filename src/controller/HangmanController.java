package controller;

import model.HangmanEngine;
import view.IHangmanDisplay;

import java.util.*;  

public class HangmanController {
	
	private HangmanEngine hangmanEngine;
	
	private Scanner scanner;
	
	private IHangmanDisplay view;
	
	private boolean finishProgram;
	
	public HangmanController(HangmanEngine engine, Scanner scanner, IHangmanDisplay view){
		this.scanner = scanner;
		this.hangmanEngine = engine;
		this.view = view;
		this.finishProgram = false;
	}
	
	public void startGame() {
		// System.out.println("Type your secret word:"); // this IS in the view!
		this.view.askForSecretWord();
		String secretWord = this.scanner.nextLine();
		if (secretWord.equals("quit")) {
			this.finishProgram = true;
			this.view.goodbye();
		}
		else {
			this.hangmanEngine.startGame(secretWord);
		}		
	}
	
	public void processUserInput() {
		
		if (this.hangmanEngine.getGameState() == HangmanEngine.GameState.LOST || 
				this.hangmanEngine.getGameState() == HangmanEngine.GameState.WON ||
				this.hangmanEngine.getGameState() == HangmanEngine.GameState.UNINITIALIZED) {
			this.startGame();
		}
		else {
			String userInput = this.scanner.nextLine();
			
			if (userInput.equals("quit")) {
				this.hangmanEngine.stopGame();
				this.finishProgram = true;
				this.view.goodbye();
			}
			else {
				this.hangmanEngine.tryLetter(userInput);
			}
		}
	}
	
	public boolean hasProgramToTerminate() {
		return this.finishProgram;
	}

}

package model;

import view.IHangmanDisplay;

public class HangmanEngine { // valdría la pena definir una interfaz?
	
	// buena práctica: definir "lo más arriba posible" los atributos de "clase" (final, subtipos como enumerados, etc)
	
	public enum GameState { // es esto una clase? No, del todo... java dice que es un "tipo especial de clase", en realidad es un tipo de dato invariable
	  UNINITIALIZED,
	  STARTED,
	  WON,
	  LOST
	}
	
	static final char missingCharacter = '_';
	
	static final int maximumLives = 7;
	
	private int remainingLives;  // variable local privada
	
	private GameState gameState; // notar uso de mayúsculas (convención)
	
	private String secretWord; // la palabra secreta
	
	private String partialWord; // palabra que vamos a ir descubriendo
	
	private String triedLetters;
	
	private IHangmanDisplay view;
	
	public HangmanEngine(IHangmanDisplay view) {
		this.remainingLives = HangmanEngine.maximumLives; // un servidor que implementa muchas instancias del juego: variable local vs variable de clase
		this.gameState = HangmanEngine.GameState.UNINITIALIZED;
		this.secretWord = "";
		this.partialWord = "";
		this.triedLetters = "";
		this.view = view; // cual view es cada view???
	}
	
	public boolean startGame(String secretWord) {
		if (secretWord.length() >= 3) {
			this.remainingLives = HangmanEngine.maximumLives;
			this.secretWord = secretWord;
			this.gameState = HangmanEngine.GameState.STARTED;
			this.partialWord = "";
			this.triedLetters = "";
			
			for (int i = 0; i < this.secretWord.length(); i++) {
				this.partialWord = this.partialWord + HangmanEngine.missingCharacter;
			}
			
			this.view.updateView(this.remainingLives, this.partialWord);
		}
		else {
			this.gameState = HangmanEngine.GameState.UNINITIALIZED;
			this.view.reportError("The secret word has to have more than 2 letters!!");
		}
		
		return this.gameState == HangmanEngine.GameState.STARTED;
	}
	
	public void stopGame() {
		this.remainingLives = HangmanEngine.maximumLives;
		this.gameState = HangmanEngine.GameState.UNINITIALIZED;
		this.secretWord = "";
		this.partialWord = "";
		this.triedLetters = "";
	}
	
	public HangmanEngine.GameState getGameState(){
		return this.gameState;
	}
	
	public void tryLetter(String userInput) {
		
		if (userInput.length() != 1) {
			this.view.reportError("User input should only have one letter!!");
		}
		else {
			
			// userInput has 1 character
			
			char letter = userInput.charAt(0);
			
			boolean letterWasTriedBefore = -1 != this.triedLetters.indexOf(letter);
			
			if (letterWasTriedBefore) {
				this.view.reportError("The letter '"+ letter + "' was already tried!");
			}
			else {
				
				this.triedLetters = this.triedLetters + letter;
				
				int indexOfLetter = this.secretWord.indexOf(letter);
				
				if (indexOfLetter == -1) {
					
					// letter missed!
					
					this.remainingLives = this.remainingLives - 1;
					this.view.updateView(this.remainingLives, this.partialWord);
					
					if (this.remainingLives == 0) {
						this.gameState = HangmanEngine.GameState.LOST;
						this.view.gameOver(this.secretWord);
					}				
				}
				else {
					
					// letter is a hit!
					
					for (int i = 0; i < this.secretWord.length(); i++) {
						if (letter == this.secretWord.charAt(i)) {
							char[] partialWordCharArray = this.partialWord.toCharArray();
							partialWordCharArray[i] = letter;
							this.partialWord = String.valueOf(partialWordCharArray);
						}
					}
					
					// verify if the user has won				
					int indexOfUnderscore = this.partialWord.indexOf(HangmanEngine.missingCharacter);
					if (indexOfUnderscore != -1) {
						this.view.updateView(this.remainingLives, this.partialWord);
					}
					else {
						this.view.congratulations();
						this.gameState = HangmanEngine.GameState.WON;
					}	
				}
			}
		}
	}

}

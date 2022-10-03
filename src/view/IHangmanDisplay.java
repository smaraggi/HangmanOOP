package view;

public interface IHangmanDisplay {
	
	public void printWelcome();

	public void updateView(int lives, String partialWord);
	
	public void gameOver(String secretWord);
	
	public void congratulations();
	
	public void reportError(String message);
	
	public void askForSecretWord();
	
	public void goodbye();
}

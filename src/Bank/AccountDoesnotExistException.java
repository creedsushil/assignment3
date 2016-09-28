package Bank;

public class AccountDoesnotExistException extends Exception {
	public AccountDoesnotExistException() {
		super("Error : This Account Doesn't Exist !");
	}
	
	public AccountDoesnotExistException(String message) {
		super(message);
	}
}

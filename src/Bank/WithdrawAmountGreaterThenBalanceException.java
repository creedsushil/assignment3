package Bank;

public class WithdrawAmountGreaterThenBalanceException extends Exception {

	public WithdrawAmountGreaterThenBalanceException() {
		super("Error : You cannot withdraw more than your baloance !");
	}
	
	public WithdrawAmountGreaterThenBalanceException(String message) {
		super(message);
	}
}

package Bank;

public class DepositCannotBeZeroException extends Exception {

	public DepositCannotBeZeroException(){
		super("Error : Please Enter Valid Deposit Amount !");
	}
	
	public DepositCannotBeZeroException(String message){
		super(message);
	}
}

package Bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.scene.paint.GradientUtils.Parser;
import com.sun.org.apache.xerces.internal.util.Status;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class Bank
 */
@WebServlet(name = "BankForm", urlPatterns = { "/BankForm" })
public class Bank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public double totalBalance = 1000.0;
	public String staticAccountNumber = "001-002-123-00";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selection = request.getParameter("selection");
		String accountNumber = request.getParameter("accountNo");
		double amount = Integer.parseInt(request.getParameter("amount"));
		MessageStatus result = new MessageStatus();
		if (accountNumber.equals(staticAccountNumber)) {
			result = transaction(selection, accountNumber, amount);
		} else {
			result.message = "Error : Please enter valid Account Number!!!!!!";
		}
		response.setContentType("text/html");
		PrintWriter outPut = response.getWriter();
		String resp = result.message;
		outPut.println(resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public MessageStatus transaction(String selection, String accountNumber, double amount) {
		MessageStatus result = new MessageStatus();

		switch (selection) {
		case "Deposit":
			try {
				amount = deposit(amount);
				result.success = true;
				result.message = "Deposit Successfull!!!! Your new balance is " + amount;
			} catch (DepositCannotBeZeroException e) {
				// TODO Auto-generated catch block
				// System.out.println(e.getMessage());
				result.success = false;
				result.message = e.getMessage();
			}
			break;

		case "Withdraw":
			try {
				amount = withdraw(amount);
				result.success = true;
				result.message = "Withdrawl Successfull!!!! Your new balance is " + amount;

			} catch (WithdrawAmountGreaterThenBalanceException e) {
				// TODO Auto-generated catch block
				result.success = false;
				result.message = e.getMessage();
			}
			break;
		case "Inquiry":
			try {

				amount = inquiryBalance(accountNumber);
				result.success = true;
				result.message = "Your Balance is " + amount;

			} catch (AccountDoesnotExistException e) {
				// TODO Auto-generated catch block
				result.success = false;
				result.message = e.getMessage();
			}
			break;
		}

		return result;
	}

	public double deposit(double depositAmount) throws DepositCannotBeZeroException {
		if (depositAmount <= 0.0) {
			throw new DepositCannotBeZeroException();
		}
		totalBalance = totalBalance + depositAmount;
		return totalBalance;

	}

	public double withdraw(double withdrawAmount) throws WithdrawAmountGreaterThenBalanceException {
		if (withdrawAmount > totalBalance) {
			throw new WithdrawAmountGreaterThenBalanceException();
		} else {
			totalBalance = totalBalance - withdrawAmount;
			return totalBalance;
		}
	}

	public double inquiryBalance(String accountNumber) throws AccountDoesnotExistException {

		if (!accountNumber.equals(staticAccountNumber)) {
			throw new AccountDoesnotExistException();
		} else {
			return totalBalance;
		}
	}

}

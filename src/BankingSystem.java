import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.Date;

/**
 * @author Li Xu (nathaniel.g.waring@gmail.com) <i>This is a banking system that
 *         has customers, <br>
 *         savings, checking and user profiles.</i> customers could login with
 *         user name and password, they could do deposit, withdraw and view
 *         balance to the saving and checking account, they also could transfer
 *         money between the two accounts.
 */

public class BankingSystem {

	public static void main(String[] args) throws IOException {

		JOptionPane.showMessageDialog(null, "Starting the banking system, please wait");
		String filename = "customerInfor1.txt";
		double theMoney; // the amount of money for deposit or withdraw
		boolean shutdown = false; // allows the customer to break out off the system
		double transNum; // stores the amount of money needs to be transferred
		double checkingBal; // checking account balance
		double savingBal; // saving account balance

		// create an event loop
		while (!shutdown) {
			// user type their userName
			String userName = JOptionPane.showInputDialog("Please enter userName"); // how to get out of this loop
			// user type their password
			String password = JOptionPane.showInputDialog("Please enter password");
			// create an object customerProfile from Profile class
			Profile customerProfile = new Profile();
			// customer type in the right userName and password to login the system
			boolean successfulLogin = customerProfile.readProfileData(filename, userName, password);

			// shows the successful login message, or the unsuccessful login message
			if (successfulLogin) {
				JOptionPane.showMessageDialog(null, "Login Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Username or password incorrect");
			}

			while (successfulLogin) {

				double curBalance = 0; // current balance
				// create an object from CheckingAccount
				CheckingAccount myChecking = new CheckingAccount(customerProfile);
				// create an object from SavingAccount
				SavingsAccount mySaving = new SavingsAccount(customerProfile);

				String fileName = myChecking.getFileName(); // get the right checking file name
				String savingFileName = mySaving.getFileName();// get the right saving file name
				String date = myChecking.getDate(); // get date

				boolean checking = true; // for user to break out off checking loop
				boolean deposit = true; // for user to break out off deposit loop
				boolean saving = true; // for user to break out off saving loop

				// choose the account they want to work with
				String[] options = { "Checking", "Saving", "Exit" };
				int account = JOptionPane.showOptionDialog(null, "Please choose a account", "Click a button",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				switch (account) {
				case 0: // checking account
					while (checking) {
						// choose the functions that user want to work with
						Object[] choice = { "Deposit", "Withdraw", "Transfer", "View balance", "Back" };
						int n = JOptionPane.showOptionDialog(null, "Press the button below", "Checking Account",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, choice[1]);

						switch (n) {

						case 0: // doDeposit
							// ask the user to type in the amount of money for deposit
							String myDeposit = JOptionPane.showInputDialog("Please type the amount of deposit");

							while (myDeposit != null) {
								theMoney = Double.parseDouble(myDeposit); // change string to double
								curBalance = myChecking.getBalance(); // get current balance
								curBalance = curBalance + theMoney; // update current balance with the amount of deposit

								// call doDeposit method, pass in four parameters
								myChecking.doDeposit(fileName, theMoney, curBalance, date);
								// show the user the new balance
								JOptionPane.showMessageDialog(null,
										"Your deposit is done, you current balance is  $" + curBalance);
								myDeposit = null; // break out of deposit while loop after finish deposit
							}
							break;

						case 1: // Withdraw
							// ask the user to type in the amount of money for withdraw
							String myWithdraw = JOptionPane.showInputDialog("Please type the amount below");

							if (myWithdraw != null) {
								theMoney = Double.parseDouble(myWithdraw); // change string to double
								curBalance = myChecking.getBalance(); // get current balance

								if (theMoney > curBalance) { // make sure no overdraft
									JOptionPane.showMessageDialog(null,
											"Your checking balance is not enough, please try again!");
									break;
								}

								theMoney = theMoney * -1; // make the number of withdraw negative
								curBalance = curBalance + theMoney; // update current balance
								// call doWithdraw method, pass in four parameters
								myChecking.doWithdraw(fileName, theMoney, curBalance, date);
								// show user the new current balance
								JOptionPane.showMessageDialog(null,
										"Your withdraw is done, you current balance is  $" + curBalance);
							}

							break;

						case 2: // Transfer
							// type in the amount needs to be transferred
							String checkingTransNum = JOptionPane.showInputDialog(
									"Please type the amount of money needs to be transferred to savings account");

							while (checkingTransNum != null) {
								transNum = Double.parseDouble(checkingTransNum); // change string to double
								checkingBal = myChecking.getBalance(); // get checking account balance
								if (transNum > checkingBal) { // check no overdraft
									JOptionPane.showMessageDialog(null,
											"Your balance is not enough, please try again!");
									break;
								}

								savingBal = mySaving.getBalance(); // get saving account balance
								savingBal = savingBal + transNum; // update saving account balance after transfer
								checkingBal = checkingBal - transNum; // update checking account balance after transfer
								// call doWithdraw method for checking account
								myChecking.doWithdraw(fileName, transNum, checkingBal, date);
								// call doDeposit method for saving account
								mySaving.doDeposit(savingFileName, transNum, savingBal, date);
								// show the user new balances of two accounts
								JOptionPane.showMessageDialog(null,
										"Transfer is done." + "\nChecking accout balance: $ " + checkingBal
												+ "\nSaving account balance: $ " + savingBal);
								checkingTransNum = null; // break out of transfer loop
							}
							break;

						case 3: // ViewBalance
							curBalance = myChecking.getBalance(); // get current balance, show the user
							JOptionPane.showMessageDialog(null, "Your balance is  $" + curBalance);
							break;

						case 4: // back
							checking = false; // break out of checking loop
							break;
						}

					}

					break;

				case 1: // saving account

					while (saving) {
						// choose the functions that user want to work with
						Object[] savingChoice = { "Deposit", "Withdraw", "Transfer", "View balance", "Back" };
						int j = JOptionPane.showOptionDialog(null, "Press the button below", "Saving Account",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, savingChoice,
								savingChoice[1]);

						switch (j) {
						case 0: // deposit
							// ask the user to type in the amount of deposit for saving account
							String savingDeposit = JOptionPane.showInputDialog("Please type the amount of deposit");
							if (savingDeposit != null) {
								theMoney = Double.parseDouble(savingDeposit); // change string to double
								curBalance = mySaving.getBalance(); // get current balance of saving account
								curBalance = curBalance + theMoney; // update the balance

								// call the method doDeposit, pass four parameters
								mySaving.doDeposit(savingFileName, theMoney, curBalance, date);
								JOptionPane.showMessageDialog(null,
										"Your deposit is done, you current balance is  $" + curBalance);
							}
							break;

						case 1: // withdraw
							// ask the user to type in the amount of withdraw from saving account
							String myWithdraw = JOptionPane.showInputDialog("Please type the amount below");
							if (myWithdraw != null) {
								theMoney = Double.parseDouble(myWithdraw); // change string to double
								curBalance = mySaving.getBalance(); // get current balance of saving account

								if (theMoney > curBalance) { // check if there are overdraft
									JOptionPane.showMessageDialog(null,
											"Your saving balance is not enough, please try again!");
									break;
								}

								theMoney = theMoney * -1;
								curBalance = curBalance + theMoney; // update current balance of saving account

								// call withdraw method
								mySaving.doWithdraw(fileName, theMoney, curBalance, date);
								JOptionPane.showMessageDialog(null,
										"Your withdraw is done, you current balance is  $" + curBalance);
							}
							break;

						case 2:
							// transfer
							// ask the user to type in the amount of withdraw from saving account
							String savingTransNum = JOptionPane.showInputDialog(
									"Please type the amount of money needs to be transferred to checking account");
							if (savingTransNum != null) {
								transNum = Double.parseDouble(savingTransNum); // change string to double
								savingBal = mySaving.getBalance();
								if (transNum > savingBal) { // check if there are overdraft
									JOptionPane.showMessageDialog(null,
											"Your saving balance is not enough, please try again!");
									break;
								}

								checkingBal = myChecking.getBalance(); // get checking balance
								savingBal = savingBal - transNum; // update saving balance
								checkingBal = checkingBal + transNum; // update checking balance

								// call doWithdraw method for saving account
								mySaving.doWithdraw(savingFileName, transNum, savingBal, date);
								// call doDeposit method for checking account
								myChecking.doDeposit(fileName, transNum, checkingBal, date);

								// show the user new balances of two accounts
								JOptionPane.showMessageDialog(null,
										"Transfer is done. \n" + "Saving accout balance is  $" + savingBal
												+ "\nChecking account balance is $" + checkingBal);
							}
							break;
						case 3: // view balance
							double myBalance = mySaving.getBalance(); // get balance and show user
							JOptionPane.showMessageDialog(null, "Your balance is  $" + myBalance);
							break;

						case 4: // back
							saving = false; // break out of the saving loop
						}

					}

					break;

				case 2:
					// exit
					successfulLogin = false; // exit the system
					break;

				}

			}

		}

	}

}

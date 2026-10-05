
public class SavingAccount extends BankAccount {
	private double interestRate;
	private static final double MIN_BALANCE = 50000;
	
	public SavingAccount (String accountNumber, String holderName, double balance, double interestRate) {
		super (accountNumber, holderName, balance);
		if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException("Tài khoản tiết kiệm phải có số dư tối thiểu 50.000 VNĐ!");
        }
		this.interestRate = interestRate;
	}
	
	//Getter
	public double getInterestRate () {
		return interestRate;
	}
	
	//Setter
	public void setInterestRate (double interestRate) {
		this.interestRate = interestRate;
	}
	
	/**
     * Rút tiền khỏi tài khoản tiết kiệm.
     * Pre-condition:
     * amount > 0
     * balance - amount >= 50,000
     * Post-condition:
     * balance_new == balance_old - amount
     * Invariant:
     * balance >= 50,000
     */
	@Override 
	public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
		// Pre-condition 1
        if (amount <= 0) {
            throw new InvalidAmountException("Số tiền rút phải lớn hơn 0!");
        }

        // Pre-condition 2
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException("Không thể rút tiền! " + "Tài khoản tiết kiệm phải duy trì số dư tối thiểu 50.000 VNĐ.");
        }
        
        double oldBalance = getBalance();
        setBalance(oldBalance - amount);
        // Post-condition
        assert getBalance() == oldBalance - amount :
                "Post-condition violated!";

        // Class invariant
        assert getBalance() >= MIN_BALANCE :
                "Class invariant violated!";
	}
	 @Override
	 public String toString() {
	     return super.toString() + ", Lãi suất: " + interestRate + "%";
	 }
}

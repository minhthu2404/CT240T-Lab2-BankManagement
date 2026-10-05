
public abstract class BankAccount {
	private String accountNumber;
	private String holderName;
	private double balance;
	
	public BankAccount (String accountNumber, String holderName, double balance) {
		if(balance < 0) {
			throw new IllegalArgumentException("Số dư ban đầu không được âm!");
		}
		
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.balance = balance;
	}
	
	//Getter
	
	public String getAccountNumber () {
		return accountNumber;
	}
	
	public String getHolderName () {
		return holderName;
	}
	
	public double getBalance () {
		return balance;
	}
	
	//Setter
	public void setAccountNumber (String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setHolderName (String holderName) {
		this.holderName =  holderName;
	}
	
	public void setBalance (double balance) {
		if(balance < 0) {
			throw new IllegalArgumentException("Số dư ban đầu không được âm!");
		}
		this.balance = balance;
	}
	

    /**
     * Nạp tiền vào tài khoản.
     * Pre-condition: amount > 0
     * Post-condition: balance_new == balance_old + amount
     * @param amount số tiền cần nạp
     * @throws InvalidAmountException nếu amount <= 0
     */
	public void deposit (double amount) throws InvalidAmountException {
	// Pre-condition
	    if (amount <= 0) {
	        throw new InvalidAmountException("Số tiền nạp phải lớn hơn 0!");
		}
	    double oldBalance = balance;
	    balance += amount;
	 // Post-condition
        assert balance == oldBalance + amount :
                "Post-condition violated!";
	}
	
	/**
	 * Rút tiền khỏi tài khoản ngân hàng.
	 * @param amount Số tiền cần rút
	 * @throws InvalidAmountException Tiền điều kiện: amount <= 0
	 * @throws InsufficientBalanceException Tiền điều kiện: balance < amount
	 * @post-condition balance_new == balance_old - amount
	 * @invariant balance_new >= 0
	 */
	public abstract void withdraw (double amount) throws InsufficientBalanceException, InvalidAmountException;
	@Override
	public String toString () {
		return "Số tài khoản: "+accountNumber+ ", Chủ tài khoản: " +holderName+ ", Số dư: " +balance;
	}
}

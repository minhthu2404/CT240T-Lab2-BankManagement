import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManager {
    private Map<String, BankAccount> accounts;

    public BankManager() {
        accounts = new HashMap<>();
    }

    /**
     * Thêm tài khoản vào hệ thống.
     */
    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Tài khoản không được null!");
        }

        String accountNumber = account.getAccountNumber();

        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Số tài khoản " + accountNumber + " đã tồn tại!");
        }

        accounts.put(accountNumber, account);
    }

    /**
     * Tìm tài khoản theo số tài khoản.
     */
    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Chuyển tiền giữa hai tài khoản.
     */
    public void transferMoney(String fromAcc, String toAcc, double amount) throws InvalidAmountException, InsufficientBalanceException {
        // Kiểm tra số tiền
        if (amount <= 0) {
            throw new InvalidAmountException("Số tiền chuyển phải lớn hơn 0!");
        }

        // Tìm tài khoản gửi
        BankAccount sender = findAccount(fromAcc);

        if (sender == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản gửi: " + fromAcc);
        }

        // Tìm tài khoản nhận
        BankAccount receiver = findAccount(toAcc);

        if (receiver == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản nhận: " + toAcc);
        }

        if (fromAcc.equals(toAcc)) {
            throw new IllegalArgumentException("Tài khoản gửi và nhận không được giống nhau!");
        }
        sender.withdraw(amount);

        try {
            receiver.deposit(amount);
        } catch (InvalidAmountException e) {
            sender.deposit(amount);
            throw e;
        }
    }

    /**
     * Generics Utility Method với Wildcard.
     */
    public double calculateTotalBalance(List<? extends BankAccount> accounts) {
        double total = 0.0;
        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    /**
     * Tính tổng số dư của tất cả tài khoản trong hệ thống.
     */
    public double calculateSystemTotalBalance() {
        return calculateTotalBalance(
                new ArrayList<>(accounts.values())
        );
    }

    /**
     * Lấy danh sách tất cả tài khoản.
     */
    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    /**
     * Hiển thị tất cả tài khoản.
     */
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println(
                    "Hệ thống chưa có tài khoản."
            );
            return;
        }

        for (BankAccount account : accounts.values()) {
            System.out.println(account);
        }
    }
}
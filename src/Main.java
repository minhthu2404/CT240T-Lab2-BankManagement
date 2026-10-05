import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BankManager manager = new BankManager();
    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            try {
                System.out.print("Nhập lựa chọn: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addSavingAccount();
                        break;

                    case 2:
                        deposit();
                        break;

                    case 3:
                        withdraw();
                        break;

                    case 4:
                        transferMoney();
                        break;

                    case 5:
                        findAccount();
                        break;

                    case 6:
                        manager.displayAllAccounts();
                        break;

                    case 7:
                        calculateTotalBalance();
                        break;

                    case 0:
                        System.out.println(
                                "Đã thoát chương trình."
                        );
                        break;

                    default:
                        System.out.println(
                                "Lựa chọn không hợp lệ!"
                        );
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số hợp lệ!");
                choice = -1;
            } catch (InvalidAmountException e) {
                System.out.println("Lỗi số tiền: " + e.getMessage());
                choice = -1;
            } catch (InsufficientBalanceException e) {
                System.out.println("Lỗi số dư: " + e.getMessage());
                choice = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
                choice = -1;
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi không xác định: " + e.getMessage());
                choice = -1;
            }
            System.out.println();
        } while (choice != 0);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("==============================");
        System.out.println(" HỆ THỐNG QUẢN LÝ NGÂN HÀNG");
        System.out.println("==============================");
        System.out.println("1. Thêm tài khoản tiết kiệm");
        System.out.println("2. Nạp tiền");
        System.out.println("3. Rút tiền");
        System.out.println("4. Chuyển tiền");
        System.out.println("5. Tìm tài khoản");
        System.out.println("6. Hiển thị tất cả tài khoản");
        System.out.println("7. Tính tổng số dư");
        System.out.println("0. Thoát");
        System.out.println("==============================");
    }

    private static void addSavingAccount() {

        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Nhập tên chủ tài khoản: ");
        String holderName = scanner.nextLine();

        System.out.print("Nhập số dư ban đầu: ");
        double balance =
                Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập lãi suất (%): ");
        double interestRate =
                Double.parseDouble(scanner.nextLine());

        SavingAccount account = new SavingAccount(accountNumber,holderName,balance,interestRate);
        manager.addAccount(account);
        System.out.println("Thêm tài khoản thành công!");
    }

    private static void deposit() throws InvalidAmountException {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = manager.findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản!");
        }

        System.out.print("Nhập số tiền nạp: ");
        double amount = Double.parseDouble(scanner.nextLine());
        account.deposit(amount);
        System.out.println("Nạp tiền thành công!");
        System.out.println("Số dư mới: " + account.getBalance());
    }

    private static void withdraw() throws InvalidAmountException, InsufficientBalanceException {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = manager.findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản!");
        }

        System.out.print("Nhập số tiền rút: ");
        double amount = Double.parseDouble(scanner.nextLine());

        account.withdraw(amount);

        System.out.println("Rút tiền thành công!");
        String formattedBalance = String.format("%,.0f", account.getBalance()).replace(",", ".");
        System.out.println("Số dư mới: " + formattedBalance + " VNĐ");
    }

    private static void transferMoney()
            throws InvalidAmountException,
                   InsufficientBalanceException {

        System.out.print("Nhập tài khoản gửi: ");
        String fromAcc = scanner.nextLine();

        System.out.print("Nhập tài khoản nhận: ");
        String toAcc = scanner.nextLine();

        System.out.print("Nhập số tiền chuyển: ");
        double amount = Double.parseDouble(scanner.nextLine());
        manager.transferMoney(fromAcc, toAcc, amount);
        System.out.println("Chuyển tiền thành công!");
    }

    private static void findAccount() {
        System.out.print("Nhập số tài khoản cần tìm: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = manager.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Không tìm thấy tài khoản.");
        } else {
            System.out.println("Thông tin tài khoản:");
            System.out.println(account);
        }
    }

    private static void calculateTotalBalance() {
        double total = manager.calculateSystemTotalBalance();
        System.out.println("Tổng số dư toàn hệ thống: " + total + " VNĐ");
    }
}
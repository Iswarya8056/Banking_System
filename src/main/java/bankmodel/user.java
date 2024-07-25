package bankmodel;

public class user {
    private String username;
    private String address;
    private String mobile;
    private String email;
    private String dob;
    private double balance;
    private String password;
    private String accountType;
    private String accountNumber;
  

    public user(String username, String address, String mobile, String email, String dob, double balance, String accountType, String accountNumber, String password) {
        this.username = username;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.balance = balance;
        this.password = password;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
  
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

  
    }


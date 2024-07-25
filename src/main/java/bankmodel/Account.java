package bankmodel;

public class Account {
    private String account_number;
    private String name;
    private String address;
    private String mobile;
    private String email;
    private String dob;
    private String accountType;

    public Account(String account_number, String name, String address, String mobile, String email, String dob, String accountType) {
        this.account_number = account_number;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.accountType = accountType;
    }

    // Getters and setters for the fields

    public String getAccountNumber() {
        return account_number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getAccountType() {
        return accountType;
    }
}


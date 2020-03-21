package AccountEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Account {
    @Id //@GeneratedValue(strategy = GenerationType.AUTO)
    private long accountNumber;
    private double money;
    private String ownerName;
    @Version
    private int version;

    public Account(){}

    public Account(long accountNumber, double money, String ownerName){
        this.money = money;
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String toString(){
        return "Account in " + this.ownerName + "'s name with account number " + this.accountNumber + " has " + this.money + " money";
    }

    public void withdraw(double amount){
        this.money -= amount;
    }
}

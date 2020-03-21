package AccountEntity;

import javax.persistence.OptimisticLockException;

public class TransferThread implements Runnable {

    private Account from;
    private Account to;
    private int amount;
    private AccountDAO dao;

    public TransferThread(Account from, Account to, int amount, AccountDAO dao){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dao = dao;
    }

    @Override
    public void run() {
        from.withdraw(amount);
        to.setMoney(to.getMoney() + amount);
        try{
            dao.updateAccount(from);
            dao.updateAccount(to);
        }
        catch (OptimisticLockException e){
            System.out.println("Could not perform transfer");
        }
    }
}

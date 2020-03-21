import AccountEntity.Account;
import AccountEntity.AccountDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Client1 {

    public static void deleteAccounts(AccountDAO dao){
        dao.deleteAccount(1);
        dao.deleteAccount(2);
    }

    public static Account[] createAccounts(AccountDAO dao){
        Account a1 = new Account(1, 50, "Gunnar");
        Account a2 = new Account(2, 200, "Frank");
        dao.createAccount(a1);
        dao.createAccount(a2);
        return new Account[]{a1, a2};
    }

    public static void main(String[] args){

        EntityManagerFactory emf = null;
        AccountDAO accountDAO = null;

        try {
            emf = Persistence.createEntityManagerFactory("TDAT2004Task6");
            accountDAO = new AccountDAO(emf, false);

            deleteAccounts(accountDAO);
            Account[] accounts = createAccounts(accountDAO);

            List<Account> allAccounts = accountDAO.getAllAccounts();
            List<Account> allAccountsLargerThan75 = accountDAO.getAllAccountsLargerThanX(75);

            System.out.println("All accounts: ");
            for (Account a : allAccounts) {
                System.out.println(a);
            }

            System.out.println("All accounts with more than 75 money: ");
            for (Account a : allAccountsLargerThan75) {
                System.out.println(a);
            }

            accounts[0].setOwnerName("Ole");
            accountDAO.updateAccount(accounts[0]);
            System.out.println("Gunnar after update: ");
            System.out.println(accountDAO.findAccount(1));
        } finally {
            if (emf != null && emf.isOpen()) emf.close();
        }


    }
}

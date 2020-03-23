package Tasks;

import AccountEntity.Account;
import AccountEntity.AccountDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Client1 {

    public static void main(String[] args){

        EntityManagerFactory emf = null;
        AccountDAO accountDAO = null;

        try {
            emf = Persistence.createEntityManagerFactory("TDAT2004Task6");
            accountDAO = new AccountDAO(emf, true);

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

            allAccounts.get(0).setOwnerName("Ole");
            accountDAO.updateAccount(allAccounts.get(0));
            System.out.println("Gunnar after update: ");
            System.out.println(accountDAO.findAccount(1));
        } finally {
            if (emf != null && emf.isOpen()) emf.close();
        }


    }
}

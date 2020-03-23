package Tasks;

import AccountEntity.Account;
import AccountEntity.AccountDAO;
import AccountEntity.TransferThread;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Client2 {

    /**
     * Overfører penger fra en konto til en annen, og trekker penger fra den andre kontoen
     * @param from [Account]: Her kommer pengene fra
     * @param to [Account]: Her går pengene
     * @param amount [int] Mengde penger som blir overført
     * @param dao [AccountDAO]: DatabaseObjektet som brukes til overføring
     * @return [Thread]: Tråden som kjører overføringen.
     */
    public static Thread transfer(Account from, Account to, int amount, AccountDAO dao){
        TransferThread transferThread1 = new TransferThread(from, to, amount, dao);
        Thread t1 = new Thread(transferThread1);
        t1.start();
        return t1;
    }

    public static void main(String[] args){
        EntityManagerFactory emf = null;
        AccountDAO accountDAO = null;
        try{
            emf = Persistence.createEntityManagerFactory("TDAT2004Task6");
            //Vi lager en ny tilkobling, uten å fjerne databasen helt
            accountDAO = new AccountDAO(emf, true);

            ArrayList<Thread> threads = new ArrayList<>();
             //Fjern de to kontoene som finnes, og lag de på nytt.

            //Finn konto nr 1 og 2
            Account account1 = accountDAO.findAccount(1);
            Account account2 = accountDAO.findAccount(2);

            System.out.println(account1);
            System.out.println(account2);

            //Overfør først 50 kroner, så 75. Konto 1 skal ende opp med 75, og konto 2 med 175
            threads.add(transfer(account1, account2, 50, accountDAO));
            threads.add(transfer(account2, account1, 75, accountDAO));

            for (Thread t : threads){
                t.join();
            }

            //Bryt løkken hvis vi ender opp med en feil her
            account1 = accountDAO.findAccount(1);
            account2 = accountDAO.findAccount(2);
            if (account1.getMoney() != 75.0 || account2.getMoney() != 175.0){
                System.out.println("Some error occurred during transfer.");
            }

            //Skriv ut dataene. Dette gir det vi ønsker å få ut.
            List<Account> allAccounts = accountDAO.getAllAccounts();
            for (Account a : allAccounts){
                System.out.println(a);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()){
                emf.close();
            }
        }
    }
}

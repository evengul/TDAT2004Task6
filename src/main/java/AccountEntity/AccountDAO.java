package AccountEntity;

import javax.persistence.*;
import java.util.List;

public class AccountDAO {
    private EntityManagerFactory emf;

    public AccountDAO(EntityManagerFactory emf, boolean resetDB){
        this.emf = emf;
        EntityManager em = getEM();
        if (resetDB){
            try{
                em.getTransaction().begin();
                em.createNativeQuery("CREATE TABLE ACCOUNT (accountNumber INT PRIMARY KEY, money INT , ownerName VARCHAR(100))").executeUpdate();
                em.getTransaction().commit();
            }
            finally {
                closeEM(em);
            }
        }
    }

    public void createAccount(Account account){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }

    public Account findAccount(long accountNumber){
        EntityManager em = getEM();
        try{
            return em.find(Account.class, accountNumber);
        }
        finally {
            closeEM(em);
        }
    }

    public void updateAccount(Account account){
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Account returnedAccount = em.merge(account);
            em.getTransaction().commit();
        }
        finally {
            closeEM(em);
        }
    }

    public void deleteAccount(long accountNumber){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            Account account = findAccount(accountNumber);
            if (!em.contains(account)){
                account = em.merge(account);
            }
            em.remove(account);
            em.getTransaction().commit();
        }finally {
            closeEM(em);
        }
    }

    public List<Account> getAllAccounts(){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("SELECT OBJECT(o) FROM Account o");
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }

    public List<Account> getAllAccountsLargerThanX(long x){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("SELECT OBJECT(o) FROM Account o WHERE o.money > :money");
            q.setParameter("money", x);
            return q.getResultList();
        }finally {
            closeEM(em);
        }
    }

    private void closeEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    private EntityManager getEM(){
        return emf.createEntityManager();
    }
}

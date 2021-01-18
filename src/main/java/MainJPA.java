import entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class MainJPA {

    static EntityManager entityManager;
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence1");

    public static void main(String[] args) {
        //create
        Cliente cliente1 = new Cliente("Rubén", "Rueda", LocalDate.now());
        createClient(cliente1);
        //read
        printClient(getClientById(cliente1.getId()));
        //update
        updateClient(cliente1);
        //delete
        removeClient(cliente1);
    }

    static void updateClient(Cliente cliente) {
        entityManager = createEntityManager();
        cliente= entityManager.merge(cliente);
        entityManager.getTransaction().begin();
        cliente.setNombre("Nicolás");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    static void removeClient(Cliente cliente) {
        entityManager = createEntityManager();
        cliente = entityManager.merge(cliente);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    static EntityManager createEntityManager(){
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    static Cliente getClientById(Integer id){
        entityManager = createEntityManager();
        Cliente cliente =  entityManager.find(Cliente.class, id);
        entityManager.close();
        return cliente;
    }

    static Cliente createClient(Cliente cliente){
        entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
        return cliente;
    }

    static void printClient(Cliente cliente){
        System.out.println("Result!................");
        System.out.println(cliente);
    }

}

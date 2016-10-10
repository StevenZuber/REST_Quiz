package client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.User;

public class UserClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizJPA");
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, 1);
		System.out.println(user);
		em.close();
		emf.close();
	}
}

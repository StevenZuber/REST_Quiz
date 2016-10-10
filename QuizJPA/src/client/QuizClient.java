package client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Quiz;

public class QuizClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizJPA");
		EntityManager em = emf.createEntityManager();
		Quiz quiz = em.find(Quiz.class, 1);
		System.out.println(quiz);
		em.close();
		emf.close();
	}
}

package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Score;

public class ScoreTest {
	   private EntityManagerFactory emf;
	   private EntityManager em;

	@Before
	public void setUp() throws Exception {
	        emf = Persistence.
	            createEntityManagerFactory("QuizJPA");
	        em = emf.createEntityManager();
	}

	@Test
	public void test() throws Exception {
		Score score = em.find(Score.class, 3);
		assertEquals(80, score.getValue(), 001);

		

	}

	@After
	public void tearDown() {
		   em.close();
	        emf.close();	}


}

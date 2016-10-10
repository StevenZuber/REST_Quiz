package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Answer;
import entities.Question;
import entities.Quiz;

public class QuizTest {
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
		Quiz quiz = em.find(Quiz.class, 1);
		
		assertEquals(1, quiz.getId());
		assertEquals("whatever", quiz.getName());
		
		Answer answer = em.find(Answer.class, 1);
		
		assertEquals("Green", answer.getAnswerText());
		assertEquals(1, answer.getId());

		Question q = em.find(Question.class, 1);
		
		assertEquals("What's my favorite color?", q.getQuestionText());
		assertEquals(1, q.getId());
	}

	@After
	public void tearDown() {
		   em.close();
	        emf.close();	}


}

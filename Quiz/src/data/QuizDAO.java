package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Question;
import entities.Quiz;
import entities.Score;

@Transactional
public class QuizDAO {
	@PersistenceContext
	private EntityManager em;
	

	public List<Quiz> index() {
		String query = "Select q from Quiz q";
		return em.createQuery(query, Quiz.class).getResultList();
		
	}
	
	public Quiz show(int id) {
		return em.find(Quiz.class, id);
	}
	
	public Quiz create(Quiz quiz){
		
		quiz.setName(quiz.getName());
	    
	    em.persist(quiz);
	    em.flush();
	    return quiz;

	}
	
	public Question createQuestion(int id, Question question){
		Quiz quiz = em.find(Quiz.class, id);
		question.setQuiz(quiz);
		em.persist(question);
		em.flush();
		return question;
		
	}
	
	public Quiz update(int id, Quiz quiz){
		Quiz editQuiz = em.find(Quiz.class, id);
		
		editQuiz.setName(quiz.getName());
		
		em.merge(editQuiz);
		return editQuiz;
	}
	
	public void destroy(int id){
		String query = "SELECT s from Score s where s.quiz_Id = " + id;
	
		List <Score> scoresWithQuizIds = em.createQuery(query, Score.class).getResultList();
		

		for (Score score : scoresWithQuizIds) {
			em.remove(score);
		}
		
		Quiz deleteQuiz = em.find(Quiz.class, id);
		
		em.remove(deleteQuiz);
	}
	
	public void destroyQuestion(int id){
		Question deleteQ = em.find(Question.class, id);
		
		em.remove(deleteQ);
	}
	
	public List<Score> indexScore(int id){
		String query = "Select s from Score s where s.quiz_Id = '" + id + "'";
		
		List <Score> scores = em.createQuery(query, Score.class).getResultList();
		
		return scores;
	}
	
//	public Set<Question> indexQuestions(int id){
//		String query = "Select q from Question q where q.quiz_Id = '" + id + "'";
//		
//		Set <Question> questions = em.createQuery(query, Question.class).getResultList();
//		
//		return questions;
//	}
}

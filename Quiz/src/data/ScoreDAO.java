package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Score;

@Transactional
public class ScoreDAO {

	@PersistenceContext
	private EntityManager em;
	public List<Score> index() {
		String query = "Select s from Score s";
		return em.createQuery(query, Score.class).getResultList();
		
	}
	
	public Score show(int id) {
		return em.find(Score.class, id);
	}
	
	public Score create(Score score){
		
		score.setValue(score.getValue());
	    
	    em.persist(score);
	    em.flush();
	    return score;

	}
	
	public Score update(int id, Score score){
		Score editScore = em.find(Score.class, id);
		
		editScore.setValue(score.getValue());
		
		em.merge(editScore);
		return editScore;
	}
	
	public void destroy(int id){
		
		Score deleteScore = em.find(Score.class, id);
		
		em.remove(deleteScore);
	}
}

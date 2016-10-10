package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import entities.Quiz;
import entities.Score;
import entities.User;

@Transactional
public class UserDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public List<User> index() {
		String query = "Select u from User u";
		return em.createQuery(query, User.class).getResultList();
		
	}
	
	public User show(int id) {
		return em.find(User.class, id);
	}
	
	public User create(User user){
	    // extract raw password
	    String rawPassword = user.getPassword();
	    // encode raw password
	    String encodedPassword = passwordEncoder.encode(rawPassword);
	    // reset the user's password to the encoded one
	    user.setPassword(encodedPassword);
	    // persist the user
	    em.persist(user);
	    // force EntityManager to persist immediately
	    em.flush();
	    // return the persisted user
	    return user;

	}
	
	public User update(int id, User user){
		User editUser = em.find(User.class, id);
		
		editUser.setUsername(user.getUsername());
		 // extract raw password
	    String rawPassword = user.getPassword();
	    // encode raw password
	    String encodedPassword = passwordEncoder.encode(rawPassword);
	    // reset the user's password to the encoded one
	    editUser.setPassword(encodedPassword);
		em.merge(editUser);
		return editUser;
	}
	
	public void destroy(int id){
		User deletedUser = em.find(User.class, id);
		
		em.remove(deletedUser);
	}
	
	public void addScoreToUser(int quizId, Score score){
		Quiz quiz = em.find(Quiz.class, quizId);
		User user = em.find(User.class, 1);
		
		Score newScore = new Score();
		
		newScore.setQuizId(quiz.getId());
		newScore.setUser(user);
		
		newScore.setValue(score.getValue());
		
		em.persist(newScore);
		em.flush();
		
	}
}

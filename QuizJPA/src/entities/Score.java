package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Score {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "value")
	private float value;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
	
	@JoinColumn(name = "quiz_id")
	private int quiz_Id;
	

	
	public Score(){
		
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public float getValue() {
		return value;
	}



	public void setValue(float value) {
		this.value = value;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getQuizId() {
		return quiz_Id;
	}



	public void setQuizId(int quizId) {
		this.quiz_Id = quizId;
	}



	public Score(int id, float value, User user, int quizId) {
		super();
		this.id = id;
		this.value = value;
		this.user = user;
		this.quiz_Id = quizId;
	}
}

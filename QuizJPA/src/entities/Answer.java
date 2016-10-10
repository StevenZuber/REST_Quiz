package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "answerText")
    private String answerText;
	
	@Column(name = "isCorrect")
    private boolean isCorrect;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public Answer(int id, String answerText, boolean isCorrect) {
		super();
		this.id = id;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	public Answer() {
		
	}
    
}

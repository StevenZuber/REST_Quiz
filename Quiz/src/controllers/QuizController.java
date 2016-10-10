package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuizDAO;
import entities.Question;
import entities.Quiz;
import entities.Score;

@RestController
public class QuizController {
	@Autowired
	private QuizDAO quizdao;

	@RequestMapping(path = "/bilbo", method = RequestMethod.GET)
	public String bilbo() {
		String baggins = "baggins";
		return baggins;
	}

	@RequestMapping(path = "/quizzes", method = RequestMethod.GET)
	public List<Quiz> index() {
		return quizdao.index();
	}

	@RequestMapping(path = "/quizzes/{id}/scores", method = RequestMethod.GET)
	public List<Score> indexScores(@PathVariable int id) {
		return quizdao.indexScore(id);
	}

	@RequestMapping(path = "/quizzes/{id}", method = RequestMethod.GET)
	public Quiz show(@PathVariable int id) {
		return quizdao.show(id);
	}
	
//	@RequestMapping(path = "/quizzes/{id}/questions", method = RequestMethod.GET)
//	public Set<Question> indexQuestions(@PathVariable int id) {
//		return quizdao.indexQuestions(id);
//	}

	@RequestMapping(path = "/quizzes", method = RequestMethod.POST)
	public Quiz create(@RequestBody String QuizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		Quiz newQuiz = null;
		try {
			newQuiz = mapper.readValue(QuizJSON, Quiz.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizdao.create(newQuiz);

	}

	@RequestMapping(path = "/quizzes/{id}", method = RequestMethod.DELETE)
	public void destroy(@PathVariable int id) {
System.out.println("IN DELETE ROUTE");
		try {
			quizdao.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(path = "/quizzes/{id}", method = RequestMethod.PUT)
	public Quiz update(@PathVariable int id, @RequestBody String QuizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		Quiz editQuiz = null;
		try {
			editQuiz = mapper.readValue(QuizJSON, Quiz.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizdao.update(id, editQuiz);

	}

	@RequestMapping(path = "/quizzes/{id}/questions/{id}", method = RequestMethod.POST)
	public void destroyQuestion(@PathVariable int id) {
		try {
			quizdao.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(path = "/quizzes/{id}/questions", method = RequestMethod.POST)
	public Question createQuestion(@RequestBody String QuestionJSON, @PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		Question newQuestion = null;
		try {
			newQuestion = mapper.readValue(QuestionJSON, Question.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizdao.createQuestion(id, newQuestion);

	}
}

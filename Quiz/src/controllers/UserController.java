package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserDAO;
import entities.Score;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(path="/ping", method=RequestMethod.GET)
	public String ping(){
		String pong = "pong";
		return pong;
	}
	
	@RequestMapping(path="/users", method=RequestMethod.GET)
	public List<User> index(){
		return userDAO.index();
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.GET)
	public User show(@PathVariable int id){
		return userDAO.show(id);
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public User create(@RequestBody String UserJSON){
		ObjectMapper mapper = new ObjectMapper();
		User newUser = null;
		try {
		  newUser = mapper.readValue(UserJSON, User.class);
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return userDAO.create(newUser);

	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){

		try {
			userDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.PUT)
	public User update(@PathVariable int id, @RequestBody String UserJSON){
		ObjectMapper mapper = new ObjectMapper();
		User editUser = null;
		try {
			editUser = mapper.readValue(UserJSON, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDAO.update(id, editUser);
		
	}
	@RequestMapping(path="/users/1/scores/{quizId}", method=RequestMethod.POST)
	public void addScoreToUser(@PathVariable int quizId, @RequestBody String JSON){
		ObjectMapper mapper = new ObjectMapper();
		Score score = null;
		try {
			score = mapper.readValue(JSON, Score.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 userDAO.addScoreToUser(quizId, score);

		
		
	}
}

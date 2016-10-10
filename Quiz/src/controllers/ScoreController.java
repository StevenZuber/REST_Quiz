package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ScoreDAO;
import entities.Score;
@RestController
public class ScoreController {

	@Autowired
	private ScoreDAO scoredao;
	
	@RequestMapping(path="/marco", method=RequestMethod.GET)
	public String marco(){
		String polo = "polo";
		return polo;
	}
	

	
	@RequestMapping(path="/scores", method=RequestMethod.GET)
	public List<Score> index(){
		return scoredao.index();
	}
	
	@RequestMapping(path="/scores/{id}", method=RequestMethod.GET)
	public Score show(@PathVariable int id){
		return scoredao.show(id);
	}
	
	@RequestMapping(path="/scores", method=RequestMethod.POST)
	public Score create(@RequestBody String ScoreJSON){
		ObjectMapper mapper = new ObjectMapper();
		Score newScore = null;
		try {
		  newScore = mapper.readValue(ScoreJSON, Score.class);
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return scoredao.create(newScore);

	}
	
	@RequestMapping(path="/scores/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){

		try {
			scoredao.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path="/scores/{id}", method=RequestMethod.PUT)
	public Score update(@PathVariable int id, @RequestBody String ScoreJSON){
		ObjectMapper mapper = new ObjectMapper();
		Score editScore = null;
		try {
			editScore = mapper.readValue(ScoreJSON, Score.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoredao.update(id, editScore);
		
	}
	
}

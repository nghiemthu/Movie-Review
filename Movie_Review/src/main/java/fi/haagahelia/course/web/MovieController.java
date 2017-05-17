package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.GenreRepository;
import fi.haagahelia.course.domain.Movie;
import fi.haagahelia.course.domain.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository repository; 

	@Autowired
	private GenreRepository grepository; 
	
	
	// login
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	// Show all movies
    @RequestMapping(value="/movielist")
    public String movieList(Model model) {	
        model.addAttribute("movies", repository.findAll());
        return "movielist";
    }
    
    // Show a movie
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showMovie(@PathVariable("id") Long movieId, Model model) {
    	model.addAttribute("movie", repository.findOne(movieId));
        return "show";
    }
    
    // edit movie
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMovie(@PathVariable("id") Long id, Model model){
    	model.addAttribute("movie", repository.findOne(id));
    	model.addAttribute("genres", grepository.findAll());
        return "edit";
    } 
    
    // update new movie
    @RequestMapping(value = "/edit/update", method = RequestMethod.POST)
    public String Update(Movie movie){
        repository.delete(movie.getId());
        Movie newMovie = repository.save(movie);
        return "redirect:../show/" + newMovie.getId();
    }
    
    // Add new movie
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addMovie(Model model){
    	model.addAttribute("movie", new Movie());
    	model.addAttribute("genres", grepository.findAll());
        return "addmovie";
    }     
    
    // Save new movie
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Movie movie){
        repository.save(movie);
        return "redirect:movielist";
    }    

    // Delete movie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
    	repository.delete(movieId);
        return "redirect:../movielist";
    }
  
	// RESTful service to get all movies
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {	
        return (List<Movie>) repository.findAll();
    }    

	// RESTful service to get movie by id
    @RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
    public @ResponseBody Movie findMovieRest(@PathVariable("id") Long movieId) {	
    	return repository.findOne(movieId);
    }       
    
         
}

package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.Genre;
import fi.haagahelia.course.domain.GenreRepository;
import fi.haagahelia.course.domain.Movie;
import fi.haagahelia.course.domain.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
    private MovieRepository repository;
	
	@Autowired
    private GenreRepository grepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Movie> movies = repository.findByTitle("Hit");
        
        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("H");
    }
    
    @Test
    public void createMovie() {
    	Movie movie = new Movie("Guardian Of The Galaxy Vol.3", 
				"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
				9.3, "eger", "Something new", grepository.findByName("Action").get(0));
    	repository.save(movie);
    	assertThat(movie.getId()).isNotNull();
    } 
}
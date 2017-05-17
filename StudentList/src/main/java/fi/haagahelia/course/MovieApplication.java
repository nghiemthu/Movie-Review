package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Genre;
import fi.haagahelia.course.domain.GenreRepository;
import fi.haagahelia.course.domain.Movie;
import fi.haagahelia.course.domain.MovieRepository;

@SpringBootApplication
public class MovieApplication {
	private static final Logger log = LoggerFactory.getLogger(MovieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepository, GenreRepository grepository) {
		return (args) -> {
			log.info("save a couple of movies");
			grepository.save(new Genre("Romatic"));
			grepository.save(new Genre("Action"));
			grepository.save(new Genre("Mystery"));
			
			mrepository.save(new Movie("Guardian Of The Galaxy Vol.2", 
					"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
					8.3, "Thunghiem", "Something new", grepository.findByName("Action").get(0)));
			mrepository.save(new Movie("Guardian Of The Galaxy Vol.1", 
					"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
					4.3, "324", "Something new", grepository.findByName("Action").get(0)));
			mrepository.save(new Movie("Guardian Of The Galaxy Vol.3", 
					"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
					9.3, "eger", "Something new", grepository.findByName("Action").get(0)));
	
			
			log.info("fetch all movies");
			for (Movie movie : mrepository.findAll()) {
				log.info(movie.toString());
			}

		};
	}
}

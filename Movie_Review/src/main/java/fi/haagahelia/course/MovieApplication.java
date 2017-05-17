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
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class MovieApplication {
	private static final Logger log = LoggerFactory.getLogger(MovieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepository, GenreRepository grepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of movies");
			grepository.save(new Genre("Romatic"));
			grepository.save(new Genre("Action"));
			grepository.save(new Genre("Mystery"));
			grepository.save(new Genre("Sci-fi"));
			grepository.save(new Genre("Crime"));
			
			mrepository.save(new Movie("Guardian Of The Galaxy Vol.2", 
					"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
					8.3, "Thu", "Peter Quill and his fellow Guardians are hired by a powerful alien race, the Sovereign, to protect their precious batteries from invaders. When it is discovered that Rocket has stolen the items they were sent to guard, ", grepository.findByName("Action").get(0)));
			mrepository.save(new Movie("Before I fall", 
					"http://t3.gstatic.com/images?q=tbn:ANd9GcQoFZ6539yiBN782jHgD_qCMAcG6ueii60SOyNRFXAgPiUO0DUw", 
					4.3, "Kayo", "Samantha Kingston (Zoey Deutch) seems to have it all: popularity, a loving boyfriend (Kian Lawley) and a seemingly perfect future. Everything changes in the blink of an eye when she dies in a car crash but then", grepository.findByName("Mystery").get(0)));
			mrepository.save(new Movie("Beauty And The Beast", 
					"http://t2.gstatic.com/images?q=tbn:ANd9GcRfYpMZYI-nnFJZ6vmdH7w8qNCp_G2OwqWQqszSdhMbfLR_CHvi", 
					9.3, "Giang", "Belle (Emma Watson), a bright, beautiful and independent young woman, is taken prisoner by a beast (Dan Stevens) in its castle. Despite her fears, she befriends the castle's enchanted staff and learns to look beyond", grepository.findByName("Romatic").get(0)));
			mrepository.save(new Movie("Logan", 
					"http://t1.gstatic.com/images?q=tbn:ANd9GcRPoMqL1vglrh7OF_69pT8gYMYnYaq1r7WfPMcD587V9uOR_hW2", 
					9.9, "Bii", "In the near future, a weary Logan (Hugh Jackman) cares for an ailing Professor X (Patrick Stewart) at a remote outpost on the Mexican border. ", grepository.findByName("Action").get(0)));
			
			
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "123456", "user@gmail.com", "USER");
			User user2 = new User("admin", "1234567","user@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all movies");
			for (Movie movie : mrepository.findAll()) {
				log.info(movie.toString());
			}

		};
	}
}

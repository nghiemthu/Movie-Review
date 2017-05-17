package fi.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String img;
    private double grade;
    private String review;
    private String reviewer;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "genreid")
    private Genre genre;

    public Movie() {}

	public Movie(String title, String img, double grade, String review, String reviewer, Genre genre) {
		super();
		this.title = title;
		this.grade = grade;
		this.review = review;
		this.img = img;
		this.reviewer = reviewer;
		this.genre = genre;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setViewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
}

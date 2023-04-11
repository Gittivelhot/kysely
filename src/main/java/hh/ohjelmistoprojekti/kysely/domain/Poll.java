package hh.ohjelmistoprojekti.kysely.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Poll {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long poll_id;
	private String title;
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @JsonIgnoreProperties("jsonpolls")
    private List<Question> questions;
    
    
	public Poll(String title) {
		super();
		this.title = title;
	}
	
	public Poll() {
		super();
		this.poll_id = null;
		this.questions = null;
		this.title = null;
	}
	public Long getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(Long poll_id) {
        this.poll_id = poll_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
	@Override
	public String toString() {
		return "Poll [poll_id=" + poll_id + ", title=" + title + ", questions=" + this.getQuestions() + "]";
	}

}

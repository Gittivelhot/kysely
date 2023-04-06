package hh.ohjelmistoprojekti.kysely.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Poll {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long poll_id;
	private String title;
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
    
    
	public Poll(Long poll_id, String title, List<Question> questions) {
		super();
		this.poll_id = poll_id;
		this.title = title;
		this.questions = questions;
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

    public void addQuestion(Question question) {
        questions.add(question);
        question.setPoll(this);
    }
    
	@Override
	public String toString() {
		return "Poll [poll_id=" + poll_id + ", title=" + title + ", questions=" + questions + "]";
	}

}

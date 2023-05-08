package hh.ohjelmistoprojekti.kysely.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Poll {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long poll_id;
	private String title;
	
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("jsonpolls")
    private List<Question> questions;
    
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<PollAnswer> answers;
    
    @ManyToOne
	@JoinColumn ( name = "username")
    @JsonIgnore
	private User user;
    @JsonIgnore
    private Boolean visible;
    
    
	public Poll(String title, User user) {
		super();
		this.title = title;
		this.user = user;
		this.visible = true;
	}

	public Poll() {
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

	public List<PollAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<PollAnswer> answers) {
		this.answers = answers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Poll [poll_id=" + poll_id + ", title=" + title + ", answerCount = " + answers.size() +"]";
	}

}

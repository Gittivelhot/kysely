package hh.ohjelmistoprojekti.kysely.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PollAnswer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long poll_id;
	
	@ManyToOne
	@JsonIgnore
	private Poll poll;

	@OneToMany(mappedBy = "pollform")
	@JsonIgnoreProperties("pollform")
	private List<Answer> answers;
	
	public PollAnswer(Long poll, List<Answer> answers) {
		super();
		this.poll_id = poll;
		this.answers = answers;
	}
	
	public PollAnswer() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(Long poll_id) {
		this.poll_id = poll_id;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "PollAnswer [poll=" + poll_id + ", PollanswerCount = " + answers.size() +"]";
	}
}

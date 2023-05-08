package hh.ohjelmistoprojekti.kysely.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private PollAnswer pollform;
	
	private String question;
	private String answer;
	
	public Answer(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	
	public Answer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PollAnswer getPollform() {
		return pollform;
	}

	public void setPollform(PollAnswer pollform) {
		this.pollform = pollform;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "AnswerForm [question=" + question + ", answer=" + answer + "]";
	}
}

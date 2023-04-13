package hh.ohjelmistoprojekti.kysely.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long reply_id;
    private String reply;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Question question;

    
    public Answer() {}
    
	public Answer(String reply, Question question) {
		super();
		this.reply = reply;
		this.question = question;
	}
	

	public Long getReply_id() {
		return reply_id;
	}

	public void setReply_id(Long reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		if (this.question != null)
		return "Answer [reply_id=" + reply_id + ", reply=" + reply + ", question=" + this.getQuestion() + "]";
		else
			return "Answer [reply_id=" + reply_id + ", reply=" + reply + "]";
	}
    
    
}

package hh.ohjelmistoprojekti.kysely.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private String query;
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    
	public Question(String query, Poll poll) {
		super();
		this.query = query;
		this.poll = poll;
	}
	
	public Question() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", query=" + query + ", poll=" + poll + "]";
	}
    
}
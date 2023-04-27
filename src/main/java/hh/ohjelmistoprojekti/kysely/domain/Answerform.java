package hh.ohjelmistoprojekti.kysely.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Answerform {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String reply_id;
	private String reply;
	
	
	public Answerform() {}
	
	public Answerform(String reply_id, String reply) {
		super();
		this.reply_id = reply_id;
		this.reply = reply;
	}
	
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Answerform [reply_id=" + reply_id + ", reply=" + reply + "]";
	}
		
	
}

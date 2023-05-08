package hh.ohjelmistoprojekti.kysely.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long>{

	List<Poll> findByTitle(String title);
	
	List<Poll> findByVisibleTrue();
	
}

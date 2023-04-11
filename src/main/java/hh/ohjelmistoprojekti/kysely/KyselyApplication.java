package hh.ohjelmistoprojekti.kysely;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.ohjelmistoprojekti.kysely.domain.Poll;
import hh.ohjelmistoprojekti.kysely.domain.PollRepository;
import hh.ohjelmistoprojekti.kysely.domain.Question;
import hh.ohjelmistoprojekti.kysely.domain.QuestionRepository;

@SpringBootApplication
public class KyselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyselyApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(PollRepository prepository, QuestionRepository qrepository) 
	{return (args) -> {
        Poll p1 = new Poll("banaani kysely");
        prepository.save(p1);

        Question q1 = new Question();
        q1.setQuery("Onko banaani keltainen?");
        q1.setPoll(p1);
        qrepository.save(q1);

        Question q2 = new Question();
        q2.setQuery("Onko banaani punainen?");
        q2.setPoll(p1);
        qrepository.save(q2);

        Question q3 = new Question();
        q3.setQuery("Onko banaani sininen?");
        q3.setPoll(p1);
        qrepository.save(q3);
        
        prepository.save(p1);

        System.out.println(p1);
        System.out.println(q1);
	};

	
}

}

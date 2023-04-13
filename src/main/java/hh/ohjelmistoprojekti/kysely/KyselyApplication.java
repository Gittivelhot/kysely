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
        Poll p2 = new Poll("vaate kysely");
        Poll p3 = new Poll("lempiruoka kysely");
        prepository.save(p1);
        prepository.save(p2);
        prepository.save(p3);

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
        
        
        Question q4 = new Question();
        q4.setQuery("Kengänkoko?");
        q4.setPoll(p2);
        qrepository.save(q4);

        Question q5 = new Question();
        q5.setQuery("Pituus?");
        q5.setPoll(p2);
        qrepository.save(q5);

        Question q6 = new Question();
        q6.setQuery("Vaatekoko?");
        q6.setPoll(p2);
        qrepository.save(q6);
        
        
        Question q7 = new Question();
        q7.setQuery("Mikä on lempijuomasi?");
        q7.setPoll(p3);
        qrepository.save(q7);

        Question q8 = new Question();
        q8.setQuery("Mikä on lempiruokasi?");
        q8.setPoll(p3);
        qrepository.save(q8);


	};

	
}

}

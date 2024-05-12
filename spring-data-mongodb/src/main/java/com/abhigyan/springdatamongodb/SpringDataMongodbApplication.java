package com.abhigyan.springdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringDataMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongodbApplication.class, args);
	}


	//see : CommandLineRunner is a functional Interface , it is used to perform initial tasks required by the application
	//an example can be , feeding initial (setup) data to database
	//also the @Bean without @Configuration seems to work just fine
	@Bean //commandLineRunner bean always needs to be registered, either this way or by @component
		CommandLineRunner commandLineRunner
			(StudentRepository studentRepository, MongoTemplate mongoTemplate){
			return (args) -> {

				Address address = new Address("India",
						"New Delhi","777777");

				Student student=new Student("Simmi",
						"Sharma",
						"simmi@ji.gmail" ,
						Gender.FEMALE,
						address,
						List.of("Math","Physics","Biology"),
						BigDecimal.TEN,
						LocalDateTime.now()
				);

				// earlier we could simply insert using studentRepository.insert(student);,
				//but now we shall see if email already exists and proceed with caution ,
				//SYNTAX WARNING : mongotemplate param was added and it used Query obj (see)
				Query query=new Query();
				query.addCriteria(Criteria.where("email").is("simmi@ji.gmail"));

				List<Student> students=mongoTemplate.find(query, Student.class);

				if(students.size()>1){ //can be redundant as unique index is in place
					throw new IllegalStateException("multiple students with same email , a problem anyway");
				}


				if(students.isEmpty()){
					System.out.println("inserting student mentioned in command line runner");
					studentRepository.insert(student); //only called when the email is not registered , helps only the commandlinerunner
				}

				else{
					System.out.println("student mentioned in command line runner already exists , insertion skipped ");
				}



		} ;

	}

}

//TO BE NOTED:
//even though we have used the Query object in conjunction with Mongotempate , which has become quite some code(probably imperative)

//in events like these , we may also choose the mongoRepository custom methods (declarative) : SYNTAX WARNING:
/*
repository.findStudentByEmail("simmi@ji.gmail)
.isPresentOrElse (
	s->{
	System.out.println("student mentioned in command line runner already exists")
	}  ,

	()->{
	System.out.println("inserting student mentioned in the command line runner");
	studentRepository.insert(student);
	}
);

>>>>>>>>>>>>>this snippet works exactly like the above mentioned query+template format
 */
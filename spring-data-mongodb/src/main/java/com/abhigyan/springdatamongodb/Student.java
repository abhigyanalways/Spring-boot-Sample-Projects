package com.abhigyan.springdatamongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//Model

@Data //lombok : generates access functions automatically
@Document //this is required to specify an entity like bean in mongoDB
public class Student {

    @Id
    private String id; //remember , this object is a record and will have a primary key

    private String firstName;
    private String lastName;
    @Indexed(unique = true) //added this later to specify (see:) database index (which is unique)
    private String email; //we shall see that finding by (indexed) email will require custom querying (template)
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;


    //making this function for the commandLineRunner
    //not using @AllArgsConstructor from LOMBOK,because we need to exclude id (auto gen)
    public Student
            (String firstName,
             String lastName,
             String email,
             Gender gender,
             Address address,
             List<String> favouriteSubjects,
             BigDecimal totalSpentInBooks,
             LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}

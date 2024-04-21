package com.abhigyan.springdatamongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StudentRepository
        extends MongoRepository<Student,String> {

    //today i realised once again that in case we dont have a default method ,
    //spring automatically generates one for us based on the name of method we mention/specify:


    Optional<Student> findStudentByEmail(String email);
    //it can be noted that this is , at times , an alternative to query+template method

    //we may also use other methods like :
    //@Query("...")
    //Student test();

    void deleteByEmail(String email); //custom function from mongodb
}

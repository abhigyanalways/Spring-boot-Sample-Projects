package com.abhigyan.springdatamongodb;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



public class StudentDTO { //making a DTO for adding students
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
   // private LocalDateTime created;

    public StudentDTO
            (String firstName, String lastName, String email, Gender gender, Address address, List<String> favouriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFavouriteSubjects() {
        return favouriteSubjects;
    }

    public void setFavouriteSubjects(List<String> favouriteSubjects) {
        this.favouriteSubjects = favouriteSubjects;
    }

    public BigDecimal getTotalSpentInBooks() {
        return totalSpentInBooks;
    }

    public void setTotalSpentInBooks(BigDecimal totalSpentInBooks) {
        this.totalSpentInBooks = totalSpentInBooks;
    }



}

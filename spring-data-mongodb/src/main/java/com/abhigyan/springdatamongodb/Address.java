package com.abhigyan.springdatamongodb;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //required for commandLineRunner
public class Address {
    private String country;
    private String city;
    private String postcode;
}

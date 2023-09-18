package com.example.car_app.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Value;

@Value
@JsonInclude(Include.NON_NULL)
public class User {

    Integer id;

    String firstName;

    String lastName;

    String email;

    Double money;

    String  type;
}

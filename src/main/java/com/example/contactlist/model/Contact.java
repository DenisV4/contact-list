package com.example.contactlist.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Builder
@Data
@FieldNameConstants
public class Contact {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}

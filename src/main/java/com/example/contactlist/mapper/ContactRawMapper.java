package com.example.contactlist.mapper;

import com.example.contactlist.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRawMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Contact
                .builder()
                .id(rs.getString(Contact.Fields.id))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString(Contact.Fields.email))
                .phone(rs.getString(Contact.Fields.phone))
                .build();
    }
}

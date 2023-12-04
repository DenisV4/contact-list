package com.example.contactlist.repository;

import com.example.contactlist.mapper.ContactRawMapper;
import com.example.contactlist.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JdbcContactRepository implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        var sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, new ContactRawMapper());
    }

    @Override
    public Optional<Contact> findById(String id) {
        var sql = "SELECT * FROM contacts WHERE id = ?";
        var contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRawMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        contact.setId(UUID.randomUUID().toString());

        var sql = "INSERT INTO contacts (id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone()
        );

        return contact;
    }

    @Override
    public Optional<Contact> update(Contact contact) {
        var optionalContact = findById(contact.getId());
        if (optionalContact.isPresent()) {
            var sql = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone(),
                    contact.getId()
            );

            return optionalContact;
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {
        var sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

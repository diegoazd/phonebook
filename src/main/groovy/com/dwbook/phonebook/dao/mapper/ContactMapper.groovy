package com.dwbook.phonebook.dao.mapper

import com.dwbook.phonebook.representations.Contact

import java.sql.SQLException

import org.skife.jdbi.v2.tweak.ResultSetMapper
import org.skife.jdbi.v2.StatementContext

import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Timestamp

class ContactMapper implements ResultSetMapper<Contact>{

  Contact map (int index, ResultSet r, StatementContext ctx) throws SQLException {
    return new Contact (r.getInt ('id'),
      r.getString ('firstName'),
      r.getString ('lastName'),
      r.getString ('phone'))
  }
}

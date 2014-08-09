package com.dwbook.phonebook.dao

import com.dwbook.phonebook.representations.Contact
import com.dwbook.phonebook.dao.mapper.ContactMapper

import org.skife.jdbi.v2.sqlobject.*
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

interface ContactDAO {
  @RegisterMapper (ContactMapper)
  @SqlQuery ("select id,firstName,lastName,phone from contact where id = :id")
  Contact getContactById (@Bind ('id') Integer id)

  @GetGeneratedKeys
  @SqlUpdate ("insert into contact (id, firstName, lastName, phone) values (NULL, :firstName, :lastName, :phone)")
  int createContact (@Bind ("firstName") String firstName, @Bind ("lastName") lastName,
                     @Bind ("phone") String phone)

  @SqlUpdate ("update contact set firstName = :firstName, lastName = :lastName, phone = :phone where id = :id")
  void updateContact (@Bind ("id") int id, @Bind ("firstName") String firstName, @Bind ("lastName") lastName, @Bind ("phone") String phone)

  @SqlUpdate ("delete from contact where id = :id")
  void deleteContact (@Bind ("id") int id)
}

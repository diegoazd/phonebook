package com.dwbook.phonebook.resources

import com.dwbook.phonebook.dao.ContactDAO
import com.dwbook.phonebook.representations.Contact

import javax.ws.rs.*
import javax.ws.rs.core.*
import org.skife.jdbi.v2.DBI

import java.net.URI
import java.net.URISyntaxException

import javax.validation.ConstraintViolation
import com.yammer.dropwizard.validation.Validator
import javax.ws.rs.core.Response.Status
import javax.validation.Valid

@Path ('/contact')
@Produces (MediaType.APPLICATION_JSON)
class ContactResource {
  private final ContactDAO contactDao
  private final Validator validator

  public ContactResource (DBI jdbi, Validator validator){
    contactDao = jdbi.onDemand (ContactDAO.class)
    this.validator = validator
  }


  @GET
  @Path ('/{id}')
  public Response getContact (@PathParam ('id') int id) {
    Contact contact = contactDao.getContactById (id)
    return Response.ok(contact).build()
  }

  @POST
  Response createContact (@Valid Contact contact) throws URISyntaxException {
    //Set<ConstraintViolation<Contact>> violations = validator.validate (contact)
    //if (violations){
      //ArrayList<String> validationMessages = new ArrayList<String> ()
      //violations.each{ violation ->
        //validationMessages.add (violation)
      //}
      //println validationMessages
      //return Response.status (Status.BAD_REQUEST).entity (validationMessages).build ()
    //}

    int newContactId  = contactDao.createContact (contact.firstName, contact.lastName, contact.phone)
    return Response.created (new URI (String.valueOf (newContactId))).build ()
  }

  @DELETE
  @Path ('/{id}')
  Response deleteContact (@PathParam ('id') int id) {
    contactDao.deleteContact (id)
    return Response.noContent ().build ()
  }

  @PUT
  @Path ('/{id}')
  Response updateContact (@PathParam ('id') int id,
    Contact contact) {
      Set<ConstraintViolation<Contact>> violations = validator.validate (contact)
      if (violations){
        ArrayList<String> validationMessages = new ArrayList<String> ()
        violations.each{ violation ->
          validationMessages.add (violation)
        }
        return Response.status (Status.BAD_REQUEST).entity (validationMessages).build ()
      }
      contactDao.updateContact (id, contact.firstName, contact.lastName, contact.phone)
      return Response.ok(contact).build()
    }
}

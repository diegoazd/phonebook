package com.dwbook.phonebook.representations

import org.hibernate.validator.constraints.*
import com.fasterxml.jackson.annotation.JsonIgnore
import com.yammer.dropwizard.validation.ValidationMethod

class Contact {
  final Integer id

  @NotBlank
  @Length (min=2, max=255)
  final String firstName

  @NotBlank
  @Length (min=2, max=255)
  final String lastName

  @NotBlank
  @Length (min=7, max=15)
  final String phone

  public Contact () {
    this.id = 0
    this.firstName = null
    this.lastName = null
    this.phone = null
  }

  public Contact (Integer id, String firstName, String lastName, String phone) {
    this.id = id
    this.firstName = firstName
    this.lastName = lastName
    this.phone = phone
  }
 
  @JsonIgnore
  @ValidationMethod (message="John Doe should not pass!")
  boolean isValidPerson (){
    if (firstName.equals ('John') && lastName.equals ("Doe")){
      return false
    }
    true
  }
}

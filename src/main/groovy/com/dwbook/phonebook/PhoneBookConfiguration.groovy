package com.dwbook.phonebook

import com.fasterxml.jackson.annotation.JsonProperty
import com.yammer.dropwizard.db.DatabaseConfiguration
import com.yammer.dropwizard.config.Configuration

import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Max
import org.hibernate.validator.constraints.NotEmpty

class PhoneBookConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
     DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration()


    @JsonProperty
    @NotEmpty
    String message

    @JsonProperty
    @Max(10L)
    Long messageRepetitions

    @JsonProperty
    String additionalMesage = 'This is optional'

    //@JsonProperty
    //DataSourceFactory database = new DataSourceFactory ()
}

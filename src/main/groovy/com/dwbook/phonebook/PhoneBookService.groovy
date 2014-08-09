package com.dwbook.phonebook

import com.dwbook.phonebook.resources.ContactResource
import com.yammer.dropwizard.Service
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.db.DatabaseConfiguration
import com.yammer.dropwizard.hibernate.HibernateBundle
import com.yammer.dropwizard.migrations.MigrationsBundle

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.skife.jdbi.v2.DBI
import com.yammer.dropwizard.jdbi.DBIFactory

class PhoneBookService extends Service<PhoneBookConfiguration> {
    Logger logger = LoggerFactory.getLogger (PhoneBookService.class)
    public static void main(String[] args) throws Exception {
        new PhoneBookService().run(args)
    }

    HibernateBundle<PhoneBookConfiguration> hibernateBundle =
        new HibernateBundle<PhoneBookConfiguration>([]) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(PhoneBookConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    MigrationsBundle<PhoneBookConfiguration> migrationsBundle =
        new MigrationsBundle<PhoneBookConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(PhoneBookConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    AssetsBundle assetsBundle = new AssetsBundle()

    @Override
    public void initialize(Bootstrap<PhoneBookConfiguration> bootstrap) {
        bootstrap.with {
            name = 'PhoneBook'
            addBundle migrationsBundle
            addBundle hibernateBundle
        }
    }

    @Override
    public void run(PhoneBookConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
      logger.info ("run methods");
       final DBIFactory factory = new DBIFactory ()
       final DBI jdbi = factory.build (environment,configuration.databaseConfiguration, "mysql")

      ContactResource contactResource = new ContactResource (jdbi, environment.validator)
      environment.addResource (contactResource)
    }
}

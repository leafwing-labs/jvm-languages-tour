#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grapes([
        @Grab(group = 'org.apache.groovy', module = 'groovy-sql', version = '4.0.22'),
        @Grab(group = 'org.postgresql', module = 'postgresql', version = '42.2.23')
])

import groovy.sql.Sql

def url = 'jdbc:postgresql://localhost:5432/mydatabase'
def user = 'myuser'
def password = 'mypassword'

def sql = Sql.newInstance(url, user, password, "org.postgresql.Driver")

sql.eachRow('SELECT * FROM product') { row ->
    println "${row.name} - ${row.description} = ${row.price}"
}

sql.close()
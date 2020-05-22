package com.demobackend.restapi.business

import com.demobackend.restapi.model.Person

interface IPersonaBusiness {

    fun list(): List<Person>
    fun load(idPerson:Long) : Person
    fun save(person: Person) :Person
    fun remove(idPerson: Long)

}
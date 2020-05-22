package com.demobackend.restapi

import com.demobackend.restapi.dao.PersonRepository
import com.demobackend.restapi.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication : CommandLineRunner{

	@Autowired
    val personRepository : PersonRepository? = null

	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val persona1 = Person(123,"Oscar","Jimenez", LocalDate.parse("21-05-1980",formatter))

		personRepository!!.save(persona1)
	}

}

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}

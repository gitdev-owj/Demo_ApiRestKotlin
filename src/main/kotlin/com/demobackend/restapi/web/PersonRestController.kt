package com.demobackend.restapi.web

import com.demobackend.restapi.business.IPersonaBusiness
import com.demobackend.restapi.exception.BusinessException
import com.demobackend.restapi.exception.NotFoundException
import com.demobackend.restapi.model.Person
import com.demobackend.restapi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSONS)
class PersonRestController {

    @Autowired
    val personBusiness  : IPersonaBusiness? = null

    @GetMapping("")
    fun list() : ResponseEntity<List<Person>>{
        return try {
            ResponseEntity(personBusiness!!.list(), HttpStatus.OK)
        }catch(e: Exception){
           ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPerson: Long) : ResponseEntity<Person>{
        return try {
            ResponseEntity(personBusiness!!.load(idPerson ),HttpStatus.OK);
        }catch(e: Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("")
    fun insert(@RequestBody person : Person) : ResponseEntity<Any>{
        return try {
          personBusiness!!.save(person)
          val responseHeader = HttpHeaders()
          responseHeader.set("location",Constants.URL_BASE_PERSONS + "/ " +person.id)
          ResponseEntity(responseHeader,HttpStatus.CREATED)


        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody person: Person) : ResponseEntity<Any>{
        return try {
            personBusiness!!.save(person)
            ResponseEntity(HttpStatus.OK)
        }catch (e: Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPerson : Long) :ResponseEntity<Any>{
        return try {
            personBusiness!!.remove(idPerson)
            ResponseEntity(HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }


}
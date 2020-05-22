package com.demobackend.restapi.business

import com.demobackend.restapi.dao.PersonRepository
import com.demobackend.restapi.exception.BusinessException
import com.demobackend.restapi.exception.NotFoundException
import com.demobackend.restapi.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonBusiness : IPersonaBusiness {
    @Autowired
    val personRepository: PersonRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Person> {
        try {
            return personRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun load(idPerson: Long): Person {
        val op: Optional<Person>
        try {
            op = personRepository!!.findById(idPerson)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (op.isPresent) {
            throw NotFoundException("Person with id $idPerson not found")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(person: Person): Person {
        try {
         return personRepository!!.save(person)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun remove(idPerson: Long) {
        val op : Optional<Person>
       try {
          op = personRepository!!.findById(idPerson)
       }catch (e: Exception){
           throw BusinessException(e.message)
       }

        if(!op.isPresent){
            throw NotFoundException("Person with id $idPerson not found")
        }else{
            try {
               personRepository!!.deleteById(idPerson)
            }catch (e: Exception){
                throw BusinessException(e.message)
            }
        }

    }

}
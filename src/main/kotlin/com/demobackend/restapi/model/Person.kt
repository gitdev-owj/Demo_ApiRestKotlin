package com.demobackend.restapi.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name ="Person")
data class Person (val dni : Long = 0 , val nombre :String = "",
                   val apellido : String ="", val fechaNacimiento : LocalDate ? = null) {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 var id: Long = 0

}
package com.example.logic

import java.sql.Timestamp

import com.example.logic.factory.CatFactoryImpl
import com.example.model.Cat
import com.example.web.form.CatInputForm
import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

/*
  Example of unit test without mocks
*/

class CatFactoryTest extends FlatSpec with Matchers {
  val catFactory = new CatFactoryImpl()

  "Form" should "be converted to entity" in {
    val name = "Meow"
    val groupId: Long = 1
    val birthDate = new DateTime(10000)
    val form = new CatInputForm(name, groupId, birthDate)

    val entity = catFactory.createEntity(form)

    entity.name should be (name)
    entity.groupId should be (groupId)
    entity.birthDate.getTime should be (birthDate.getMillis)
  }

  "Entity" should "be converted to form" in {
    val id = 1L
    val name = "Meow"
    val groupId: Long = 1
    val birthDate = new Timestamp(10000)
    val entity = new Cat(Some(id), name, groupId, birthDate)

    val form = catFactory.createForm(entity)

    form.name should be (name)
    entity.groupId should be (groupId)
    form.birthDate.getMillis should be (birthDate.getTime)
  }
}

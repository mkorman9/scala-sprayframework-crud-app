package com.example.logic.factory

import java.sql.Timestamp

import com.example.model.{Cat, CatGroup}
import com.example.web.form.{CatGroupInputForm, CatOutputForm}
import org.joda.time.DateTime
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

/*
  Example of unit test with mocks
*/

class CatGroupFactoryTest extends FlatSpec with Matchers with MockFactory {
  val catFactory = stub[CatFactory]
  val catGroupFactory = new CatGroupFactoryImpl(catFactory)

  "Form" should "be converted to entity" in {
    val name = "Pirates"
    val form = new CatGroupInputForm(name)

    val entity = catGroupFactory.createEntity(form)

    entity.name should be (name)
  }

  "Entity" should "be converted to form" in {
    def mockCatsFactory: List[Cat] = {
      val catsInGroup = List(
        new Cat(Some(1L), "Jack", 1L, new Timestamp(1)),
        new Cat(Some(2L), "Daniels", 1L, new Timestamp(1)))
      val catsInGroupForms = List(
        new CatOutputForm("Jack", new DateTime(1)),
        new CatOutputForm("Daniels", new DateTime(1)))

      (catFactory.createForm _).when(catsInGroup(0)).returns(catsInGroupForms(0))
      (catFactory.createForm _).when(catsInGroup(1)).returns(catsInGroupForms(1))

      catsInGroup
    }

    val id = Some(1L)
    val name = "Pirates"
    val entity = new CatGroup(id, name)
    val catsInGroup: List[Cat] = mockCatsFactory

    val form = catGroupFactory.createForm(entity)((id: Long) => catsInGroup)

    form.name should be (name)
    form.cats(0).name should be (catsInGroup(0).name)
    form.cats(1).name should be (catsInGroup(1).name)
  }
}

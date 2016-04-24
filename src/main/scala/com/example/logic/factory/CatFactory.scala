package com.example.logic.factory

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.{Sex, Cat, CatGroup}
import com.example.web.form.{CatInputForm, CatOutputForm}
import com.google.inject.ImplementedBy
import org.joda.time.DateTime

/*
  Provides conversion between domain model of Cat and it's serializable representation (form)
*/

@ImplementedBy(classOf[CatFactoryImpl])
trait CatFactory {
  def createEntity(catForm: CatInputForm): Cat
  def createForm(cat: Cat): CatOutputForm
}

@Singleton
class CatFactoryImpl extends CatFactory {

  override def createEntity(catForm: CatInputForm): Cat = {
    new Cat(None,
      catForm.name,
      catForm.sex,
      catForm.groupId,
      new Timestamp(catForm.birthDate.getMillis)
    )
  }

  override def createForm(cat: Cat): CatOutputForm = {
    new CatOutputForm(cat.name,
      cat.sex,
      new DateTime(cat.birthDate.getTime)
    )
  }
}

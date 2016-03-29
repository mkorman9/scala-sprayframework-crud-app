package com.example.logic

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.{CatGroup, Cat}
import com.example.web.form.{CatOutputForm, CatGroupOutputForm, CatInputForm}
import com.google.inject.ImplementedBy
import org.joda.time.DateTime

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
      catForm.groupId,
      new Timestamp(catForm.birthDate.getMillis)
    )
  }

  override def createForm(cat: Cat): CatOutputForm = {
    new CatOutputForm(cat.name,
      new CatGroupOutputForm(cat.groupId, ""), // TODO
      new DateTime(cat.birthDate.getTime)
    )
  }
}

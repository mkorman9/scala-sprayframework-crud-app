package com.example.logic.factory

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.Cat
import com.example.web.form.{CatGroupOutputForm, CatInputForm, CatOutputForm}
import com.google.inject.{ImplementedBy, Inject}
import org.joda.time.DateTime

@ImplementedBy(classOf[CatFactoryImpl])
trait CatFactory {
  def createEntity(catForm: CatInputForm): Cat
  def createForm(cat: Cat): CatOutputForm
}

@Singleton
class CatFactoryImpl @Inject() (catGroupFactory: CatGroupFactory) extends CatFactory {

  override def createEntity(catForm: CatInputForm): Cat = {
    new Cat(None,
      catForm.name,
      catForm.groupId,
      new Timestamp(catForm.birthDate.getMillis)
    )
  }

  override def createForm(cat: Cat): CatOutputForm = {
    new CatOutputForm(cat.name,
      new CatGroupOutputForm(cat.groupId, ""), // TODO: use catGroupFactory to create output form
      new DateTime(cat.birthDate.getTime)
    )
  }
}

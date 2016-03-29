package com.example.logic

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.{CatGroup, Cat}
import com.example.web.form.{CatGroupForm, CatForm}
import com.google.inject.ImplementedBy
import org.joda.time.DateTime

@ImplementedBy(classOf[CatFactoryImpl])
trait CatFactory {
  def createEntity(catForm: CatForm): Cat
  def createForm(cat: Cat): CatForm
}

@Singleton
class CatFactoryImpl extends CatFactory {

  override def createEntity(catForm: CatForm): Cat = {
    new Cat(None,
      catForm.name,
      catForm.groupId,
      new Timestamp(catForm.birthDate.getMillis)
    )
  }

  override def createForm(cat: Cat): CatForm = {
    new CatForm(cat.name,
      cat.groupId,
      new DateTime(cat.birthDate.getTime)
    )
  }
}

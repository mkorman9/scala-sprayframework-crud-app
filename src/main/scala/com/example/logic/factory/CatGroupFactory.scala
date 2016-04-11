package com.example.logic.factory

import javax.inject.Singleton

import com.example.model.{Cat, CatGroup}
import com.example.web.form.{CatGroupInputForm, CatGroupOutputForm}
import com.google.inject.{Inject, ImplementedBy}

/*
  Provides conversion between domain model of CatGroup and it's serializable representation (form)
*/

@ImplementedBy(classOf[CatGroupFactoryImpl])
trait CatGroupFactory {
  def createEntity(catGroupForm: CatGroupInputForm): CatGroup
  def createForm(catGroup: CatGroup)(catsListResolver: (Long => Seq[Cat])): CatGroupOutputForm
}

@Singleton
class CatGroupFactoryImpl @Inject() (catFactory: CatFactory) extends CatGroupFactory {

  override def createEntity(catGroupForm: CatGroupInputForm): CatGroup = {
    new CatGroup(None,
      catGroupForm.name)
  }

  override def createForm(catGroup: CatGroup)(catsListResolver: (Long => Seq[Cat])): CatGroupOutputForm = {
    val catsInGroup: Seq[Cat] = catsListResolver(catGroup.id.get)
    new CatGroupOutputForm(catGroup.name,
                      catsInGroup map catFactory.createForm)
  }
}

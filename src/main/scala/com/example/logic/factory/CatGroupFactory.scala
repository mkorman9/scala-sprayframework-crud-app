package com.example.logic.factory

import javax.inject.Singleton

import com.example.model.CatGroup
import com.example.web.form.{CatGroupInputForm, CatGroupOutputForm}
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[CatGroupFactoryImpl])
trait CatGroupFactory {
  def createEntity(catGroupForm: CatGroupInputForm): CatGroup
  def createForm(catGroup: CatGroup): CatGroupOutputForm
}

@Singleton
class CatGroupFactoryImpl extends CatGroupFactory {

  override def createEntity(catGroupForm: CatGroupInputForm): CatGroup = {
    new CatGroup(None,
                 catGroupForm.name)
  }

  override def createForm(catGroup: CatGroup): CatGroupOutputForm = {
    new CatGroupOutputForm(catGroup.id.get,
                           catGroup.name)
  }
}

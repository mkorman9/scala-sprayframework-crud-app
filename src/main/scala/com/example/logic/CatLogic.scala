package com.example.logic

import javax.inject.{Inject, Singleton}

import com.example.logic.factory.CatFactory
import com.example.model.Cats
import com.example.web.form.{CatInputForm, CatOutputForm}
import com.google.inject.ImplementedBy

import scala.slick.driver.MySQLDriver.simple._

/*
  Transactional logic providing access to Cats stored in database
*/

@ImplementedBy(classOf[CatLogicImpl])
trait CatLogic {
  def allCats: Seq[CatOutputForm]
  def catsWithNameStartingWithA: Seq[CatOutputForm]
  def catsCount: Int
  def persistCat(catForm: CatInputForm): Unit
}

@Singleton
class CatLogicImpl @Inject() (db: Database, catFactory: CatFactory) extends CatLogic {
  override def allCats = db withSession (implicit session => {
    TableQuery[Cats].run map catFactory.createForm
  })

  override def catsWithNameStartingWithA = db withSession (implicit session => {
    val result = TableQuery[Cats].filter(_.name like "A%").run
    result map catFactory.createForm
  })

  override def catsCount = db withSession (implicit session => {
    TableQuery[Cats].size.run
  })

  override def persistCat(catForm: CatInputForm) = db withSession (implicit session => {
    TableQuery[Cats].insert(catFactory.createEntity(catForm))
  })
}

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
  override def allCats = {
    val query = TableQuery[Cats]
    val result = db withSession (implicit session => query.run)
    result map catFactory.createForm
  }

  override def catsWithNameStartingWithA = {
    val query = TableQuery[Cats].filter(_.name like "A%")
    val result = db withSession (implicit session => query.run)
    result map catFactory.createForm
  }

  override def catsCount = {
    val query = TableQuery[Cats].size
    db withSession (implicit session => query.run)
  }

  override def persistCat(catForm: CatInputForm) = {
    db withTransaction (implicit session => {
      TableQuery[Cats].insert(catFactory.createEntity(catForm))
    })
  }
}

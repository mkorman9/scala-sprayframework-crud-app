package com.example.logic

import javax.inject.{Inject, Singleton}

import com.example.model.Cats
import com.example.web.form.{CatInputForm, CatOutputForm}
import com.google.inject.ImplementedBy

import scala.slick.driver.MySQLDriver.simple._

@ImplementedBy(classOf[CatLogicImpl])
trait CatLogic {
  def allCats: Seq[CatOutputForm]
  def catsWithNameStartingWithA: Seq[CatOutputForm]
  def dataCount: Int
  def persistForm(catForm: CatInputForm): Unit
}

@Singleton
class CatLogicImpl @Inject()(db: Database, catFactory: CatFactory) extends CatLogic {
  override def allCats = {
    val query = TableQuery[Cats]
    val result = db withSession (implicit session => query.run)
    result.map(catFactory.createForm)
  }

  override def catsWithNameStartingWithA = {
    val query = TableQuery[Cats].filter(c => c.name like "A%")
    val result = db withSession (implicit session => query.run)
    result.map(catFactory.createForm)
  }

  override def dataCount = {
    val query = TableQuery[Cats].size
    db withSession (implicit session => query.run)
  }

  override def persistForm(catForm: CatInputForm) = {
    db withTransaction (implicit session => {
      TableQuery[Cats].insert(catFactory.createEntity(catForm))
    })
  }
}

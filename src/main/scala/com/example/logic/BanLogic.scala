package com.example.logic

import javax.inject.{Inject, Singleton}

import com.example.model.BanTableDef
import com.example.web.form.BanForm
import com.google.inject.ImplementedBy

import scala.slick.driver.MySQLDriver.simple._

@ImplementedBy(classOf[BanLogicImpl])
trait BanLogic {
  def allData: Seq[BanForm]
  def dataWithIdLowerThan1000: Seq[BanForm]
  def dataCount: Int
  def persistForm(banForm: BanForm): Unit
}

@Singleton
class BanLogicImpl @Inject() (db: Database, banFactory: BanFactory) extends BanLogic {
  override def allData = {
    val query = TableQuery[BanTableDef]
    val result = db withSession (implicit session => query.run)
    result.map(banFactory.createForm)
  }

  override def dataWithIdLowerThan1000 = {
    val query = TableQuery[BanTableDef].filter(d => d.id < 1000L)
    val result =  db withSession (implicit session => query.run)
    result.map(banFactory.createForm)
  }

  override def dataCount = {
    val query = TableQuery[BanTableDef].size
    db withSession (implicit session => query.run)
  }

  override def persistForm(banForm: BanForm) = {
    db withTransaction (implicit session => {
      TableQuery[BanTableDef].insert(banFactory.createEntity(banForm))
    })
  }
}

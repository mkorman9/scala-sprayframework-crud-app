package com.example.logic

import com.example.model.{Ban, BanTableDef}
import com.example.web.form.BanForm

import scala.slick.driver.MySQLDriver.simple._

class BanLogic(db: Database) {
  def allData = {
    val query = TableQuery[BanTableDef]
    generateList(db withSession (implicit session => query.run))
  }

  def dataWithIdLowerThan1000 = {
    val query = TableQuery[BanTableDef].filter(d => d.id < 1000L)
    generateList(db withSession (implicit session => query.run))
  }

  def dataCount = {
    val query = TableQuery[BanTableDef].size
    db withSession (implicit session => query.run)
  }

  def persistForm(banForm: BanForm) = {
    db withTransaction (implicit session => {
      TableQuery[BanTableDef].insert(BanFactory.createEntity(banForm))
    })
  }

  private def generateList(list: Seq[Ban]): String = {
    val result = list.map {
      case d => d.toString
    }.mkString(" ")
    result
  }
}

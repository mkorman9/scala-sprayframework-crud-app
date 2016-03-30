package com.example.model

import scala.slick.driver.MySQLDriver.simple._

/*
  Domain model of CatGroup
*/

case class CatGroup(id: Option[Long],
                    name: String)

class CatGroups(tag: Tag) extends Table[CatGroup](tag, "CAT_GROUP") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")

  override def * = (id.?, name) <>(CatGroup.tupled, CatGroup.unapply)
}

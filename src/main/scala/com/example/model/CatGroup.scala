package com.example.model

import scala.slick.driver.MySQLDriver.simple._

case class CatGroup(id: Long,
               name: String)

class CatGroups(tag: Tag) extends Table[CatGroup](tag, "CAT_GROUP") {
  def id = column[Long]("ID", O.PrimaryKey)
  def name = column[String]("NAME")

  override def * = (id, name) <>(CatGroup.tupled, CatGroup.unapply)
}

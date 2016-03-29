package com.example.model

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._

case class Cat(id: Option[Long],
               name: String,
               groupId: Long,
               birthDate: Timestamp)

class Cats(tag: Tag) extends Table[Cat](tag, "CAT") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def groupId = column[Long]("GROUP_ID")
  def birthDate = column[Timestamp]("BIRTH_DATE")

  def group = foreignKey("FK_GROUP", groupId, TableQuery[CatGroups])(_.id)

  override def * = (id.?, name, groupId, birthDate) <>(Cat.tupled, Cat.unapply)
}

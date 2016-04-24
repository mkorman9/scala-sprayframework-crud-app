package com.example.model

import java.sql.Timestamp

import com.example.model.Sex.Sex

import scala.slick.driver.MySQLDriver.simple._

/*
  Domain model of Cat
*/

case class Cat(id: Option[Long],
               name: String,
               sex: Sex,
               groupId: Long,
               birthDate: Timestamp)

class Cats(tag: Tag) extends Table[Cat](tag, "CAT") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def sex = column[Sex]("SEX")
  def groupId = column[Long]("GROUP_ID")
  def birthDate = column[Timestamp]("BIRTH_DATE")

  def group = foreignKey("FK_GROUP", groupId, TableQuery[CatGroups])(_.id)

  override def * = (id.?, name, sex, groupId, birthDate) <>(Cat.tupled, Cat.unapply)
}

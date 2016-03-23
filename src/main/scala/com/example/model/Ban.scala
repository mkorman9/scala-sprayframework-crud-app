package com.example.model

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._

case class Ban(id: Int,
               bannedUserId: Int,
               banAuthorId: Int,
               created: Timestamp,
               lifted: Timestamp)

class BanTableDef(tag: Tag) extends Table[Ban](tag, "Ban") {
  def id = column[Int]("BanId", O.PrimaryKey, O.AutoInc)
  def bannedUserId = column[Int]("BannedUserId")
  def banAuthorId = column[Int]("BanAuthorId")
  def created = column[Timestamp]("Created")
  def lifted = column[Timestamp]("Lifted")

  override def * = (id, bannedUserId, banAuthorId, created, lifted) <>(Ban.tupled, Ban.unapply)
}

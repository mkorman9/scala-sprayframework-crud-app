package com.example.model

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._

case class Ban(id: Option[Long],
               bannedUserId: Long,
               banAuthorId: Long,
               created: Timestamp,
               lifted: Timestamp)

class BanTableDef(tag: Tag) extends Table[Ban](tag, "Ban") {
  def id = column[Long]("BanId", O.PrimaryKey, O.AutoInc)
  def bannedUserId = column[Long]("BannedUserId")
  def banAuthorId = column[Long]("BanAuthorId")
  def created = column[Timestamp]("Created")
  def lifted = column[Timestamp]("Lifted")

  override def * = (id.?, bannedUserId, banAuthorId, created, lifted) <>(Ban.tupled, Ban.unapply)
}

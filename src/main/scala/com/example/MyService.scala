package com.example

import akka.actor.Actor
import com.example.model._
import spray.routing._

import scala.slick.driver.MySQLDriver.simple._

class MyService(db: Database) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(
    path("allData") {
      get {
        complete {
          val query = TableQuery[BanTableDef]
          generateList(db withSession (implicit session => query.run))
        }
      }
    } ~
    path("dataWithIdLowerThan1000") {
      get {
        complete {
          val query = TableQuery[BanTableDef].filter(d => d.id < 1000)
          generateList(db withSession (implicit session => query.run))
        }
      }
    }
  )

  private def generateList(list: Seq[Ban]): String = {
    val result = list.map {
      case d => d.toString
    }.mkString(" ")
    result
  }
}

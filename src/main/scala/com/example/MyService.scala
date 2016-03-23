package com.example

import akka.actor.Actor
import com.example.model._
import slick.driver.H2Driver.simple._
import spray.routing._

class MyService(db: Database) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(
    path("allData") {
      get {
        complete {
          val query = TableQuery[DataTableDef]
          generateList(db withSession (implicit session => query.run))
        }
      }
    } ~
    path("dataStartingWithHe") {
      get {
        complete {
          val query = TableQuery[DataTableDef].filter(d => d.user like "he%")
          generateList(db withSession (implicit session => query.run))
        }
      }
    }
  )

  private def generateList(list: Seq[Data]): String = {
    val result = list.map {
      case d => d.toString
    }.mkString(" ")
    result
  }
}

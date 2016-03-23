package com.example

import akka.actor.Actor
import com.example.logic.BanLogic
import com.example.model.Ban
import spray.routing._
import com.example.model.BanJsonSupport._

class WebService(banLogic: BanLogic) extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(
    path("allData") {
      get {
        complete {
          banLogic.allData
        }
      }
    } ~
    path("dataWithIdLowerThan1000") {
      get {
        complete {
          banLogic.dataWithIdLowerThan1000
        }
      }
    } ~
    path("dataCount") {
      get {
        complete {
          banLogic.dataCount.toString()
        }
      }
    } ~
    path("addData") {
        post {
          entity(as[Ban]) {
            ban => {
              banLogic.persist(ban)
              complete("ok")
            }
          }
        }
      }
  )
}

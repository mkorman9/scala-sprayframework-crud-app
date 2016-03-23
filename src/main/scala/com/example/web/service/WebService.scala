package com.example.web.service

import akka.actor.Actor
import com.example.logic.BanLogic
import com.example.web.form.BanForm
import spray.routing._
import com.example.web.form.BanFormJsonSupport._

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
          entity(as[BanForm]) {
            ban => {
              banLogic.persistForm(ban)
              complete("ok")
            }
          }
        }
      }
  )
}

package com.example.web.service

import javax.inject.{Singleton, Inject}

import akka.actor.Actor
import com.example.logic.BanLogic
import com.example.web.form.BanForm
import com.google.inject.ImplementedBy
import spray.routing._
import com.example.web.form.BanFormJsonSupport._

@ImplementedBy(classOf[WebServiceImpl])
trait WebService extends Actor with HttpService {
}

@Singleton
class WebServiceImpl @Inject() (banLogic: BanLogic) extends WebService {
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

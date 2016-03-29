package com.example.web.service

import javax.inject.{Singleton, Inject}

import akka.actor.Actor
import com.example.logic.CatLogic
import com.example.web.form.CatInputForm
import com.google.inject.ImplementedBy
import spray.routing._
import com.example.web.form.CatJsonSupport._

@ImplementedBy(classOf[WebServiceImpl])
trait WebService extends Actor with HttpService {
}

@Singleton
class WebServiceImpl @Inject() (catLogic: CatLogic) extends WebService {
  def actorRefFactory = context

  def receive = runRoute(
    path("all") {
      get {
        complete {
          catLogic.allCats
        }
      }
    } ~
    path("catsWithNameStartingWithA") {
      get {
        complete {
          catLogic.catsWithNameStartingWithA
        }
      }
    } ~
    path("count") {
      get {
        complete {
          catLogic.dataCount.toString()
        }
      }
    } ~
    path("add") {
      post {
        entity(as[CatInputForm]) {
          cat => {
            catLogic.persistForm(cat)
            complete("ok")
          }
        }
      }
    }
  )
}

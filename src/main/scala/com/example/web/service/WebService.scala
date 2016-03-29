package com.example.web.service

import javax.inject.{Singleton, Inject}

import akka.actor.Actor
import com.example.logic.{CatGroupLogic, CatLogic}
import com.example.web.form.{CatGroupInputForm, CatInputForm}
import com.google.inject.ImplementedBy
import spray.routing._
import com.example.web.form.CatJsonSupport._

@ImplementedBy(classOf[WebServiceImpl])
trait WebService extends Actor with HttpService {
}

@Singleton
class WebServiceImpl @Inject() (catLogic: CatLogic, catGroupLogic: CatGroupLogic) extends WebService {
  def actorRefFactory = context

  def receive = runRoute(
    path("cats" / "all") {
      get {
        complete {
          catLogic.allCats
        }
      }
    } ~
    path("cats" / "count") {
      get {
        complete {
          catLogic.catsCount.toString()
        }
      }
    } ~
    path("cats" / "add") {
      post {
        entity(as[CatInputForm]) {
          cat => {
            catLogic.persistCat(cat)
            complete("ok")
          }
        }
      }
    } ~
    path("groups" / "all") {
      get {
        complete {
          catGroupLogic.allGroups
        }
      }
    } ~
      path("groups" / "count") {
        get {
          complete {
            catGroupLogic.groupsCount.toString()
          }
        }
      } ~
      path("groups" / "add") {
        post {
          entity(as[CatGroupInputForm]) {
            group => {
              catGroupLogic.persistGroup(group)
              complete("ok")
            }
          }
        }
      }
  )
}

package com.example.web.service

import javax.inject.{Singleton, Inject}

import akka.actor.Actor
import com.example.logic.{CatGroupLogic, CatLogic}
import com.example.web.form.{SetGroupForm, StatusForm, CatGroupInputForm, CatInputForm}
import com.google.inject.ImplementedBy
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing._

/*
  Definition of webservice routes
  Relies on transactional logic
*/

@ImplementedBy(classOf[WebServiceImpl])
trait WebService extends Actor with HttpService {
}

@Singleton
class WebServiceImpl @Inject()(catLogic: CatLogic, catGroupLogic: CatGroupLogic) extends WebService with DefaultJsonProtocol with SprayJsonSupport  {
  def actorRefFactory = context

  def receive = runRoute(
    pathPrefix("cats") {
      path("all") {
        get {
          complete {
            catLogic.allCats
          }
        }
      } ~
      path("count") {
        get {
          complete {
            catLogic.catsCount.toString()
          }
        }
      } ~
      path("add") {
        post {
          entity(as[CatInputForm]) {
            cat => {
              catLogic.persistCat(cat)
              complete(new StatusForm("ok"))
            }
          }
        }
      }
    } ~

    pathPrefix("groups") {
      path("all") {
        get {
          complete {
            catGroupLogic.allGroups
          }
        }
      } ~
      path("count") {
        get {
          complete {
            catGroupLogic.groupsCount.toString()
          }
        }
      } ~
      path("add") {
        post {
          entity(as[CatGroupInputForm]) {
            group => {
              catGroupLogic.persistGroup(group)
              complete(new StatusForm("ok"))
            }
          }
        }
      } ~
      path("set") {
        post {
          entity(as[SetGroupForm]) {
            setGroupForm => {
              catGroupLogic.setGroupForCat(setGroupForm)
              complete(new StatusForm("ok"))
            }
          }
        }
      }
    }
  )
}

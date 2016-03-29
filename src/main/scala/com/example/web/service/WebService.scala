package com.example.web.service

import javax.inject.{Singleton, Inject}

import akka.actor.Actor
import com.example.logic.{CatGroupLogic, CatLogic}
import com.example.web.form.{CatGroupInputForm, CatInputForm}
import com.google.inject.ImplementedBy
import spray.routing._

@ImplementedBy(classOf[WebServiceImpl])
trait WebService extends Actor with HttpService {
}

@Singleton
class WebServiceImpl @Inject()(catLogic: CatLogic, catGroupLogic: CatGroupLogic) extends WebService {
  def actorRefFactory = context

  def receive = runRoute(
    path("cats" / "all") {
      import com.example.web.form.CatJsonSupport._

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
      import com.example.web.form.CatJsonSupport._

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
      import com.example.web.form.CatGroupJsonSupport._

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
      import com.example.web.form.CatGroupJsonSupport._

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

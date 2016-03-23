package com.example

import akka.actor.{Props, ActorSystem}
import com.example.logic.BanLogic
import slick.driver.MySQLDriver.simple._
import spray.servlet.WebBoot

class SprayBoot extends WebBoot {
  val db = Database.forName("java:comp/env/jdbc/baza")
  val banLogic = new BanLogic(db)

  override val system = ActorSystem()
  override val serviceActor = system.actorOf(Props(new WebService(banLogic)))
}

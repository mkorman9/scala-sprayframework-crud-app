package com.example

import akka.actor.{Props, ActorSystem}
import slick.driver.H2Driver.simple._
import spray.servlet.WebBoot

class Boot extends WebBoot {
  val system = ActorSystem()
  val db = Database.forConfig("h2db_config")
  val serviceActor = system.actorOf(Props(new MyService(db)))
}

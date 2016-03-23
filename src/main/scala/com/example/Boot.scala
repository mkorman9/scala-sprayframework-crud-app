package com.example

import akka.actor.{Props, ActorSystem}
import slick.driver.MySQLDriver.simple._
import spray.servlet.WebBoot

class Boot extends WebBoot {
  val system = ActorSystem()
  val db = Database.forName("java:comp/env/jdbc/baza")
  val serviceActor = system.actorOf(Props(new MyService(db)))
}

package com.example

import akka.actor.{ActorSystem, Props}
import com.example.logic.CronLogic
import com.example.module.MainModule
import com.example.web.service.WebService
import com.google.inject.Guice
import spray.servlet.WebBoot

/*
  Entry point of application
*/

class SprayBoot extends WebBoot {
  val injector = Guice.createInjector(new MainModule)

  override val system = injector.getInstance(classOf[ActorSystem])
  override val serviceActor = system.actorOf(Props(injector.getInstance(classOf[WebService])))
  initializeCrons

  def initializeCrons = {
    injector.getInstance(classOf[CronLogic]).init
  }
}

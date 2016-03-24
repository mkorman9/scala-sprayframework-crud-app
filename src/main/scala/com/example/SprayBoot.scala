package com.example

import akka.actor.{ActorSystem, Props}
import com.example.modules.MainModule
import com.example.web.service.WebService
import com.google.inject.Guice
import spray.servlet.WebBoot

class SprayBoot extends WebBoot {
  val injector = Guice.createInjector(new MainModule)

  override val system = injector.getInstance(classOf[ActorSystem])
  override val serviceActor = system.actorOf(Props(injector.getInstance(classOf[WebService])))
}

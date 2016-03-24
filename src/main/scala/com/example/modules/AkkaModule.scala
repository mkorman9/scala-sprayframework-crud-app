package com.example.modules

import akka.actor.ActorSystem
import com.google.inject.{Injector, Provides}
import net.codingwell.scalaguice.ScalaModule

class AkkaModule extends ScalaModule {
  override def configure = {
  }

  @Provides
  def provideActorSystem(injector: Injector) : ActorSystem = {
    ActorSystem()
  }
}

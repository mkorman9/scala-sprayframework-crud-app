package com.example.module

import javax.inject.Singleton

import akka.actor.ActorSystem
import com.google.inject.{Injector, Provides}
import net.codingwell.scalaguice.ScalaModule

/*
  Definition of Akka's ActorSytem, ready to inject via Guice
*/

class AkkaModule extends ScalaModule {
  override def configure = {
  }

  @Provides @Singleton
  def provideActorSystem(injector: Injector): ActorSystem = {
    ActorSystem()
  }
}

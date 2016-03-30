package com.example.module

import net.codingwell.scalaguice.ScalaModule

/*
  Aggregator for other modules
*/

class MainModule extends ScalaModule {
  override def configure = {
    install(new AkkaModule())
    install(new DbModule())
  }
}

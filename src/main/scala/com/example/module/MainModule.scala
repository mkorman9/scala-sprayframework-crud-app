package com.example.module

import net.codingwell.scalaguice.ScalaModule

class MainModule extends ScalaModule {
  override def configure = {
    install(new AkkaModule())
    install(new DbModule())
  }
}

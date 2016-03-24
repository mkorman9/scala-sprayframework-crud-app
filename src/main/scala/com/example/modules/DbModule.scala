package com.example.modules

import com.google.inject.{Injector, Provides}
import net.codingwell.scalaguice.ScalaModule
import scala.slick.driver.MySQLDriver.simple._

class DbModule extends ScalaModule {
  override def configure = {
  }

  @Provides
  def provideDatabase(injector: Injector) : Database = {
    Database.forName("java:comp/env/jdbc/baza")
  }
}
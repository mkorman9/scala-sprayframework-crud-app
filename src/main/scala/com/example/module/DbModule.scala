package com.example.module

import javax.inject.Singleton

import com.google.inject.{Injector, Provides}
import net.codingwell.scalaguice.ScalaModule
import scala.slick.driver.MySQLDriver.simple._

/*
  Data source definition retrieved from JNDI tree, ready to inject via Guice
*/

class DbModule extends ScalaModule {
  override def configure = {
  }

  @Provides @Singleton
  def provideDatabase(injector: Injector): Database = {
    Database.forName("java:comp/env/jdbc/db")
  }
}

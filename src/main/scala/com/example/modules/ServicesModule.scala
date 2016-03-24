package com.example.modules

import com.example.logic.{BanLogicImpl, BanLogic}
import com.example.web.service.{WebServiceImpl, WebService}
import net.codingwell.scalaguice.ScalaModule

class ServicesModule extends ScalaModule {
  override def configure = {
    bind[WebService].to[WebServiceImpl]
    bind[BanLogic].to[BanLogicImpl]
  }
}

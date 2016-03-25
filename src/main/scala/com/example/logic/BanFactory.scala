package com.example.logic

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.Ban
import com.example.web.form.BanForm
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[BanFactoryImpl])
trait BanFactory {
  def createEntity(banForm: BanForm): Ban
}

@Singleton
class BanFactoryImpl extends BanFactory {
  def createEntity(banForm: BanForm): Ban = {
    new Ban(None,
      banForm.bannedUserId,
      banForm.banAuthorId,
      new Timestamp(banForm.created.getMillis),
      new Timestamp(banForm.lifted.getMillis)
    )
  }
}

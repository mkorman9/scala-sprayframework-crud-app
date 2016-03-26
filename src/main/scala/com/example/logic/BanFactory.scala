package com.example.logic

import java.sql.Timestamp
import javax.inject.Singleton

import com.example.model.Ban
import com.example.web.form.BanForm
import com.google.inject.ImplementedBy
import org.joda.time.DateTime

@ImplementedBy(classOf[BanFactoryImpl])
trait BanFactory {
  def createEntity(banForm: BanForm): Ban
  def createForm(ban: Ban): BanForm
}

@Singleton
class BanFactoryImpl extends BanFactory {
  override def createEntity(banForm: BanForm): Ban = {
    new Ban(None,
      banForm.bannedUserId,
      banForm.banAuthorId,
      new Timestamp(banForm.created.getMillis),
      new Timestamp(banForm.lifted.getMillis)
    )
  }

  override def createForm(ban: Ban): BanForm = {
    new BanForm(ban.bannedUserId,
      ban.banAuthorId,
      new DateTime(ban.created.getTime),
      new DateTime(ban.lifted.getTime)
    )
  }
}

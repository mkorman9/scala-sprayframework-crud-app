package com.example.logic

import java.sql.Timestamp

import com.example.model.Ban
import com.example.web.form.BanForm

object BanFactory {
  def createEntity(banForm: BanForm): Ban = {
    new Ban(banForm.id,
      banForm.bannedUserId,
      banForm.banAuthorId,
      new Timestamp(banForm.created.getMillis),
      new Timestamp(banForm.lifted.getMillis)
    )
  }
}

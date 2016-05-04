package com.example.web.form

import spray.json.DefaultJsonProtocol._

case class SetGroupForm (catId: Long,
                         groupId: Long)

object SetGroupForm {
  implicit val Protocol = jsonFormat2(SetGroupForm.apply)
}
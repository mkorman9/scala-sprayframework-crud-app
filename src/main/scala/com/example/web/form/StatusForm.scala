package com.example.web.form

import spray.json.DefaultJsonProtocol._

case class StatusForm(status: String)

object StatusForm {
  implicit val Protocol = jsonFormat1(StatusForm.apply)
}
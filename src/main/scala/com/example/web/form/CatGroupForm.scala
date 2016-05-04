package com.example.web.form

import spray.json.DefaultJsonProtocol._

/*
  CatGroupInputForm - form used to receive CatGroup's data from client
  CatGroupOutputForm - form used to send CatGroup's data to client
*/

case class CatGroupInputForm(name: String)

object CatGroupInputForm {
  implicit val Protocol = jsonFormat1(CatGroupInputForm.apply)
}

case class CatGroupOutputForm(name: String,
                              cats: Seq[CatOutputForm])

object CatGroupOutputForm {
  implicit val Protocol = jsonFormat2(CatGroupOutputForm.apply)
}

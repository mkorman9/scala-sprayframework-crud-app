package com.example.web.form

import com.example.util.DateTimeJSONFormat
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/*
  CatGroupInputForm - form used to receive CatGroup's data from client
  CatGroupOutputForm - form used to send CatGroup's data to client
*/

case class CatGroupInputForm(name: String)

case class CatGroupOutputForm(name: String,
                              cats: Seq[CatOutputForm])

object CatGroupJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val DateTimeFormat = DateTimeJSONFormat
  implicit val CatOutputFormat = jsonFormat2(CatOutputForm)

  implicit val CatGroupInputFormat = jsonFormat1(CatGroupInputForm)
  implicit val CatGroupOutputFormat = jsonFormat2(CatGroupOutputForm)
}

package com.example.web.form

import com.example.util.DateTimeJSONFormat
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

object JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val DateTimeFormat = DateTimeJSONFormat

  implicit val CatInputFormat = jsonFormat3(CatInputForm)
  implicit val CatOutputFormat = jsonFormat2(CatOutputForm)

  implicit val CatGroupInputFormat = jsonFormat1(CatGroupInputForm)
  implicit val CatGroupOutputFormat = jsonFormat2(CatGroupOutputForm)

  implicit val StatusFormat = jsonFormat1(StatusForm)
  implicit val SetGroupFormat = jsonFormat2(SetGroupForm)
}

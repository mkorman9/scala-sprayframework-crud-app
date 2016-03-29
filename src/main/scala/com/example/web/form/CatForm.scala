package com.example.web.form

import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class CatInputForm(name: String,
                        groupId: Long,
                        birthDate: DateTime)

case class CatGroupInputForm(name: String)

case class CatGroupOutputForm(name: String,
                              cats: Seq[CatOutputForm])

case class CatOutputForm(name: String,
                         birthDate: DateTime)

object CatJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val CatDateTimeFormat = DateTimeJSONFormat
  implicit val CatInputFormats = jsonFormat3(CatInputForm)
  implicit val CatOutputFormats = jsonFormat2(CatOutputForm)
  implicit val CatGroupInputFormats = jsonFormat1(CatGroupInputForm)
  implicit val CatGroupOutputFormats = jsonFormat2(CatGroupOutputForm)
}

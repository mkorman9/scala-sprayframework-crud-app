package com.example.web.form

import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class CatInputForm(name: String,
                        groupId: Long,
                        birthDate: DateTime)

case class CatOutputForm(name: String,
                         birthDate: DateTime)

object CatJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val DateTimeFormat = DateTimeJSONFormat

  implicit val CatInputFormat = jsonFormat3(CatInputForm)
  implicit val CatOutputFormat = jsonFormat2(CatOutputForm)
}

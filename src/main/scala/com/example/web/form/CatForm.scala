package com.example.web.form

import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class CatGroupForm(id: Long,
                        name: String)

case class CatForm(name: String,
                   groupId: Long,
                   birthDate: DateTime)

object CatJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val CatDateTimeFormat = DateTimeJSONFormat
  implicit val CatGroupFormats = jsonFormat2(CatGroupForm)
  implicit val CatFormats = jsonFormat3(CatForm)
}

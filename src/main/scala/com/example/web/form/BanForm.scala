package com.example.web.form

import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class BanForm(id: Int,
                   bannedUserId: Int,
                   banAuthorId: Int,
                   created: DateTime,
                   lifted: DateTime)

object BanFormJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val BanDateTimeFormat = DateTimeJSONFormat
  implicit val BanFormats = jsonFormat5(BanForm)
}

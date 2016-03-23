package com.example.web.form

import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class BanForm(bannedUserId: Long,
                   banAuthorId: Long,
                   created: DateTime,
                   lifted: DateTime)

object BanFormJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val BanDateTimeFormat = DateTimeJSONFormat
  implicit val BanFormats = jsonFormat4(BanForm)
}

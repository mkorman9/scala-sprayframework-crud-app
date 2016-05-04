package com.example.web.form

import com.example.model.Sex.Sex
import com.example.util.DateTimeJSONFormat
import org.joda.time.DateTime
import spray.json.DefaultJsonProtocol._

/*
  CatInputForm - form used to receive new Cat from client
  CatOutputForm - form used to send Cat's data to client
*/

case class CatInputForm(name: String,
                        sex: Sex,
                        groupId: Long,
                        birthDate: DateTime)

object CatInputForm {
  implicit val DateTimeProtocol = DateTimeJSONFormat
  implicit val Protocol = jsonFormat4(CatInputForm.apply)
}

case class CatOutputForm(name: String,
                         sex: Sex,
                         birthDate: DateTime)

object CatOutputForm {
  implicit val DateTimeProtocol = DateTimeJSONFormat
  implicit val Protocol = jsonFormat3(CatOutputForm.apply)
}

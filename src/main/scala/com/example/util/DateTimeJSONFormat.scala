package com.example.util

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

/*
  Conversion between joda's DateTime and it's JSON representation
*/

object DateTimeJSONFormat extends RootJsonFormat[DateTime] {
  private val parserISO : DateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis()
  def write(obj: DateTime) = JsString(parserISO.print(obj))
  def read(json: JsValue) = json match {
    case JsString(s) => parserISO.parseDateTime(s)
    case _ => throw new DeserializationException("Not a DateTime")
  }
}

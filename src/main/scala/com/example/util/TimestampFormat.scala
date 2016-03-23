package com.example.util

import java.sql.Timestamp

import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

object TimestampFormat extends RootJsonFormat[Timestamp] {
  private val parserISO : DateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis()
  def write(obj: Timestamp) = JsString(parserISO.print(obj.getTime))
  def read(json: JsValue) = json match {
    case JsString(s) => new Timestamp(parserISO.parseDateTime(s).getMillis)
    case _ => throw new DeserializationException("Not a timestamp")
  }
}

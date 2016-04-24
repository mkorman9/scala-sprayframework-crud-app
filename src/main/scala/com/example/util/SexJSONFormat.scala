package com.example.util

import com.example.model.Sex
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

object SexJSONFormat extends RootJsonFormat[Sex.Sex] {
  def write(obj: Sex.Sex) = JsString(obj.toString)
  def read(json: JsValue) = json match {
    case JsString(s) => Sex.withName(s)
    case _ => throw new DeserializationException("Not a valid sex value")
  }
}

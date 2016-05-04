package com.example.model

import spray.json.{DeserializationException, JsValue, JsString, RootJsonFormat}

import slick.driver.MySQLDriver.simple._

/*
  Sample enum value representing sex of a single cat
*/

object Sex extends Enumeration {
  type Sex = Value
  val Male = Value("MALE")
  val Female = Value("FEMALE")

  implicit val Mapper = MappedColumnType.base[Value, String](_.toString, Sex.withName)
  implicit val Protocol = new RootJsonFormat[Sex.Sex] {
    def write(obj: Sex.Sex) = JsString(obj.toString)
    def read(json: JsValue) = json match {
      case JsString(s) => Sex.withName(s)
      case _ => throw new DeserializationException("Not a valid sex value")
    }
  }
}

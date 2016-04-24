package com.example.model

import slick.driver.MySQLDriver.simple._

object Sex extends Enumeration {
  type Sex = Value
  val Male = Value("MALE")
  val Female = Value("FEMALE")

  implicit val Mapper = MappedColumnType.base[Value, String](_.toString, Sex.withName)
}

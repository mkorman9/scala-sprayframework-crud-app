package com.example.web.form

import org.joda.time.DateTime

/*
  CatInputForm - form used to receive new Cat from client
  CatOutputForm - form used to send Cat's data to client
*/

case class CatInputForm(name: String,
                        groupId: Long,
                        birthDate: DateTime)

case class CatOutputForm(name: String,
                         birthDate: DateTime)


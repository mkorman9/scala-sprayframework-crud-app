package com.example.web.form

import com.example.model.Sex.Sex
import org.joda.time.DateTime

/*
  CatInputForm - form used to receive new Cat from client
  CatOutputForm - form used to send Cat's data to client
*/

case class CatInputForm(name: String,
                        sex: Sex,
                        groupId: Long,
                        birthDate: DateTime)

case class CatOutputForm(name: String,
                         sex: Sex,
                         birthDate: DateTime)


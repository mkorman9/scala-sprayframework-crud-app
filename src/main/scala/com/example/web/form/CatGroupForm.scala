package com.example.web.form

/*
  CatGroupInputForm - form used to receive CatGroup's data from client
  CatGroupOutputForm - form used to send CatGroup's data to client
*/

case class CatGroupInputForm(name: String)

case class CatGroupOutputForm(name: String,
                              cats: Seq[CatOutputForm])


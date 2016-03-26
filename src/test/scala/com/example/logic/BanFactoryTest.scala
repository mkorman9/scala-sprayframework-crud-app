package com.example.logic

import java.sql.Timestamp

import com.example.model.Ban
import com.example.web.form.BanForm
import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class BanFactoryTest extends FlatSpec with Matchers {
  val banFactory = new BanFactoryImpl()

  "Form" should "be converted to entity" in {
    val userId = 1L
    val authorId = 2L
    val created = new DateTime(10000)
    val lifted = new DateTime(20000)
    val form = new BanForm(userId, authorId, created, lifted)

    val entity = banFactory.createEntity(form)

    entity.bannedUserId should be (userId)
    entity.banAuthorId should be (authorId)
    entity.created.getTime should be (created.getMillis)
    entity.lifted.getTime should be (lifted.getMillis)
  }

  "Entity" should "be converted to form" in {
    val userId = 1L
    val authorId = 2L
    val created = new Timestamp(10000)
    val lifted = new Timestamp(20000)
    val entity = new Ban(None, userId, authorId, created, lifted)

    val form = banFactory.createForm(entity)

    form.bannedUserId should be (userId)
    form.banAuthorId should be (authorId)
    form.created.getMillis should be (created.getTime)
    form.lifted.getMillis should be (lifted.getTime)
  }
}

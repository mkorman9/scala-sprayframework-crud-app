package com.example.logic

import com.example.web.form.BanForm
import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class BanFactoryTest extends FlatSpec with Matchers {
  val banFactory = new BanFactoryImpl()

  "Form" should "be converted to entity" in {
    val userId: Long = 1
    val authorId: Long = 2
    val created: DateTime = new DateTime(10000)
    val lifted: DateTime = new DateTime(20000)
    val form = new BanForm(userId, authorId, created, lifted)

    val entity = banFactory.createEntity(form)

    entity.bannedUserId should be (userId)
    entity.banAuthorId should be (authorId)
    entity.created.getTime should be (created.getMillis)
    entity.lifted.getTime should be (lifted.getMillis)
  }
}

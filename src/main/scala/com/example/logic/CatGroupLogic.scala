package com.example.logic

import javax.inject.{Inject, Singleton}

import com.example.logic.factory.CatGroupFactory
import com.example.model.{Cat, Cats, CatGroups}
import com.example.web.form.{SetGroupForm, CatGroupInputForm, CatGroupOutputForm}
import com.google.inject.ImplementedBy

import scala.slick.driver.MySQLDriver.simple._

/*
  Transactional logic providing access to CatGroups stored in database
*/

@ImplementedBy(classOf[CatGroupLogicImpl])
trait CatGroupLogic {
  def allGroups: Seq[CatGroupOutputForm]
  def groupsCount: Int
  def persistGroup(groupForm: CatGroupInputForm): Unit
  def setGroupForCat(setGroupForm: SetGroupForm): Unit
}

@Singleton
class CatGroupLogicImpl @Inject() (db: Database, catGroupFactory: CatGroupFactory) extends CatGroupLogic {
  override def allGroups = {
    val query = TableQuery[CatGroups]
    val result = db withSession (implicit session => query.run)
    val catsListResolver =
      (groupId: Long) => db withSession (implicit session => TableQuery[Cats].filter(_.groupId === groupId).run)
    result map (catGroupFactory.createForm(_, catsListResolver))
  }

  override def groupsCount = {
    val query = TableQuery[CatGroups].size
    db withSession (implicit session => query.run)
  }

  override def persistGroup(groupForm: CatGroupInputForm) = {
    db withTransaction (implicit session => {
      TableQuery[CatGroups].insert(catGroupFactory.createEntity(groupForm))
    })
  }

  override def setGroupForCat(setGroupForm: SetGroupForm): Unit = {
    db withTransaction (implicit session => {
      TableQuery[Cats].filter(_.id === setGroupForm.catId).map(g => g.groupId).update(setGroupForm.groupId)
    })
  }
}
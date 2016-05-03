package com.example.logic

import javax.inject.{Singleton, Inject}

import akka.actor.ActorSystem
import com.google.inject.ImplementedBy
import org.joda.time.DateTime
import scala.concurrent.duration._

@ImplementedBy(classOf[CronLogicImpl])
trait CronLogic {
  def init
  def doWork
}

@Singleton
class CronLogicImpl @Inject() (actorSystem: ActorSystem) extends CronLogic {
  val InitialDelay = 0.seconds
  val Interval = 1.hour

  override def init = {
    import actorSystem.dispatcher
    actorSystem.scheduler.schedule(InitialDelay, Interval)(doWork)
  }

  override def doWork = {
    val now: DateTime = new DateTime()
    println("Cron event! It's " + now.hourOfDay().get() + ":" + now.minuteOfHour().get())
  }
}

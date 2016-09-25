package org.repo

import org.connection.{DBComponent, H2DBComponent, MysqlDBComponent}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

/**
  * @author Humayun
  */
trait BankRepository extends BankTable { this: DBComponent =>
  import profile.api._

  def createTable = exec(banks.schema.drop.asTry andThen banks.schema.create)

  def insert(bank: Bank) = exec(insertBank += bank)

  def update(bank: Bank)  =  exec( banks.filter(_.id === bank.id.get).update(bank) )

  def delete(id: Long) = exec( banks.filter(_.id === id).delete )

  def getAll = exec( banks.to[List].result )

  def exec[T](action: DBIO[T]): T =
    Await.result( db.run(action), 5 seconds)

}

private[repo] trait BankTable { this: DBComponent =>
  import profile.api._

  class BankTable(tag: Tag) extends Table[Bank](tag, "bank") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (name, id.?) <> (Bank.tupled, Bank.unapply)
  }

  lazy val banks = TableQuery[BankTable]

  lazy val insertBank = banks returning banks.map(_.id)

}

case class Bank(name: String, id: Option[Long] = None)

object BankRepository extends BankRepository with MysqlDBComponent

//object BankRepository extends BankRepository with H2DBComponent
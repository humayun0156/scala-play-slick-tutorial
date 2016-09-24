package ch05

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.JdbcProfile

object NullableColumnEx {
  trait Profile {
    val profile: JdbcProfile
  }
  trait Tables {
    this: Profile =>
    import profile.api._

    case class User(name: String, email: Option[String] = None, id: Long = 0L)

    class UserTable(tag: Tag) extends Table[User](tag, "user") {
      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
      def name = column[String]("name")
      def email = column[Option[String]]("email")

      def * = (name, email, id) <> (User.tupled, User.unapply)
    }

    lazy val users = TableQuery[UserTable]
    lazy val insertUser = users returning users.map(_.id)
  }

  class Schema(val profile: JdbcProfile) extends Tables with Profile

  //val schema = new Schema(slick.driver.H2Driver)
  val schema = new Schema(slick.driver.MySQLDriver)
  //val schema = new Schema(slick.driver.PostgresDriver)

  import schema._, profile.api._

  val db = Database.forConfig("mysqlDB")  // H2, mysqlDB, postgreDB

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 9 seconds)

  val program = for {
    _ <- users.schema.drop.asTry
    _ <- users.schema.create
    devaId <- insertUser += User("Dave", Some("dave@example.org"))
    halId  <- insertUser += User("HAL")
    elena  <- insertUser += User("Elena", Some("elena@example.org"))
    folks  <- users.result
  } yield folks



  def main(args: Array[String]): Unit = {

    println("\nUsers with optional email address:")
    exec(program).foreach( println )

    println("\nUsers sorted with NULLs last")
    val sortingOnNullableExample = users.sortBy{ _.name.asc.nullsLast }.result
    exec(sortingOnNullableExample).foreach { println }
    //exec(users.schema.drop)
  }
}

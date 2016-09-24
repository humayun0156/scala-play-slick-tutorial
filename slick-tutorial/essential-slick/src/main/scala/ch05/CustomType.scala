package ch05
import java.sql.Timestamp

import org.joda.time.{DateTime, DateTimeZone}
import slick.driver.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object CustomType {
  trait Profile {
    val profile: JdbcProfile
  }
  // We represent primary keys using these mapped value classes
  // described in section 5.4.1
  object PKs {
    import slick.lifted.MappedTo
    case class MessagePK(value: Long) extends AnyVal with MappedTo[Long]
    case class UserPK(value: Long) extends AnyVal with MappedTo[Long]
  }

  trait Tables {
    this: Profile =>
    import PKs._
    import profile.api._

    implicit val jodaDateTimeType =
      MappedColumnType.base[DateTime, Timestamp](
        dt => new Timestamp(dt.getMillis),
        ts => new DateTime(ts.getTime, DateTimeZone.UTC)
      )

    case class User(name: String, id: UserPK = UserPK(0L))

    class UserTable(tag: Tag) extends Table[User](tag, "user") {
      def id   = column[UserPK]("id", O.PrimaryKey, O.AutoInc)
      def name = column[String]("name")

      def * = (name, id) <> (User.tupled, User.unapply)
    }

    lazy val users = TableQuery[UserTable]
    lazy val insertUser = users returning users.map(_.id)

    sealed trait Priority
    case object HighPriority extends Priority
    case object LowPriority  extends Priority

    implicit val priorityType =
      MappedColumnType.base[Priority, String](
        flag => flag match {
          case HighPriority => "y"
          case LowPriority => "n"
        },
        ch => ch match {
          case "Y" | "y" | "+" | "high"       => HighPriority
          case "N" | "n" | "-" | "low" | "lo" => LowPriority
        }
      )

    case class Message(
         senderId: UserPK,
         content:  String,
         ts:       DateTime,
         flag:     Option[Priority] = None,
         id:        MessagePK = MessagePK(0L))

    class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
      def id = column[MessagePK]("id", O.PrimaryKey, O.AutoInc)
      def senderId = column[UserPK]("sender")
      def content = column[String]("content")
      def priority = column[Option[Priority]]("priority")
      def ts = column[DateTime]("ts")

      def * = (senderId, content, ts, priority, id) <> (Message.tupled, Message.unapply)

      def sender = foreignKey("sender_fk", senderId, users)(_.id, onDelete = ForeignKeyAction.Cascade)
    }

    lazy val messages = TableQuery[MessageTable]

    lazy val ddl = users.schema ++ messages.schema
  }

  class Schema(val profile: JdbcProfile) extends Tables with Profile

  val schema = new Schema(slick.driver.MySQLDriver)

  import schema._
  import profile.api._

  val db = Database.forConfig("mysqlDB")

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 9 seconds)

  // Insert the conversation, which took place in Feb, 2001:
  val start = new DateTime(2001, 2, 17, 10, 22, 50)

  val program =
    for {
      _      <- ddl.create
      halId  <- insertUser += User("HAL")
      daveId <- insertUser += User("Dave")
      _      <- messages ++= Seq(
        Message(daveId, "Hello, HAL. Do you read me, HAL?", start),
        Message(halId,  "Affirmative, Dave. I read you.", start plusSeconds 2),
        Message(daveId, "Open the pod bay doors, HAL.", start plusSeconds 4),
        Message(halId,  "I'm sorry, Dave. I'm afraid I can't do that.", start plusSeconds 6, Some(HighPriority)))
      result <- messages.filter(_.priority === (HighPriority:Priority)).result
    } yield result

  def main(args: Array[String]): Unit = {
    println(s"query result ${exec(program)}" )
  }
}

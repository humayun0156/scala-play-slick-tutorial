package ch05
import slick.driver.JdbcProfile
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object PrimaryKeyEx {
  trait Profile {
    val profile: JdbcProfile
  }

  trait Tables {
    this: Profile =>
    import profile.api._

    case class User(id: Option[Long], name: String, email: Option[String] = None)

    class UserTable(tag: Tag) extends Table[User](tag, "user") {
      def id = column[Long]("id", O.AutoInc, O.PrimaryKey)
      def name = column[String]("name")
      def email = column[Option[String]]("email")

      def * = (id.?, name, email) <> (User.tupled, User.unapply)
    }

    lazy val users = TableQuery[UserTable]
    lazy val insertUser = users returning users.map(_.id)

    case class Room(title: String, id: Long = 0L)

    class RoomTable(tag: Tag) extends Table[Room](tag, "room") {
      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
      def title = column[String]("title")
      def * = (title, id) <> (Room.tupled, Room.unapply)
    }

    lazy val rooms = TableQuery[RoomTable]
    lazy val insertRoom = rooms returning rooms.map(_.id)

    case class Occupant(roomId: Long, userId: Long)

    class OccupantTable(tag: Tag) extends Table[Occupant](tag, "occupant") {
      def roomId = column[Long]("room")
      def userId = column[Long]("user")
      def pd = primaryKey("room_user_pl", (roomId, userId))
      def * = (roomId, userId) <> (Occupant.tupled, Occupant.unapply)
    }

    lazy val occupants = TableQuery[OccupantTable]

    //
    // The schema for all three tables
    //
    lazy val ddl = users.schema ++ rooms.schema ++ occupants.schema
  }

  class Schema(val profile: JdbcProfile) extends Tables with Profile

  //val schema = new Schema(slick.driver.H2Driver)
  val schema = new Schema(slick.driver.MySQLDriver)

  import schema._
  import profile.api._

  val db = Database.forConfig("mysqlDB")

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 9 seconds)

  val init = for {
    _ <- ddl.drop.asTry
    _ <- ddl.create
    daveId <- insertUser += User(None, "Dave", Some("dave@example.org"))
    halId <- insertUser += User(None, "HAL")
    elena <- insertUser += User(None, "Elena", Some("elena@example.com"))
    airLockId <- insertRoom += Room("Air Lock")
    _ <- occupants += Occupant(airLockId, halId)
  } yield ()

  def main(args: Array[String]): Unit = {
    exec(init)

    println("\nUsers databse contais:")
    exec(users.result).foreach(println)

    println("\nOccupation is: ")
    exec(occupants.result).foreach(println)
  }

}

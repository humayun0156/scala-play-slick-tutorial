package ch05

import slick.driver.JdbcProfile

// way-1
trait DatabaseModule {
  val profile: JdbcProfile
  import profile.api._
}

//way-2
trait ProfileEx01 {
  val profile: JdbcProfile
}
trait DatabaseModule1 { self: ProfileEx01 =>
  import profile.api._
  // Write more database code here
}
trait DatabaseModule2 { self: ProfileEx01 =>
  import profile.api._
  // Write more database code here
}
class DatabaseLayer(val profile: JdbcProfile)
  extends ProfileEx01
    with DatabaseModule1 with DatabaseModule2

object Main {
  def main(args: Array[String]): Unit = {
    //way-1
    val databaseLayer = new DatabaseModule {
      override val profile: JdbcProfile = slick.driver.H2Driver
    }

    //way-2
    val databaseLayer_1 = new DatabaseLayer(slick.driver.H2Driver)
  }
}

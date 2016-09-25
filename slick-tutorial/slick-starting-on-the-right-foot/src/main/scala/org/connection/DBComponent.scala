package org.connection

import slick.driver.JdbcProfile

/**
  * @author Humayun
  */
trait DBComponent {

  val profile: JdbcProfile

  import profile.api._

  val db: Database
}

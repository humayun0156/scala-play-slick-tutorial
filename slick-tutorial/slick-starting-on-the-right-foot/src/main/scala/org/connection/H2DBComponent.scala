package org.connection

import slick.driver.{H2Driver, JdbcProfile}

trait H2DBComponent extends DBComponent {
  override val profile: JdbcProfile = H2Driver

  import profile.api._

  val db: Database = H2DB.databaseConfig
}

private[connection] object H2DB {
  import slick.driver.H2Driver.api._

  val databaseConfig = Database.forConfig("H2")
}

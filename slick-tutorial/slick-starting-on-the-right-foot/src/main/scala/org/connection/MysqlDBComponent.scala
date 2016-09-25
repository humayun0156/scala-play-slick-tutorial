package org.connection

import slick.driver.{JdbcProfile, MySQLDriver}

trait MysqlDBComponent extends DBComponent{
  override val profile: JdbcProfile = MySQLDriver

  import profile.api._

  val db: Database = MysqlDB.databaseConfig
}

private[connection] object MysqlDB {
  import slick.driver.MySQLDriver.api._

  val databaseConfig = Database.forConfig("mysqlDB")
}
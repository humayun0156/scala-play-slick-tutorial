import RichConsole._
/**
  * @author Humayun
  */

abstract class Role {
  def canAccess(page: String): Boolean
}
class Root extends Role {
  override def canAccess(page: String): Boolean = true
}
class SuperAnalyst extends Role {
  override def canAccess(page: String): Boolean = page != "Admin"
}
class Analyst extends Role {
  override def canAccess(page: String): Boolean = false
}
object Role {
  def apply(roleName: String): Role = roleName match {
    case "root" => new Root
    case "superAnalyst" => new SuperAnalyst
    case "analyst" => new Analyst
  }
}

object FactoryPattern {
  def main(args: Array[String]): Unit = {
    val root = Role("root")
    val analyst = Role("analyst")
    val superAnalyst = Role("superAnalyst")
    p(root.canAccess("2"))
    p(analyst.canAccess("3"))
    p(superAnalyst.canAccess("465"))
  }
}

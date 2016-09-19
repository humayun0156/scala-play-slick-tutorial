import scala.language.dynamics
/**
  * @author Humayun
  */
class MyDynamicMap extends Dynamic {
  def selectDynamic(fieldName: String) = map.get(fieldName)
  private val map = Map("foo" -> 1, "bar" -> 2)

}
object MyMap {
  def main(args: Array[String]): Unit = {
    val someMap = new MyDynamicMap
    println(someMap.foo)
  }
}

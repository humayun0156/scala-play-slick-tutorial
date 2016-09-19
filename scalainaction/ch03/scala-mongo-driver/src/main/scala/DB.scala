import com.mongodb.{DB => MongoDB}

import scala.collection.convert.Wrappers.JSetWrapper

/**
  * @author Humayun
  */
class DB private(val underlying: MongoDB) {
  private def collection(name: String) = underlying.getCollection(name)

  def readOnlyCollection(name: String) = new DBCollection(collection(name))
  def administrableCollection(name: String) = new
      DBCollection(collection(name)) with Administerable
  def updatableCollection(name: String) = new
      DBCollection(collection(name)) with Updatable

  def collectionNames = for (name <- new
      JSetWrapper(underlying.getCollectionNames)) yield name

}

object DB {
  def apply(underlying: MongoDB): DB = new DB(underlying)
}

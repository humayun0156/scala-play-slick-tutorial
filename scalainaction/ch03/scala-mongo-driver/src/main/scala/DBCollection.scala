import com.mongodb.{DBCursor, DBObject, DBCollection => MongoDBCollection}
/**
  * @author Humayun
  */
class DBCollection(override val underlying: MongoDBCollection) extends ReadOnly {

}

trait ReadOnly {
  val underlying: MongoDBCollection

  def name = underlying.getName
  def fullName = underlying getFullName

  def find(query: Query):DBCursor = {
    def applyOptoins(cursor: DBCursor, option: QueryOption): DBCursor = {
      option match {
        case Skip(skip, next) => applyOptoins(cursor.skip(skip), next)
        case Sort(sorting, next) => applyOptoins(cursor.sort(sorting), next)
        case Limit(limit, next) => applyOptoins(cursor.limit(limit), next)
        case NoOption => cursor
      }
    }
    applyOptoins(find(query.q), query.option)
  }

  def find(doc: DBObject) = underlying find doc
  def findOne(doc: DBObject) = underlying findOne doc
  def findOne = underlying findOne
  def getCount(doc: DBObject) = underlying getCount doc //underlying.getCount(doc)
}

trait Administerable extends ReadOnly {
  def drop: Unit = underlying drop
  def dropIndexes: Unit = underlying dropIndexes
}

trait Updatable extends ReadOnly {
  def -=(doc: DBObject): Unit = underlying remove doc
  def +=(doc: DBObject): Unit = underlying.save(doc)
}

case class Query(q: DBObject, option: QueryOption = NoOption) {
  def sort(sorting: DBObject) = Query(q, Sort(sorting, option))
  def skip(skip: Int) = Query(q, Skip(skip, option))
  def limit(limit: Int) = Query(q, Limit(limit, option))
}

sealed trait QueryOption
case object NoOption extends QueryOption
case class Sort(sorting: DBObject, anotherOption: QueryOption) extends QueryOption
case class Skip(number: Int, anotherOption: QueryOption) extends QueryOption
case class Limit(limit: Int, anotherOption: QueryOption) extends QueryOption
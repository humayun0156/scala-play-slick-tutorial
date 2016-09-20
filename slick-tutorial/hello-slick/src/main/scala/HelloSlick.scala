import slick.backend.DatabasePublisher
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * @author humayun
  */
object HelloSlick {
  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("h2mem1")

    try {
      // The query interface for the Suppliers table
      val suppliers: TableQuery[Suppliers] = TableQuery[Suppliers]

      // The query interface for the Coffees table
      val coffees: TableQuery[Coffees] = TableQuery[Coffees]

      val setupAction: DBIO[Unit] = DBIO.seq(
        (suppliers.schema ++ coffees.schema).create,

        // Insert some suppliers
        suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        suppliers += ( 49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
      )
      val setupFuture: Future[Unit] = db.run(setupAction)
      val f = setupFuture.flatMap { _ =>

        // Insert some coffees (using JDBC's batch insert feature)
        val insertAction: DBIO[Option[Int]] = coffees ++= Seq (
          ("Colombian",         101, 7.99, 0, 0),
          ("French_Roast",       49, 8.99, 0, 0),
          ("Espresso",          150, 9.99, 0, 0),
          ("Colombian_Decaf",   101, 8.99, 0, 0),
          ("French_Roast_Decaf", 49, 9.99, 0, 0)
        )

        val insertAndPrintAction: DBIO[Unit] = insertAction.map { coffeesInsertResult =>
          // Print the number of rows inserted
          coffeesInsertResult foreach { numRows =>
            println(s"Inserted $numRows rows into the Coffees table")
          }
        }

        val allSuppliersAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
          suppliers.result

        val combinedAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
          insertAndPrintAction >> allSuppliersAction

        val combinedFuture: Future[Seq[(Int, String, String, String, String, String)]] =
          db.run(combinedAction)

        combinedFuture.map { allSuppliers =>
          allSuppliers.foreach(println)
        }

      }.flatMap { _ =>
        // Streaming
        val coffeeNamesAction: StreamingDBIO[Seq[String], String] =
          coffees.map(_.name).result

        val coffeeNamesPublisher: DatabasePublisher[String] =
          db.stream(coffeeNamesAction)

        println("=== Coffee Names ===")
        coffeeNamesPublisher.foreach(println)


      }.flatMap { _ =>
        // Filtering with where

        // construct a query where the price of coffee is > 9.0
        val filterQuery: Query[Coffees, (String, Int, Double, Int, Int), Seq] =
          coffees.filter(_.price > 9.0)

        println("Generated SQL for filter query:\n " + filterQuery.result.statements)
        db.run(filterQuery.result.map(println))

      }



      Await.result(f, Duration.Inf)
    } finally db.close()
  }
}

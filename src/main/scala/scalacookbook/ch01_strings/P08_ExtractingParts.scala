package scalacookbook.ch01_strings

/**
  * @author Humayun
  */
object P08_ExtractingParts {
  def main(args: Array[String]): Unit = {
    val pattern = "([0-9]+) ([A-Za-z]+)".r
    val pattern(count, fruit) = "100 Bananas"
    println(pattern)




    // match "movies 80301"
    val MoviesZipRE = "movies (\\d{5})".r
    // match "movies near boulder, co"
    val MoviesNearCityStateRE = "movies near ([a-z]+), ([a-z]{2})".r

    /*textUserTyped match {
      case MoviesZipRE(zip) => getSearchResults(zip)
      case MoviesNearCityStateRE(city, state) => getSearchResults(city, state)
      case _ => println("did not match a regex")
    }*/

  }
}

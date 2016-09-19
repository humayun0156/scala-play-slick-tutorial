/**
  * @author Humayun
  */
object ForComprehansion {
  case class Artist(name: String, genre: String)
  val artists = List(Artist("Pink Floyd", "Rock"),
    Artist("Led Zeppelin", "Rock"),
    Artist("Michael Jackson", "Pop"),
    Artist("Above & Beyond", "trance")
  )

  case class ArtistWithAlbums(artist: Artist, albums: List[String])
  val artistsWithAlbums = List(
    ArtistWithAlbums(Artist("Pink Floyd", "Rock"), List("Dark side of the moon", "Wall")),
    ArtistWithAlbums(Artist("Led Zeppelin", "Rock"), List("Led Zeppelin IV", "Presence")),
    ArtistWithAlbums(Artist("Michael Jackson", "Pop"),List("Bad", "Thriller")),
    ArtistWithAlbums(Artist("Above & Beyond", "trance"), List("Tri-State", "Sirens of the Sea"))
  )


  def main(args: Array[String]): Unit = {
    val x = for(Artist(name, genre) <- artists; if genre == "Rock") yield name
    val y = for(art <- artists; if art.genre == "Rock") yield art.name
    println(x)
    println(y)

    val z = for {
      ArtistWithAlbums(artist, albums) <- artistsWithAlbums
      album <- albums
      if artist.genre == "Rock"
    } yield album

    println(z)

    val rockAlbums = artistsWithAlbums flatMap {
      case ArtistWithAlbums(artist, albums) => albums withFilter {
        album => artist.genre == "Rock"
      } map { case album => album }
    }
    println(rockAlbums)

  }
}

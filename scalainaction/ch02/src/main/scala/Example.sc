val book = <book>
            <title>Scala in Action</title>
            <author>Nilanjan Raychaudhuri</author>
           </book>

val message = "I don't like xml"
val code = "1"
val alert =
  <alert>
    <message priority={code}>{message}</message>
    <date>{new java.util.Date()}</date>
  </alert>

val a = 5
lazy val b = a + 5
a
b
val first::second::last = List(1,2,3,4)

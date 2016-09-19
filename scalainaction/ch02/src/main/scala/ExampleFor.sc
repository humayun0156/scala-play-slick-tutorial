import java.io.File

val files = new File(".").listFiles
for (
  f <- files;
  fName = f.getName
  if fName.endsWith(".exe")
  )  println(f.getName)

val aList = List(1,2,3)
val bList = List(6,7,8)
for(a <- aList; b <- bList) {
  println(a + b)
}

val result =
  for (a <- aList; b <- bList) yield a + b

for (r <- result) println(r)

val xmlNode = <result>{result.mkString(",")}</result>
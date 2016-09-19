
def loopTill(cond: => Boolean)(body: => Unit): Unit = {
  if (cond) {
    body
    loopTill(cond)(body)
  }
}

def loopTo(cond: => Boolean)(body: Unit): Unit = {
  if (cond) {
    body
    loopTo(cond)(body)
  }
}
var i = 10
loopTill(i > 0) {
  println(i)
  i -= 1
}
var j = -5
loopTo(j < 0) {
  print("j: " + j)
  j += 1
}
package fpexamples

/**
  * @author Humayun
  */
trait Resource {
  def dispose(): Unit
}
class LoanPattern {
  def use[A, B <: Resource](r: B)(f: B => A): A = {
    try {
      f(r)
    } finally {
      r.dispose()
    }
  }
}

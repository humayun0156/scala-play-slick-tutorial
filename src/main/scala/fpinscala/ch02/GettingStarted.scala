package fpinscala.ch02

/**
  * @author Humayun
  */
object GettingStarted {

  def partial[A,B,C](a: A, f: (A, B) => C): B => C = {
    (b: B) => f(a, b)
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }
  def compose[A,B,C](f: A => B, g: B => C): A => C = {
    a => g(f(a))
  }
}

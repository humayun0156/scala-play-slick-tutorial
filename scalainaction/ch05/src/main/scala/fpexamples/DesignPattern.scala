package fpexamples

/**
  * @author Humayun
  */
object DesignPattern {
  trait TaxStrategy {
    def taxIt(product: String): Double
  }
  class ATaxStrategy extends TaxStrategy {
    override def taxIt(product: String): Double = 10.0
  }
  class BTaxStrategy extends TaxStrategy {
    override def taxIt(product: String): Double = 20.0
  }


  def taxIt: TaxStrategy => String => Double = s => p => s.taxIt(p)

  def taxIt_a: String => Double = taxIt(new ATaxStrategy)
  def taxIt_b: String => Double = taxIt(new BTaxStrategy)

  def calculatePrice(product: String, taxingStrategy: String => Double) = {
    val tax = taxingStrategy(product)
  }

  def main(args: Array[String]): Unit = {
    val s = "soft-drinks"
  }
}

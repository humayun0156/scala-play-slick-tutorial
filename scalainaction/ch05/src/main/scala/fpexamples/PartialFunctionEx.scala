package fpexamples

/**
  * @author Humayun
  */
sealed trait Claim {
  val claimId: Int
}
case class Full(claimId: Int) extends Claim
case class Partial(claimId: Int, percentage: Double) extends Claim
case class Generic(override val claimId: Int) extends Claim

case class Location(stageCode: Option[String], zipCode: Option[String])
case class Req(productId: String, location: Location, claim: Claim)

object PricingSystem {
  type PC = Tuple2[Req, Option[Double]]

  def handleFullClaim: PartialFunction[PC, PC] = {
    case (c@Req(id, l, Full(claimId)), basePrice)  =>  (c, basePrice.map(_ + 10))
  }

  def handlePartialClaim: PartialFunction[PC, PC] = {
    case (c@Req(id, l, Partial(claimId, percentage)), basePrice)  =>  (c, basePrice.map(_ + 20))
  }

  def handleZipCode: PartialFunction[PC, PC] = {
    case (c@Req(id, Location(_, Some(zipCode)), _), price) => (c, price.map(_ + 5))
  }

  def handleStateCode: PartialFunction[PC, PC] = {
    case (c@Req(id, Location(Some(stateCode), _), _), price) => (c, price.map(_ + 10))
  }

  val claimHandlers = handleFullClaim orElse handlePartialClaim
  val locationHandlers = handleZipCode orElse handleStateCode

  def default: PartialFunction[PC, PC] = {case p => p}
  def priceCalculator: PartialFunction[PC, PC] = {
    (claimHandlers andThen locationHandlers) orElse default
  }

  def main(args: Array[String]): Unit = {
    priceCalculator((Req("some product", Location(None, Some("43230")), Full(1)), Some(10)))
    match {
      case (c, finalPrice) => println(finalPrice)
    }

    priceCalculator((Req("some product", Location(None, None), Generic(10)), Some(10)))
    match {
      case (c, finalPrice) => println(finalPrice)
    }
  }
}

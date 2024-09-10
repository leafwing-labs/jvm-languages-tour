package tech.leafwinglabs

// An {{ORDER}} is noted with an espresso shot numbe
// `x` ranging between 1 and 4, and an optional letter
// `L` that indicates that the student wants to have a
// `x`-shot latte. For example, an order 2 means a 2-shot
// espresso, and an order 3L means a 3-shot latte:
//
//  - Making an x-shot espresso consumes x ounces of
//    water
//  - Making a latte requires steaming milk and
//    consumes one additional ounce of water
//
// The espresso machine at the micro kitchen has a water
// {{TANK}} of `s` ounces that is full at the beginning
// of the day. John refills the water tank to s ounces
// whenever the remaining water in the tank is not
// enough to fulfill the next student’s order.
//
// How many times do John have to {{REFILL}} the water tank today
// in order to serve all `n` students?
//
// see: https://icpcarchive.github.io/North%20America%20Contests/Mid-Atlantic%20USA%20Regional%20Contest/2021%20ICPC%20Mid-Atlantic%20USA%20Regional%20Contest/problems.pdf

object ScalaCoffeeBar:

  // {{ORDER}}
  case class Order(waterOunces: Int)

  def parseOrder(order: String): Order =
    order.takeRight(1) match
      case "L" => Order(order.dropRight(1).toInt + 1)
      case _ => Order(order.toInt)

  // {{TANK}}
  case class Tank(capacity: Int, refills: Int = 0)

  // {{REFILL}}
  def makeDrink(tank: Tank, order: Order, max: Int): Tank =
    if (tank.capacity - order.waterOunces < 0) tank.copy(
      capacity = max,
      refills = tank.refills + 1
    )
    else tank.copy(
      capacity = tank.capacity - order.waterOunces
      )

  // Context
  def countTankRefills(orders: List[String], tankCapacity: Int): Int =
    orders
      .map(parseOrder)
      .foldLeft(Tank(tankCapacity)):
        (tank, order) => makeDrink(tank, order, tankCapacity)
      .refills

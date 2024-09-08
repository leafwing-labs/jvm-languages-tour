object CoffeeBar {

  case class Tank(capacity: Int, refills: Int = 0)

  def parseOrder(s: String): Int = if (s.last == 'L') s.dropRight(1).toInt else 0

  def makeDrink(tank: Tank, order: Int, max: Int): Tank =
    if (tank.capacity - order < 0) Tank(max, tank.refills + 1)
    else Tank(tank.capacity - order, tank.refills)

  def countTankRefills(orders: List[String], tankCapacity: Int): Map[String, Int] =
    orders
      .map(parseOrder)
      .foldLeft(Tank(tankCapacity)) { (tank, order) => makeDrink(tank, order, tankCapacity) }

  @main def main(): Unit = {
    val orders = List("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    val tankCapacity = 10
    val tank = countTankRefills(orders, tankCapacity)
    println(tank)
  }

}
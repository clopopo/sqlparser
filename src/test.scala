object test {
  def main(args: Array[String]) {
    //    val b = new BMW
    //    show(b)
    //    showy(b)
    //    b.close
    //
    //    val rc = new Rectangle(new Point(1, 2), new Point(2, 3))
    //    val p = new Point(2, 3)
    //    println(p(2, 3))
    //    println(rc.width)
    //    println("ss:" + addOne(2))
    //    val times = 1
    //    println(mm(times))
    println(mm(""))
  }

  def mm(times: Any): Any = {
    times match {
      case 2           => "two"
      case i if i == 1 => "one"
      case _ => {
        if (true) { "hahah" }
        else "fff"

      }

    }
  }

  def testmatch(x: Int): String = {
    while (x != 0) {
      x match {
        case 1 => println("one")
        case 2 => { "two" }
        case _ => "nothing"
      }
    }
    "ss"
  }

  def show(x: Car): Unit = {
    println(x.getBrand)
  }

  def showy(x: Shiny): Unit = {
    println(x.getShine)
  }

}

class Clculator(brand: String) {
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  def add(m: Int, n: Int): Int = m + n
}

abstract class Shape {
  def getArea(): Int

}

class Circle(r: Int) extends Shape {
  def getArea(): Int = r * r * 3

}

trait Car {
  val brand: String
  def getBrand(): String = brand
  def close(): Unit

}

trait Shiny {
  val shineRefraction: Int
  def getShine(): Int = shineRefraction
}

class BMW extends Car with Shiny {
  val brand = "BMW"
  val shineRefraction = 2
  def close(): Unit = {
    println("close")
  }
  override def getShine(): Int = 4
}

class Point(val x: Int, val y: Int) {
  def apply(x: Int, y: Int): Point = {
    new Point(x, y)
  }
  override def toString(): String = {
    "x: " + x + "\ty: " + y
  }
}

abstract class Component {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left

}

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point

  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and many more geometric methods...
}

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {

}

object addOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}





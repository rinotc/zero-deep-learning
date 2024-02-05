import matrix.Matrix

@main
def main(): Unit = {
  val X = Matrix(Array(Array(1, 2)))
  val W = Matrix(Array(Array(1, 3, 5), Array(2, 4, 6)))
  val Y = X dot W
  println(Y)
}

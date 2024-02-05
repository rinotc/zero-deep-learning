import matrix.Matrix

@main
def main(): Unit = {
  val X = Matrix(Array(Array(1, 2)))
  val W = Matrix(Array(Array(1, 3, 5), Array(2, 4, 6)))
  val Y = X dot W
  println(Y.prettyString)
}

@main
def main2(): Unit = {
  val X  = Matrix(Array(Array(0.1, 0.5)))
  val W1 = Matrix(Array(Array(0.1, 0.3, 0.5), Array(0.2, 0.4, 0.6)))
  val B1 = Matrix(Array(Array(0.1, 0.2, 0.3)))
  println(X.shape)
  println(W1.shape)
  println(B1.shape)

  val A1 = (X dot W1) + B1
  println(A1.prettyString)
}

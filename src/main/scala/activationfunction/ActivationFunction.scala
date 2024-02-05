package activationfunction

import matrix.Matrix

/**
 * 活性化関数
 */
trait ActivationFunction {

  def apply(m: Matrix): Matrix = {
    val result = Matrix(m.rows, m.cols)
    for (i <- 0 until m.rows; j <- 0 until m.cols) {
      result(i, j) = apply1(m(i, j))
    }
    result
  }

  def apply(xs: Seq[Double]): Seq[Double] = xs.map(apply1)

  def apply1(x: Double): Double
}

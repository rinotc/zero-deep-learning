package activationfunction
import matrix.Matrix

import scala.math.exp

object SoftmaxFunction extends ActivationFunction {
  override def apply(m: Matrix): Matrix = {
    require(m.rows == 1, "Softmax function can only be applied to a row matrix")
    val result    = Matrix(1, m.cols)
    val max_a     = m(0).max
    val exp_a     = m(0).map(x => exp(x - max_a))
    val sum_exp_a = exp_a.sum
    for (i <- 0 until m.cols) {
      result(0, i) = exp_a(i) / sum_exp_a
    }
    result
  }

  def apply1(x: Double): Double = throw new UnsupportedOperationException(
    "Softmax function can only be applied to a matrix"
  )
}

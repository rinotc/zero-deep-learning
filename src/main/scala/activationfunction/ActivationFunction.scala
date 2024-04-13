package activationfunction

import com.github.rinotc.smatrix.mutable.MutableMatrix
import com.github.rinotc.smatrix.immutable.Matrix

/**
 * 活性化関数
 */
trait ActivationFunction {

  def apply(m: Matrix[Double]): Matrix[Double] = {
    val result = MutableMatrix[Double](m.rows, m.cols)
    for (i <- 0 until m.rows; j <- 0 until m.cols) {
      result(i, j) = apply1(m(i, j))
    }
    result.toImmutable
  }

  def apply1(x: Double): Double
}

package neuralnetwork

import activationfunction.ActivationFunction
import com.github.rinotc.smatrix.immutable.Matrix

/**
 * ニューラルネットワークの層
 * @param layer
 *   層の番号
 * @param W
 *   重み行列
 * @param B
 *   バイアス行列
 * @param af
 *   活性化関数
 */
class Layer(val layer: Int, val W: Matrix[Double], val B: Matrix[Double], val af: ActivationFunction)
    extends Ordered[Layer] {

  /** 順伝搬 */
  def forward(X: Matrix[Double]): Matrix[Double] = {
    val A = X * W + B
    val Z = af(A)
    Z
  }

  /** 逆伝搬 */
  def backward(x: Matrix[Double]): Matrix[Double] = ???

  def compare(that: Layer): Int = this.layer - that.layer
}

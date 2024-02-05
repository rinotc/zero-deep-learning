package neuralnetwork

import activationfunction.ActivationFunction
import matrix.Matrix

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
class Layer(val layer: Int, val W: Matrix, val B: Matrix, val af: ActivationFunction) extends Ordered[Layer] {

  /** 順伝搬 */
  def forward(X: Matrix): Matrix = {
    val A = X * W + B
    val Z = af(A)
    Z
  }

  /** 逆伝搬 */
  def backward(x: Matrix): Matrix = ???

  def compare(that: Layer): Int = this.layer - that.layer
}

object Layer {
  def apply(layer: Int, W: Matrix, B: Matrix, af: ActivationFunction): Layer = new Layer(layer, W, B, af)
}

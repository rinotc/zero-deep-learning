package activationfunction

/**
 * ReLU関数（Rectified Linear Unit Function）
 */
class ReLUFunction extends ActivationFunction {
  def apply1(x: Double): Double = {
    if (x > 0) x else 0
  }
}

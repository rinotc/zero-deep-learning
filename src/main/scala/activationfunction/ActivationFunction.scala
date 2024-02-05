package activationfunction

/**
 * 活性化関数
 */
trait ActivationFunction {

  def apply(xs: Seq[Double]): Seq[Double] = xs.map(apply1)

  def apply1(x: Double): Double
}

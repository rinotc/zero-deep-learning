package activationfunction

import activationfunction.ActivationFunction

import scala.math.exp

/** シグモイド関数 */
object SigmoidFunction extends ActivationFunction {

  def apply1(x: Double): Double = 1 / (1 + exp(-x))
}

package activationfunction

import activationfunction.ActivationFunction

/**
 * ステップ関数
 */
object StepFunction extends ActivationFunction {

  def apply1(x: Double): Double = if x > 0 then 1 else 0
}

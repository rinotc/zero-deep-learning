package perceptron

class Perceptron(
    val weights: List[Double],
    val bias: Double
) {
  def inputLength: Int = weights.length
  def predict(inputs: List[Double]): Int = {
    require(inputs.length == weights.length, "The lengths of inputs and weights must be the same.")
    val tmp = inputs.zip(weights).map((x, w) => x * w).sum + bias
    if tmp <= 0 then 0 else 1
  }
}

object Perceptron {
  def AND: Perceptron  = new Perceptron(List(0.5, 0.5), -0.7)
  def OR: Perceptron   = new Perceptron(List(0.5, 0.5), -0.2)
  def NAND: Perceptron = new Perceptron(List(-0.5, -0.5), 0.7)

  def XOR(inputs: List[Double]): Int = {
    val s1 = NAND.predict(inputs)
    val s2 = OR.predict(inputs)
    AND.predict(List(s1, s2))
  }
}

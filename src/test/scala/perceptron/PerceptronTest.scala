package perceptron

import base.BaseTest
import perceptron.Perceptron

class PerceptronTest extends BaseTest {

  it("ANDゲート") {
    val p = Perceptron.AND
    p.predict(List(1, 1)) should be(1)
    p.predict(List(0, 1)) should be(0)
    p.predict(List(1, 0)) should be(0)
    p.predict(List(0, 0)) should be(0)
  }

  it("ORゲート") {
    val p = Perceptron.OR
    p.predict(List(1, 1)) should be(1)
    p.predict(List(0, 1)) should be(1)
    p.predict(List(1, 0)) should be(1)
    p.predict(List(0, 0)) should be(0)
  }

  it("NANDゲート") {
    val p = Perceptron.NAND
    p.predict(List(1, 1)) should be(0)
    p.predict(List(0, 1)) should be(1)
    p.predict(List(1, 0)) should be(1)
    p.predict(List(0, 0)) should be(1)
  }

  it("XORゲート") {
    val p = Perceptron.XOR
    p(List(1, 1)) should be(0)
    p(List(0, 1)) should be(1)
    p(List(1, 0)) should be(1)
    p(List(0, 0)) should be(0)
  }
}

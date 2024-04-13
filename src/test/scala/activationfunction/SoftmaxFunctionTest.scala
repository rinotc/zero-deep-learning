package activationfunction

import base.BaseTest
import com.github.rinotc.smatrix.immutable.Matrix

class SoftmaxFunctionTest extends BaseTest {

  describe("SoftmaxFunctionTest") {
    describe("apply") {
      it("ソフトマックス関数の出力は各要素0以上1以下かつ、その合計は1になる") {
        val input  = Matrix(Array(Array(1.0, 2.0, 3.0)))
        val output = SoftmaxFunction(input)
        assert(output(0).forall(x => x >= 0 && x <= 1))
        output(0).sum shouldBe 1.0 +- 0.00001
      }

      it("2行以上のの行列には対応できない") {
        val input = Matrix(Array(Array(1.0, 2.0, 3.0), Array(4.0, 5.0, 6.0)))
        intercept[IllegalArgumentException] {
          SoftmaxFunction(input)
        }
      }
    }

    describe("apply1") {
      it("apply1を呼ぼうとすると,UnsupportedOperationExceptionが発生する") {
        intercept[UnsupportedOperationException] {
          SoftmaxFunction.apply1(1.0)
        }
      }
    }
  }
}

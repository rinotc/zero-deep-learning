package matrix

import base.BaseTest

class MatrixTest extends BaseTest {

  it("toString") {
    val matrix = new Matrix(2, 2)
    matrix.update(0, 0, 1)
    matrix.update(0, 1, 2)
    matrix.update(1, 0, 3)
    matrix.update(1, 1, 4)
    val expected = "1.0 2.0\n3.0 4.0"
    matrix.toString should be(expected)
  }

  describe("dot 行列の積のテスト") {
    it("2x2と2x2の行列の積の計算が正しい") {
      val matrix1  = Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.0)))
      val matrix2  = Matrix(Array(Array(5.0, 6.0), Array(7.0, 8.0)))
      val expected = Matrix(Array(Array(19.0, 22.0), Array(43.0, 50.0)))
      matrix1.dot(matrix2) should be(expected)
    }

    it("2x3 と 3x5 の行列の積の計算が正しい") {
      val matrix1 = Matrix(Array(Array(1.0, 2.0, 3.0), Array(4.0, 5.0, 6.0)))
      val matrix2 = Matrix(
        Array(
          Array(7.0, 8.0, 9.0, 10.0, 11.0),
          Array(12.0, 13.0, 14.0, 15.0, 16.0),
          Array(17.0, 18.0, 19.0, 20.0, 21.0)
        )
      )
      val expected = Matrix(Array(Array(82.0, 88.0, 94.0, 100.0, 106.0), Array(190.0, 205.0, 220.0, 235.0, 250.0)))
      val actual   = matrix1.dot(matrix2)
      actual shouldBe expected
    }
  }
  describe("等価性のテスト") {
    it("要素が一つでも異なれば、false") {
      val matrix1 = Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.0)))
      val matrix2 = Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.1)))
      matrix1.equals(matrix2) should be(false)
    }

    it("要素が全て一致していればtrue") {
      val matrix1 = Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.0)))
      val matrix2 = Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.0)))
      matrix1.equals(matrix2) should be(true)
    }
  }
}

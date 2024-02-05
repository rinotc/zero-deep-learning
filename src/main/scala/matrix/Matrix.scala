package matrix

import scala.annotation.targetName

class Matrix(val rows: Int, val cols: Int) {
  private val data: Array[Array[Double]] = Array.ofDim[Double](rows, cols)

  def apply(row: Int, col: Int): Double = data(row)(col)

  def update(row: Int, col: Int, value: Double): Unit = {
    data(row)(col) = value
  }

  @targetName("dotSymbol")
  def *(that: Matrix): Matrix = dot(that)

  def dot(that: Matrix): Matrix = {
    require(
      this.cols == that.rows, // 2 x 3 * 3 x 5 => 2 x 5
      "The number of columns of the first matrix must be equal to the number of rows of the second matrix"
    )
    val result = new Matrix(this.rows, that.cols)
    for {
      i <- 0 until this.rows
      j <- 0 until that.cols
    } {
      var sum = 0.0
      for (k <- 0 until this.cols) {
        sum += this(i, k) * that(k, j)
      }
      result(i, j) = sum
    }
    result
  }

  def shape: (Int, Int) = (rows, cols)

  override def toString: String = {
    val rowsStrings = for (row <- data) yield row.mkString(" ")
    rowsStrings.mkString("\n")
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[Matrix]

  override def equals(other: Any): Boolean = other match
    case that: Matrix =>
      that.canEqual(this) &&
      this.rows == that.rows &&
      this.cols == that.cols &&
      this.data.corresponds(that.data)(_ sameElements _)
    case _ => false

  override def hashCode(): Int = {
    val prime  = 31
    var result = 1
    result = prime * result + rows
    result = prime * result + cols
    result = prime * result + data.map(_.toSeq).toSeq.hashCode()
    result
  }
}

object Matrix {
  def apply(rows: Int, cols: Int): Matrix = new Matrix(rows, cols)

  def apply(data: Array[Array[Double]]): Matrix = {
    val rows   = data.length
    val cols   = data(0).length
    val matrix = new Matrix(rows, cols)
    for {
      i <- 0 until rows
      j <- 0 until cols
    } {
      matrix(i, j) = data(i)(j)
    }
    matrix
  }
}

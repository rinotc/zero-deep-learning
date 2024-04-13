package mnist

import com.github.rinotc.smatrix.immutable.Matrix

import java.awt.Color
import java.nio.ByteBuffer
import java.nio.file.{Files, Path, Paths}
import javax.swing.{JFrame, JPanel, WindowConstants}

//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
object Mnist {

  given Conversion[Vector[Int], Array[Int]] with
    def apply(v: Vector[Int]): Array[Int] = v.toArray

  /**
   * これは60,000枚の訓練画像データを含んでいます。 各画像は28x28ピクセルのグレースケール画像で、各ピクセルの値は0から255までの整数です
   */
  val `train-image-idx3-ubyte`: Path = Paths.get(getClass.getResource("/train-images.idx3-ubyte").getPath)

  /**
   * これは上記の訓練画像データに対応するラベルデータを含んでいます。各ラベルは0から9までの整数で、対応する画像の数字を表しています。
   */
  val `train-labels-idx1-ubyte`: Path = Paths.get(getClass.getResource("/train-labels.idx1-ubyte").getPath)

  /**
   * これは10,000枚のテスト画像データを含んでいます。 訓練画像データと同様に、各画像は28x28ピクセルのグレースケール画像で、各ピクセルの値は0から255までの整数です。
   */
  val `t10k-images-idx3-ubyte`: Path = Paths.get(getClass.getResource("/t10k-images.idx3-ubyte").getPath)

  /**
   * れは上記のテスト画像データに対応するラベルデータを含んでいます。訓練ラベルデータと同様に、各ラベルは0から9までの整数で、対応する画像の数字を表しています。
   */
  val `t10k-labels-idx1-ubyte`: Path = Paths.get(getClass.getResource("/t10k-labels.idx1-ubyte").getPath)

  def loadImages(path: Path): Array[Array[Int]] = {
    val buffer: ByteBuffer = ByteBuffer.wrap(Files.readAllBytes(path))
    val magicNumber        = buffer.getInt
    assert(magicNumber == 2051)
    val numImages = buffer.getInt
    val numRows   = buffer.getInt
    val numCols   = buffer.getInt
    val images    = Array.ofDim[Int](numImages, numRows * numCols)
    for {
      i <- 0 until numImages
      j <- 0 until numRows * numCols
    } {
      images(i)(j) = buffer.get() & 0xff
    }
    images
  }

  /**
   * ラベルデータを読み込む
   * @param path
   *   ラベルデータのパス
   * @return
   *   ラベルデータ
   */
  def loadLabels(path: Path): Array[Int] = {
    val buffer      = ByteBuffer.wrap(Files.readAllBytes(path))
    val magicNumber = buffer.getInt
    assert(magicNumber == 2049)
    val numLabels = buffer.getInt
    val labels    = new Array[Int](numLabels)
    for (i <- 0 until numLabels) labels(i) = buffer.get() & 0xff
    labels
  }

  /**
   * 画像を表示する
   * @param bytes
   *   画像データ
   */
  def showImage(bytes: Array[Int]): Unit = {
    val numRows = 28
    val numCols = 28
    // 画像データを二次元配列に変換
    val imageArray = Array.ofDim[Int](numRows, numCols)
    for (i <- 0 until numRows; j <- 0 until numCols) {
      imageArray(i)(j) = bytes(i * numCols + j)
    }

    // 2次元配列として画像を表示
    val frame = new JFrame("MNIST Image")
    frame.setSize(numCols * 10, numRows * 10)
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    val panel = new JPanel {
      override def paintComponent(g: java.awt.Graphics): Unit = {
        val g2 = g.create().asInstanceOf[java.awt.Graphics2D]
        for (i <- 0 until numRows; j <- 0 until numCols) {
          g2.setColor(new Color(imageArray(i)(j)))
          g2.fillRect(j * 10, i * 10, 10, 10)
        }
      }
    }

    frame.setContentPane(panel)
    frame.setVisible(true)
  }

  def showImage(bytes: Seq[Int]): Unit = showImage(bytes.toArray)

  /**
   * MNISTデータセットの読み込み
   *
   * @param normalize
   *   画像のピクセル値を0.0-1.0に正規化する
   * @param flatten
   *   画像を一次元配列にするかどうか
   * @param one_hot_label
   *   one_hot_labelが`true`の場合、ラベルはone-hot配列として返す. one-hot配列とは、たとえば[0,0,1,0,0,0,0,0,0,0]のような配列
   */
  def loadMnist(
      normalize: Boolean = true,
      flatten: Boolean = true,
      one_hot_label: Boolean = false
  ): LoadMnistResult = {
    val trainImages = loadImages(`train-image-idx3-ubyte`).map(_.map(_.toDouble))
    val trainLabels = loadLabels(`train-labels-idx1-ubyte`)
    val testImages  = loadImages(`t10k-images-idx3-ubyte`).map(_.map(_.toDouble))
    val testLabels  = loadLabels(`t10k-labels-idx1-ubyte`)

    if (normalize) {
      for (i <- trainImages.indices) {
        for (j <- trainImages(i).indices) {
          val x = trainImages(i)(j)
          trainImages(i)(j) = trainImages(i)(j) / 255.0
        }
      }
      for (i <- testImages.indices) {
        for (j <- testImages(i).indices) {
          testImages(i)(j) = testImages(i)(j) / 255
        }
      }
    }

    if (one_hot_label) {
      val newTrainLabels = Array.ofDim[Int](trainLabels.length, 10)
      for (i <- trainLabels.indices) {
        newTrainLabels(i)(trainLabels(i)) = 1
      }
      val newTestLabels = Array.ofDim[Int](testLabels.length, 10)
      for (i <- testLabels.indices) {
        newTestLabels(i)(testLabels(i)) = 1
      }
    }

//    if (flatten) {
//      LoadMnistResult(
//        Matrix(trainImages),
//        Matrix(trainLabels.map(Array(_))),
//        Matrix(testImages),
//        Matrix(testLabels.map(Array(_)))
//      )
//    } else {
//      LoadMnistResult(
//        Matrix(trainImages.map(_.map(Array(_)))),
//        Matrix(trainLabels.map(Array(_))),
//        Matrix(testImages.map(_.map(Array(_)))),
//        Matrix(testLabels.map(Array(_)))
//      )
//    }
    ???
  }

  case class LoadMnistResult(
      trainImages: Matrix[Double],
      trainLabels: Matrix[Int],
      testImages: Matrix[Double],
      testLabels: Matrix[Int]
  )
}

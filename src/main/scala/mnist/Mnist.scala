package mnist

import java.awt.Color
import java.nio.ByteBuffer
import java.nio.file.{Files, Path, Paths}
import javax.swing.{JFrame, JPanel, WindowConstants}

//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
object Mnist {

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

  def loadLabels(path: Path): Array[Int] = {
    val buffer      = ByteBuffer.wrap(Files.readAllBytes(path))
    val magicNumber = buffer.getInt
    assert(magicNumber == 2049)
    val numLabels = buffer.getInt
    val labels    = new Array[Int](numLabels)
    for (i <- 0 until numLabels) labels(i) = buffer.get() & 0xff
    labels
  }

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
}

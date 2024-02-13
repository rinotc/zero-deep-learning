import activationfunction.{ActivationFunction, IdentityFunction, SigmoidFunction}
import io.circe.parser.parse
import matrix.Matrix
import mnist.Mnist.{loadImages, showImage}
import neuralnetwork.{Layer, NeuralNetwork}

import java.awt.{Color, Graphics, Graphics2D}
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.file.{Files, Path, Paths}
import javax.swing.{JFrame, JPanel}
import scala.io.{BufferedSource, Source}
import scala.util.{Try, Using}

@main
def main(): Unit = {
  val X = Matrix(Array(Array(1, 2)))
  val W = Matrix(Array(Array(1, 3, 5), Array(2, 4, 6)))
  val Y = X dot W
  println(Y.prettyString)
}

@main
def main2(): Unit = {
  val X  = Matrix(Array(Array(1.0, 0.5)))
  val W1 = Matrix(Array(Array(0.1, 0.3, 0.5), Array(0.2, 0.4, 0.6)))
  val B1 = Matrix(Array(Array(0.1, 0.2, 0.3)))
  println(X.shape)
  println(W1.shape)
  println(B1.shape)

  val A1 = (X * W1) + B1
  println(A1.prettyString)
  val Z1 = SigmoidFunction(A1)
  println(Z1.prettyString)

  val W2 = Matrix(Array(Array(0.1, 0.4), Array(0.2, 0.5), Array(0.3, 0.6)))
  val B2 = Matrix(Array(Array(0.1, 0.2)))
  println(Z1.shape)
  println(W2.shape)
  println(B2.shape)

  val A2 = (Z1 * W2) + B2
  val Z2 = SigmoidFunction(A2)
  println(Z2.prettyString)

  def identityFunction(x: Matrix): Matrix = x

  val W3 = Matrix(Array(Array(0.1, 0.3), Array(0.2, 0.4)))
  val B3 = Matrix(Array(Array(0.1, 0.2)))
  val A3 = (Z2 * W3) + B3
  val Y  = identityFunction(A3) // 恒等関数
  println(Y.prettyString)
}

@main
def main3(): Unit = {
  val layer1: Layer =
    Layer(
      1,
      W = Matrix(Array(Array(0.1, 0.3, 0.5), Array(0.2, 0.4, 0.6))),
      B = Matrix(Array(Array(0.1, 0.2, 0.3))),
      af = SigmoidFunction
    )

  val layer2: Layer = Layer(
    2,
    W = Matrix(Array(Array(0.1, 0.4), Array(0.2, 0.5), Array(0.3, 0.6))),
    B = Matrix(Array(Array(0.1, 0.2))),
    SigmoidFunction
  )
  val layer3: Layer = Layer(
    3,
    W = Matrix(Array(Array(0.1, 0.3), Array(0.2, 0.4))),
    B = Matrix(Array(Array(0.1, 0.2))),
    IdentityFunction
  )

  val nn = NeuralNetwork(layers = List(layer1, layer2, layer3))
  val X  = Matrix(Array(Array(1.0, 0.5)))
  val Y  = nn.forward(X)
  println(Y.prettyString)
}

@main
def main4(): Unit = {
  given Conversion[Float, Double] with
    def apply(f: Float): Double = f.toDouble

  val pathString  = getClass.getResource("/train-images.idx3-ubyte").getPath
  val path        = Paths.get(pathString)
  val trainImages = Matrix(loadImages(path))
  println(trainImages.shape)
//  println(trainLabels.length)
  showImage(trainImages(0).map(_.toInt))
  showImage(trainImages(1).map(_.toInt))

//  val source: BufferedSource  = Source.fromResource("sample_weight.json")
//  val jsonString: Try[String] = Using(source)(_.mkString)
//  val json                    = parse(jsonString.get)
}

package neuralnetwork

import com.github.rinotc.smatrix.immutable.Matrix

/** ニューラルネットワーク */
class NeuralNetwork private (
    _layers: List[Layer]
) {
  private val layers: List[Layer] = _layers.sorted

  def forward(X: Matrix[Double]): Matrix[Double] = {
    layers.foldLeft(X) { (X, layer) =>
      layer.forward(X)
    }
  }
}

object NeuralNetwork {
  def apply(layers: List[Layer]): NeuralNetwork = new NeuralNetwork(layers)
}

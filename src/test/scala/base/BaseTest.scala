package base

import org.scalatest.diagrams.Diagrams
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{EitherValues, Inside, OptionValues}

trait BaseTest extends AnyFunSpec with OptionValues with EitherValues with Inside with Diagrams with Matchers

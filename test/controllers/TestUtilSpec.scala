package controllers

import org.scalatest.FlatSpec
import org.scalatest.WordSpec

class TestUtilSpec extends WordSpec {

  "isZero" when {

    "number is 0" should {
      "return true" in {
        assert(TestUtil.isZero(0) === true)
      }
    }

    "number is greater than 0" should {
      "return false" in {
        assert(TestUtil.isZero(1) === false)
        assert(TestUtil.isZero(2) === false)
        assert(TestUtil.isZero(-1) === false)

      }
    }

  }


}

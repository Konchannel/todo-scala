package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class TodoControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "TodoController POST" should {
    "return BadRequest when parameters is empty" in {
      val request = FakeRequest(POST, "/todo/tasks")
      val result: Future[Result] = route(app, request).get

      status(result) mustBe BAD_REQUEST
      contentType(result) mustBe Some("text/plain")
      contentAsString(result) mustBe ("illegal parameters")
    }
    "return OK" in {
      val request = FakeRequest(POST, "/todo/tasks").withJsonBody(Json.parse("""{"title":"test task","memo":"Test_first"}"""))
      val result: Future[Result] = route(app, request).get

      status(result) mustBe OK
      contentType(result) mustBe Some("text/plain")
      contentAsString(result) mustBe ("Insert Complete")
    }
  }

  "TodoController index" should {
    "return OK" in {
      val request = FakeRequest(GET, "/todo/tasks")
      val result: Future[Result] = route(app, request).get

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")
      contentAsString(result) must include ("id")
    }
  }
}

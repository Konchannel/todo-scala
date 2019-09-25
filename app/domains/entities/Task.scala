package domains.entities

import java.text.SimpleDateFormat
import java.util.Date

import play.api.libs.json.{JsNull, JsNumber, JsObject, JsString, JsValue}
import scalikejdbc.WrappedResultSet


/**
  *
  * @param id
  * @param title
  * @param deadline
  * @param memo
  */
case class Task(id: Int, title: String, deadline: Option[Date], memo: Option[String]) {

  def toJson(): JsObject = {
    JsObject(
      Seq(
        "id" -> JsNumber(id),
        "title" -> JsString(title),
        "deadline" -> {
          /*
          val deadline2 = deadline.getOrElse(JsNull)
          val strDay:Option[String] = new SimpleDateFormat("yyyy-MM-dd").format(deadline2)
          JsString(strDay)
          */
          val jsDay: JsValue with Serializable = deadline match {
            case Some(x) => {
              val strDay:String = new SimpleDateFormat("yyyy-MM-dd").format(x)
              JsString(strDay)
            }
            case None => JsNull
          }
          jsDay
        },
        "memo" -> {
          val step1: Option[String] = memo
          val step2: Option[JsString] = step1.map(_memo => JsString(_memo))
          val step3: JsValue = step2.getOrElse(JsNull)
          step3
        }
      )
    )
  }

}

object Task {

  def setTask(set: WrappedResultSet): Task = Task(
    id = set.int("id"),
    title = set.string("title"),
    deadline = set.dateOpt("deadline"),
    memo = set.stringOpt("memo")
  )

  def fromJson(json: JsValue): Task = {
    //val jsonString: String = Json.stringify(json)

    val jsonId = (json \ "id").asOpt[Int].getOrElse(-1)
    val jsonTitle = (json \ "title").as[String]
    val jsonDate = (json \ "deadline").asOpt[Date]
    val jsonMemo: Option[String] = (json \ "memo").asOpt[String]

    val jsonValue: Task = Task.apply(jsonId, jsonTitle, jsonDate, jsonMemo)
    jsonValue
  }


}


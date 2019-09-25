package controllers

import java.text.SimpleDateFormat
import java.util.Date

import domains.entities.Task
import domains.repositories.TaskRepository
import javax.inject._
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import scalikejdbc.AutoSession

@Singleton
class TodoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  implicit val session = AutoSession

  val logger = Logger(this.getClass)


  /**
    * 全件取得API
    *
    * @return
    */
  def index() = Action { implicit request: Request[AnyContent] =>

    // 1. database からデータを取ってくる
    val tasks: Seq[Task] = TaskRepository.findAll()

    // 2. Jsonに変換する
    // コレクションライブラリ （変換処理）
    val jsonArray: JsArray = JsArray(tasks.map(_.toJson()))

    // 3. responseを返す
    Ok(jsonArray)
  }

  /** 一件取得API
    *
    * @param id
    * @return
    */
  def get(id: Int) = Action { implicit request: Request[AnyContent] =>

    val taskOpt = TaskRepository.find(id)

    //logger.info(s"task => $taskOpt")

    taskOpt match {
      case Some(task) => {
        // 1件を変換処理

        val jsonTask: JsValue = task.toJson()

        Created(jsonTask)
      }
      case None => NotFound

    }
  }

  /**
    * 1件追加　//動作未確認のためご注意
    *
    * @return
    */
  def insert() = Action { implicit request: Request[AnyContent] =>

    request.body.asJson match {
      case Some(json) => {
        val task: Task = Task.fromJson(json)
        val inserted: Task = TaskRepository.insert(task)
        Ok(s"Insert Complete. id: ${inserted.id}")
      }
      case None => BadRequest("illegal parameters")
    }
  }


  /** 1件削除
    *
    * @param id
    * @return
    */
  def delete(id: Int) = Action { implicit request: Request[AnyContent] =>

    TaskRepository.delete(id) match {
      case 1 => Ok("kill it.")
      case 0 => NotFound(s"Error: id=$id not found.")
      case _ => InternalServerError("yabee era-")
    }

  }

  /** 1件更新
    *
    */
  def update() = Action { implicit request: Request[AnyContent] =>

    request.body.asJson match {
      case Some(json) => {
        val task: Task = Task.fromJson(json)
        TaskRepository.update(task) match {
          case 1 => Ok("Update Done")
          case 0 => NotFound(s"Error: id=${task.id} not found.")
          case _ => InternalServerError("yabee era-")
        }

      }
      case None => BadRequest("illegal parameters")


    }
  }

}

package domains.repositories

import domains.entities.Task
import scalikejdbc.DBSession
import scalikejdbc._

/**
  * Taskデータに関するデータベース操作をやるやつ
  */
object TaskRepository {

  //1件取得する処理
  def find(id: Int)(implicit session: DBSession): Option[Task] = {
    sql"select * from tasks where id = $id"
      .map(Task.setTask(_))
      .single()
      .apply()
  }

  //Task  から全件取ってくる処理
  def findAll()(implicit session: DBSession): Seq[Task] = {
    sql"select * from tasks" // SQLを定義
      .map(Task.setTask(_)) // ResultSet を変換する処理を定義
      .list // 結果は複数件（List）であるという定義
      .apply() // ここまで定義した処理を実行！
  }

  //1件追加
  def insert(insertTask: Task)(implicit session: DBSession): Task = {
    val generatedId: Long = sql"insert into tasks (title,deadline,memo) values (${insertTask.title},${insertTask.deadline},${insertTask.memo})"
      .updateAndReturnGeneratedKey("id").apply()

    insertTask.copy(id = generatedId.toInt)
  }

  //1件削除
  def delete(id: Int)(implicit session: DBSession): Int = {
    sql"delete from tasks where id = $id"
      .update.apply()
  }

  def update(newTask: Task)(implicit session: DBSession): Int = {
    sql"update tasks set id = ${newTask.id},title = ${newTask.title},deadline = ${newTask.deadline},memo = ${newTask.memo} where id = ${newTask.id}"
      .update.apply()
  }
}

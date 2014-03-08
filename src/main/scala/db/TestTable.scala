package db

import slick.driver.JdbcDriver.simple._

case class Row(id: Int)

class Rows(tag: Tag) extends Table[Row](tag, "table") {
  def id = column[Int]("id")
  def * = (id) <> (Row, Row.unapply _)
}

object Rows extends TableQuery(new Rows(_)) {

  def test : Query[Column[Int], Int] = {
    (for {
      r <- Rows // r should be [Rows], but it's [Any]
      id = r.id // commenting this out fixes the problem
    } yield(r.id))
  }

}


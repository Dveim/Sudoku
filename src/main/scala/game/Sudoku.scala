package game

import java.io.FileWriter

import scala.collection.immutable.IndexedSeq

class Sudoku private(val rows: IndexedSeq[IndexedSeq[Int]]) {

  import Sudoku.noDuplicates

  val size = rows.length
  val regionSize = math.sqrt(size).toInt // possible precision loss is handled
  lazy val columns = rows.transpose
  lazy val regions = for (i <- 0 until regionSize; j <- 0 until regionSize) yield
    region(i * regionSize, j * regionSize)

  def row(i: Int): IndexedSeq[Int] = rows(i)

  def column(i: Int): IndexedSeq[Int] = columns(i)

  def region(x: Int, y: Int): IndexedSeq[Int] = {
    val (rx, ry) = (x / regionSize, y / regionSize) // region coordinates
    val leftTop = (rx * regionSize, ry * regionSize)
    val rightBottom = (leftTop._1 + regionSize - 1, leftTop._2 + regionSize - 1)

    {
      for (i <- leftTop._2 to rightBottom._2) yield
        row(i).slice(leftTop._1, rightBottom._1 + 1)
    }.flatten
  }

  lazy val isSolved =
    rows.forall(x => noDuplicates(x) && x.forall(_ != 0)) &&
      regions.forall(noDuplicates) &&
      columns.forall(noDuplicates)

  def updated(x: Int, y: Int, v: Int): Sudoku = {
    // since this method in Solver is used safely, one could return just `new Sudoku` w/o checks
    // but, in general, it's unsafe behaviour
    val sudoku = new Sudoku(rows.updated(y, rows(y).updated(x, v)))

    require(noDuplicates(sudoku.column(x)), "each column must have no >0 duplicates")
    require(noDuplicates(sudoku.row(y)), "each row must have no >0 duplicates")
    require(noDuplicates(sudoku.region(x, y)), "each region must have no >0 duplicates")

    sudoku
  }

  override def toString = rows.map(_.mkString(",")).mkString("\n")

  def writeToFile(fileName: String): Unit = {
    val fw = new FileWriter(fileName)
    fw.write(toString)
    fw.close()
  }
}

object Sudoku {
  def noDuplicates(s: Seq[Int]): Boolean = {
    val noZeros = s.filter(_ != 0)
    noZeros.distinct.length == noZeros.length
  }

  // get some run-time guarantees
  def apply(grid: IndexedSeq[IndexedSeq[Int]]): Sudoku = {
    val sudoku = new Sudoku(grid)
    import sudoku._

    require(regionSize * regionSize == size, "rows must be NxN square of NxN squares")

    for (row <- rows) {
      require(row.length == size, "rows is not a valid square")
      row.foreach(x => require(0 <= x && x <= size, "invalid value in rows"))
      require(noDuplicates(row), "each row must have no >0 duplicates")
    }

    columns.foreach(x => require(noDuplicates(x), "each column must have no >0 duplicates"))

    regions.foreach(x => require(noDuplicates(x), "each region must have no >0 duplicates"))

    sudoku
  }

  // input format is pretty easy, so don't need heavy csv-parsing library
  def apply(fileName: String): Sudoku = {
    import scala.io.Source

    val lines = Source.fromFile(fileName).getLines()
    val grid = for (line <- lines) yield
      line.split(",").map(x => Integer.parseInt(x.trim)).toVector

    Sudoku(grid.toVector)
  }
}

package game

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SudokuSuite extends FunSuite {

  test("valid non-solved sudoku") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    Sudoku(v)
  }

  test("sudoku is not N^2 x N^2") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6),
      Vector(0, 8, 2, 4, 1, 0, 7, 0),
      Vector(7, 6, 4, 3, 8, 0, 0, 9),
      Vector(2, 1, 8, 7, 3, 9, 0, 4),
      Vector(0, 0, 0, 8, 0, 4, 2, 3),
      Vector(0, 4, 3, 0, 5, 2, 9, 7),
      Vector(4, 0, 6, 5, 7, 1, 0, 0),
      Vector(3, 5, 9, 0, 2, 8, 4, 1)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("sudoku is not a square (1)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("sudoku is not a square (2)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4, 6),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("some value not in 1..size (1)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 42),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("some value not in 1..size (2)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, -666),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("noDuplicates() works on valid input") {
    val v = Vector(1, 2, 3, 4, 5)

    assert(Sudoku.noDuplicates(v))
  }

  test("noDuplicates() works with valid but many zeros") {
    val v = Vector(0, 0, 0, 0, 1, 2, 3, 4, 5)

    assert(Sudoku.noDuplicates(v))
  }

  test("noDuplicates() doesn't work when duplicates") {
    val v = Vector(0, 0, 0, 0, 1, 2, 3, 4, 5, 5)

    assert(Sudoku.noDuplicates(v) === false)
  }

  test("each row must have no >0 duplicates") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 4, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("each column must have no >0 duplicates") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 7),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("row() works") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).row(1) === Vector(0, 8, 2, 4, 1, 0, 7, 0, 3))
  }

  test("column() works") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).column(6) === Vector(8, 7, 0, 0, 2, 9, 0, 4, 5))
  }

  test("region() works (1)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).region(0, 0) === Vector(0, 3, 5, 0, 8, 2, 7, 6, 4))
  }

  test("region() works (2)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).region(0, 4) === Vector(2, 1, 8, 0, 0, 0, 0, 4, 3))
  }

  test("region() works (3)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).region(6, 6) === Vector(0, 0, 9, 4, 1, 7, 5, 2, 6))
  }

  test("each region must have no >0 duplicates (1)") {
    val v = Vector(
      Vector(0, 7, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("each region must have no >0 duplicates (2)") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 1, 0, 0, 5, 2, 6)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("isSolved() on non-filled sudoku") {
    val v = Vector(
      Vector(0, 3, 5, 2, 9, 0, 8, 6, 4),
      Vector(0, 8, 2, 4, 1, 0, 7, 0, 3),
      Vector(7, 6, 4, 3, 8, 0, 0, 9, 0),
      Vector(2, 1, 8, 7, 3, 9, 0, 4, 0),
      Vector(0, 0, 0, 8, 0, 4, 2, 3, 0),
      Vector(0, 4, 3, 0, 5, 2, 9, 7, 0),
      Vector(4, 0, 6, 5, 7, 1, 0, 0, 9),
      Vector(3, 5, 9, 0, 2, 8, 4, 1, 7),
      Vector(8, 0, 0, 9, 0, 0, 5, 2, 6)
    )

    assert(Sudoku(v).isSolved === false)
  }

  test("isSolved() on filled but invalid sudoku") {
    val v = Vector(
      Vector(1, 3, 5, 2, 9, 7, 8, 6, 4),
      Vector(9, 8, 2, 4, 1, 6, 7, 5, 3),
      Vector(7, 6, 4, 3, 8, 5, 1, 9, 2),
      Vector(2, 1, 8, 7, 3, 9, 6, 4, 5),
      Vector(5, 9, 7, 8, 6, 4, 2, 3, 1),
      Vector(6, 4, 3, 1, 5, 2, 9, 7, 8),
      Vector(4, 2, 6, 5, 7, 1, 3, 8, 9),
      Vector(3, 5, 9, 6, 2, 8, 4, 1, 7),
      Vector(8, 7, 1, 9, 4, 3, 5, 6, 2)
    )

    intercept[IllegalArgumentException] {
      Sudoku(v)
    }
  }

  test("isSolved() on solved sudoku") {
    val v = Vector(
      Vector(1, 3, 5, 2, 9, 7, 8, 6, 4),
      Vector(9, 8, 2, 4, 1, 6, 7, 5, 3),
      Vector(7, 6, 4, 3, 8, 5, 1, 9, 2),
      Vector(2, 1, 8, 7, 3, 9, 6, 4, 5),
      Vector(5, 9, 7, 8, 6, 4, 2, 3, 1),
      Vector(6, 4, 3, 1, 5, 2, 9, 7, 8),
      Vector(4, 2, 6, 5, 7, 1, 3, 8, 9),
      Vector(3, 5, 9, 6, 2, 8, 4, 1, 7),
      Vector(8, 7, 1, 9, 4, 3, 5, 2, 6)
    )

    assert(Sudoku(v).isSolved === true)
  }

  test("updated() with valid update") {
    val v = Vector(
      Vector(1, 3, 5, 2, 9, 7, 8, 6, 4),
      Vector(9, 8, 2, 4, 1, 6, 7, 5, 3),
      Vector(7, 6, 4, 3, 8, 5, 1, 9, 2),
      Vector(2, 1, 8, 7, 3, 9, 6, 4, 5),
      Vector(5, 9, 7, 8, 6, 4, 2, 3, 1),
      Vector(6, 4, 3, 1, 5, 2, 9, 7, 8),
      Vector(4, 2, 6, 5, 7, 1, 3, 8, 9),
      Vector(3, 5, 9, 6, 2, 8, 4, 1, 7),
      Vector(8, 7, 1, 9, 0, 3, 5, 2, 6)
    )

    val s = Sudoku(v)
    s.updated(4, 8, 4)
  }

  test("updated() with invalid update") {
    val v = Vector(
      Vector(1, 3, 5, 2, 9, 7, 8, 6, 4),
      Vector(9, 8, 2, 4, 1, 6, 7, 5, 3),
      Vector(7, 6, 4, 3, 8, 5, 1, 9, 2),
      Vector(2, 1, 8, 7, 3, 9, 6, 4, 5),
      Vector(5, 9, 7, 8, 6, 4, 2, 3, 1),
      Vector(6, 4, 3, 1, 5, 2, 9, 7, 8),
      Vector(4, 2, 6, 5, 7, 1, 3, 8, 9),
      Vector(3, 5, 9, 6, 2, 8, 4, 1, 7),
      Vector(8, 7, 1, 9, 0, 3, 5, 2, 6)
    )

    val s = Sudoku(v)
    intercept[IllegalArgumentException] {
      s.updated(4, 8, 9)
    }
  }
}

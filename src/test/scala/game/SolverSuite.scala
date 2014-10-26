package game

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SolverSuite extends FunSuite {

  test("solver on easy valid sudoku 9x9") {
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

    val res = Vector(
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

    val s = Sudoku(v)
    assert(Solver.solve(s).rows === res)
  }

  test("solver on hard valid sudoku 9x9") {
    val v = Vector(
      Vector(1, 0, 0, 0, 0, 7, 0, 9, 0),
      Vector(0, 3, 0, 0, 2, 0, 0, 0, 8),
      Vector(0, 0, 9, 6, 0, 0, 5, 0, 0),
      Vector(0, 0, 5, 3, 0, 0, 9, 0, 0),
      Vector(0, 1, 0, 0, 8, 0, 0, 0, 2),
      Vector(6, 0, 0, 0, 0, 4, 0, 0, 0),
      Vector(3, 0, 0, 0, 0, 0, 0, 1, 0),
      Vector(0, 4, 0, 0, 0, 0, 0, 0, 7),
      Vector(0, 0, 7, 0, 0, 0, 3, 0, 0)
    )

    val res = Vector(
      Vector(1, 6, 2, 8, 5, 7, 4, 9, 3),
      Vector(5, 3, 4, 1, 2, 9, 6, 7, 8),
      Vector(7, 8, 9, 6, 4, 3, 5, 2, 1),
      Vector(4, 7, 5, 3, 1, 2, 9, 8, 6),
      Vector(9, 1, 3, 5, 8, 6, 7, 4, 2),
      Vector(6, 2, 8, 7, 9, 4, 1, 3, 5),
      Vector(3, 5, 6, 4, 7, 8, 2, 1, 9),
      Vector(2, 4, 1, 9, 3, 5, 8, 6, 7),
      Vector(8, 9, 7, 2, 6, 1, 3, 5, 4)
    )

    val s = Sudoku(v)
    assert(Solver.solve(s).rows === res)
  }

  test("solver on easy sudoku 4x4") {
    val v = Vector(
      Vector(0, 0, 0, 0),
      Vector(4, 2, 3, 1),
      Vector(1, 3, 4, 2),
      Vector(2, 4, 1, 3)
    )

    val res = Vector(
      Vector(3, 1, 2, 4),
      Vector(4, 2, 3, 1),
      Vector(1, 3, 4, 2),
      Vector(2, 4, 1, 3)
    )

    val s = Sudoku(v)
    assert(Solver.solve(s).rows === res)
  }

  test("solver on unsolvable sudoku") {
    val v = Vector(
      Vector(1, 2, 3, 0),
      Vector(0, 0, 0, 2),
      Vector(0, 0, 0, 3),
      Vector(0, 0, 0, 4)
    )

    val s = Sudoku(v)
    intercept[NoSolutionFound]{
      Solver.solve(s)
    }
  }
}

import game._

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length != 2)
      println("\nUsage: sbt \"run <input> <output>\"")
    else {
      try {
        val sudoku = Sudoku(args(0))
        Solver.solve(sudoku).writeToFile(args(1))
      } catch {
        case e: IllegalArgumentException =>
          println("\nPlease, check input file for valid sudoku:\n" + e.getMessage)

        case e: NoSolutionFound =>
          println("\nNo solution found for this sudoku")
      }
    }
  }
}
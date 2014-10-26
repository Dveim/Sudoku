package game

class NoSolutionFound extends Exception

object Solver {

  def solve(s: Sudoku): Sudoku = solve(Stream(s))

  @scala.annotation.tailrec
  def solve(l: Stream[Sudoku]): Sudoku = l match {
    case x #:: xs =>
      lazy val _step = step(x)
      if (x.isSolved)
        x
      else if (_step.isEmpty)
        solve(xs)
      else
        solve(_step #::: xs)

    case _ =>
      throw new NoSolutionFound
  }

  def step(s: Sudoku): Stream[Sudoku] = {
    val possibleInsertions =
      for (y <- Stream.range(0, s.size); // rows
           x <- Stream.range(0, s.size); // columns
           if s.rows(y)(x) == 0;
           row = s.row(y);
           column = s.column(x);
           region = s.region(x, y);
           numbers = 1 to s.size;
           vs = numbers.filterNot((row ++ column ++ region).toSet)
      ) yield (x, y, vs) // x, y, possible numbers it that cell

    val confidentInsertions = possibleInsertions.filter(_._3.length == 1)

    if (confidentInsertions.nonEmpty) {
      val (x, y, vs) = confidentInsertions.head
      Stream(s.updated(x, y, vs.head))
    }

    else if (possibleInsertions.nonEmpty) {
      val (x, y, vs) = possibleInsertions.head
      for (v <- vs.toStream) yield
        s.updated(x, y, v)
    }

    else
      Stream.empty
  }
}
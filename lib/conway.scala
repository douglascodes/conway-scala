object Dish {
//     """Petry dish where all things happen. More commenting forthcoming.
//     Check the main_test.py file for a better understanding of what is going on.
//     http://en.wikipedia.org/wiki/Conway's_Game_of_Life
//     ^ The rules. This first version is just to be able to use Scala. A 2nd version 
//     will seek to utilize the best techniques in Scala.
//     """
  val measure: Double = 8.5
  val limit: Int = (measure * measure).toInt 
  val max_cells: Int = (limit * limit)
  val vigor: Double = (30.0 / 100.0)
  val start_count: Int = (max_cells * vigor).toInt

  var generation: Int = 0
  var pause: Boolean = false
  var potentials: Set[Tuple2[Int, Int]] = Set()
  var next_gen: Set[Tuple2[Int, Int]] = Set()
  var prev_gen: Set[Tuple2[Int, Int]] = Set()
  var cells: Set[Tuple2[Int, Int]] = Set()  
  spawn()


  def spawn() = {
    // One time call to randomly populate the Dish.cells value with random Tuple2[Int,Int]'s
    while ( start_count != cells.size ) {
      val x: Int = ((scala.math.random*(limit + 1)).toInt)
      val y: Int = ((scala.math.random*(limit + 1)).toInt)
      cells = cells ++ Set((x, y)) 
    }
  }

  def create_potentials() = {
    
    // potentials = cells ++ cells.map ( cell => 
    //   Set((cell._1 + 1, cell._2 + 1),
    //   (cell._1 - 1, cell._2 + 1),
    //   (cell._1, cell._2 + 1),
    //   (cell._1 + 1, cell._2),
    //   (cell._1 - 1, cell._2),
    //   (cell._1, cell._2),
    //   (cell._1 + 1, cell._2 - 1),
    //   (cell._1 - 1, cell._2 - 1),
    //   (cell._1, cell._2 - 1))
    // )
  }

  // def count_neighbors(cell: Tuple2[Int, Int]) = {
  //   cells.map ()

  // }

// def count_neighbors(self, cellA, passed_set):
//   c = 0
//   xA, yA = cellA
//   for cellB in iter(passed_set):
//       if cellA == cellB: continue
//       xB, yB = cellB
//       if abs(xB - xA) <= 1 and abs(yB - yA) <= 1:
//           c += 1
//   return c


}
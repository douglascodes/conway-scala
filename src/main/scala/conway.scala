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

  def loop_generations() = {
      create_potentials()
      generate_second_gen()
  }

  def create_potentials() = {
    var temp_potentials: Set[Set[Tuple2[Int, Int]]] = Set()
    temp_potentials = cells.map ( cell => 
      Set((cell._1 + 1, cell._2 + 1),
    (cell._1 - 1, cell._2 + 1),
    (cell._1, cell._2 + 1),
    (cell._1 + 1, cell._2),
    (cell._1 - 1, cell._2),
    (cell._1, cell._2),
    (cell._1 + 1, cell._2 - 1),
    (cell._1 - 1, cell._2 - 1),
    (cell._1, cell._2 - 1)))
    potentials = temp_potentials.flatten
  }

  def generate_second_gen() = {
    cells = potentials.filter ( cell => 
      survives(neighbors = count_neighbors(cell), cell)
    )
  }

  def survives(neighbors: Int, cell: Tuple2[Int, Int]): Boolean = ( neighbors, cells.contains(cell) ) match {
    case ( 3, _ ) => true
    case ( 2, true ) => true
    case _ => false
  }

  def count_neighbors(cell: Tuple2[Int, Int]): Int = {
    cells.count ( test => isA_neighbor(test = test, cell = cell))
  }

  def isA_neighbor(test: Tuple2[Int,Int], cell: Tuple2[Int, Int]): Boolean = ((cell._1 - test._1).abs, (cell._2 - test._2).abs) match  {
    case (1, 1) => true
    case (0, 1) => true
    case (1, 0) => true
    case (0, 0) => false
    case _ => false
  }
}
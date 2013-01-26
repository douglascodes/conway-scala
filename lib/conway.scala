object Dish {
//     """Petry dish where all things happen. More commenting forthcoming.
//     Check the main_test.py file for a better understanding of what is going on.
//     http://en.wikipedia.org/wiki/Conway's_Game_of_Life
//     ^ The rules. This first version is just to be able to use Scala. A 2nd version 
//     will seek to utilize the best techniques in Scala.
//     """
  val measure: Double = 8.5
  val limit: Double = (measure * measure).toInt 
  val max_cells: Int = (limit * limit).toInt
  val vigor: Double = (30.0 / 100.0)
  val start_count: Int = (max_cells * vigor).toInt

  var generation: Int = 0
  var pause: Boolean = false
  var potentials: Set[Tuple2[Int, Int]] = Set()
  var next_gen: Set[Tuple2[Int, Int]] = Set()
  var prev_gen: Set[Tuple2[Int, Int]] = Set()
  var cells: Set[Tuple2[Int, Int]] = Set()  
  spawn

  def spawn { 
  // var x = rand(0, limit-1)
  // var y = rand(0, limit-1)
  
  // for z in range(count):
  //     while ((x, y)) in a_set
  //         x = rand(0, limit-1)
  //         y = rand(0, limit-1)
  //     a_set.add((x, y))
  }
}

// def printMeRands {
// var x: Int = ((scala.math.random*(101)).toInt)
// var y: Int = x
// while(y != 100 ) {
// x = ((scala.math.random*(101)).toInt)
// print(x)
// print(" ")
// y = x
// }
// }

package com.DouglasCodes.Conway
import javax.swing._
import java.awt.event._
import java.awt._


object Dish {

  // The Dish (Petry Dish) simulates a hypothetical cell/bacteria environment limited by the visual field constraints. Each cell is a combination of
  // X,Y coordinates originating from the top left of the screen. It then generates a list of Potentials cells, and then combines the two lists
  // with the rules of the Conways Game of Life with the original B3/S23 rules. http://en.wikipedia.org/wiki/Conway's_Game_of_Life

  val measure: Double = 8.5                         // A simple value for determining the rest of the restraints
  val limit: Int = (measure * measure).toInt        // Size of cell field 
  val max_cells: Int = (limit * limit)              // Number of possible cells
  val vigor: Double = (30.0 / 100.0)                // Percentage of cell field that will be populated
  val start_count: Int = (max_cells * vigor).toInt  // Number of cells to begin with
  val offset: Int = 5                               // Size of boxes to be drawn, and multiply limit for window size
  val win_limit: Int = offset * limit               // Size for X, Y sizes for the window to be drawn

  var generation: Int = 0                           // Simple counter for keeping track of current generation
  var pause: Boolean = false                        // Continues game until this is true
  var potentials: Set[Tuple2[Int, Int]] = Set()     // A set of all cells with their surrounding cells
  var temp_potentials: Set[Set[Tuple2[Int, Int]]] = Set() // A set of sets only used in create_potentials method
  // var next_gen: Set[Tuple2[Int, Int]] = Set()
  // var prev_gen: Set[Tuple2[Int, Int]] = Set()
  var cells: Set[Tuple2[Int, Int]] = Set()          // A set of Tuple2's (Int, Int) These correspond to x,y coords on the cell field
  spawn()                                           // Initializes the cells set with N cells where N is the start_count


  def spawn() = {
    // One time call to randomly populate the Dish.cells value with random Tuple2[Int,Int]'s
    while ( start_count != cells.size ) {           // Continue til we get a set of unique cells numbering the start count.
      val x: Int = ((scala.math.random*(limit + 1)).toInt)    // Gets random integers from 0 -> limit for X and Y
      val y: Int = ((scala.math.random*(limit + 1)).toInt)
      cells = cells ++ Set((x, y))                            // Adds this tuple to the set of cells
    }
  }

  def loop_generations(): Unit = {
      while (pause)  { return}
      create_potentials()         // Runs the create_potentials method
      generate_second_gen()       // Filters the potentials list to keep current cells with 2 or 3 neighbors and new cells with
                                  // Exactly three neighbors. NEW cells are ones that exist in potential but NOT current cells. 
      generation += 1
      println("Generation", generation)
  }

  def create_potentials() = {     // Creates a set of sets. Each subSet includes the cell and each neighbor in 8 directions.

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
    case ( 3, _ ) => true         // This potential OR Current cell has exactly three neighbors it exist next generation
    case ( 2, true ) => true      // This cell only has two neighbors and will only CONTINUE to exist if it currently does
    case _ => false               // All other cells cease to exist in the next generation. 
  }

  def count_neighbors(cell: Tuple2[Int, Int]): Int = {
    cells.count ( test => isA_neighbor(test = test, cell = cell)) // Returns an Int value for # of cells adjacent in 8 places
  }

  def isA_neighbor(test: Tuple2[Int,Int], cell: Tuple2[Int, Int]): Boolean = ((cell._1 - test._1).abs, (cell._2 - test._2).abs) match  {
    case (1, 1) => true   // This cell is only 1x and 1y from the current cell so it is a neighbor
    case (0, 1) => true   // This cell is on the same X-axis and 1 Y-axis off, so it is a neighbor
    case (1, 0) => true   // This cell is on the same Y-axis and 1 X-axis off, so it is a neighbor
    case (0, 0) => false  // This is itself, so NOT a neighbor. This can be skipped, but it illustrates better.
    case _ => false       // This cell is not self or a neighbor
  }
}

object ConwayWindow {     // Sets the main display object
  def main(args: Array[String]): Unit = {            // Uses this as the cycling method throughout the main program. 
    while ( true ) {
      ConwayWindow.mdp.repaint()  // Clears the display and repaints with the current Dish.cells addresses
      Dish.loop_generations()}    // Runs the next generation of cells from the current set. 
  } 

  class MyDrawPanel extends JPanel { 
    override def paintComponent(g: Graphics) {      
      g.setColor(Color.black)                             // Redraws the full field with black
      g.fillRect(0,0, this.getWidth(), this.getHeight())  // From top left to bottom right

      g.setColor(Color.blue)                              // Cells arae blue
      Dish.cells.foreach (                                // Each cell Tuple2(int, int), corresponds to a pixel address
        x => g.fillRect(x._1*Dish.offset+10,x._2*Dish.offset+5, Dish.offset, Dish.offset))
    }
  }

  class PauseButtonListener extends ActionListener {
    def actionPerformed(event: ActionEvent): Unit = {
      Dish.pause = !Dish.pause    // Changes the value of Pause in Dish to the opposite. Unpaused becomes paused... vice versa.
                                  // Does not loop generations if the condition is paused.
    }  
  }

    val pbl = new PauseButtonListener       // Sets the listening object for the pause button. It alternates between stoppend/going.
    val frame: JFrame = new JFrame()        
    val start_button: JButton = new JButton("Pause / Continue")
    val mdp = new this.MyDrawPanel

    frame.getContentPane().add(BorderLayout.SOUTH, start_button)
    frame.getContentPane().add(BorderLayout.CENTER, mdp)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(Dish.win_limit+30, Dish.win_limit+70) 
    frame.setVisible(true)
    start_button.addActionListener(pbl)
}

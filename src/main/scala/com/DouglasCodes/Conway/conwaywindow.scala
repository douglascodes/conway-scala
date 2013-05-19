package com.DouglasCodes.Conway
package conwaywindow

import com.DouglasCodes.Conway.dish
import javax.swing._
import java.awt.event._
import java.awt._

object ConwayWindow {     // Sets the main display object
  val Dish = com.DouglasCodes.Conway.dish.Dish;
    
  def main(): Unit = {            // Uses this as the cycling method throughout the main program. 
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

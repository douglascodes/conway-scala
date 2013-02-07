package com.DouglasCodes.Conway
import org.specs2.mutable.Specification
import java.lang.{ IllegalArgumentException => IAE }

class DishSpec extends Specification {
	"Dish" should {
		"have a number of cells equal to start count" in {
			Dish.cells.size must be_> (1)
			Dish.cells.size ==== (Dish.start_count)
		}

		"create a list of super set of cells that are potentials" in {
			Dish.create_potentials
			Dish.cells.subsetOf(Dish.potentials) must be_==(true)
		}

		"create a seperate set of cells in next gen that is also a subset of potentials" in {
			val first_cells: Set[Tuple2[Int, Int]] = Dish.cells
			Dish.loop_generations
			Dish.cells.subsetOf(Dish.potentials) must be_==(true)
			first_cells.subsetOf(Dish.potentials) must be_==(true)
			Dish.cells must be_!=(first_cells)
		}

		"create potentials that include all 9 of the cell's positional neighbors (including self)" in {
			Dish.cells = Dish.cells ++ Set((25,25))
			Dish.create_potentials
			val test_set: Set[Tuple2[Int,Int]] =
					 Set((24,24),(24,25),(24,26),
							(25,24),(25,25),(25,26),
							(26,24),(26,25),(26,26))
			test_set.subsetOf(Dish.potentials) must be_==(true)
		}
	}


//   "Creating a Time" should {
//   "throw an Illegal Argument" in {
//     Time(minutes = 60) must throwAn[IAE]
//   }
//   "throw an Illegal Argument" in {
//     Time(hours = 25) must throwAn[IAE]
//   }

//   "Default to zero for minutes and hours" in {
//     Time().minutes must beEqualTo(0)
//     Time().hours must beEqualTo(0)
//   }
// }


}
package packingFactory;

import java.util.ArrayList;

import customExceptions.OverWeightItemException;
import customExceptions.UnableToAddInLineException;
import customExceptions.UnableToRemoveFromLineException;
import customExceptions.UnableToaddInboxException;
import customExceptions.ZeroWeightBoxException;
import customExceptions.ZeroWeightItemException;

/**
 * 
 * @author lucie
 * Packing process
 * Process the whole workflow of robot packing the items
 */
public class PackingProcess {

	private Robot robot;
	private ArrayList<Integer> weights;

	public PackingProcess(ArrayList<Integer> weights) {
		this.robot = new Robot();
		this.weights = weights;
	}

	/**
	 * Getters
	 */
	public ArrayList<Integer> getWeights() {
		return this.weights;
	}

	/**
	 * Process the packing while robot has item to pack it fills the current box
	 * The robot always maximizes the weight of the current box, it is a greedy algorithm)
	 * @param weights
	 * @return 
	 */
	public String processPacking() {
		try {
			robot.generateItemsToPackLine(this.weights);
			Box currentBox = new Box();
			while (robot.getItemsLineSize() != 0) {
				while (robot.hasAnotherItemToAddInBox(currentBox)) {
					Item item = robot.getNextItemToPutInBox(currentBox);
					robot.addItemInBox(item, currentBox);
				}
				robot.addBoxToClosedBoxes(currentBox);
				currentBox = new Box();
			}
		} catch (OverWeightItemException | UnableToAddInLineException | ZeroWeightItemException | UnableToRemoveFromLineException | UnableToaddInboxException | ZeroWeightBoxException e) {
			e.printStackTrace();
		}
		return robot.printBoxClosedLine();
	}
}

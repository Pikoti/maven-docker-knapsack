package packingFactory;

import java.util.ArrayList;
import java.util.List;

import customExceptions.OverWeightItemException;
import customExceptions.UnableToAddInLineException;
import customExceptions.UnableToRemoveFromLineException;
import customExceptions.UnableToaddInboxException;
import customExceptions.ZeroWeightBoxException;
import customExceptions.ZeroWeightItemException;

/**
 * 
 * @author lucie
 * Packing factory Class
 * 
 * A packing factory as a line with items to pack
 * When box is filled it is closed by the robot and put in another area ready to be sent
 *                   
 */

public class Robot {

	private ArrayList<Item> itemsLine;
	private ArrayList<Box> closedBoxes;

	public Robot () {
		this.itemsLine = new ArrayList<Item>();
		this.closedBoxes = new ArrayList<Box>();
	}

	/**
	 * Get list of items to pack
	 * @return list of items to pack
	 */
	public ArrayList<Item> getItemsToPackLine() {
		return this.itemsLine;
	}

	/**
	 * Get number of remaining items to pack
	 * @return number of remaining items to pack
	 */
	public Integer getItemsLineSize() {
		return this.itemsLine.size();
	}

	/**
	 * Add an item to line of item to pack
	 * @param item
	 * @return true if item has been added
	 */
	public boolean addItemToItemLine(Item item) {
		return this.itemsLine.add(item);
	}

	/**
	 * Get number of properly closed boxes ready to dispatch
	 * @return number of boxes
	 */
	public Integer getNumberOfClosedBoxes() {
		return this.closedBoxes.size();
	}
	
	/**
	 * Remove item from packing items line
	 * @param item
	 * @return true if item has been removed
	 */
	public boolean removeItemFromItemLine(Item item) {
		return this.itemsLine.remove(item);
	}

	/**
	 * Build the line of items to pack with the specified weights
	 * @param weights list of the items weights
	 * @return the generated list of items
	 * @throws OverWeightItemException 
	 * @throws UnableToAddInLineException 
	 * @throws ZeroWeightItemException 
	 */
	public ArrayList<Item> generateItemsToPackLine(List<Integer> weights) throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException {
		for (Integer weight : weights) {
			if (weight > 9) throw new OverWeightItemException("Item has weight > 9");
			if (weight <= 0) throw new ZeroWeightItemException("Item has invalid weight 0 or under");
			Item item = new Item(weight);
			if(!addItemToItemLine(item)) throw new UnableToAddInLineException("unable to add item in to pack line");
		}
		return this.itemsLine;
	}

	/**
	 * Search in items-to-pack line if there is another item fitting in this box
	 * @param box box to fill
	 * @return true if there is another valid item for this box
	 */
	public boolean hasAnotherItemToAddInBox(Box box) {
		for(Item item: this.itemsLine) {
			if (item.getWeight() + box.getWeight() <= Box.MAXWEIGHT) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Search in items to pack line the next item to put in box
	 * The next item is the heavier one we can put in the box (to use after hasAnotherItemToAddInBox)
	 * @param box box to fill
	 * @return item the next item to put in box
	 * @throws ZeroWeightItemException 
	 */
	public Item getNextItemToPutInBox(Box box) throws ZeroWeightItemException {
		Item nextItemInBox = new Item(0); 
		for(Item item: this.itemsLine) {
			if (item.getWeight() + box.getWeight() <= Box.MAXWEIGHT && item.getWeight() > nextItemInBox.getWeight()) {
				nextItemInBox = item;
			}
		}
		if(nextItemInBox.getWeight() == 0) throw new ZeroWeightItemException("item weight should not be zero");
		return nextItemInBox;
	}
	
	/**
	 * Add an item in a box
	 * @throws OverWeightItemException 
	 * @throws UnableToRemoveFromLineException 
	 * @throws UnableToaddInboxException 
	 */
	public void addItemInBox(Item item, Box box) throws OverWeightItemException, UnableToRemoveFromLineException, UnableToaddInboxException {
		if (!this.itemsLine.remove(item)) throw new UnableToRemoveFromLineException("Unable to remove box from to pack line");
		if (!box.addItemInBox(item)) throw new UnableToaddInboxException("unable to add in box");
	}
	
	/**
	 * Close a box and add it to other closed boxes
	 * @param box
	 * @throws ZeroWeightBoxException
	 * @throws UnableToAddInLineException
	 */
	public void addBoxToClosedBoxes(Box box) throws ZeroWeightBoxException, UnableToAddInLineException {
		if (box.getWeight() <= 0) throw new ZeroWeightBoxException("Box weight is 0, cannot be closed");
		if (!this.closedBoxes.add(box)) throw new UnableToAddInLineException("Unable to add box in to closed boxes line ");
	}	
	
	/**
	 * Build string of the weight of each items inside each closed boxes
	 * @return stringboxes the string of boxes and their items weights
	 */
	public String printBoxClosedLine() {
		String stringBoxes = "";
		for (Box box : this.closedBoxes) {
			for (Item item : box.getItems()) {
				stringBoxes = stringBoxes + item.getWeight().toString();
			}
			stringBoxes = stringBoxes + "/";
		}
		return stringBoxes;
	}

}

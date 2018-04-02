package packingFactory;

import java.util.ArrayList;

import customExceptions.OverWeightItemException;
import packingFactory.Item;


/**
 * Box class
 * A box is filled with items
 * 
 */
public class Box {

	public static final Integer MAXWEIGHT = 10;

	private ArrayList<Item> items;
	private Integer weight;

	public Box() {
		items = new ArrayList<Item>();
		weight = 0;
	}

	/**
	 * Get list of items in box
	 * @return list of items
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}

	/**
	 * Get full weight of box
	 * @return weight of box
	 */
	public Integer getWeight() {
		return this.weight;
	}

	/**
	 * Add an item in a box
	 * @param item to pack
	 * @return true if item has been added in box
	 */
	public boolean addItemInBox(Item item) throws OverWeightItemException {
		if (item.getWeight() + this.getWeight() > Box.MAXWEIGHT) throw new OverWeightItemException("item is overweighted for the box");		
		this.weight += item.getWeight();
		return this.items.add(item);
	}

}

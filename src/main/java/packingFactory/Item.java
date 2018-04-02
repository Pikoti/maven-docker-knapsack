package packingFactory;

/**
 * 
 * @author lucie
 * Item class
 * An item has a weight and must be packed in a box to
 * be sent
 */
public class Item {

	private Integer weight;
	
	public Item(Integer weight) {
		this.weight = weight;
	}

	/**
	 * Get weight of an item
	 * @return weight the weight of the item
	 */
	public Integer getWeight() {
		return this.weight;
	}
	
	/**
	 * Equals for item
	 */
	public boolean equals(Object o, Item item) {
		return (o instanceof Item && ((Item) o).getWeight() == item.getWeight()); 
	}
}

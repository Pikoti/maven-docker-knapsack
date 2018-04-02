package ouisncf.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import customExceptions.OverWeightItemException;
import packingFactory.Item;

public class ItemTest {

	Item item = new Item(5);
	Item item3 = new Item(8);
	Item item2 = item;

	@Test
	public final void getWeightTest() throws OverWeightItemException {
		assertTrue(item.getWeight() == 5);
	}
	
	@Test
	public final void equalsTest() throws OverWeightItemException {
		assertTrue(item.equals(item2));
		assertFalse(item.equals(item3));
	}
}

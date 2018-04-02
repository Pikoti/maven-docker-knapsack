package ouisncf.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import customExceptions.OverWeightItemException;
import packingFactory.Box;
import packingFactory.Item;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBoxTest {

	Item item = new Item(5);
	Item itemZero = new Item(0);
	Item itemOver = new Item(15);
	Box box = new Box();

	@Test
	public final void testAddItemInBox() throws OverWeightItemException {
		box.addItemInBox(item);
		assertTrue(box.getWeight() == 5);
		assertTrue(box.getItems().size() == 1);
		box.addItemInBox(item);
		assertTrue(box.getWeight() == 10);
		assertTrue(box.getItems().size() == 2);
	}

	@Test(expected = OverWeightItemException.class)
	public final void testAddOverItem() throws OverWeightItemException {
		box.addItemInBox(itemOver);
	}

	@Test
	public final void testGetItems() throws OverWeightItemException {
		box.addItemInBox(item);
		assertTrue(box.getWeight() == 5);
		assertTrue(box.getItems().size() == 1);
	}	

	@Test
	public final void testMaxWeight() {
		assertTrue(Box.MAXWEIGHT == 10);		
	}	
}

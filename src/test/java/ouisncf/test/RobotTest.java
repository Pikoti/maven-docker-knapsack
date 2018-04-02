package ouisncf.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.Test;

import customExceptions.OverWeightItemException;
import customExceptions.UnableToAddInLineException;
import customExceptions.UnableToRemoveFromLineException;
import customExceptions.UnableToaddInboxException;
import customExceptions.ZeroWeightBoxException;
import customExceptions.ZeroWeightItemException;
import packingFactory.Box;
import packingFactory.Item;
import packingFactory.Robot;

public class RobotTest {


	Robot robot = new Robot();
	Box box = new Box();
	Item item = new Item(5);


	@Test
	public final void TestAddItemTopackLine(){
		robot.addItemToItemLine(item);
		assertTrue(robot.getItemsLineSize() == 1);
		robot.addItemToItemLine(item);
		assertTrue(robot.getItemsLineSize() == 2);
	}

	@Test
	public final void TestRemoveItemTopackLine(){
		robot.addItemToItemLine(item);
		assertTrue(robot.getItemsLineSize() == 1);
		robot.removeItemFromItemLine(item);
		assertTrue(robot.getItemsLineSize() == 0);
	}


	@Test
	public final void TestAddBoxToClosedLine() throws ZeroWeightBoxException, UnableToAddInLineException, OverWeightItemException{
		box.addItemInBox(item);
		assertTrue(box.getWeight() > 0);
		robot.addBoxToClosedBoxes(box);
		assertTrue(robot.getNumberOfClosedBoxes() == 1);
	}

	@Test (expected = ZeroWeightBoxException.class)
	public final void TestAddEmptyBoxToClosedLine() throws ZeroWeightBoxException, UnableToAddInLineException{
		robot.addBoxToClosedBoxes(box);
		assertTrue(robot.getNumberOfClosedBoxes() == 0);
	}

	@Test
	public final void TestGenerateItemLine() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException{
		Integer i = 0;
		ArrayList<Integer> weights = Lists.newArrayList(1,2,3);
		robot.generateItemsToPackLine(weights);
		assertTrue(robot.getItemsLineSize() == 3);
		for (Item item : robot.getItemsToPackLine()) {
			assertTrue(item.getWeight() == weights.get(i));
			i++;
		}
	}

	@Test(expected = OverWeightItemException.class)
	public final void TestGenerateItemLineOverWeight() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException{
		ArrayList<Integer> weights = Lists.newArrayList(10,25,3);
		robot.generateItemsToPackLine(weights);
	}

	@Test(expected = ZeroWeightItemException.class)
	public final void TestGenerateItemLineZeroWeight() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException{
		ArrayList<Integer> weights = Lists.newArrayList(9,0,3);
		robot.generateItemsToPackLine(weights);
	}

	@Test
	public final void TestAddItemInbox() throws OverWeightItemException, UnableToAddInLineException, UnableToRemoveFromLineException, UnableToaddInboxException, ZeroWeightItemException{
		ArrayList<Integer> weights = Lists.newArrayList(1,2,3);
		ArrayList<Item> items = robot.generateItemsToPackLine(weights);
		assertTrue(robot.getItemsLineSize() == 3);
		robot.addItemInBox(items.get(0), box);
		assertTrue(robot.getItemsLineSize() == 2);
		assertTrue(box.getWeight() == 1 );
	}

	@Test
	public final void TestAddBoxToclosedOnes() throws OverWeightItemException, UnableToAddInLineException, UnableToRemoveFromLineException, UnableToaddInboxException, ZeroWeightItemException, ZeroWeightBoxException{
		ArrayList<Integer> weights = Lists.newArrayList(1,2,3);
		ArrayList<Item> items = robot.generateItemsToPackLine(weights);
		assertTrue(robot.getItemsLineSize() == 3);
		robot.addItemInBox(items.get(0), box);
		assertTrue(robot.getItemsLineSize() == 2);
		assertTrue(box.getWeight() == 1 );
		robot.addBoxToClosedBoxes(box);
		assertTrue(robot.getNumberOfClosedBoxes() == 1);
	}

	@Test
	public final void hasAndAddNextItemInBoxTest() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException {
		ArrayList<Integer> weights = Lists.newArrayList(1,2,3);
		ArrayList<Item> items = robot.generateItemsToPackLine(weights);
 		assertTrue(robot.hasAnotherItemToAddInBox(box));
 		assertTrue(robot.getNextItemToPutInBox(box).equals(items.get(2)));
	}
	
	@Test
	public final void hasnotNextItemInBoxTest() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException, UnableToRemoveFromLineException, UnableToaddInboxException {
		Box almostFullBox = new Box();
		Item item = new Item(9);
		almostFullBox.addItemInBox(item);
		ArrayList<Integer> weights = Lists.newArrayList(3,2,3);
 		robot.generateItemsToPackLine(weights);
 		assertFalse(robot.hasAnotherItemToAddInBox(almostFullBox));
	}
	
	@Test
	public final void printInBoxTest() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException, UnableToRemoveFromLineException, UnableToaddInboxException, ZeroWeightBoxException {
		Box box1 = new Box();
		Item item1 = new Item(1);
		Item item2 = new Item(2);
		box1.addItemInBox(item1);
		box1.addItemInBox(item2);
		robot.addBoxToClosedBoxes(box1);
		
		Box box2 = new Box();
		Item item3 = new Item(3);
		Item item4 = new Item(4);
		box2.addItemInBox(item3);
		box2.addItemInBox(item4);

		robot.addBoxToClosedBoxes(box2);
		System.out.println(robot.printBoxClosedLine());
		assertTrue(robot.printBoxClosedLine().equals("12/34/"));
	}
}

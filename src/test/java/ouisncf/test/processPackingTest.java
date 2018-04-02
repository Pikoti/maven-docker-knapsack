package ouisncf.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.Test;

import customExceptions.OverWeightItemException;
import customExceptions.UnableToAddInLineException;
import customExceptions.ZeroWeightItemException;
import packingFactory.PackingProcess;
import packingFactory.Robot;

public class processPackingTest {

	Robot robot =new Robot();
	
	@Test
	public final void packingProcessTest() throws OverWeightItemException, UnableToAddInLineException, ZeroWeightItemException {
		ArrayList<Integer> weights = Lists.newArrayList(1,2,3);
		PackingProcess process = new PackingProcess(weights);
		String result = process.processPacking();
		assertTrue(result.equals("321/"));
		
		ArrayList<Integer> weights2 = Lists.newArrayList(8,1,2,3);
		PackingProcess process2 = new PackingProcess(weights2);
		String result2 = process2.processPacking();
		assertTrue(result2.equals("82/31/"));		
	}
}

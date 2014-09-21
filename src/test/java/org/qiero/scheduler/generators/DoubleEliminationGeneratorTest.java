package org.qiero.scheduler.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DoubleEliminationGeneratorTest {

	private DoubleEliminationGenerator doubleEliminationGenerator;

	@Test
	public void testGetRoundZeroForEightTeams() {
		this.doubleEliminationGenerator = new DoubleEliminationGenerator(8);

		Map<Integer, Integer> roundExpected = new HashMap<Integer, Integer>();
		roundExpected.put(0, 7);
		roundExpected.put(4, 3);
		roundExpected.put(2, 5);
		roundExpected.put(6, 1);

		Map<Integer, Integer> round = doubleEliminationGenerator.getRound(0);

		assertEquals(roundExpected.size(), round.size());

		for (Map.Entry<Integer, Integer> match : roundExpected.entrySet()) {
			Integer home = match.getKey();
			Integer away = match.getValue();

			assertTrue(round.containsKey(home));
			assertTrue(round.containsValue(away));

			assertFalse(round.containsKey(away));
			assertFalse(round.containsValue(home));
		}
	}

	@Test
	public void testGetRoundZeroFiveTeams() {
		this.doubleEliminationGenerator = new DoubleEliminationGenerator(5);

		Map<Integer, Integer> roundExpected = new HashMap<Integer, Integer>();
		roundExpected.put(0, -1);
		roundExpected.put(4, 3);
		roundExpected.put(2, -3);
		roundExpected.put(-2, 1);
		
		Map<Integer, Integer> round = doubleEliminationGenerator.getRound(0);

		assertEquals(roundExpected.size(), round.size());

		for (Map.Entry<Integer, Integer> match : roundExpected.entrySet()) {
			Integer home = match.getKey();
			Integer away = match.getValue();

			assertTrue(round.containsKey(home));
			assertTrue(round.containsValue(away));
		}
	}

}

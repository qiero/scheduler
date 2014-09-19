package org.qiero.scheduler.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DoubleRoundRobinGeneratorTest {

	private DoubleRoundRobinGenerator doubleRoundRobinGenerator;

	@Test
	public void testGetRoundZeroForFourteenTeams() {
		this.doubleRoundRobinGenerator = new DoubleRoundRobinGenerator(14);

		Map<Integer, Integer> roundExpected = new HashMap<Integer, Integer>();
		roundExpected.put(0, 13);
		roundExpected.put(1, 12);
		roundExpected.put(2, 11);
		roundExpected.put(3, 10);
		roundExpected.put(4, 9);
		roundExpected.put(5, 8);
		roundExpected.put(6, 7);

		Map<Integer, Integer> round = doubleRoundRobinGenerator.getRound(0);

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
	public void testGetRoundThirteenForFourteenTeams() {
		this.doubleRoundRobinGenerator = new DoubleRoundRobinGenerator(14);

		Map<Integer, Integer> roundExpected = new HashMap<Integer, Integer>();
		roundExpected.put(13, 0);
		roundExpected.put(12, 1);
		roundExpected.put(11, 2);
		roundExpected.put(10, 3);
		roundExpected.put(9, 4);
		roundExpected.put(8, 5);
		roundExpected.put(7, 6);

		Map<Integer, Integer> round = doubleRoundRobinGenerator.getRound(13);
		// 13 is the first revenge round

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
		this.doubleRoundRobinGenerator = new DoubleRoundRobinGenerator(5);

		Map<Integer, Integer> roundExpected = new HashMap<Integer, Integer>();
		roundExpected.put(0, 0);
		roundExpected.put(1, 4);
		roundExpected.put(2, 3);

		Map<Integer, Integer> round = doubleRoundRobinGenerator.getRound(0);

		assertEquals(roundExpected.size(), round.size());

		for (Map.Entry<Integer, Integer> match : roundExpected.entrySet()) {
			Integer home = match.getKey();
			Integer away = match.getValue();

			assertTrue(round.containsKey(home));
			assertTrue(round.containsValue(away));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetRoundOutOfBounds() {
		this.doubleRoundRobinGenerator = new DoubleRoundRobinGenerator(5);

		// Maximum valid parameter is 9. This line should throw
		// IllegalArgumentException.
		doubleRoundRobinGenerator.getRound(10);

	}

	@Test
	public void testGetRoundsForFiveTeams() {
		this.doubleRoundRobinGenerator = new DoubleRoundRobinGenerator(5);

		List<Map<Integer, Integer>> roundsExpected = new ArrayList<Map<Integer, Integer>>();
		Map<Integer, Integer> round0Expected = new HashMap<Integer, Integer>();
		round0Expected.put(0, 0);
		round0Expected.put(1, 4);
		round0Expected.put(2, 3);
		roundsExpected.add(round0Expected);

		Map<Integer, Integer> round1Expected = new HashMap<Integer, Integer>();
		round1Expected.put(0, 1);
		round1Expected.put(2, 4);
		round1Expected.put(3, 3);
		roundsExpected.add(round1Expected);

		Map<Integer, Integer> round2Expected = new HashMap<Integer, Integer>();
		round2Expected.put(0, 2);
		round2Expected.put(1, 1);
		round2Expected.put(3, 4);
		roundsExpected.add(round2Expected);

		Map<Integer, Integer> round3Expected = new HashMap<Integer, Integer>();
		round3Expected.put(0, 3);
		round3Expected.put(1, 2);
		round3Expected.put(4, 4);
		roundsExpected.add(round3Expected);

		Map<Integer, Integer> round4Expected = new HashMap<Integer, Integer>();
		round4Expected.put(0, 4);
		round4Expected.put(1, 3);
		round4Expected.put(2, 2);
		roundsExpected.add(round4Expected);

		Map<Integer, Integer> round5Expected = new HashMap<Integer, Integer>();
		round5Expected.put(0, 0);
		round5Expected.put(4, 1);
		round5Expected.put(3, 2);
		roundsExpected.add(round5Expected);

		Map<Integer, Integer> round6Expected = new HashMap<Integer, Integer>();
		round6Expected.put(1, 0);
		round6Expected.put(4, 2);
		round6Expected.put(3, 3);
		roundsExpected.add(round6Expected);

		Map<Integer, Integer> round7Expected = new HashMap<Integer, Integer>();
		round7Expected.put(2, 0);
		round7Expected.put(1, 1);
		round7Expected.put(4, 3);
		roundsExpected.add(round7Expected);

		Map<Integer, Integer> round8Expected = new HashMap<Integer, Integer>();
		round8Expected.put(3, 0);
		round8Expected.put(2, 1);
		round8Expected.put(4, 4);
		roundsExpected.add(round8Expected);

		Map<Integer, Integer> round9Expected = new HashMap<Integer, Integer>();
		round9Expected.put(4, 0);
		round9Expected.put(3, 1);
		round9Expected.put(2, 2);
		roundsExpected.add(round9Expected);

		List<Map<Integer, Integer>> rounds = doubleRoundRobinGenerator
		        .getRounds();

		assertEquals(roundsExpected.size(), rounds.size());

		for (int i = 0; i < 10; i++) {
			Map<Integer, Integer> roundExpected = roundsExpected.get(i);
			Map<Integer, Integer> round = rounds.get(i);

			for (Map.Entry<Integer, Integer> match : roundExpected.entrySet()) {
				Integer home = match.getKey();
				Integer away = match.getValue();

				assertTrue(round.containsKey(home));
				assertTrue(round.containsValue(away));
			}
		}
	}

}

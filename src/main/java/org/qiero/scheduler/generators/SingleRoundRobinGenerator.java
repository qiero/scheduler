package org.qiero.scheduler.generators;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Generator for implementing Single Round Robin
 * (http://en.wikipedia.org/wiki/Round-robin_tournament) scheduling algorithm.
 * 
 * This implementation does not construct the whole matrix up-front. The
 * required part of the table (diagonal) is evaluated lazily during the
 * invocation of the {@link getRound} method.
 * 
 * @author Tomasz Guzialek
 * 
 */
public class SingleRoundRobinGenerator implements TabularGenerator {

	private static final Logger logger = LoggerFactory
	        .getLogger(SingleRoundRobinGenerator.class);

	/**
	 * The number of teams for which the schedule is generated for.
	 */
	protected int n;

	/**
	 * Equal to n if n is even. If n is odd, it is equal to n + 1 (adding a
	 * dummy team).
	 */
	protected int algorithmN;

	/**
	 * Constructs a {@link SingleRoundRobinGenerator} for given number
	 * of teams.
	 * 
	 * @param n
	 *            The number of teams.
	 */
	public SingleRoundRobinGenerator(int n) {
		this.n = n;
		if (n % 2 == 0)
			this.algorithmN = n;
		else
			this.algorithmN = n + 1;
	}

	/**
	 * Returns the matches scheduled for given round.
	 * 
	 * @param roundNumber
	 *            The number of the round to be retrieved. Zero-based indexing.
	 * @return {@link Map} from home team to away team for this round.
	 */
	public Map<Integer, Integer> getRound(int roundNumber) {
		if ((roundNumber < 0) || (roundNumber >= algorithmN - 1)) {
			throw new IllegalArgumentException(
			        "The round number in Single Round Robin for " + n
			                + " teams needs to be between 0 and "
			                + (algorithmN - 1) + " (inclusive).");
		}

		Map<Integer, Integer> round = new HashMap<Integer, Integer>();

		logger.debug("Round no. " + roundNumber + " out of " + (algorithmN - 2)
		        + ":");
		for (int i = 0; i < algorithmN - 1; i++) {
			int j = roundNumber - i;
			if (j < 0) {
				j = algorithmN + j - 1;
			}
			if ((n % 2 == 0) && (i == j)) {
				logger.debug(i + " vs. " + (n - 1));
				round.put(i, n - 1);
			} else if (i <= j) {
				logger.debug(i + " vs. " + j);
				round.put(i, j);
			}
		}
		return round;
	}

	/**
	 * Returns the total schedule as the {@link List} of rounds ({@link Map}
	 * from home to away team.
	 * 
	 * @return {@link List} of rounds.
	 */
	public List<Map<Integer, Integer>> getRounds() {
		List<Map<Integer, Integer>> rounds = new ArrayList<Map<Integer, Integer>>();

		for (int roundNumber = 0; roundNumber < algorithmN - 1; roundNumber++) {
			rounds.add(getRound(roundNumber));
		}

		return rounds;
	}

}

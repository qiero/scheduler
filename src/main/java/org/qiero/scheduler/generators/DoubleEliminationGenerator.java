package org.qiero.scheduler.generators;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qiero.scheduler.utils.BitSetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link TabularGenerator} for implementing Double Elimination scheduling
 * algorithm.
 * 
 * @author Tomasz Guzialek
 * 
 */
public class DoubleEliminationGenerator implements TabularGenerator {

	private static final Logger logger = LoggerFactory
	        .getLogger(DoubleEliminationGenerator.class);

	/**
	 * The number of teams for which the schedule is generated for.
	 */
	private int n;

	/**
	 * Equal to n if n is a power of 2. If not, it is rounded (ceiling) to a
	 * closest power of 2.
	 */
	private int algorithmN;

	private static int rulerFunction[] = new int[] { 1, 2, 1, 3, 1, 2, 1, 4, 1, 2, 1,
	        3, 1, 2, 1, 5, 1, 2, 1, 3, 1, 2, 1, 4, 1, 2, 1, 3, 1, 2, 1, 6, 1,
	        2, 1, 3, 1, 2, 1, 4, 1, 2, 1, 3, 1, 2, 1, 5, 1, 2, 1, 3, 1, 2, 1,
	        4, 1, 2, 1, 3, 1, 2, 1 };

	/**
	 * Constructs a {@link DoubleEliminationGenerator} for given number of
	 * teams.
	 * 
	 * @param n
	 *            The number of teams.
	 */
	public DoubleEliminationGenerator(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
			        "The number of teams cannot be negative.");
		
		//FIXME Throw an IllegalArgumentException when it exceeds the predefined Ruler array.
		//TODO Recursive generation of Ruler function instead of predefined values.

		this.n = n;
		int powerOfTwo = (int) Math.ceil(Math.log(n) / Math.log(2));
		this.algorithmN = (int) Math.pow(2, powerOfTwo);
		logger.debug("algorithmN for " + n + " teams is: " + algorithmN);
	}

	/**
	 * Starts the processing needed to generate the schedule.
	 */
	public void run() {
	}
	
	private Map<Integer, Integer> getFirstRoundPairs() {
		Map<Integer, Integer> firstRoundPairs = new HashMap<>();
		List<Integer> firstRoundTeams = new ArrayList<>();

		int numberOfBits = (int) Math.ceil(Math.log(n) / Math.log(2));

		int rulerIndex = 0;
		BitSet current = new BitSet(numberOfBits); // initially 0
		BitSet one = new BitSet(numberOfBits);
		one.set(0); // Setting the integer value to 1
		while (!current.equals(one)) {
			addToRound(firstRoundTeams, current);
			if (current.get(0) == false) { // last bit equal to 0
				current.flip(0, numberOfBits);
			} else { // last bit equal to 1
				// fix some bits (according to ruler) and flip the rest
				current.flip(0, numberOfBits - rulerFunction[rulerIndex]);
				rulerIndex++;
			}
		}
		addToRound(firstRoundTeams, current);
		
		logger.debug("The first round pairs: ");
		for (int i = 0; i < firstRoundTeams.size() - 1; i += 2) {
	        Integer home = firstRoundTeams.get(i);
	        Integer away = firstRoundTeams.get(i + 1);
	        logger.debug(home + " vs. " + away);
	        firstRoundPairs.put(home, away);
        }

		return firstRoundPairs;
	}

	private void addToRound(List<Integer> firstRoundTeams, BitSet teamNumber) {
	    int teamNumberInteger = BitSetUtils.toInteger(teamNumber);
	    if (teamNumberInteger < n) {
	    	firstRoundTeams.add(teamNumberInteger);
	    } else { // bye
	    	firstRoundTeams.add(teamNumberInteger - algorithmN);
	    }
    }

	@Override
	public Map<Integer, Integer> getRound(int roundNumber) {
		if (roundNumber == 0) {
			return getFirstRoundPairs();
		} else {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public List<Map<Integer, Integer>> getRounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

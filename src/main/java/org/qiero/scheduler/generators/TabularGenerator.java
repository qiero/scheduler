package org.qiero.scheduler.generators;

import java.util.List;
import java.util.Map;

/**
 * An generator interface for tournaments represented in a form of a table. The
 * table includes all the matches being played.
 * 
 * @author Tomasz Guzialek
 * 
 */
public interface TabularGenerator {

	/**
	 * Returns all the matches played in a specified round.
	 * 
	 * @param roundNumber
	 *            The number of round. Zero-based indexing.
	 * @return The {@link Map} with all the matches in the specified round. The
	 *         key of the Map is the home team and the value is the away team.
	 */
	Map<Integer, Integer> getRound(int roundNumber);

	/**
	 * Returns all the matches played in a tournament.
	 * 
	 * @return The {@link List} of {@link Map}s. A Map includes all the matches
	 *         played in a single round. The key of the Map is the home team and
	 *         the value is the away team.
	 */
	List<Map<Integer, Integer>> getRounds();
}

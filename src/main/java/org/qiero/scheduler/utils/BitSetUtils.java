package org.qiero.scheduler.utils;

import java.util.BitSet;

/**
 * Utility class for {@link BitSet}s.
 * 
 * @author Tomasz Guzialek
 *
 */
public class BitSetUtils {

	/**
	 * Returns the {@link Integer} value of the {@link BitSet}. 
	 * 
	 * @param value The {@link BitSet} to be converted to.
	 * @return The {@link Integer} value.
	 */
	public static int toInteger(BitSet value) {
		int intValue = 0;
		for (int i = value.nextSetBit(0); i >= 0; i = value.nextSetBit(i+1)) {
			intValue += (int) (Math.pow(2, i));
		}
		return intValue;
	}
	
}

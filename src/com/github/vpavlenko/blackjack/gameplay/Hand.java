package com.github.vpavlenko.blackjack.gameplay;

import java.util.ArrayList;
import java.util.Collections;

public class Hand extends ArrayList<Card> {
	public int getScore() {
		ArrayList<Integer> possibleSums = new ArrayList<>();
		possibleSums.add(0);
		for (Card card : this) {
			if (card.getMinValue() == card.getMaxValue()) {
				for (int i = 0; i < possibleSums.size(); ++i) {
					possibleSums.set(i,
							possibleSums.get(i) + card.getMinValue());
				}
			} else {
				int oldSize = possibleSums.size();
				for (int i = 0; i < oldSize; ++i) {
					possibleSums.add(possibleSums.get(i) + card.getMaxValue());
				}
				for (int i = 0; i < oldSize; ++i) {
					possibleSums.set(i,
							possibleSums.get(i) + card.getMinValue());
				}
			}
		}
		int sum = 0;
		for (int possibleSum : possibleSums) {
			if (possibleSum <= 21) {
				sum = Integer.max(sum, possibleSum);
			}
		}
		if (sum == 0 && !possibleSums.isEmpty()) {
			sum = Collections.min(possibleSums);
		}
		return sum;
	}
}

package com.github.vpavlenko.blackjack.gameplay;

public enum Rank {
	Ace(1, "A"), _2(2, "2"), _3(3, "3"), _4(4, "4"), _5(5, "5"), _6(6, "6"),
		_7(7, "7"), _8(8, "8"), _9(9, "9"), _10(10, "10"), Jack(10, "J"),
		Queen(10, "Q"), King(10, "K");
	
	int minValue;
	String shortcut;
	
	Rank(int minValue, String shortcut) {
		this.minValue = minValue;
		this.shortcut = shortcut;
	}
	
	int getMinValue() {
		return minValue;
	}
	
	int getMaxValue() {
		if (this == Ace) {
			return 11;
		} else {
			return minValue;
		}
	}
	
	@Override
	public String toString() {
		return shortcut;
	}
}

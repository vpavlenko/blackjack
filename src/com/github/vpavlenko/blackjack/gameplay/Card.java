package com.github.vpavlenko.blackjack.gameplay;

public class Card {

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getMaxValue() {
		return rank.getMaxValue();
	}

	public int getMinValue() {
		return rank.getMinValue();
	}
	
	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}

}

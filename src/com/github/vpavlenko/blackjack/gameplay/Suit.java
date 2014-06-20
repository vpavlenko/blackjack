package com.github.vpavlenko.blackjack.gameplay;

public enum Suit {
	Diamonds("♢"), Hearts("♡"), Clubs("♣"), Spades("♠");
	
	private String name;

	Suit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

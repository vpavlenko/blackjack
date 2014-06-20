package com.github.vpavlenko.blackjack.gameplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {
	private Stack<Card> deck;
	
	static Random random = new Random(0);
	
	Deck() {
		this.deck = shuffleDeck(generateDeck());
	}

	private Stack<Card> shuffleDeck(List<Card> deck) {
		Stack<Card> newDeck = new Stack<Card>();
		Collections.shuffle(deck, random);
		newDeck.addAll(deck);
		return newDeck;
	}

	private List<Card> generateDeck() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
		return deck;
	}

	public Card getNewCard() {
		return deck.pop();
	}
}

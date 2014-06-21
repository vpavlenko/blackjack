package com.github.vpavlenko.blackjack.gameplay;

public class Deal {

	private Deck deck;
	private Hand dealerHand, playerHand;
	private Game game;

	Deal(Game game) {
		this.game = game;
	}

	public void start() {
		deck = new Deck();
		dealerHand = new Hand();
		playerHand = new Hand();

		dealerHand.add(deck.getNewCard());

		playerHand.add(deck.getNewCard());
		playerHand.add(deck.getNewCard());
	}

	public void hit() {
		if (game.getState() != GameResult.NONE) {
			return;
		}
		playerHand.add(deck.getNewCard());
		if (playerHand.getScore() > 21) {
			busted();
		}
	}

	public void stand() {
		if (game.getState() != GameResult.NONE) {
			return;
		}
		take_for_dealer();
	}

	private void take_for_dealer() {
		while (dealerHand.getScore() < 17) {
			dealerHand.add(deck.getNewCard());
		}
		if (dealerHand.getScore() < playerHand.getScore()
				|| dealerHand.getScore() > 21) {
			win();
		} else if (dealerHand.getScore() == playerHand.getScore()) {
			push();
		} else {
			busted();
		}
	}

	private void busted() {
		game.setState(GameResult.BUSTED);
	}

	private void push() {
		game.setState(GameResult.PUSH);
	}

	public void win() {
		game.setState(GameResult.WIN);
	}

	public Hand getDealerHand() {
		return dealerHand;
	}

	public Hand getPlayerHand() {
		return playerHand;
	}

}

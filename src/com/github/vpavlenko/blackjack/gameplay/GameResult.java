package com.github.vpavlenko.blackjack.gameplay;

public enum GameResult {
	WIN("win"), BUSTED("busted"), PUSH("push");
	
	private String name;

	private GameResult(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

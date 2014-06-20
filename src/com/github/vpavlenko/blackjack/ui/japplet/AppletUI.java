package com.github.vpavlenko.blackjack.ui.japplet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.github.vpavlenko.blackjack.gameplay.Card;
import com.github.vpavlenko.blackjack.gameplay.Game;
import com.github.vpavlenko.blackjack.gameplay.GameResult;

public class AppletUI extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727406432867799177L;
	JButton stand;
	JButton hit;

	JLabel dealer, player, result;

	Game game;
	private JButton deal;
	private JLabel scoreLabel;
	
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					game = new Game();
					guiInit(); // initialize the GUI
				}
			});
		} catch (Exception exc) {
			System.out.println("Can't create because of " + exc);
		}
	}

	// Called second, after init(). Also called
	// whenever the applet is restarted.
	public void start() {
		// Not used by this applet.
	}

	// Called when the applet is stopped.
	public void stop() {
		// Not used by this applet.
	}

	// Called when applet is terminated. This is
	// the last method executed.
	public void destroy() {
		// Not used by this applet.
	}

	// Setup and initialize the GUI.
	private void guiInit() {
		dealer = new JLabel("dealer state");
		player = new JLabel("player state");
		scoreLabel = new JLabel("game state");

		deal = new JButton("Deal");
		stand = new JButton("Stand");
		hit = new JButton("Hit");

		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
		labelsPanel.add(dealer);
		labelsPanel.add(player);
		labelsPanel.add(scoreLabel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel.add(deal);
		buttonsPanel.add(stand);
		buttonsPanel.add(hit);
		
		getContentPane().add(labelsPanel, BorderLayout.CENTER);
		getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);
		
		game = new Game();
		game.startNewDeal();
		drawGameState();
		
		stand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				game.stand();
				drawGameState();
			}
		});

		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				game.hit();
				drawGameState();
			}
		});
		
		deal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				game.startNewDeal();
				drawGameState();
			}
		});
	
	}

	private void drawGameState() {
		StringBuffer dealerState = new StringBuffer("dealer: ");
		boolean firstItem = true;
		for (Card card : game.deal.getDealerHand()) {
			dealerState.append((firstItem  ? "" : ", ") + card.toString());
			firstItem = false;
		}
		dealerState.append(": " + game.deal.getDealerHand().getScore());
		dealer.setText(dealerState.toString());
		
		StringBuffer playerState = new StringBuffer("player: ");
		firstItem = true;
		for (Card card : game.deal.getPlayerHand()) {
			playerState.append((firstItem ? "" : ", ") + card.toString());
			firstItem = false;
		}
		playerState.append(": " + game.deal.getPlayerHand().getScore());
		player.setText(playerState.toString(	));
		
		StringBuffer score = new StringBuffer("<html>score:<br>");
		for (Entry<GameResult, Integer> entry : game.agreggatedResults.entrySet()) {
			score.append("&nbsp;&nbsp;&nbsp;" + entry.getKey() + ": " + entry.getValue() + "<br>");
		}
		score.append("</html>");
		scoreLabel.setText(score.toString());
	}
}

package com.github.vpavlenko.blackjack.ui.japplet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.github.vpavlenko.blackjack.gameplay.Card;
import com.github.vpavlenko.blackjack.gameplay.Game;
import com.github.vpavlenko.blackjack.gameplay.GameResult;
import com.github.vpavlenko.blackjack.gameplay.Hand;

public class AppletUI extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727406432867799177L;
	private JButton stand, hit, deal;

	private JLabel scoreLabel;
	private JPanel playerState, dealerState;

	private Game game;

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
		setSize(250, 200);

		dealerState = new JPanel();
		dealerState.setLayout(new BoxLayout(dealerState, BoxLayout.X_AXIS));
		dealerState.setAlignmentX(LEFT_ALIGNMENT);
		dealerState.setAlignmentY(TOP_ALIGNMENT);

		playerState = new JPanel();
		playerState.setLayout(new BoxLayout(playerState, BoxLayout.X_AXIS));
		playerState.setAlignmentX(LEFT_ALIGNMENT);
		playerState.setAlignmentY(TOP_ALIGNMENT);

		scoreLabel = new JLabel("game state");
		scoreLabel.setAlignmentX(LEFT_ALIGNMENT);
		scoreLabel.setAlignmentY(TOP_ALIGNMENT);

		JPanel statesPanel = new JPanel();
		statesPanel.setLayout(new BoxLayout(statesPanel, BoxLayout.Y_AXIS));
		statesPanel.setAlignmentX(LEFT_ALIGNMENT);
		statesPanel.add(dealerState);
		statesPanel.add(playerState);
		statesPanel.add(scoreLabel);

		getContentPane().add(statesPanel, BorderLayout.CENTER);

		deal = new JButton("Deal");
		stand = new JButton("Stand");
		hit = new JButton("Hit");

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(deal);
		buttonsPanel.add(stand);
		buttonsPanel.add(hit);

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
		drawCardsAndScore(playerState, "Player", game.deal.getPlayerHand());
		drawCardsAndScore(dealerState, "Dealer", game.deal.getDealerHand());

		StringBuffer score = new StringBuffer("<html>score:<br>");
		for (Entry<GameResult, Integer> entry : game.agreggatedResults
				.entrySet()) {
			score.append("&nbsp;&nbsp;&nbsp;" + entry.getKey() + ": "
					+ entry.getValue() + "<br>");
		}
		score.append("</html>");
		scoreLabel.setText(score.toString());

		repaint();
	}

	private void drawCardsAndScore(JPanel state, String side, Hand hand) {
		state.removeAll();
		state.add(new JLabel(side + ": "));
		for (Card card : hand) {
			JLabel label = new JLabel(card.toString());
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			state.add(label);
		}
		state.add(new JLabel(": " + hand.getScore()));
		state.revalidate();
	}
}

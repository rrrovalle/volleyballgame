package view;
 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GameController;
import controller.IGame;
import view.command.CommandInvoker;
import view.command.gameCommands.AddScoreCommand;
import view.command.gameCommands.DefineGameRefereeCommand;
import view.command.gameCommands.ResetGameCommand;
import view.command.gameCommands.SubScoreCommand;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameView extends JFrame implements IGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTeam1;
	private JLabel lbTeam2;
	private JLabel lbPointsA;
	private JLabel lbPointsB;
	private JLabel lbSetValue; 
	private JButton btnRemoveA;
	private JButton btnRemoveB;
	private JButton btnAddA;
	private JButton btnAddB;
	private JList<String> list;
	private JLabel lbScore;
	private JButton btnReset;
	private JLabel lbGameReferee;
	
	private GameController game    = GameController.getIntance();
	private CommandInvoker command = new CommandInvoker();

	/**
	 * Create the frame.
	 */
	public GameView() {
		game.attach(this); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 501);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbTeam1 = new JLabel("Team 1");
		lbTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lbTeam1.setBounds(92, 50, 56, 16);
		contentPane.add(lbTeam1);
		
		lbTeam2 = new JLabel("Team 2");
		lbTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lbTeam2.setBounds(574, 50, 56, 16);
		contentPane.add(lbTeam2);
		
		JLabel lbSet = new JLabel("SET");
		lbSet.setHorizontalAlignment(SwingConstants.CENTER);
		lbSet.setBounds(329, 50, 56, 16);
		contentPane.add(lbSet);
		
		lbPointsA = new JLabel("0");
		lbPointsA.setHorizontalAlignment(SwingConstants.CENTER);
		lbPointsA.setBounds(92, 79, 56, 42);
		contentPane.add(lbPointsA);
		
		lbPointsB = new JLabel("0");
		lbPointsB.setHorizontalAlignment(SwingConstants.CENTER);
		lbPointsB.setBounds(574, 79, 56, 42);
		contentPane.add(lbPointsB);
		
		lbSetValue = new JLabel("1");
		lbSetValue.setHorizontalAlignment(SwingConstants.CENTER);
		lbSetValue.setBounds(329, 79, 56, 42);
		contentPane.add(lbSetValue);
		
		btnRemoveA = new JButton("-");
		btnRemoveA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubScoreCommand subScore = new SubScoreCommand(game, 1);
				command.add(subScore);
				command.execute();
			}
		});
		btnRemoveA.setBounds(77, 134, 50, 25);
		contentPane.add(btnRemoveA);
		
		btnAddA = new JButton("+");
		btnAddA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScoreCommand addScore = new AddScoreCommand(game, 1);
				command.add(addScore);
				command.execute();
			}
		});
		btnAddA.setBounds(131, 134, 50, 25);
		contentPane.add(btnAddA);
		
		btnAddB = new JButton("+");
		btnAddB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScoreCommand addScore = new AddScoreCommand(game, 2);
				command.add(addScore);
				command.execute();
			}
		});
		btnAddB.setBounds(607, 134, 50, 25);
		contentPane.add(btnAddB);
		
		btnRemoveB = new JButton("-");
		btnRemoveB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubScoreCommand subScore = new SubScoreCommand(game, 2);
				command.add(subScore);
				command.execute();
			}
		});
		btnRemoveB.setBounds(553, 134, 50, 25);
		contentPane.add(btnRemoveB);
		
		list = new JList<String>();
		list.setBounds(77, 217, 576, 186);
		contentPane.add(list);
		
		lbScore = new JLabel("Score");
		lbScore.setHorizontalAlignment(SwingConstants.CENTER);
		lbScore.setBounds(329, 188, 56, 16);
		contentPane.add(lbScore);
		
		btnReset = new JButton("Reset Game");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetGameCommand reset = new ResetGameCommand(game);
				command.add(reset);
				command.execute();
			}
		});
		btnReset.setBounds(77, 416, 128, 25);
		contentPane.add(btnReset);
		
		lbGameReferee = new JLabel("Referee: Default");
		lbGameReferee.setHorizontalAlignment(SwingConstants.CENTER);
		lbGameReferee.setBounds(307, 13, 105, 16);
		contentPane.add(lbGameReferee);
		 
		/** Set game referee */
		initializeReferee();
	}
	
	public void initializeReferee() {
		String name = JOptionPane.showInputDialog("Referee Name?");
		DefineGameRefereeCommand refereeName = new DefineGameRefereeCommand(game, name);
		command.add(refereeName);
		command.execute();
	}

	@Override
	public void resetGame() {
		lbPointsA.setText("" + 0);
		lbPointsB.setText("" + 0);
		lbSetValue.setText("" + 1);
        list.setListData(new String[5]);
        btnAddA.setEnabled(true);
        btnAddB.setEnabled(true);
        btnRemoveA.setEnabled(true);
        btnRemoveB.setEnabled(true);
	}

	@Override
	public void refreshScore(int x, int y) {
        lbPointsA.setText("" + x);
        lbPointsB.setText("" + y);
	}

	@Override
	public void wonSet(int i, int set, String[] points) {
       lbSetValue.setText("" + set);
       JOptionPane.showMessageDialog(null, "Set won by " + i + "!!");
       list.setListData(points);
	}

	@Override
	public void finishGame(int i) {
        JOptionPane.showMessageDialog(null, "Team " + i + " won the game!");
        btnAddA.setEnabled(false);
        btnAddB.setEnabled(false);
        btnRemoveA.setEnabled(false);
        btnRemoveB.setEnabled(false);
	}
	
	@Override
	public void defineGameReferee(String name) {
		lbGameReferee.setText(name);
	}
}

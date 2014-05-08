package org.sfsoft.jfighter2d.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.sfsoft.jfighter2d.JFighter2D;
import org.sfsoft.jfighter2d.JFighter2D.GameState;
import org.sfsoft.jfighter2d.managers.ConfigurationManager;

public class MenuGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btSave;
	private JCheckBox checkSound;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MenuGame dialog = new MenuGame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		ConfigurationManager.SOUND = checkSound.isSelected();
	}
	
	private void play() {
		JFighter2D.gameState = GameState.PLAY;
		setVisible(false);
	}
	
	private void quit() {
		JFighter2D.gameState = GameState.QUIT;
		setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	public MenuGame() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblJfighterd = new JLabel("JFighter2D");
			lblJfighterd.setBounds(180, 10, 90, 16);
			lblJfighterd.setFont(new Font("Lucida Console", Font.BOLD, 15));
			contentPanel.add(lblJfighterd);
		}
		
		btSave = new JButton("Guardar");
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btSave.setBounds(327, 204, 117, 29);
		contentPanel.add(btSave);
		
		checkSound = new JCheckBox("Sonido");
		checkSound.setBounds(6, 57, 128, 23);
		contentPanel.add(checkSound);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btPlay = new JButton("Jugar");
				btPlay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						play();
					}
				});
				btPlay.setActionCommand("OK");
				buttonPane.add(btPlay);
				getRootPane().setDefaultButton(btPlay);
			}
			{
				JButton btQuit = new JButton("Salir");
				btQuit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						quit();
					}
				});
				btQuit.setActionCommand("Cancel");
				buttonPane.add(btQuit);
			}
		}
		
		setLocationRelativeTo(null);
	}
}

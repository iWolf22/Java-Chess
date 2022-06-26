package com.company;

// Imports everything we will need

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.event.*;

class HomeScreen {
  
  /**
   * Home Screen function to display the home screen
   *
   * @param void
   * @return void
   */
  public static void homeScreenScript() {

    // Creates the frame

    JFrame frame = new JFrame("Home Screen");
    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Creates the single player button
    
    JButton buttonSinglePlayer = new JButton("");
    buttonSinglePlayer.setBounds(50, 200, 200, 200);
    buttonSinglePlayer.setContentAreaFilled(false);
    frame.add(buttonSinglePlayer);
    ImageIcon buttonSinglePlayerIcon = new ImageIcon("images/single player image.png");
    Image buttonSinglePlayerImage = buttonSinglePlayerIcon.getImage();
    Image newButtonSinglePlayerImage = buttonSinglePlayerImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    buttonSinglePlayerIcon = new ImageIcon(newButtonSinglePlayerImage);
    buttonSinglePlayer.setIcon(buttonSinglePlayerIcon);

    // Cretes the multiplayer button
    
    JButton buttonMultiplayer = new JButton("");
    buttonMultiplayer.setBounds(300, 200, 200, 200);
    buttonMultiplayer.setContentAreaFilled(false);
    frame.add(buttonMultiplayer);
    ImageIcon buttonMultiplayerIcon = new ImageIcon("images/multi player image.png");
    Image buttonMultiplayerImage = buttonMultiplayerIcon.getImage();
    Image newButtonMultiplayerImage = buttonMultiplayerImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    buttonMultiplayerIcon = new ImageIcon(newButtonMultiplayerImage);
    buttonMultiplayer.setIcon(buttonMultiplayerIcon);

    // Creates the instruction button
    
    JButton buttonPractice = new JButton("");
    buttonPractice.setBounds(550, 200, 200, 200);
    buttonPractice.setContentAreaFilled(false);
    frame.add(buttonPractice);
    ImageIcon buttonPracticeIcon = new ImageIcon("images/practice image.png");
    Image buttonPracticeImage = buttonPracticeIcon.getImage();
    Image newButtonPracticeImage = buttonPracticeImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    buttonPracticeIcon = new ImageIcon(newButtonPracticeImage);
    buttonPractice.setIcon(buttonPracticeIcon);

    // Creates the single player label
    
    JLabel singlePlayerLabel = new JLabel("Singleplayer", SwingConstants.CENTER);
    singlePlayerLabel.setBounds(50, 420, 200, 50);
    singlePlayerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
    singlePlayerLabel.setForeground(Color.black);
    frame.add(singlePlayerLabel);

    // Creates the mutiplayer label
    
    JLabel multiPlayerLabel = new JLabel("Multiplayer", SwingConstants.CENTER);
    multiPlayerLabel.setBounds(300, 420, 200, 50);
    multiPlayerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
    multiPlayerLabel.setForeground(Color.black);
    frame.add(multiPlayerLabel);

    // Creates the practice label
    
    JLabel practiceLabel = new JLabel("Instructions", SwingConstants.CENTER);
    practiceLabel.setBounds(550, 420, 200, 50);
    practiceLabel.setFont(new Font("Serif", Font.PLAIN, 30));
    practiceLabel.setForeground(Color.black);
    frame.add(practiceLabel);

    // Creates the java chess title label
    
    JLabel titleLabel = new JLabel("Java Chess", SwingConstants.CENTER);
    titleLabel.setBounds(100, 25, 600, 150);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
    titleLabel.setForeground(Color.black);
    frame.add(titleLabel);

    // Creates the green background
    
    JLabel mainScreenBackground = new JLabel();
    mainScreenBackground.setIcon(new ImageIcon("images/main screen background.png"));
    mainScreenBackground.setBounds(0, 0, 800, 600);
    frame.add(mainScreenBackground);

    // If the single player button is pressed enter the single player game mode
    
    buttonSinglePlayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });

    // If the multipler button is pressed enter the multiplayer game mode
    
    buttonMultiplayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        ChessBoard.board();
      }
    });

    // If the practice button is pressed enter the practice game mode
    
    buttonPractice.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        InstructionScreen.mainScreen();
      }
    });

    // Formating label just because otherwise the frame will glitch out
    
    JLabel formating = new JLabel("");
    formating.setBounds(0, 0, 0, 0);
    frame.add(formating);

    // Showing the frame
    
    frame.show();

  }
}
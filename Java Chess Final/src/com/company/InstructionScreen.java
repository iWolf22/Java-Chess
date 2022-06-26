package com.company;

/*
 * @author Joshua Dierickse
 * @author Son Le
 */

// Imports everything that we will need

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class InstructionScreen {
  //Makes JFrame frame that will be used throughout the code.
  
  public static JFrame frame = new JFrame("Instruction Screen");
  /**
   * mainScreen
   *
   * Makes the main UI of the instruction screen
   * 
   * @param void
   * @return void
   */
  public static void mainScreen(){
    // Creates the frame

    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Creates the title label
    JLabel titleLabel = new JLabel("Instructions");
    titleLabel.setBounds(250, 10, 800, 70);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
    titleLabel.setForeground(Color.black);
    //Adds the label to the frame
    frame.add(titleLabel);

    //Creates the sub title lael
    JLabel subTitleLabel = new JLabel("Click on each button to learn more about chess mechanics");
    subTitleLabel.setBounds(100, 100, 800, 30);
    subTitleLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    subTitleLabel.setForeground(Color.black);
    //Adds the label to the frame
    frame.add(subTitleLabel);

    //Creates the movement label
    JLabel movement = new JLabel("Movement");
    movement.setBounds(295, 240, 800, 30);
    movement.setFont(new Font("Serif", Font.PLAIN, 40));
    movement.setForeground(Color.black);
    //Adds the label to the frame
    frame.add(movement);

    //Creates the basics button to teach the user about the basics of the game
    JButton basicsButton = new JButton("Chess Basics");
    basicsButton.setBounds(300, 150, 200, 50);
    basicsButton.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(basicsButton);

    //Creates the pawn movement button to teach the user about how pawns move
    JButton pawnMovement = new JButton("Pawn");
    pawnMovement.setBounds(50, 300, 100, 100);
    pawnMovement.setContentAreaFilled(false);
    frame.add(pawnMovement);

    //Creates the bishop movement button to teach the user about how bishops move
    JButton bishopMovement = new JButton("Bishop");
    bishopMovement.setBounds(200, 300, 100, 100);
    bishopMovement.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(bishopMovement);

    //Creates the knight movement button
    JButton knightMovement = new JButton("Knight");
    knightMovement.setBounds(350, 300, 100, 100);
    knightMovement.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(knightMovement);

    //Creates the rook movement button
    JButton rookMovement = new JButton("Rook");
    rookMovement.setBounds(500, 300, 100, 100);
    rookMovement.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(rookMovement);

    //Creates the queen movement button
    JButton queenMovement = new JButton("Queen");
    queenMovement.setBounds(650, 300, 100, 100);
    queenMovement.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(queenMovement);

    //Creates the king movement button
    JButton kingMovement = new JButton("King");
    kingMovement.setBounds(50, 450, 100, 100);
    kingMovement.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(kingMovement);

    //Creates the castling button
    JButton castling = new JButton("Castling");
    castling.setBounds(200, 450, 100, 100);
    castling.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(castling);

    //Creates the check button
    JButton check = new JButton("Check");
    check.setBounds(350, 450, 100, 100);
    check.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(check);

    //Creates the checkmate button
    JButton checkmate = new JButton("<html>Check Mate</html>");
    checkmate.setBounds(500, 450, 100, 100);
    checkmate.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(checkmate);

    //Creates the stalemate button
    JButton stalemate = new JButton("<html>Stale Mate</html>");
    stalemate.setBounds(650, 450, 100, 100);
    stalemate.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(stalemate);

    //Creates the back button
    JButton back = new JButton("Back");
    back.setBounds(30, 30, 150, 50);
    back.setContentAreaFilled(false);
    //Adds the button to the frame
    frame.add(back);

    //Back action listener
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Deletes the frame and goes back to the home screen
        frame.dispose();
        HomeScreen.homeScreenScript();
      }
    });

    //Action listener for the basics button
    basicsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //Makes a new frame
        frame.dispose();
        JFrame frame = new JFrame("Chess Basics");
        frame.setSize(800, 600);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Title label
        JLabel titleLabel = new JLabel("Chess Basics");
        titleLabel.setBounds(230, 10, 800, 70);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        titleLabel.setForeground(Color.black);
        //Adds label to the screen
        frame.add(titleLabel);

        //Back button
        JButton back = new JButton("Back");
        back.setBounds(30, 30, 150, 50);
        back.setContentAreaFilled(false);
        frame.add(back);

        //Diagram to show the user how a normal chess board looks
        JLabel diagram = new JLabel("");
        diagram.setBounds(250, 100, 300, 300);
        ImageIcon Icon = new ImageIcon("Instructions/startingChessBoard.png");
        Image Image = Icon.getImage();
        Image newImage = Image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        Icon = new ImageIcon(newImage);
        diagram.setIcon(Icon);
        //Adds the diagram to the frame
        frame.add(diagram);

        //Instruction labels, telling the user how the game works
        JLabel text = new JLabel("A chess board is set up like above.");
        text.setBounds(150, 380, 500, 70);
        text.setFont(new Font("Serif", Font.PLAIN, 20));
        text.setForeground(Color.black);
        frame.add(text);

        JLabel text2 = new JLabel("<html> White moves first, then black moves. Each player can move one piece at a time. </html>");
        text2.setBounds(150, 420, 500, 70);
        text2.setFont(new Font("Serif", Font.PLAIN, 20));
        text2.setForeground(Color.black);
        frame.add(text2);

        JLabel text3 = new JLabel("<html> Players can take pieces by moving their own piece onto an enemy piece. However, each piece has a different way of moving. </html>");
        text3.setBounds(150, 470, 500, 100);
        text3.setFont(new Font("Serif", Font.PLAIN, 20));
        text3.setForeground(Color.black);
        frame.add(text3);

        back.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            frame.dispose();
            mainScreen();
          }
        });

        JLabel formatting = new JLabel("");
        formatting.setBounds(0, 0, 0, 0);
        frame.add(formatting);
        
        frame.show();
      }
    });

    //Action listeners for the movement buttons. For each of the action listeners below, it runs a method that makes a new frame on the screen, that has a different title, diagram, and instruction based on the input we put into the method. 
    pawnMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Pawn Movement", "When a pawn is in its starting position. It can move two spaces up. Otherwise, it can only move one space up. If there is an enemy piece to the direct diagonal of the pawn, the pawn can move diagonally and take the enemy piece. In addition, if the pawn reaches the other side of the board, it can turn into a queen, rook, knight, or bishop.", "pawnMovement");
      }
    });

    bishopMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Bishop Movement", "Bishops can move diagonally. However, if there is a piece blocking its path, the bishop can not move past that piece.", "bishopMovement");
      }
    });

    knightMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Knight Movement", "Knights can move in an L shape. Basically, a knight's movement consists of 2 spaces in one axis, and 1 space in the other. Knights can \"jump\" over pieces and therefore will not be blocked by a piece in its path.", "knightMovement");
      }
    });

    rookMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Rook Movement", "Rooks can move horizontally and vertically. However, if there is a piece blocking its path, the rook can not move past that piece.", "rookMovement");
      }
    });

    queenMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Queen Movement", "Queens are a combination of rooks and bishops; they can move horizontally, vertically, as well as diagonally. However, if there is a piece blocking its path, the queen can not move past that piece.", "queenMovement");
      }
    });

    kingMovement.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionDoubleImage("King Movement", "The king can move in any direction in a one square radius around itself.", "However, the king can not move into a square that is currently being attacked by an enemy piece, as shown in the image above.", "kingMovementNoCheck", "restrictedKingMovement");
      }
    });

    castling.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImageForCastling ("Castling", "Castling can be done with a king and a rook. The squares between the rook and the king must be empty and both pieces has not been moved. In addition, the king is not currently in check, and the king must not move over a square that is being attacked by an enemy piece.", "castling1"); 
      }
    });

    check.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Checking", "\"Check\" or \"Checking\" refers to when the king is currently under attack. If the king is in the path of an enemy piece, it is referred to as being \"in check\" and lights up red. The next move from the player in check must move the king out of check.", "check");
      }
    });

    checkmate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Checkmate", "Checkmate happens when the king is currently in check, and does not have anywhere to move. If a player ever reaches a position where they are \"in checkmate\", they lose the game and the game is over.", "checkmate");
      }
    });

    stalemate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        instructionSingleImage ("Stalemate", "Stalemate happens when it is a player's turn, and they have nowhere to move. In a stalemate, the game ends in a draw.", "stalemate");
      }
    });

    //Formatting
    JLabel formatting = new JLabel("");
    formatting.setBounds(0, 0, 0, 0);
    frame.add(formatting);

    //Shows the frame
    frame.show();
  }

  /**
   * instructionsSingleImage
   *
   * Makes a frame based on the input it is given
   * 
   * @param String titleString, String textString, String imageName
   * @return void
   */
  public static void instructionSingleImage (String titleString, String textString, String imageName) {
    //Disposes the old frame
    frame.dispose();
    JFrame frame = new JFrame(titleString);
    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Title label
    JLabel titleLabel = new JLabel(titleString);
    titleLabel.setBounds((800 - titleString.length() * 31)/2, 10, 800, 70);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
    titleLabel.setForeground(Color.black);
    //Adds title to the frame
    frame.add(titleLabel);

    //Back button
    JButton back = new JButton("Back");
    back.setBounds(30, 100, 150, 50);
    back.setContentAreaFilled(false);
    //Adds button to the frame
    frame.add(back);

    //Diagram; image is based on the image name in the input
    JLabel diagram = new JLabel("");
    diagram.setBounds(250, 100, 300, 300);
    ImageIcon Icon = new ImageIcon("Instructions/" + imageName + ".png");
    Image Image = Icon.getImage();
    Image newImage = Image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    Icon = new ImageIcon(newImage);
    diagram.setIcon(Icon);
    //Adds the diagram to the frame
    frame.add(diagram);

    //Instruction label. Its content is based on the the textString in the input
    JLabel text = new JLabel("<html>" + textString + "</html>");
    text.setBounds(100, 410, 600, 150);
    text.setFont(new Font("Serif", Font.PLAIN, 20));
    text.setForeground(Color.black);
    //Adds label to the frame
    frame.add(text);

    //Back action listener
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        mainScreen();
      }
    });

    //Formatting
    JLabel formatting = new JLabel("");
    formatting.setBounds(0, 0, 0, 0);
    frame.add(formatting);

    //Shows the frame
    frame.show();
  }

  /**
   * instructionsSingleImageForCastling
   *
   * Makes a frame based on the input it is given. This has the same function as instructionsSingleImage, but with a next button
   * 
   * @param String titleString, String textString, String imageName
   * @return void
   */
  public static void instructionSingleImageForCastling (String titleString, String textString, String imageName) {
    //Same logic as the instructionsSingleImage method, therefore the same comments. 
    frame.dispose();
    JFrame frame = new JFrame(titleString);
    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel titleLabel = new JLabel(titleString);
    titleLabel.setBounds((800 - titleString.length() * 31)/2, 10, 800, 70);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
    titleLabel.setForeground(Color.black);
    frame.add(titleLabel);

    JButton back = new JButton("Back");
    back.setBounds(30, 100, 150, 50);
    back.setContentAreaFilled(false);
    frame.add(back);

    //Next button
    JButton next = new JButton("Next");
    next.setBounds(625, 100, 150, 50);
    next.setContentAreaFilled(false);
    //Adds button to the frame
    frame.add(next); 

    JLabel diagram = new JLabel("");
    diagram.setBounds(250, 100, 300, 300);
    ImageIcon Icon = new ImageIcon("Instructions/" + imageName + ".png");
    Image Image = Icon.getImage();
    Image newImage = Image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    Icon = new ImageIcon(newImage);
    diagram.setIcon(Icon);
    frame.add(diagram);

    JLabel text = new JLabel("<html>" + textString + "</html>");
    text.setBounds(100, 410, 600, 150);
    text.setFont(new Font("Serif", Font.PLAIN, 20));
    text.setForeground(Color.black);
    frame.add(text);

    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        mainScreen();
      }
    });

    //Runs the instructionDoubleImage method to show the different types of stalemates when the next button is pressed
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        instructionDoubleImage("Castling Types", "King side castling", "Queen side castling", "kingSideCastling", "queenSideCastling");
      }
    });

    JLabel formatting = new JLabel("");
    formatting.setBounds(0, 0, 0, 0);
    frame.add(formatting);
    
    frame.show();
  }

  /**
   * instructionDoubleImage
   *
   * Makes a frame based on the input it is given.
   * 
   * @param String titleString, String titleString, String textString, String textString2, String imageName1, String imageName2
   * @return void
   */
  public static void instructionDoubleImage (String titleString, String textString, String textString2, String imageName1, String imageName2) {
    //Literally the same logic as instructionsSingleImage, but with two images instead of one. No need for comments.
    frame.dispose();
    JFrame frame = new JFrame(titleString);
    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel titleLabel = new JLabel(titleString);
    titleLabel.setBounds((800 - titleString.length() * 31)/2, 10, 800, 70);
    titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
    titleLabel.setForeground(Color.black);
    frame.add(titleLabel);

    JButton back = new JButton("Back");
    back.setBounds(30, 25, 150, 50);
    back.setContentAreaFilled(false);
    frame.add(back);

    JLabel diagram1 = new JLabel("");
    diagram1.setBounds(75, 100, 300, 300);
    ImageIcon Icon1 = new ImageIcon("Instructions/" + imageName1 + ".png");
    Image Image1 = Icon1.getImage();
    Image newImage1 = Image1.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    Icon1 = new ImageIcon(newImage1);
    diagram1.setIcon(Icon1);
    frame.add(diagram1);

    JLabel diagram2 = new JLabel("");
    diagram2.setBounds(425, 100, 300, 300);
    ImageIcon Icon2 = new ImageIcon("Instructions/" + imageName2 + ".png");
    Image Image2 = Icon2.getImage();
    Image newImage2 = Image2.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    Icon2 = new ImageIcon(newImage2);
    diagram2.setIcon(Icon2);
    frame.add(diagram2);

    JLabel text = new JLabel("<html>" + textString + "</html>");
    text.setBounds(75, 410, 300, 150);
    text.setFont(new Font("Serif", Font.PLAIN, 20));
    text.setForeground(Color.black);
    frame.add(text);

    JLabel text2 = new JLabel("<html>" + textString2 + "</html>");
    text2.setBounds(425, 410, 300, 150);
    text2.setFont(new Font("Serif", Font.PLAIN, 20));
    text2.setForeground(Color.black);
    frame.add(text2);

    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        mainScreen();
      }
    });

    JLabel formatting = new JLabel("");
    formatting.setBounds(0, 0, 0, 0);
    frame.add(formatting);
    
    frame.show();
  }
}
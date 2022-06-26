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

class ChessBoard extends JFrame {

  // Creates all the public varibles that we will need for our game
  
  public static JFrame frame = new JFrame("Chess Board");
  
  public static JButton[][] chessBoardButton = new JButton[8][8];

  public static JButton drawBlack = new JButton("Draw");
  public static JButton drawWhite = new JButton("Draw");
  public static JButton resignWhite = new JButton("Resign");
  public static JButton resignBlack = new JButton("Resign");
  public static boolean whiteDraw = false;
  public static boolean blackDraw = false;
  public static boolean gameOver = false;
  
  public static int currentButtonX = -1;
  public static int currentButtonY = -1;
  public static int updatedButtonX = -1;
  public static int updatedButtonY = -1;
  public static String promotionReturn = "";
  public static int promoCurrentButtonX = -1;
  public static int promoCurrentButtonY = -1;
  public static int promoUpdatedButtonX = -1;
  public static int promoUpdatedButtonY = -1;

  public static ArrayList<String> whitePieceTaken = new ArrayList<String>();
  public static ArrayList<String> blackPieceTaken = new ArrayList<String>();

  public static JLabel whitePieceLabel = new JLabel("");
  public static JLabel blackPieceLabel = new JLabel("");
  public static JLabel whitePieceLabel2 = new JLabel("");
  public static JLabel blackPieceLabel2 = new JLabel("");

  public static JLabel player1emoji = new JLabel("  😊");
  public static JLabel player2emoji = new JLabel("  😊");

  public static boolean checkMateWhite;
  public static boolean checkMateBlack;

  public static String playerTurn = "white";
  public static int chessPieceIndex;

  public static String[][] chessBoard = new String[8][8];
  public static String[][] tempChessBoard = new String[8][8];
  public static String[][] whiteMoves = new String[8][8];
  public static String[][] blackMoves = new String[8][8];

  public static int redSquareX = -1;
  public static int redSquareY = -1;
  public static boolean inCheck = false;

  public static String[][] templateBoard = new String [8][8];

  public static ArrayList<ChessPiece> chessBoardPieces = new ArrayList<ChessPiece>();

  public static JTextArea movesLogArea = new JTextArea();
  public static JScrollPane movesLogScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  public static ArrayList<String> movesLogList = new ArrayList<String>();
  
  public static boolean promote = false;

  /**
   * Creates the promotion screen where you select what piece you would like to promote the pawn too
   *
   * @param void
   * @return void
   */
  public static void promotionFrame() {

    // Creates promotion frame
    
    JFrame promotion = new JFrame("Promotion");
    promotion.setVisible(false);
    promotion.setSize(400, 150);
    promotion.setLocation(400, 5);
    promotion.setResizable(false);

    // Creates the 4 buttons
    
    JButton queenPromote = new JButton();
    JButton bishopPromote = new JButton();
    JButton rookPromote = new JButton();
    JButton knightPromote = new JButton();

    // Creates the promotion label
    
    JLabel promoteLabel = new JLabel("Choose the piece you want to promote to:");
    promoteLabel.setBounds(20, 5, 400, 35);
    promoteLabel.setFont(new Font("Serif", Font.PLAIN, 17));
    promoteLabel.setForeground(Color.black);
    promotion.add(promoteLabel);

    // Buffer for formatting
    
    JLabel buffer = new JLabel("");
    buffer.setBounds(0, 0, 0, 0);

    // Moving the buttons around
    
    queenPromote.setBounds(50, 50, 50, 50);
    bishopPromote.setBounds(133, 50, 50, 50);
    rookPromote.setBounds(216, 50, 50, 50);
    knightPromote.setBounds(300, 50, 50, 50);
    promotion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Adding the corresponding images to the buttons
    
    if (playerTurn.equals("black")){
      queenPromote.setIcon(new ImageIcon("images/whiteQueen.png"));
      bishopPromote.setIcon(new ImageIcon("images/whiteBishop.png"));
      rookPromote.setIcon(new ImageIcon("images/whiteRook.png"));
      knightPromote.setIcon(new ImageIcon("images/whiteKnight.png"));
    }
    else{
      queenPromote.setIcon(new ImageIcon("images/blackQueen.png"));
      bishopPromote.setIcon(new ImageIcon("images/blackBishop.png"));
      rookPromote.setIcon(new ImageIcon("images/blackRook.png"));
      knightPromote.setIcon(new ImageIcon("images/blackKnight.png"));
    }

    // Adding everything to the frame

    promotion.add(queenPromote);
    promotion.add(bishopPromote);
    promotion.add(rookPromote);
    promotion.add(knightPromote);
    promotion.add(buffer);
    promotion.setVisible(true);

    // Removing the old chess pieces from the chessBoardPieces list
    
    for (int k = 0; k < chessBoardPieces.size(); k++) {
      if (chessBoardPieces.get(k).xPos == promoUpdatedButtonX && chessBoardPieces.get(k).yPos == promoUpdatedButtonY) {
        chessBoardPieces.remove(k);
      }
    }

    // Formating label
    
    JLabel formating = new JLabel("");
    formating.setBounds(0, 0, 0, 0);
    frame.add(formating);

    // If the queen promotion button is pressed
    
    queenPromote.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        // Get rid of the promotion frame
        
        promotion.dispose();

        // Add the new queen the the pieces list and board matrix
        
        if (playerTurn.equals("white")){
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♛";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "black", "blackQueen", "♛", false, false));
        }
        else{
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♕";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "white", "whiteQueen", "♕", false, false));
        }
        
        // Checks for check and checkmate given the promotion
        
        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }
        
        updatedButtonX = promoUpdatedButtonX;
        updatedButtonY = promoUpdatedButtonY;
        currentButtonX = promoCurrentButtonX;
        currentButtonY = promoCurrentButtonY;
        checkDetection();
        checkMateDetection();

        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }

        // Resets everything
        
        promote = false;
        buttonMaker();
        buttonReset();
        frame.add(formating);
        frame.show();
      }
    });

    // If the bishop promotion button is pressed
    
    bishopPromote.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        // Get rid of the promotion frame
        
        promotion.dispose();

        // Adds the new bishop to the pieces list and board matrix
        
        if (playerTurn.equals("white")){
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♝";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "black", "blackBishop", "♝", false, false));
        }
        else{
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♗";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "white", "whiteBishop", "♗", false, false));
        }
        
        // Checks for check and checkmate given the promotion
        
        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }
        
        updatedButtonX = promoUpdatedButtonX;
        updatedButtonY = promoUpdatedButtonY;
        currentButtonX = promoCurrentButtonX;
        currentButtonY = promoCurrentButtonY;
        checkDetection();
        checkMateDetection();

        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }

        // Resets everything
        
        promote = false;
        buttonMaker();
        buttonReset();
        frame.add(formating);
        frame.show();
      }
    });

    // If the rook promotion button is pressed
    
    rookPromote.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        // Delete the promotion frame
        
        promotion.dispose();

        // Add the rook to the chess pieces list and chess board matrix
        
        if (playerTurn.equals("white")){
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♜";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "black", "blackRook", "♜", false, false));
        }
        else{
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♖";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "white", "whiteRook", "♖", false, false));
        }

        // Checks for check and checkmate given the promotion
        
        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }
        
        updatedButtonX = promoUpdatedButtonX;
        updatedButtonY = promoUpdatedButtonY;
        currentButtonX = promoCurrentButtonX;
        currentButtonY = promoCurrentButtonY;
        checkDetection();
        checkMateDetection();

        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }

        // Resets everything
        
        promote = false;
        buttonMaker();
        buttonReset();
        frame.add(formating);
        frame.show();
      }
    });

    // If the knight promotion button is pressed
    
    knightPromote.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        // Delete the promotion frame
        
        promotion.dispose();

        // Add the new knight to the chess piece list and chess board matrix
        
        if (playerTurn.equals("white")){
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♞";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "black", "blackKnight", "♞", false, false));
        }
        else{
          chessBoard[promoUpdatedButtonY][promoUpdatedButtonX] = "♘";
          chessBoardPieces.add(new ChessPiece(promoUpdatedButtonX, promoUpdatedButtonY, "white", "whiteKnight", "♘", false, false));
        }

        // Checks for check and checkmate given the promotion
        
        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }
        
        updatedButtonX = promoUpdatedButtonX;
        updatedButtonY = promoUpdatedButtonY;
        currentButtonX = promoCurrentButtonX;
        currentButtonY = promoCurrentButtonY;
        checkDetection();
        checkMateDetection();

        if (playerTurn.equals("black")) {
          playerTurn = "white";
        }
        else {
          playerTurn = "black";
        }

        // Resets everything
        
        promote = false;
        buttonMaker();
        buttonReset();
        frame.add(formating);
        frame.show();
      }
    });
  }

  /**
   * Creates a "victory frame" when the game is over
   *
   * @param String winner String victoryMethod
   * @return void
   */
  public static void victoryFrame(String winner, String victoryMethod) {

    // Create the victory frame and call the game over
    
    gameOver = true;
    JFrame victoryFrame = new JFrame("Victory Frame");
    victoryFrame.setSize(500, 100);
    victoryFrame.setLocation(400, 5);
    victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the victory string
    
    String victoryString = "";
    
    if (!winner.equals("Draw")) {
      victoryString = winner + " won by " + victoryMethod;
    }
    else {
      victoryString = winner + " by " + victoryMethod;
    }

    // Create the victory string's label
    
    JLabel victoryLabel = new JLabel(victoryString);
    victoryLabel.setBounds(10, 10, 400, 50);
    victoryLabel.setFont(new Font("Serif", Font.PLAIN, 30));
    victoryLabel.setForeground(Color.black);
    victoryFrame.add(victoryLabel);

    // Create the home button
    
    JButton homeButton = new JButton("Home");
    homeButton.setBounds(410, 10, 80, 50);
    victoryFrame.add(homeButton);

    // If the home button is pressed
    
    homeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        // Go back to the home screen
        
        frame.dispose();
        victoryFrame.dispose();
        HomeScreen.homeScreenScript();
      }
    });

    // Formating label
    
    JLabel formating = new JLabel("");
    formating.setBounds(0, 0, 0, 0);
    victoryFrame.add(formating);

    // Show the victory screen

    victoryFrame.show();
  }

  /**
   * Generates a list of chess piece objects from the chess board
   *
   * @param void
   * @return void
   */
  public static void pieceMaker() {
    for (int i = 0; i < chessBoard.length; i++) {
      for (int  j= 0; j < chessBoard.length; j++) {
        if (chessBoard[i][j].equals("♜")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackRook", "♜", false, false));
        }
        else if (chessBoard[i][j].equals("♞")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackKnight", "♞", false, false));
        }
        else if (chessBoard[i][j].equals("♝")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackBishop", "♝", false, false));
        }
        else if (chessBoard[i][j].equals("♛")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackQueen", "♛", false, false));
        }
        else if (chessBoard[i][j].equals("♚")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackKing", "♚", false, false));
        }
        else if (chessBoard[i][j].equals("♟")){
          chessBoardPieces.add(new ChessPiece(j, i, "black", "blackPawn", "♟", false, false));
        }
        else if (chessBoard[i][j].equals("♖")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whiteRook", "♖", false, false));
        }
        else if (chessBoard[i][j].equals("♘")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whiteKnight", "♘", false, false));
        }
        else if (chessBoard[i][j].equals("♗")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whiteBishop", "♗", false, false));
        }
        else if (chessBoard[i][j].equals("♕")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whiteQueen", "♕", false, false));
        }
        else if (chessBoard[i][j].equals("♔")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whiteKing", "♔", false, false));
        }
        else if (chessBoard[i][j].equals("♙")){
          chessBoardPieces.add(new ChessPiece(j, i, "white", "whitePawn", "♙", false, false));
        }
      }
    }
  }

  /**
   * Puts the chess piece images onto the buttons
   *
   * @param void
   * @return void
   */
  public static void buttonMaker() {
    
    int x;
    int y; 

    // Resets all the button images
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        chessBoardButton[i][j].setIcon(null);
      }
    }

    // Loops through the chess pieces and add the images onto the correct chess piece buttons
    
    for (int i = 0; i < chessBoardPieces.size(); i++) {
      x = chessBoardPieces.get(i).xPos;
      y = chessBoardPieces.get(i).yPos;
      chessBoardButton[x][y].setIcon(chessBoardPieces.get(i).chessSprite);
      chessBoardButton[x][y].setBounds(x * 50 + 20, y * 50 + 80, 50, 50);
      frame.add(chessBoardButton[x][y]);
    }
  }

  /**
   * Updates the background color of the buttons
   *
   * @param void
   * @return void
   */
  public static void buttonUpdate() {

    // Creates the white and brown alternating pattern
    
    for(int i = 0; i < chessBoardButton.length; i++) {
      for(int j = 0; j < chessBoardButton.length; j++) {
        if (i % 2 == 0) {
          if (j % 2 == 1) {
            chessBoardButton[i][j].setBackground(new Color(205, 133, 63));
          }
          else {
            chessBoardButton[i][j].setBackground(new Color(237, 201, 175));
          }
        }
        else {
          if (j % 2 == 0) {
            chessBoardButton[i][j].setBackground(new Color(205, 133, 63));
          }
          else {
            chessBoardButton[i][j].setBackground(new Color(237, 201, 175));
          }
        }
      }
    }

    // Adds the red square for check and checkmate
    
    if (redSquareX != -1 && redSquareY != -1) {
      chessBoardButton[redSquareY][redSquareX].setBackground(new Color(220, 20, 60));
    }
  }

  /**
   * The main board function
   * This is the first code to run and sets everything up
   *
   * @param void
   * @return void
   */
  public static void board() {

    // Resets all the global varibles so when the code is run twice it has a clean slate
    
    frame = new JFrame("Chess Board");
    
    chessBoardButton = new JButton[8][8];

    drawBlack = new JButton("Draw");
    drawWhite = new JButton("Draw");
    resignWhite = new JButton("Resign");
    resignBlack = new JButton("Resign");
    whiteDraw = false;
    blackDraw = false;
    gameOver = false;
    
    currentButtonX = -1;
    currentButtonY = -1;
    updatedButtonX = -1;
    updatedButtonY = -1;
    promotionReturn = "";
    promoUpdatedButtonX = -1;
    promoUpdatedButtonY = -1;
    promoCurrentButtonX = -1;
    promoCurrentButtonY = -1;
  
    whitePieceTaken = new ArrayList<String>();
    blackPieceTaken = new ArrayList<String>();
  
    whitePieceLabel = new JLabel("");
    blackPieceLabel = new JLabel("");
    whitePieceLabel2 = new JLabel("");
    blackPieceLabel2 = new JLabel("");
  
    player1emoji = new JLabel("  😊");
    player2emoji = new JLabel("  😊");
  
    checkMateWhite = false;
    checkMateBlack = false;
  
    playerTurn = "white";
    chessPieceIndex = -1;
  
    chessBoard = new String[8][8];
    tempChessBoard = new String[8][8];
    whiteMoves = new String[8][8];
    blackMoves = new String[8][8];
  
    redSquareX = -1;
    redSquareY = -1;
    inCheck = false;
  
    templateBoard = new String [8][8];
  
    chessBoardPieces = new ArrayList<ChessPiece>();
  
    movesLogArea = new JTextArea();
    movesLogScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    movesLogList = new ArrayList<String>();

    // Generates the frame
    
    frame.setSize(800, 600);
    frame.setLocation(300, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Puts the pieces onto the chess board

    chessBoard[0] = new String[] {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"};
    chessBoard[1] = new String[] {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"};
    for (int i = 2 ; i < 6; i++){
      chessBoard[i] = new String[] {"□", "□", "□", "□", "□", "□", "□", "□"}; 
    }
    chessBoard[6] = new String[] {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"};
    chessBoard[7] = new String[] {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"};

    // Puts the pieces onto the template board
    
    templateBoard[0] = new String[] {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"};
    templateBoard[1] = new String[] {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"};
    for (int i = 2 ; i < 6; i++){
      templateBoard[i] = new String[] {"□", "□", "□", "□", "□", "□", "□", "□"}; 
    }
    templateBoard[6] = new String[] {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"};
    templateBoard[7] = new String[] {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"};

    // Creates the 2D matrix of buttons
    
    for(int i = 0; i < chessBoardButton.length; i++){
      for(int j = 0; j < chessBoardButton.length; j++){

        // Makes the button
        
        chessBoardButton[i][j] = new JButton("");

        // Mass produces them at I and J
        
        chessBoardButton[i][j].setBounds(20 + i * 50, 80 + j * 50, 50, 50);

        // Creates the white and brown alternating pattern
        
        if (i % 2 == 0) {
          if (j % 2 == 1) {
            chessBoardButton[i][j].setBackground(new Color(205, 133, 63));
          }
          else {
            chessBoardButton[i][j].setBackground(new Color(237, 201, 175));
          }
        }
        else {
          if (j % 2 == 0) {
            chessBoardButton[i][j].setBackground(new Color(205, 133, 63));
          }
          else {
            chessBoardButton[i][j].setBackground(new Color(237, 201, 175));
          }
        }

        // Adds the buttons to the frame
        
        frame.add(chessBoardButton[i][j]);
      }
    }

    // Creates the pieces
    
    pieceMaker();

    // Updates the buttons
    
    buttonMaker();
    buttonUpdate();

    // If one of the buttons are pressed run the button pressed function
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        chessBoardButton[i][j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            JButton button = (JButton)event.getSource();
            buttonPressed(button);
          }
        });
      }
    }

    // Creates the white resign button
    
    resignWhite.setBounds(465, 500, 125, 40);
    resignWhite.setBackground(new Color(237, 201, 175));
    resignWhite.setFont(new Font("Dialog", Font.PLAIN, 15));
    frame.add(resignWhite);

    // Creates the white draw button
    
    drawWhite.setBounds(640, 500, 125, 40);
    drawWhite.setBackground(new Color(237, 201, 175));
    drawWhite.setFont(new Font("Dialog", Font.PLAIN, 15));
    frame.add(drawWhite);

    // Creates the black resign button
    
    resignBlack.setBounds(465, 20, 125, 40);
    resignBlack.setBackground(new Color(237, 201, 175));
    resignBlack.setFont(new Font("Dialog", Font.PLAIN, 15));
    frame.add(resignBlack);

    // Creates the black draw button
    
    drawBlack.setBounds(640, 20, 125, 40);
    drawBlack.setBackground(new Color(237, 201, 175));
    drawBlack.setFont(new Font("Dialog", Font.PLAIN, 15));
    frame.add(drawBlack);


    // If the white resign button is pressed, then resign the game
    
    resignWhite.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (gameOver == false) {
          victoryFrame("Black", "resignation");
        }
      }
    });

    // If the black resign button is pressed, then resign the game
    
    resignBlack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (gameOver == false) {
          victoryFrame("White", "resignation");
        }
      }
    });

    // If the white draw button is pressed
    
    drawWhite.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (gameOver == false) {

          // Set white draw the true
          
          whiteDraw = true;
          drawWhite.setEnabled(false);

          // If both draw buttons are pressed then draw the game
          
          if (blackDraw == true) {
            victoryFrame("Draw", "mutual agreement");
          }
        }
      }
    });

    // If the black draw button is pressed
    
    drawBlack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (gameOver == false) {

          // Set black draw to true
          
          blackDraw = true;
          drawBlack.setEnabled(false);

          // If both draw buttons are pressed then draw the game
          
          if (whiteDraw == true){
            victoryFrame("Draw", "mutual agreement");
          }
        }
      }
    });

    // Creates the moves log area

    movesLogArea.setBounds(465, 80, 300, 400);
    movesLogArea.setFont(new Font("Dialog", Font.PLAIN, 20));
    movesLogArea.setEditable(false);
    movesLogScroller.setBounds(465, 80, 300, 400);
    movesLogScroller.setViewportView(movesLogArea);
    frame.add(movesLogScroller);

    // Creates the black pieces taken list
    
    blackPieceLabel.setBounds(250, 10, 150, 60);
    blackPieceLabel.setForeground(Color.black);
    blackPieceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    frame.add(blackPieceLabel);
    blackPieceLabel2.setBounds(250, 30, 150, 60);
    blackPieceLabel2.setForeground(Color.black);
    blackPieceLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
    frame.add(blackPieceLabel2);

    // Creates the white pieces taken list
    
    whitePieceLabel.setBounds(250, 470, 150, 60);
    whitePieceLabel.setForeground(Color.black);
    whitePieceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    frame.add(whitePieceLabel);
    whitePieceLabel2.setBounds(250, 490, 150, 60);
    whitePieceLabel2.setForeground(Color.black);
    whitePieceLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
    frame.add(whitePieceLabel2);

    // Player 1 and player 2's title label
    
    JLabel player2Text = new JLabel("        Player 2");
    player2Text.setBounds(20, 20, 400, 60);
    player2Text.setForeground(Color.black);
    player2Text.setFont(new Font("Serif", Font.PLAIN, 30));
    frame.add(player2Text);
    JLabel player1Text = new JLabel("        Player 1");
    player1Text.setBounds(20, 480, 400, 60);
    player1Text.setForeground(Color.black);
    player1Text.setFont(new Font("Serif", Font.PLAIN, 30));
    frame.add(player1Text);

    // Player 1 and player 2's emoji to tell who's move it is
    
    player2emoji.setBounds(20, 20, 400, 60);
    player2emoji.setForeground(Color.red);
    player2emoji.setFont(new Font("Serif", Font.PLAIN, 30));
    frame.add(player2emoji);
    player1emoji.setBounds(20, 480, 400, 60);
    player1emoji.setForeground(Color.green);
    player1emoji.setFont(new Font("Serif", Font.PLAIN, 30));
    frame.add(player1emoji);

    // Top and bottom coloured boxes for aesthetic
    
    JLabel topFormatting = new JLabel("");
    topFormatting.setBounds(20, 20, 400, 60);
    topFormatting.setOpaque(true);
    topFormatting.setBackground(new Color(205, 133, 63));
    frame.add(topFormatting);
    JLabel bottomFormatting = new JLabel("");
    bottomFormatting.setBounds(20, 480, 400, 60);
    bottomFormatting.setOpaque(true);
    bottomFormatting.setBackground(new Color(205, 133, 63));
    frame.add(bottomFormatting);

    // Formating label
    
    JLabel formating = new JLabel("");
    formating.setBounds(0, 0, 0, 0);
    frame.add(formating);

    // Shows the frame
    
    frame.show();
  }

  /**
   * Reset and updates the buttons
   *
   * @param void
   * @return void
   */
  private static void buttonReset() {
    currentButtonX = -1;
    currentButtonY = -1;
    updatedButtonX = -1;
    updatedButtonY = -1;
    buttonUpdate();
  }

  /**
   * Returns the color of an inputed chess piece emoji
   *
   * @param String currentPiece
   * @return String color
   */
  private static String chessPieceColor(String currentPiece) {
    if (currentPiece.equals("♙") || currentPiece.equals("♗") || currentPiece.equals("♘") || currentPiece.equals("♖") || currentPiece.equals("♕") || currentPiece.equals("♔")) {
      return("white");
    }
    if (currentPiece.equals("♟") || currentPiece.equals("♝") || currentPiece.equals("♞") || currentPiece.equals("♜") || currentPiece.equals("♛") || currentPiece.equals("♚")) {
      return("black");
    }
    return("null");
  }

  /**
   * Returns true if the rook movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  private static boolean rookMovement() {

    // Checks if the rook moved on one of the 4 axis
    
    if ((currentButtonX == updatedButtonX && currentButtonY == updatedButtonY) || (currentButtonX != updatedButtonX && currentButtonY != updatedButtonY)) {
      buttonReset();
      return false;
    }

    // Checks if the rook is moving through pieces above it
    
    if (currentButtonX == updatedButtonX) {
      if (currentButtonY < updatedButtonY) {
        for (int i = 1; i < updatedButtonY - currentButtonY; i++) {
          if (!chessBoard[currentButtonY + i][currentButtonX].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }

      // Checks if the rook is moving through pieces below it
        
      else {
        for (int i = 1; i < currentButtonY - updatedButtonY; i++) {
          if (!chessBoard[currentButtonY - i][currentButtonX].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
    }

    // Checks if the rook is moving through pieces to the right of it
    
    if (currentButtonX < updatedButtonX) {
      for (int i = 1; i < updatedButtonX - currentButtonX; i++) {
        if (!chessBoard[currentButtonY][currentButtonX + i].equals("□")) {
          buttonReset();
          return false;
        }
      }
    }

    // Checks if the rook is moving through pieces to the left of it
      
    else {
      for (int i = 1; i < currentButtonX - updatedButtonX; i++) {
        if (!chessBoard[currentButtonY][currentButtonX - i].equals("□")) {
          buttonReset();
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns true if the knight movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  private static boolean knightMovement() {

    // If the knight moves in an L shape return true
    
    if ((Math.abs(updatedButtonX - currentButtonX) == 2 && Math.abs(updatedButtonY - currentButtonY) == 1) || (Math.abs(updatedButtonX - currentButtonX) == 1 && Math.abs(updatedButtonY - currentButtonY) == 2)) {
      return true;
    }

    // Otherwise return false
      
    else {
      buttonReset();
      return false;
    }
  }

  /**
   * Returns true if the bishop movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  private static boolean bishopMovement() {

    // Checks if the bishop is moving on an diagonal
    
    if (Math.abs(updatedButtonX - currentButtonX) != Math.abs(updatedButtonY - currentButtonY)) {
      buttonReset();
      return false;
    }

    // Checks if the there are no pieces in the way on the following diagonals
    
    if (updatedButtonX > currentButtonX) {
      if (updatedButtonY > currentButtonY) {

        // North east diagonal
        
        for (int i = 1; i < updatedButtonY - currentButtonY; i++) {
          if (!chessBoard[currentButtonY + i][currentButtonX + i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
      else {

        // South east diagonal
        
        for (int i = 1; i < Math.abs(updatedButtonX - currentButtonX); i++) {
          if (!chessBoard[currentButtonY - i][currentButtonX + i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
    }
    else {
      if (updatedButtonY > currentButtonY) {

        // North west diagonal
        
        for (int i = 1; i < Math.abs(updatedButtonY - currentButtonY); i++) {
          if (!chessBoard[currentButtonY + i][currentButtonX - i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
      else {

        // South west diagonal
        
        for (int i = 1; i < currentButtonX - updatedButtonX; i++) {
          if (!chessBoard[currentButtonY - i][currentButtonX - i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
    }
    return true;
    
  }

  /**
   * Returns true if the king movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  private static boolean kingMovement() {

    // If the king is in the same position return false
    
    if (currentButtonX == updatedButtonX && currentButtonY == updatedButtonY) {
      buttonReset();
      return false;
    }

    // If the king moves by only one square and that square is empty, return true
    
    if (Math.abs(updatedButtonY - currentButtonY) <= 1 && Math.abs(updatedButtonX - currentButtonX) <= 1) {
      if (chessBoard[updatedButtonY][updatedButtonX].equals("□") || chessPieceColor(chessBoard[updatedButtonY][updatedButtonX]) != chessPieceColor(chessBoard[currentButtonY][currentButtonX])) {
        return true;
      }

      // Otherwise return false
        
      else {
        buttonReset();
        return false;
      }
    }
    else {
      
      // Castling

      // Makes tempChessBoard to check for whether or not the castling action is possible
      
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          tempChessBoard[i][j] = chessBoard[i][j];
        }
      }

      tempChessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
      tempChessBoard[currentButtonY][currentButtonX] = "□";

      whiteMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"white");
      blackMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"black");

      inCheck = false;
      
      // Checks whether or not the king has already moved or not
      
      if (chessBoardPieces.get(chessPieceIndex).alreadyMoved == true) {
        buttonReset();
        return false;
      }
      else {
        
        // Checks fo castling to the left 
        
        if (updatedButtonX == 2) {
          for (int i = 0; i < chessBoardPieces.size(); i++) {
            
            if (chessBoardPieces.get(i).emoji == "♜" && chessBoardPieces.get(chessPieceIndex).colour == "black" && updatedButtonY == 0) {
              if (chessBoardPieces.get(i).xPos == 0 && chessBoardPieces.get(i).yPos == 0 && chessBoardPieces.get(i).alreadyMoved == false) {
                
                // Check if any square in between the king and rook is being attacked
                
                for (int a = 2; a < 5; a++){
                  if (whiteMoves[0][0 + a].equals("🟦")) {
                    buttonReset();
                    return false;
                  }
                }
                chessBoard[0][0] = "□";
                chessBoard[0][3] = "♜";
                chessBoardPieces.get(i).xPos = 3;
                chessBoardPieces.get(i).alreadyMoved = true;
                return true;
              }
            }
            if (chessBoardPieces.get(i).emoji == "♖" && chessBoardPieces.get(chessPieceIndex).colour == "white" && updatedButtonY == 7) {
              if (chessBoardPieces.get(i).xPos == 0 && chessBoardPieces.get(i).yPos == 7 && chessBoardPieces.get(i).alreadyMoved == false) {
                
                // Check if any square in between the king and rook is being attacked
                
                for (int a = 2; a < 5; a++){
                  if (blackMoves[7][0 + a].equals("🟥")) {
                    buttonReset();
                    return false;
                  }
                }
                chessBoard[7][0] = "□";
                chessBoard[7][3] = "♖";
                chessBoardPieces.get(i).xPos = 3;
                chessBoardPieces.get(i).alreadyMoved = true;
                return true;
              }
            }
            
          }
        }
        
        // Checks for castling to the right
        
        if (updatedButtonX == 6) {

          for (int i = 0; i < chessBoardPieces.size(); i++) {            
            if (chessBoardPieces.get(i).emoji == "♜" && chessBoardPieces.get(chessPieceIndex).colour == "black" && updatedButtonY == 0) {
              if (chessBoardPieces.get(i).xPos == 7 && chessBoardPieces.get(i).yPos == 0 && chessBoardPieces.get(i).alreadyMoved == false) {
                
                // Check if any square in between the king and rook is being attacked
                
                for (int a = 1; a < 4; a++){
                  if (whiteMoves[0][7 - a].equals("🟦")) {
                    buttonReset();
                    return false;
                  }
                }
                chessBoard[0][7] = "□";
                chessBoard[0][5] = "♜";
                chessBoardPieces.get(i).xPos = 5;
                chessBoardPieces.get(i).alreadyMoved = true;
                return true;
              }
            }
            
            if (chessBoardPieces.get(i).emoji == "♖" && chessBoardPieces.get(chessPieceIndex).colour == "white" && updatedButtonY == 7) {
              if (chessBoardPieces.get(i).xPos == 7 && chessBoardPieces.get(i).yPos == 7 && chessBoardPieces.get(i).alreadyMoved == false) {
                
                // Check if any square in between the king and rook is being attacked
                
                for (int a = 1; a < 4; a++){
                  if (blackMoves[7][7 - a].equals("🟥")) {
                    buttonReset();
                    return false;
                  }
                }
                chessBoard[7][7] = "□";
                chessBoard[7][5] = "♖";
                chessBoardPieces.get(i).xPos = 5;
                chessBoardPieces.get(i).alreadyMoved = true;
                return true;
              }
            }
          }
        }
                    
      }
      buttonReset();
      return false;
    }
  }

  /**
   * Returns true if the queen movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  private static boolean queenMovement() {

    // If the queen movement in not on one of the possible lines
    
    if ((Math.abs(updatedButtonX - currentButtonX) != Math.abs(updatedButtonY - currentButtonY)) && ((currentButtonX == updatedButtonX && currentButtonY == updatedButtonY) || (currentButtonX != updatedButtonX && currentButtonY != updatedButtonY))) {
      buttonReset();
      return false;
    }

    // Checks if there is anything in the way of the movement
    
    if (updatedButtonX == currentButtonX || updatedButtonY == currentButtonY) {
      if (currentButtonX == updatedButtonX) {
        if (currentButtonY < updatedButtonY) {

          // Above the queen
          
          for (int i = 1; i < updatedButtonY - currentButtonY; i++) {
            if (!chessBoard[currentButtonY + i][currentButtonX].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
        else {

          // Below the queen
          
          for (int i = 1; i < currentButtonY - updatedButtonY; i++) {
            if (!chessBoard[currentButtonY - i][currentButtonX].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
      }
      if (currentButtonX < updatedButtonX) {

        // To the right of the queen
        
        for (int i = 1; i < updatedButtonX - currentButtonX; i++) {
          if (!chessBoard[currentButtonY][currentButtonX + i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
      else {

        // To the left of the queen
        
        for (int i = 1; i < currentButtonX - updatedButtonX; i++) {
          if (!chessBoard[currentButtonY][currentButtonX - i].equals("□")) {
            buttonReset();
            return false;
          }
        }
      }
    }
    else {
      if (updatedButtonX > currentButtonX) {
        if (updatedButtonY > currentButtonY) {

          // The the top right of the queen
          
          for (int i = 1; i < updatedButtonY - currentButtonY; i++) {
            if (!chessBoard[currentButtonY + i][currentButtonX + i].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
        else {

          // To the bottom right of the queen
          
          for (int i = 1; i < Math.abs(updatedButtonX - currentButtonX); i++) {
            if (!chessBoard[currentButtonY - i][currentButtonX + i].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
      }
      else {

        // To the top left of the queen
        
        if (updatedButtonY > currentButtonY) {
          for (int i = 1; i < Math.abs(updatedButtonY - currentButtonY); i++) {
            if (!chessBoard[currentButtonY + i][currentButtonX - i].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
        else {

          // To the bottom left of the queen
          
          for (int i = 1; i < currentButtonX - updatedButtonX; i++) {
            if (!chessBoard[currentButtonY - i][currentButtonX - i].equals("□")) {
              buttonReset();
              return false;
            }
          }
        }
      }
    }

    // If there is nothing in the way return false

    return true;
  }

  /**
   * Returns true if the white pawn movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  public static boolean whitePawnMovement() {
    
    //White pawn movement
    
    if (chessBoard[currentButtonX] == chessBoard[updatedButtonX]) {
      if (currentButtonY == 6) {

        // Makes sure your staying in the right row and not moving backwards
        
        if(currentButtonY - updatedButtonY > 2 || currentButtonY - updatedButtonY <= 0){
          buttonReset();
          return false;
        }

        // If the pawn is moved 2 tiles upwards
          
        else if (currentButtonY - updatedButtonY == 2){
          if (!chessBoard[currentButtonY - 1][currentButtonX].equals("□") || !chessBoard[currentButtonY - 2][currentButtonX].equals("□")){
            buttonReset();
            return false;
            }
          chessBoardPieces.get(chessPieceIndex).enPassant = true;
        }

        // If the pawn is moved a single tile upwards
          
        else if (currentButtonY - updatedButtonY == 1 && !chessBoard[currentButtonY - 1][currentButtonX].equals("□")){
          buttonReset();
          return false;
        }
      }

      // If there is something in the way, or the pawn can't make the move return false
        
      else if (currentButtonY != 6){
        if (currentButtonY - updatedButtonY > 1 || currentButtonY - updatedButtonY <= 0){
          buttonReset();
          return false;
        }
        else if (!chessBoard[currentButtonY - 1][currentButtonX].equals("□")){
          buttonReset();
          return false;
        }
      }
    }
      
    // Checks if new updated square is in the same X location as the old square
      
    else if(chessBoard[currentButtonX] != chessBoard[updatedButtonX]){
      
      // Checks if diagonal movement is too far
      
      if (currentButtonX - updatedButtonX > 1 || currentButtonY - updatedButtonY > 1 || updatedButtonX - currentButtonX > 1 || currentButtonY - updatedButtonY <= 0){
        buttonReset();
        return false; 
      }
      
        // Checks for empty squares
        
      else if (chessBoard[updatedButtonY][updatedButtonX].equals("□")){
        
        // Checks if there is a pawn "below" the updated square
        
        if (chessBoard[updatedButtonY + 1][updatedButtonX].equals("♟")){
          
          // Checks if the pawn has enPassant enabled. If it is not enabled, we restrict the movement
          
          for (int k = 0; k < chessBoardPieces.size(); k++) {
            if (chessBoardPieces.get(k).xPos == updatedButtonX && chessBoardPieces.get(k).yPos == updatedButtonY + 1) {
              if (chessBoardPieces.get(k).enPassant == false){
                buttonReset();
                return false;
              }
              else{
                for (int i = 0; i < 8; i++) {
                  for (int j = 0; j < 8; j++) {
                    tempChessBoard[i][j] = chessBoard[i][j];
                  }
                }

                // Creates a temp chess board for testing
          
                tempChessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
                tempChessBoard[currentButtonY][currentButtonX] = "□";
                tempChessBoard[updatedButtonY + 1][updatedButtonX] = "□";
          
                whiteMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"white");
                blackMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"black");
          
                inCheck = false;
          
                // Is the move is possible (as in your not putting yourself in check)
                
                for (int i = 0; i < 8; i++) {
                  for (int j = 0; j < 8; j++) {
                    if (tempChessBoard[i][j].equals("♔") && blackMoves[i][j].equals("🟥") && playerTurn == "white") {
                      buttonReset();
                      return false;
                    }
                    if (tempChessBoard[i][j].equals("♚") && whiteMoves[i][j].equals("🟦") && playerTurn == "black") {
                      buttonReset();
                      return false;
                    }
                  }
                }
                
                // Updates the board
                
                chessBoard[updatedButtonY + 1][updatedButtonX] = "□";
                whitePieceTaken.add("♟");
                chessBoardPieces.remove(k);
              }
            }
          }
        }
        
        // If there is no pawn "below" the updated square, restrict the movement
          
        else{
          buttonReset();
          return false;
        }
      }
    }
    else{
      buttonReset();
      return false;
    }
    
    // Checks if the pawn is going to promote
    
    if (updatedButtonY == 0){
      if (checkDetection() == false){
        buttonReset();
        return false;
      }

      // Saves the button values
      
      promoUpdatedButtonX = updatedButtonX;
      promoUpdatedButtonY = updatedButtonY;
      promoCurrentButtonX = currentButtonX;
      promoCurrentButtonY = currentButtonY;
      promote = true;
    }
    return true;
  }

  /**
   * Returns true if the black pawn movement is possible otherwise returns false
   *
   * @param void
   * @return boolean
   */
  public static boolean blackPawnMovement() {
    
    // Black Pawn
    
    if (chessBoard[currentButtonX] == chessBoard[updatedButtonX]) {
      if (currentButtonY == 1){

        // If the pawn wasn't moved too far or backwards
        
        if(updatedButtonY - currentButtonY > 2 || updatedButtonY - currentButtonY <= 0){
          buttonReset();
          return false;
        }

        // If the pawn was moved two tiles up
          
        else if(updatedButtonY - currentButtonY == 2){ 
          if (!chessBoard[currentButtonY + 1][currentButtonX].equals("□") || !chessBoard[currentButtonY + 2][currentButtonX].equals("□")){
            buttonReset();
            return false;
          }
          chessBoardPieces.get(chessPieceIndex).enPassant = true;
        }

        // If the pawn was moved a single tile up
          
        else if(updatedButtonY - currentButtonY == 1){
          if(!chessBoard[updatedButtonY][updatedButtonX].equals("□")){
            buttonReset();
            return false;
          }
        }
      }
        
      // Case where pawn is not in starting position
        
      else if (currentButtonY != 1) {
        if (updatedButtonY - currentButtonY > 1 || updatedButtonY - currentButtonY <= 0){
          buttonReset();
          return false;
        }
        else if(!chessBoard[currentButtonY + 1][currentButtonX].equals("□")) {
          buttonReset();
          return false;
        }
      }
    }
    else if(chessBoard[currentButtonX] != chessBoard[updatedButtonX]) {
      
      //Checks if diagonals are possible, and checks whether or not those diagonals have an enemy piece. This should also prevent the piece from moving sideways
      
      if(updatedButtonX - currentButtonX > 1 || updatedButtonY - currentButtonY > 1 || currentButtonX - updatedButtonX > 1 || updatedButtonY - currentButtonY <= 0){
        buttonReset();
        return false;
      }
      
      //Checks if the updated square is a blank square
        
      else if (chessBoard[updatedButtonY][updatedButtonX].equals("□")) {
        
        //Checks if there is a black pawn "above" the updated square.
        
        if (chessBoard[updatedButtonY - 1][updatedButtonX].equals("♙")) {
          
          //Checks if the black pawn has enPassant set to true. If it is not set to true, the pawn can't en passant
          
          for (int k = 0; k < chessBoardPieces.size(); k++) {
            if (chessBoardPieces.get(k).xPos == updatedButtonX && chessBoardPieces.get(k).yPos == updatedButtonY - 1) {
              if (chessBoardPieces.get(k).enPassant == false){
                buttonReset();
                return false;
              }
              else{
                for (int i = 0; i < 8; i++) {
                  for (int j = 0; j < 8; j++) {
                    tempChessBoard[i][j] = chessBoard[i][j];
                  }
                }

                // Creates a temp board for testing
          
                tempChessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
                tempChessBoard[currentButtonY][currentButtonX] = "□";
                tempChessBoard[updatedButtonY - 1][updatedButtonX] = "□";
          
                whiteMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"white");
                blackMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"black");
          
                inCheck = false;
          
                // Is the move is possible (as in your not putting yourself in check)
                
                for (int i = 0; i < 8; i++) {
                  for (int j = 0; j < 8; j++) {
                    if (tempChessBoard[i][j].equals("♔") && blackMoves[i][j].equals("🟥") && playerTurn == "white") {
                      buttonReset();
                      return false;
                    }
                    if (tempChessBoard[i][j].equals("♚") && whiteMoves[i][j].equals("🟦") && playerTurn == "black") {
                      buttonReset();
                      return false;
                    }
                  }
                }
                
                // Updates the board
                
                chessBoard[updatedButtonY - 1][updatedButtonX] = "□";
                blackPieceTaken.add("♙");
                chessBoardPieces.remove(k);
              }
            }
          }
        }
          
        // If the pawn "above" the updated square is not a pawn, restrict the pawn movement
          
        else{
          buttonReset();
          return false;
        }
      }
    }
    else{
      buttonReset();
      return false;
    }
    
    // Checks if the pawn reaches the promotion square
    
    if (updatedButtonY == 7) {
      if (checkDetection() == false){
        buttonReset();
        return false;
      }

      // Saves the old button values
      
      promoUpdatedButtonX = updatedButtonX;
      promoUpdatedButtonY = updatedButtonY;
      promoCurrentButtonX = currentButtonX;
      promoCurrentButtonY = currentButtonY;
      promote = true;
    }
    return true;
  }

  /**
   * If one of the chess board buttons are pressed
   *
   * @param JButton button
   * @return void
   */
  private static void buttonPressed(JButton button) {

    // If the game is over, do nothing
    
    if (gameOver == true) {
      return;
    }

    // Find what button it is
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (chessBoardButton[i][j] == button) {
          if (currentButtonX == -1 && currentButtonY == -1) {

            // If the button is empty do nothing
            
            if (chessBoard[j][i].equals("□") || !chessPieceColor(chessBoard[j][i]).equals(playerTurn)) {
              buttonReset();
            }
            else {

              // If the button is full, save the coordinates
              
              currentButtonX = i;
              currentButtonY = j;
              chessBoardButton[i][j].setBackground(new Color(51,204,255));
            }
          }        
          else if (currentButtonX == i && currentButtonY == j) {
            buttonReset();
          }

          // Save the updated button coordinates
            
          else if (updatedButtonX == -1 && updatedButtonY == -1){
            updatedButtonX = i;
            updatedButtonY = j;
          }

          // Otherwise reset the button
            
          else {
            buttonReset();
          }
        }
      }
    }

    // If we have both updated and current button coordinates
    
    if (currentButtonX != -1 && currentButtonY != -1 && updatedButtonX != -1 && updatedButtonY != -1){

      // Gets the current piece
      
      String currentPiece = chessBoard[currentButtonY][currentButtonX];

      // Gets the index of the chess piece in the chess piece list
      
      for (int k = 0; k < chessBoardPieces.size(); k++) {
        if (chessBoardPieces.get(k).xPos == currentButtonX && chessBoardPieces.get(k).yPos == currentButtonY) {
          chessPieceIndex = k;
        }
      }

      // Checks if it is the right players turn
      
      if (playerTurn == "white" && chessPieceColor(currentPiece) == "black") {
        buttonReset();
        return;
      }
      if (playerTurn == "black" && chessPieceColor(currentPiece) == "white") {
        buttonReset();
        return;
      }

      // Checks if the player is taking there own chess piece

      if (chessPieceColor(currentPiece) == chessPieceColor(chessBoard[updatedButtonY][updatedButtonX])) {
        buttonReset();
        return;
      }

      // Checks for promotion
      
      if (promote){
        promotionFrame();
        buttonReset();
        return;
      }

      // Checks if rook movement is possible
      
      if (currentPiece.equals("♜") || currentPiece.equals("♖")){
        if (rookMovement() == false) {
          return;
        }
      }

      // Checks if knight movement is possible
        
      else if (currentPiece.equals("♞") || currentPiece.equals("♘")) {
        if (knightMovement() == false) {
          return;
        }
      }

      // Checks if bishop movement is possible
        
      else if (currentPiece.equals("♝") || currentPiece.equals("♗")) {
        if (bishopMovement() == false) {
          return;
        }
      }

      // Checks if queen movement is possible
        
      else if (currentPiece.equals("♛") || currentPiece.equals("♕")){
        if (queenMovement() == false) {
          return;
        }
      }

      // Checks if king movement is possible
        
      else if (currentPiece.equals("♚") || currentPiece.equals("♔")){
        if (kingMovement() == false) {
          return;
        }
      }

      // Checks if black pawn movement is possible
        
      else if (currentPiece.equals("♟")){
        if (blackPawnMovement() == false){
          return;
        }
      }

      // Checks if white pawn movement is possible
        
      else if (currentPiece.equals("♙")){
        if (whitePawnMovement() == false){
          return;
        }
      }

      // If the player moved into check, return
      
      if (checkDetection() == false) {
        return;
      }

      // Checks if the opponent is in check mate
      
      checkMateDetection();
      
      // Sets the piece's alreadyMoved value to true
      
      chessBoardPieces.get(chessPieceIndex).alreadyMoved = true;

      // Turns the emoji green and red based on the player turn
      
      if (playerTurn == "white") {
        player1emoji.setForeground(Color.red);
        player2emoji.setForeground(Color.green);
      }
      else {
        player1emoji.setForeground(Color.green);
        player2emoji.setForeground(Color.red);
      }

      // Adds the move to the move list
      
      movesLogList.add(chessBoardPieces.get(chessPieceIndex).emoji + (char)(updatedButtonX + 97) + (8 - updatedButtonY));

      // Turns the moves list into a string
      
      String movesString = "";
      for (int i = 0; i < movesLogList.size(); i++) {
        movesString = movesString + movesLogList.get(i);
        if (i % 2 == 0) {
          movesString = movesString + "\t";
        }
        else {
          movesString = movesString + "\n";
        }
      }

      // Adds the moves string to the screen
      
      movesLogArea.setText(movesString);
      
      // En Passant reset
      
      if (playerTurn == "white"){
        for (int k = 0; k < chessBoardPieces.size(); k++) {
          if (chessBoardPieces.get(k).colour == "black") {
            chessBoardPieces.get(k).enPassant = false;
          }
        }
      }
      else if (playerTurn == "black"){
        for (int k = 0; k < chessBoardPieces.size(); k++) {
          if (chessBoardPieces.get(k).colour == "white") {
            chessBoardPieces.get(k).enPassant = false;
          }
        }
      }
      
      // Deletes the taken chess piece
      for (int k = 0; k < chessBoardPieces.size(); k++) {
        if (chessBoardPieces.get(k).xPos == updatedButtonX && chessBoardPieces.get(k).yPos == updatedButtonY) {
          if (chessBoardPieces.get(k).colour == "black") {
            whitePieceTaken.add(chessBoardPieces.get(k).emoji);
          }
          else {
            blackPieceTaken.add(chessBoardPieces.get(k).emoji);
          }
          chessBoardPieces.remove(k);
        }
      }
      
      // Better pieceMaker(), moves the X and Y location of the classes
      for (int k = 0; k < chessBoardPieces.size(); k++) {
        if (chessBoardPieces.get(k).xPos == currentButtonX && chessBoardPieces.get(k).yPos == currentButtonY) {
          chessBoardPieces.get(k).xPos = updatedButtonX;
          chessBoardPieces.get(k).yPos = updatedButtonY;
        }
      }

      // Sorts the taken pieces
      
      blackPieceTaken.sort(Comparator.naturalOrder());
      whitePieceTaken.sort(Comparator.naturalOrder());

      // Resets the taken pieces strings
      
      String newTextBlack = "";
      String newTextWhite = "";
      String newTextBlack2 = "";
      String newTextWhite2 = "";

      // Divides the black taken pieces into two different strings
      
      for (int i = 0; i < blackPieceTaken.size(); i++) {
        if (i < 8) {
          newTextBlack = newTextBlack + blackPieceTaken.get(i);
        }
        else {
          newTextBlack2 = newTextBlack2 + blackPieceTaken.get(i);
        }
      }

      // Divides the white taken pieces into two different strings
      
      for (int i = 0; i < whitePieceTaken.size(); i++) {
        if (i < 8) {
          newTextWhite = newTextWhite + whitePieceTaken.get(i);
        }
        else {
          newTextWhite2 = newTextWhite2 + whitePieceTaken.get(i);
        }
      }

      // Creates the taken pieces labels

      blackPieceLabel.setText("<html>" + newTextBlack + "</html>");
      whitePieceLabel.setText("<html>" + newTextWhite + "</html>");
      blackPieceLabel2.setText("<html>" + newTextBlack2 + "</html>");
      whitePieceLabel2.setText("<html>" + newTextWhite2 + "</html>");

      // Moves the piece on the chess board
      
      chessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
      chessBoard[currentButtonY][currentButtonX] = "□";
      
      // Switches player turn
      
      if (playerTurn == "white") {
        playerTurn = "black";
      }
      else {
        playerTurn = "white";
      }      

      // Resets the buttons
      
      buttonMaker();

      // Formating label
      
      JLabel formating = new JLabel("");
      formating.setBounds(0, 0, 0, 0);
      frame.add(formating);

      // Shows the frame
      
      frame.show();
      
      // Shows the promotion frame if promotion boolean is true
      
      if (promote){
        promotionFrame();
        buttonReset();
      }
      else{
        buttonReset();
      }

      if (gameOver == true) {
        return;
      }

      // Resets the draw buttons and variables
      
      whiteDraw = false;
      drawWhite.setEnabled(true);
      blackDraw = false;
      drawBlack.setEnabled(true);

      // Printing out the chess board for debugging

      /*
      System.out.println("-----------------");
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          System.out.print(chessBoard[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.println("-----------------");
      */

      // Number of every piece

      int numPawnsWhite = 0;
      int numKnightsWhite = 0;
      int numBishopsWhite = 0;
      int numRooksWhite = 0;
      int numQueensWhite = 0;

      int numPawnsBlack = 0;
      int numKnightsBlack = 0;
      int numBishopsBlack = 0;
      int numRooksBlack = 0;
      int numQueensBlack = 0;

      // Counts the number of every single piece
      
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (chessBoard[i][j].equals("♜")) {
            numRooksBlack += 1;
          }
          else if (chessBoard[i][j].equals("♞")) {
            numKnightsBlack += 1;
          }
          else if (chessBoard[i][j].equals("♝")) {
            numBishopsBlack += 1;
          }
          else if (chessBoard[i][j].equals("♛")) {
            numQueensBlack += 1;
          }
          else if (chessBoard[i][j].equals("♟")) {
            numPawnsBlack += 1;
          }
          if (chessBoard[i][j].equals("♖")) {
            numRooksWhite += 1;
          }
          else if (chessBoard[i][j].equals("♘")) {
            numKnightsWhite += 1;
          }
          else if (chessBoard[i][j].equals("♗")) {
            numBishopsWhite += 1;
          }
          else if (chessBoard[i][j].equals("♕")) {
            numQueensWhite += 1;
          }
          else if (chessBoard[i][j].equals("♟")) {
            numPawnsWhite += 1;
          }
        }
      }

      // Sees if white or black has sufficient mating material
      
      boolean insufficientWhite = false;
      boolean insufficientBlack = false;
      if (numPawnsWhite == 0 && numRooksWhite == 0 && numQueensWhite == 0) {
        if (numBishopsWhite >= 2 || (numBishopsWhite >= 1 && numKnightsWhite >= 1)) {
          insufficientWhite = false;
        }
        else {
          insufficientWhite = true;
        }
      }
      if (numPawnsBlack == 0 && numRooksBlack == 0 && numQueensBlack == 0) {
        if (numBishopsBlack >= 2 || (numBishopsBlack >= 1 && numKnightsBlack >= 1)) {
          insufficientBlack = false;
        }
        else {
          insufficientBlack = true;
        }
      }

      // If both teams don't have sufficient mating material then it is a draw

      if (insufficientBlack == true && insufficientWhite == true) {
        victoryFrame("Draw","insufficient mating material");
      }

      // Checks for stalemate
      
      boolean possibleMoves = false;

      // Given all the chess board pieces

      for (int i = 0; i < chessBoardPieces.size(); i++) {

        // Narrowing the chess board pieces to the next player's pieces
        
        if (chessBoardPieces.get(i).colour.equals(playerTurn)) {

          // Given all the possible moves

          chessBoardPieces.get(i).possibleMoves(tempChessBoard);

          // Given those moves
          
          for (int moves = 0; moves < chessBoardPieces.get(i).movesList.size(); moves++) {

            // Create a temp chess board for testing
            
            for (int a = 0; a < 8; a++) {
              for (int b = 0; b < 8; b++) {
                tempChessBoard[a][b] = chessBoard[a][b];
              }
            }

            // Make the move on the temp chess board
            
            tempChessBoard[chessBoardPieces.get(i).yPos][chessBoardPieces.get(i).xPos] = "□";
            tempChessBoard[chessBoardPieces.get(i).movesList.get(moves)[0]][chessBoardPieces.get(i).movesList.get(moves)[1]] = chessBoardPieces.get(i).emoji;

            // Get the kings location
            
            int kingX = -1;
            int kingY = -1;
            for (int a = 0; a < 8; a++) {
              for (int b = 0; b < 8; b++) {
                if (playerTurn.equals(chessPieceColor(tempChessBoard[a][b])) && (tempChessBoard[a][b].equals("♔") || tempChessBoard[a][b].equals("♚"))) {
                  kingX = a;
                  kingY = b;
                }
              }
            }

            // Get a check board

            String[][] tempCheckBoard = new String[8][8];

            // If the black king is not in check, then there are possible moves
            
            if (playerTurn.equals("black") && kingX != -1 && kingY != -1) {
              tempCheckBoard = CheckMatrix.checkMatrixFunction(tempChessBoard,"white");
              
              if (tempCheckBoard[kingX][kingY].equals("⬜")) {
                possibleMoves = true;
              }
            }

            // If the white king is not in check, then there are possible moves
            
            if (playerTurn.equals("white") && kingX != -1 && kingY != -1) {
              tempCheckBoard = CheckMatrix.checkMatrixFunction(tempChessBoard,"black");
              
              if (tempCheckBoard[kingX][kingY].equals("⬛")) {
                possibleMoves = true;
              }
            }
          }
        }
      }

    // If there are no possible moves, then call it a draw

    if (possibleMoves == false) {
      victoryFrame("Draw","stalemate");
    }

    // 3 move repetition detection
      
    if (movesLogList.size() >= 12) {

      // Given the last 4 moves
      
      boolean moveRepetition = true;
      String lastMove1 = movesLogList.get(movesLogList.size() - 1);
      String lastMove2 = movesLogList.get(movesLogList.size() - 2);
      String lastMove3 = movesLogList.get(movesLogList.size() - 3);
      String lastMove4 = movesLogList.get(movesLogList.size() - 4);

      // If all of the last 4 moves are repeats
      
      for (int i = 0; i < 3; i++) {
        if (!lastMove1.equals(movesLogList.get(movesLogList.size() - (i * 4) - 1)) || !lastMove2.equals(movesLogList.get(movesLogList.size() - (i * 4) - 2)) || !lastMove3.equals(movesLogList.get(movesLogList.size() - (i * 4) - 3)) || !lastMove4.equals(movesLogList.get(movesLogList.size() - (i * 4) - 4))) {
          moveRepetition = false;
        }
      }

      // Draw the game by 3 move repetition
      
      if (moveRepetition == true) {
        victoryFrame("Draw","3 move repetition");
      }
    }

    // End of the button pressed function
      
    return;
    }
  } 

  /**
   * Checks if someone is in check
   *
   * @param void
   * @return boolean
   */
  public static boolean checkDetection() {

    // Creates a temp chess board for testing
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        tempChessBoard[i][j] = chessBoard[i][j];
      }
    }

    // Moves the piece on the temp chess board
    
    if (promote == false) {
      tempChessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
      tempChessBoard[currentButtonY][currentButtonX] = "□";
    }

    // Generates white and black check matrixs given the move
    
    whiteMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"white");
    blackMoves = CheckMatrix.checkMatrixFunction(tempChessBoard,"black");
  
    inCheck = false;
  
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        // If white is in check and it is white's move, then it is a illegal move
        
        if (tempChessBoard[i][j].equals("♔") && blackMoves[i][j].equals("🟥") && playerTurn == "white") {
          buttonReset();
          return false;
        }

        // If black is in check and it is black's move, then it is a illegal move
        
        if (tempChessBoard[i][j].equals("♚") && whiteMoves[i][j].equals("🟦") && playerTurn == "black") {
          buttonReset();
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checkmate detection
   *
   * @param void
   * @return void
   */
  public static void checkMateDetection() {

    // Generates a temp board for testing
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        tempChessBoard[i][j] = chessBoard[i][j];
      }
    }

    // Makes the move on the temp chess board
    
    if (promote == false) {
      tempChessBoard[updatedButtonY][updatedButtonX] = chessBoard[currentButtonY][currentButtonX];
      tempChessBoard[currentButtonY][currentButtonX] = "□";
    }
    
    inCheck = false;
    
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        // If the white king is in check
        
        if (tempChessBoard[i][j].equals("♔") && blackMoves[i][j].equals("🟥") && playerTurn == "black") {

          // Sets white check mate by default
          
          checkMateWhite = true;

          // Given all the white chess pieces

          for (int k = 0; k < chessBoardPieces.size(); k++) {
            if (chessBoardPieces.get(k).colour == "white") {
              chessBoardPieces.get(k).possibleMoves(tempChessBoard);

              // Given all those chess piece's possible moves
              
              for (int moves = 0; moves < chessBoardPieces.get(k).movesList.size(); moves++) {

                // If the piece is not the piece that was moved
                
                if (chessBoardPieces.get(k).xPos == updatedButtonX && chessBoardPieces.get(k).yPos == updatedButtonY) {
                  continue;
                }

                // Creates a another temp chess board for testing
                
                String[][] tempCheckBoard = new String[8][8];
                for (int a = 0; a < 8; a++) {
                  for (int b = 0; b < 8; b++) {
                    tempCheckBoard[a][b] = tempChessBoard[a][b];
                  }
                }

                // Tries every possible move
                
                tempCheckBoard[chessBoardPieces.get(k).yPos][chessBoardPieces.get(k).xPos] = "□";
                tempCheckBoard[chessBoardPieces.get(k).movesList.get(moves)[0]][chessBoardPieces.get(k).movesList.get(moves)[1]] = chessBoardPieces.get(k).emoji;

                // Gets the king's location
                
                int kingX = -1;
                int kingY = -1;
                for (int a = 0; a < 8; a++) {
                  for (int b = 0; b < 8; b++) {
                    if (tempCheckBoard[a][b] == "♔") {
                      kingX = a;
                      kingY = b;
                    }
                  }
                }

                // If the king is not in check, then the position is not check mate
                
                if (kingX != -1 && kingY != -1) {
                  tempCheckBoard = CheckMatrix.checkMatrixFunction(tempCheckBoard,"black");
                  if (tempCheckBoard[kingX][kingY].equals("⬛")) {
                    checkMateWhite = false;
                  }
                }
              }
            }
          }
          if (checkMateWhite == true) {
            victoryFrame("Black","checkmate");
          }
          redSquareX = i;
          redSquareY = j;
          inCheck = true;
        }

        // Same thing as above but for black (line comments would be the same)
        
        if (tempChessBoard[i][j].equals("♚") && whiteMoves[i][j].equals("🟦") && playerTurn == "white") {
          checkMateBlack = true;
          for (int k = 0; k < chessBoardPieces.size(); k++) {
            if (chessBoardPieces.get(k).colour == "black") {
              chessBoardPieces.get(k).possibleMoves(tempChessBoard);
              for (int moves = 0; moves < chessBoardPieces.get(k).movesList.size(); moves++) {
                if (chessBoardPieces.get(k).xPos == updatedButtonX && chessBoardPieces.get(k).yPos == updatedButtonY) {
                  continue;
                }
                String[][] tempCheckBoard = new String[8][8];
                for (int a = 0; a < 8; a++) {
                  for (int b = 0; b < 8; b++) {
                    tempCheckBoard[a][b] = tempChessBoard[a][b];
                  }
                }
                tempCheckBoard[chessBoardPieces.get(k).yPos][chessBoardPieces.get(k).xPos] = "□";
                tempCheckBoard[chessBoardPieces.get(k).movesList.get(moves)[0]][chessBoardPieces.get(k).movesList.get(moves)[1]] = chessBoardPieces.get(k).emoji;
                int kingX = -1;
                int kingY = -1;
                for (int a = 0; a < 8; a++) {
                  for (int b = 0; b < 8; b++) {
                    if (tempCheckBoard[a][b] == "♚") {
                      kingX = a;
                      kingY = b;
                    }
                  }
                }
                if (kingX != -1 && kingY != -1) {
                  tempCheckBoard = CheckMatrix.checkMatrixFunction(tempCheckBoard,"white");
                  if (tempCheckBoard[kingX][kingY].equals("⬜")) {
                    checkMateBlack = false;
                  }
                }
              }
            }
          }
          if (checkMateBlack == true) {
            victoryFrame("White","checkmate");
          }
          redSquareX = i;
          redSquareY = j;
          inCheck = true;
        }
      }
    }
  
    if (inCheck == false) {
      redSquareX = -1;
      redSquareY = -1;
    }
  }
}
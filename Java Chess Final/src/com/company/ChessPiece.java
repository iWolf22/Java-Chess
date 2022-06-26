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

public class ChessPiece {

  // All the information our chess piece will need to have
  
  int xPos;
  int yPos;
  int id;
  String emoji;
  String colour;
  String imageName;
  ImageIcon chessSprite;
  boolean alreadyMoved;
  boolean enPassant;
  ArrayList<Integer[]> movesList = new ArrayList<Integer[]>();

  // Chess piece constructor function
  
  public ChessPiece(int xPos, int yPos, String colour, String imageName, String emoji, boolean alreadyMoved, boolean enPassant) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.colour = colour;
    this.imageName = imageName;
    this.emoji = emoji;
    this.alreadyMoved = alreadyMoved;
    this.enPassant = enPassant;     
    this.chessSprite = new ImageIcon(String.format("images/%s.png", imageName));
    
  }

  /**
   * Creates a list of all the possible moves for the chess piece
   *
   * @param String[][] chessBoard
   * @return void
   */
  public void possibleMoves(String[][] chessBoard) {

    // Clears all the old moves
    
    this.movesList.clear();

    // Possible knight and king moves lists
    
    int[][] knightMoves = {{2,1},{1,2},{-2,1},{-1,2},{2,-1},{1,-2},{-2,-1},{-1,-2}};
    int[][] kingMoves = {{0,1},{1,1},{1,0},{0,-1},{-1,-1},{-1,0},{1,-1},{-1,1}};

    // If the piece is a black rook
    // Loop through all the rows and columns the rook can move to
    // If the rook can legally move to that location, add the coordinates to the possible moves list
    
    if (this.emoji.equals("♜")) {

      // Checking movement above the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i < 8) {
          if (chessBoard[this.yPos + i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the right of the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos + i < 8) {
          if (chessBoard[this.yPos][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement below the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i > -1) {
          if (chessBoard[this.yPos - i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the left of the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos - i > -1) {
          if (chessBoard[this.yPos][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a black knight
    // Loop through all the places the knight can move to given it's current location
    // If the knight can legally move to the location, add the coordinates to the possible moves list
      
    else if (this.emoji.equals("♞")){
      for (int i = 0; i < knightMoves.length; i++) {
        if (this.yPos + knightMoves[i][0] >= 0 && this.yPos + knightMoves[i][0] <= 7 && this.xPos + knightMoves[i][1] >= 0 && this.xPos + knightMoves[i][1] <= 7) {
          if (!chessPieceColor(chessBoard[this.yPos + knightMoves[i][0]][this.xPos + knightMoves[i][1]]).equals("black")) {
            Integer[] movesXY = {this.yPos + knightMoves[i][0],this.xPos + knightMoves[i][1]};
            this.movesList.add(movesXY);
          }
        }
      }
    }

    // If the piece is a black bishop
    // Loop through the 4 diagonals it can move to given its current location
    // If the bishop can legally move to the location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♝")) {

      // Checking movement to the top right of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos + i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the top left of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos + i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom right of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos - i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom left of bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos - i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a black queen
    // Loop through the 8 directional lines the queen can move on
    // If the queen can legally move to the location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♛")){

      // Checking movement to the top right of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos + i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the top left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos + i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom right of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos - i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos - i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement above the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i < 8) {
          if (chessBoard[this.yPos + i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos]).equals("white")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement the right of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos + i < 8) {
          if (chessBoard[this.yPos][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos + i]).equals("white")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement below the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i > -1) {
          if (chessBoard[this.yPos - i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos]).equals("white")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos - i > -1) {
          if (chessBoard[this.yPos][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos - i]).equals("white")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a black king
    // Loop through all of the possible moves it can make given it's current location
    // If the king can legally move to that location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♚")){
      for (int i = 0; i < kingMoves.length; i++) {
        if (this.yPos + kingMoves[i][0] >= 0 && this.yPos + kingMoves[i][0] <= 7 && this.xPos + kingMoves[i][1] >= 0 && this.xPos + kingMoves[i][1] <= 7) {
          if (!chessPieceColor(chessBoard[this.yPos + kingMoves[i][0]][this.xPos + kingMoves[i][1]]).equals("black")) {
            Integer[] movesXY = {this.yPos + kingMoves[i][0], this.xPos + kingMoves[i][1]};
            this.movesList.add(movesXY);
          }
        }
      }
    }

    // If the piece is a black pawn
      
    else if (this.emoji.equals("♟")){

      // If the pawn can move 1 tile up
      
      if (chessBoard[this.yPos + 1][this.xPos].equals("□")) {
        Integer[] movesXY = {this.yPos + 1, this.xPos};
        this.movesList.add(movesXY);
      }

      // If the pawn can move 2 tiles up
      
      if (this.alreadyMoved == false && chessBoard[this.yPos + 1][this.xPos].equals("□") && chessBoard[this.yPos + 2][this.xPos].equals("□")) {
        Integer[] movesXY = {this.yPos + 2, this.xPos};
        this.movesList.add(movesXY);
      }

      // If the pawn can eat a certain piece to the left and the right of it
      
      if (this.xPos + 1 <= 7) {
        if (chessPieceColor(chessBoard[this.yPos + 1][this.xPos + 1]).equals("white")) {
          Integer[] movesXY = {this.yPos + 1, this.xPos + 1};
          this.movesList.add(movesXY);
        }
      }
      if (this.xPos - 1 >= 0) {
        if (chessPieceColor(chessBoard[this.yPos + 1][this.xPos - 1]).equals("white")) {
          Integer[] movesXY = {this.yPos + 1, this.xPos - 1};
          this.movesList.add(movesXY);
        }
      }
    }

    // If the piece is a white rook
    // Loop through all the rows and columns the rook can move to
    // If the rook can legally move to that location, add the coordinates to the possible moves list
      
    else if (this.emoji.equals("♖")) {

      // Checking movement above the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i < 8) {
          if (chessBoard[this.yPos + i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the right of the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos + i < 8) {
          if (chessBoard[this.yPos][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement below the rook
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i > -1) {
          if (chessBoard[this.yPos - i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the left of the roof
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos - i > -1) {
          if (chessBoard[this.yPos][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a black knight
    // Loop through all the places the knight can move to given it's current location
    // If the knight can legally move to the location, add the coordinates to the possible moves list
      
    else if (this.emoji.equals("♘")){
      for (int i = 0; i < knightMoves.length; i++) {
        if (this.yPos + knightMoves[i][0] >= 0 && this.yPos + knightMoves[i][0] <= 7 && this.xPos + knightMoves[i][1] >= 0 && this.xPos + knightMoves[i][1] <= 7) {
          if (!chessPieceColor(chessBoard[this.yPos + knightMoves[i][0]][this.xPos + knightMoves[i][1]]).equals("white")) {
            Integer[] movesXY = {this.yPos + knightMoves[i][0],this.xPos + knightMoves[i][1]};
            this.movesList.add(movesXY);
          }
        }
      }
    }

    // If the piece is a white bishop
    // Loop through the 4 diagonals it can move to given its current location
    // If the bishop can legally move to the location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♗")){

      // Checking movement to the top right of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos + i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the top left of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos + i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom right of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos - i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom left of the bishop
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos - i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a white queen
    // Loop through the 8 directional lines the queen can move on
    // If the queen can legally move to the location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♕")){

      // Checking movement to the top right of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos + i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the top left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i <= 7 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos + i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom right of the queen
        
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos + i <= 7) {
          if (chessBoard[this.yPos - i][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the bottom left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i >= 0 && this.xPos - i >= 0) {
          if (chessBoard[this.yPos - i][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement above the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos + i < 8) {
          if (chessBoard[this.yPos + i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos + i][this.xPos]).equals("black")) {
            Integer[] movesXY = {this.yPos + i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement to the right of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos + i < 8) {
          if (chessBoard[this.yPos][this.xPos + i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos + i]).equals("black")) {
            Integer[] movesXY = {this.yPos,this.xPos + i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement below the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.yPos - i > -1) {
          if (chessBoard[this.yPos - i][this.xPos].equals("□")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos - i][this.xPos]).equals("black")) {
            Integer[] movesXY = {this.yPos - i,this.xPos};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }

      // Checking movement the left of the queen
      
      for (int i = 1; i < 8; i++) {
        if (this.xPos - i > -1) {
          if (chessBoard[this.yPos][this.xPos - i].equals("□")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
          }
          else if (chessPieceColor(chessBoard[this.yPos][this.xPos - i]).equals("black")) {
            Integer[] movesXY = {this.yPos,this.xPos - i};
            this.movesList.add(movesXY);
            break;
          }
          else {
            break;
          }
        }
      }
    }

    // If the piece is a white king
    // Loop through all of the possible moves it can make given it's current location
    // If the king can legally move to that location, add the coordinates to the possible move list
      
    else if (this.emoji.equals("♔")){
      for (int i = 0; i < kingMoves.length; i++) {
        if (this.yPos + kingMoves[i][0] >= 0 && this.yPos + kingMoves[i][0] <= 7 && this.xPos + kingMoves[i][1] >= 0 && this.xPos + kingMoves[i][1] <= 7) {
          if (!chessPieceColor(chessBoard[this.yPos + kingMoves[i][0]][this.xPos + kingMoves[i][1]]).equals("white")) {
            Integer[] movesXY = {this.yPos + kingMoves[i][0], this.xPos + kingMoves[i][1]};
            this.movesList.add(movesXY);
          }
        }
      }
    }

    // If the piece is a white pawn
      
    else if (this.emoji.equals("♙")){

      // If the pawn can move 1 tile up
      
      if (chessBoard[this.yPos - 1][this.xPos].equals("□")) {
        Integer[] movesXY = {this.yPos - 1, this.xPos};
        this.movesList.add(movesXY);
      }

      // If the pawn can move 2 tiles up
      
      if (this.alreadyMoved == false && chessBoard[this.yPos - 1][this.xPos].equals("□") && chessBoard[this.yPos - 2][this.xPos].equals("□")) {
        Integer[] movesXY = {this.yPos - 2, this.xPos};
        this.movesList.add(movesXY);
      }

      // If the pawn can attack a piece to the left or right of it
      
      if (this.xPos + 1 <= 7) {
        if (chessPieceColor(chessBoard[this.yPos - 1][this.xPos + 1]).equals("black")) {
          Integer[] movesXY = {this.yPos - 1, this.xPos + 1};
          this.movesList.add(movesXY);
        }
      }
      if (this.xPos - 1 >= 0) {
        if (chessPieceColor(chessBoard[this.yPos - 1][this.xPos - 1]).equals("black")) {
          Integer[] movesXY = {this.yPos - 1, this.xPos - 1};
          this.movesList.add(movesXY);
        }
      }
    }

    // Return at the end of the function
    
    return;
    
  }

  /**
   * Returns the color of an inputed chess piece emoji
   *
   * @param String currentPiece
   * @return String color
   */
  public static String chessPieceColor(String currentPiece) {
    if (currentPiece.equals("♙") || currentPiece.equals("♗") || currentPiece.equals("♘") || currentPiece.equals("♖") || currentPiece.equals("♕") || currentPiece.equals("♔")) {
      return("white");
    }
    if (currentPiece.equals("♟") || currentPiece.equals("♝") || currentPiece.equals("♞") || currentPiece.equals("♜") || currentPiece.equals("♛") || currentPiece.equals("♚")) {
      return("black");
    }
    return("null");
  }
  
}

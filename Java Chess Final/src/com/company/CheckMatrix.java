package com.company;

/*
 * @author Joshua Dierickse
 * @author Son Le
 */

class CheckMatrix {

  /**
   * Creates a 2D array of the squares which are underattack by the inputed color
   *
   * @param String[][] chessBoard String colour
   * @return String[][] checkBoard
   */
  public static String[][] checkMatrixFunction(String[][] chessBoard, String colour) {

    // Creates the checkBoard
    
    String[][] checkBoard = new String[8][8];

    // Possible knight and king moves from there current locations
    
    int[][] knightMoves = {{2,1},{1,2},{-2,1},{-1,2},{2,-1},{1,-2},{-2,-1},{-1,-2}};
    int[][] kingMoves = {{0,1},{1,1},{1,0},{0,-1},{-1,-1},{-1,0},{1,-1},{-1,1}};

    // Creates the entire board of black or white squares
    
    if (colour.equals("black")) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          checkBoard[i][j] = "⬛";
        }
      }
    }
    else {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          checkBoard[i][j] = "⬜";
        }
      }
    }

    // If the piece is black
    
    if (colour.equals("black")) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {

          // If the piece is a rook, check the four directions from the rook and see if the rook can attack those squares
          
          if (chessBoard[i][j].equals("♜")){
            for (int k = 1; k < 8; k++) {
              if (k + j > 7) {
                break;
              }
              checkBoard[i][j + k] = "🟥";
              if (!chessBoard[i][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (j - k < 0) {
                break;
              }
              checkBoard[i][j - k] = "🟥";
              if (!chessBoard[i][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (k + i > 7) {
                break;
              }
              checkBoard[i + k][j] = "🟥";
              if (!chessBoard[i + k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0) {
                break;
              }
              checkBoard[i - k][j] = "🟥";
              if (!chessBoard[i - k][j].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a knight loop through the possible moves, and see if the knight can attack those squares
            
          else if (chessBoard[i][j].equals("♞")){
            for (int a = 0; a < 8; a++) {
              if (i + knightMoves[a][0] > -1 && i + knightMoves[a][0] < 8 && j + knightMoves[a][1] > -1 && j + knightMoves[a][1] < 8) {
                checkBoard[i + knightMoves[a][0]][j + knightMoves[a][1]] = "🟥";
              }
            }
          }

          // If the piece is a bishop, check the four diagonals in a line and see if the bishop can attack those squares
            
          else if (chessBoard[i][j].equals("♝")){
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j + k > 7) {
                break;
              }
              checkBoard[i + k][j + k] = "🟥";
              if (!chessBoard[i + k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j + k > 7) {
                break;
              }
              checkBoard[i - k][j + k] = "🟥";
              if (!chessBoard[i - k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j - k < 0) {
                break;
              }
              checkBoard[i + k][j - k] = "🟥";
              if (!chessBoard[i + k][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j - k < 0) {
                break;
              }
              checkBoard[i - k][j - k] = "🟥";
              if (!chessBoard[i - k][j - k].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a queen, do a combination of both rook and bishop attacks
            
          else if (chessBoard[i][j].equals("♛")){
            for (int k = 1; k < 8; k++) {
              if (k + j > 7) {
                break;
              }
              checkBoard[i][j + k] = "🟥";
              if (!chessBoard[i][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (j - k < 0) {
                break;
              }
              checkBoard[i][j - k] = "🟥";
              if (!chessBoard[i][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (k + i > 7) {
                break;
              }
              checkBoard[i + k][j] = "🟥";
              if (!chessBoard[i + k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0) {
                break;
              }
              checkBoard[i - k][j] = "🟥";
              if (!chessBoard[i - k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j + k > 7) {
                break;
              }
              checkBoard[i + k][j + k] = "🟥";
              if (!chessBoard[i + k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j + k > 7) {
                break;
              }
              checkBoard[i - k][j + k] = "🟥";
              if (!chessBoard[i - k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j - k < 0) {
                break;
              }
              checkBoard[i + k][j - k] = "🟥";
              if (!chessBoard[i + k][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j - k < 0) {
                break;
              }
              checkBoard[i - k][j - k] = "🟥";
              if (!chessBoard[i - k][j - k].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a king, go through the possible king movements and see if the king can attack those squares
            
          else if (chessBoard[i][j].equals("♚")){
            for (int a = 0; a < 8; a++) {
              if (i + kingMoves[a][0] > -1 && i + kingMoves[a][0] < 8 && j + kingMoves[a][1] > -1 && j + kingMoves[a][1] < 8) {
                checkBoard[i + kingMoves[a][0]][j + kingMoves[a][1]] = "🟥";
              }
            }
          }

          // If the piece is a pawn, attack the two adjacent squares to the left and right of the pawn
            
          else if (chessBoard[i][j].equals("♟")){
            if (i + 1 < 8 && j + 1 < 8) {
              checkBoard[i + 1][j + 1] = "🟥";
            }
            if (i + 1 < 8 && j - 1 > -1) {
              checkBoard[i + 1][j - 1] = "🟥";
            }
          }
          else {
            continue;
          }
        }
      }
    }

    // If the piece is white
    
    if (colour.equals("white")) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {

          // If the piece is a rook, check the four directions from the rook and see if the rook can attack those squares
          
          if (chessBoard[i][j].equals("♖")){
            for (int k = 1; k < 8; k++) {
              if (k + j > 7) {
                break;
              }
              checkBoard[i][j + k] = "🟦";
              if (!chessBoard[i][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (j - k < 0) {
                break;
              }
              checkBoard[i][j - k] = "🟦";
              if (!chessBoard[i][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (k + i > 7) {
                break;
              }
              checkBoard[i + k][j] = "🟦";
              if (!chessBoard[i + k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0) {
                break;
              }
              checkBoard[i - k][j] = "🟦";
              if (!chessBoard[i - k][j].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a knight loop through the possible moves, and see if the knight can attack those squares
            
          else if (chessBoard[i][j].equals("♘")){
            for (int a = 0; a < 8; a++) {
              if (i + knightMoves[a][0] > -1 && i + knightMoves[a][0] < 8 && j + knightMoves[a][1] > -1 && j + knightMoves[a][1] < 8) {
                checkBoard[i + knightMoves[a][0]][j + knightMoves[a][1]] = "🟦";
              }
            }
          }

          // If the piece is a bishop, check the four diagonals in a line and see if the bishop can attack those squares
            
          else if (chessBoard[i][j].equals("♗")){
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j + k > 7) {
                break;
              }
              checkBoard[i + k][j + k] = "🟦";
              if (!chessBoard[i + k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j + k > 7) {
                break;
              }
              checkBoard[i - k][j + k] = "🟦";
              if (!chessBoard[i - k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j - k < 0) {
                break;
              }
              checkBoard[i + k][j - k] = "🟦";
              if (!chessBoard[i + k][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j - k < 0) {
                break;
              }
              checkBoard[i - k][j - k] = "🟦";
              if (!chessBoard[i - k][j - k].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a queen, do a combination of both rook and bishop attacks
            
          else if (chessBoard[i][j].equals("♕")){
            for (int k = 1; k < 8; k++) {
              if (k + j > 7) {
                break;
              }
              checkBoard[i][j + k] = "🟦";
              if (!chessBoard[i][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (j - k < 0) {
                break;
              }
              checkBoard[i][j - k] = "🟦";
              if (!chessBoard[i][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (k + i > 7) {
                break;
              }
              checkBoard[i + k][j] = "🟦";
              if (!chessBoard[i + k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0) {
                break;
              }
              checkBoard[i - k][j] = "🟦";
              if (!chessBoard[i - k][j].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j + k > 7) {
                break;
              }
              checkBoard[i + k][j + k] = "🟦";
              if (!chessBoard[i + k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j + k > 7) {
                break;
              }
              checkBoard[i - k][j + k] = "🟦";
              if (!chessBoard[i - k][j + k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i + k > 7 || j - k < 0) {
                break;
              }
              checkBoard[i + k][j - k] = "🟦";
              if (!chessBoard[i + k][j - k].equals("□")) {
                break;
              }
            }
            for (int k = 1; k < 8; k++) {
              if (i - k < 0 || j - k < 0) {
                break;
              }
              checkBoard[i - k][j - k] = "🟦";
              if (!chessBoard[i - k][j - k].equals("□")) {
                break;
              }
            }
          }

          // If the piece is a king, go through the possible king movements and see if the king can attack those squares
            
          else if (chessBoard[i][j].equals("♔")){
            for (int a = 0; a < 8; a++) {
              if (i + kingMoves[a][0] > -1 && i + kingMoves[a][0] < 8 && j + kingMoves[a][1] > -1 && j + kingMoves[a][1] < 8) {
                checkBoard[i + kingMoves[a][0]][j + kingMoves[a][1]] = "🟦";
              }
            }
          }

          // If the piece is a pawn, attack the two adjacent squares to the left and right of the pawn
            
          else if (chessBoard[i][j].equals("♙")){
            if (i - 1 > -1 && j + 1 < 8) {
              checkBoard[i - 1][j + 1] = "🟦";
            }
            if (i - 1 > -1 && j - 1 > -1) {
              checkBoard[i - 1][j - 1] = "🟦";
            }
          }
          else {
            continue;
          }
        }
      }
    }

    // For printing out a debugable chessboard
    
    /*
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        System.out.print(checkBoard[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("-----------------");
    */

    // Returns the checkBoard
    
    return checkBoard;
  }
}
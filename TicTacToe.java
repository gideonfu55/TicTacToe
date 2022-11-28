import java.util.Scanner;

public class TicTacToe {

  static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    
    System.out.println("\nLet's play a game of Tic Tac Toe!");

    // Pretty sure this shows 9 blank faces in need of an input from a user. Heh.
    char[][] gameBoard = {
      { '_', '_', '_' },
      { '_', '_', '_' },
      { '_', '_', '_' },
    };

    printBoard(gameBoard);

    for (int i = 0; i < 9; i++) {
      if (i % 2 == 0) {
        System.out.println("Turn for X: ");

        int[] spotX = askUserSpot(gameBoard);
        gameBoard[spotX[0]][spotX[1]] = 'X';

      } else {
        System.out.println("Turn for O: ");

        int[] spotO = askUserSpot(gameBoard);
        gameBoard[spotO[0]][spotO[1]] = 'O';

      }
      printBoard(gameBoard);

      int countWin = checkWin(gameBoard);
      if (countWin == 3) {
        System.out.println("X has won!!\n");
        System.exit(0);
      } else if (countWin == -3) {
        System.out.println("O has won!!\n");
        System.exit(0);
      }
    }

    System.out.println("Good game! It is a tie!\n");
  }

  /**
   * Function name - printBoard
   * 
   * @param board (char[][])
   * 
   * · Takes an existing 2D array and prints the array when called each time.
   * 
   */

  public static void printBoard(char[][] board) {

    System.out.print("\n");

    for (int i = 0; i < board.length; i++) {
      System.out.print("\t");
      for (int j = 0; j < board[i].length; j++) {

        System.out.print(board[i][j] + " ");
      }
      System.out.print("\n\n");
    }
  }

  /**
   * Function name - askUserSpot
   * 
   * @param board (char[][])
   * @return spot (int[])
   * 
   * · Prints question to ask user for their choice to place their X or O.
   * · Returns the value of user's placement (X, Y) so it can be used to update the gameboard.
   * 
   */

   public static int[] askUserSpot(char[][] board) {

    System.out.print("Pick your spot to place on the board: ");
    int rowChoice = scan.nextInt();
    int columnChoice = scan.nextInt();

    while (true) {

      while (rowChoice < 0 || columnChoice < 0 || rowChoice > 2 || columnChoice > 2) {
        System.out.print("\nYou are not playing Go. Please pick a spot on the board: ");
        rowChoice = scan.nextInt();
        columnChoice = scan.nextInt();
      }

      if (board[rowChoice][columnChoice] == '_') {
        int[] spot = {rowChoice, columnChoice};
        return spot;
      }

      System.out.print("\nPick a different spot, this has been placed: ");

      rowChoice = scan.nextInt();
      columnChoice = scan.nextInt();
    }
   }

   /**
    * Function name - checkCount
    * 
    * @param count (int)
    * @return countThree (boolean)
    * 
    * This function checks has if 3 has been reach for either X or O and returns true/false
    *
    */
    public static boolean checkCount(int count) {
      if (count == 3 || count == -3) {
        return true;
      } else {
        return false;
      }
    }

   /**
    * Function name - checkWin
    * 
    * @param board (char[][])
    * @return count (int)
    * 
    * · This function will check for each row, column and left/right diagonals to find if there are 3 X/Os in each category.
    * · To check, the count will +1 for each X and -1 for each O. If the count reaches 3, X has been completed. If the count reaches -3, O has been completed.
    */

    public static int checkWin(char[][] board) {

      int count = 0;

      // Check each row for 3 X/Os:
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++){
          if (board[i][j] == 'X' ) {
            count++;
          } else if (board[i][j] == 'O') {
            count--;
          }
        }
        if (checkCount(count)) {
          return count;
        }
        count = 0;
      } 

      // Check each column for 3 X/Os:
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
          if (board[j][i] == 'X') {
            count++;
          } else if (board[j][i] == 'O') {
            count--;
          }
        }
        if (checkCount(count)) {
          return count;
        }
        count = 0;
      }
      
      // Check for completion in left to right diagonal:
      for (int i = 0; i < board.length; i++){
        if (board[i][i] == 'X') {
          count++;
        } else if (board[i][i] == 'O') {
          count--;
        }
      }

      if (checkCount(count)) {
        return count;
      }
      count = 0;

      // Check for completion in right to left diagonal:
      for (int i = 0; i < board.length; i++) {
        int columnIndex = board.length - 1;
        if (board[i][columnIndex - i] == 'X') {
          count++;
        } else if (board[i][columnIndex - i] == 'O') {
          count--;
        }
      }

      return count;
    }
}

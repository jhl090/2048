//------------------------------------------------------------------//
// Board.java                                                       //
//                                                                  //
// Class used to represent a 2048 game board                        //
//                                                                  //
// Author:  Brandon Williams                                        //
// Date:    1/17/15                                                 //
//------------------------------------------------------------------//

/**     Sample Board
 *
 *      0   1   2   3
 *  0   -   -   -   -
 *  1   -   -   -   -
 *  2   -   -   -   -
 *  3   -   -   -   -
 *
 *  The sample board shows the index values for the columns and rows
 */

/*
 * Name: Jim H. Lee
 * Login: cs8bann
 * Date: March 05, 2015
 * Due Date: March 05, 2015
 * File: Board.java
 * Sources of Help: Tutors in the CSE lab
 * Program Description: This program generates (constructs) a new score and 
 *                      a board of a new 2048 game and loads (constructs) 
 *                      the score and board of an old 2048 game. When a
 *                      new game is created, a certain quantity of tiles will
 *                      be randomly added to the new board. The saveBoard() 
 *                      method will save the a game's board with its boardSize 
 *                      (or GRID_SIZE), tiles within the board and the score. 
 *                      The addRandomTile() method will add a new tile on a
 *                      random spot on the board.
 */ 

import java.util.*;
import java.io.*;

public class Board
{
   public final int NUM_START_TILES = 2;
   public final int TWO_PROBABILITY = 90;
   public final int GRID_SIZE;

   private final Random random;
   private int[][] grid;
   private int score;

   // TODO PA3
   // Constructs a fresh board with random tiles
   /* Constructor Description: This constructor generates a fresh board with
    *                          random tiles by setting the instance variables
    *                          to some of the following parameters. A new 
    *                          two-dimensional array (the board) is created
    *                          based off the boardSize parameter. Then, a
    *                          certain quantity of tiles are placed on random
    *                          spots of this new board. 
    */ 
   public Board(int boardSize, Random random)
   {
      this.random = random; // FIXME
      GRID_SIZE = boardSize; //FIXME
      grid = new int[GRID_SIZE][GRID_SIZE];
      score = 0; 
      for ( int index = 0; index < NUM_START_TILES; index++ )
      {
         addRandomTile();
      }
   }

   // TODO PA3
   // Construct a board based off of an input file 
   /* Constructor Description: This constructor loads an old game by 
    *                          implementing a Scanner Object to read the
    *                          GRID_SIZE and score of the input file. 
    *                          The GRID_SIZE and score are referenced
    *                          respectively to their correct values. 
    *                          This grid instance variable takes input 
    *                          elements from the Scanner Object and 
    *                          references them to the tiles. 
    */
   public Board(String inputBoard, Random random) throws IOException
   {
      this.random = random; // FIXME
      File input = new File ( inputBoard ); 
      Scanner loadedBoard = new Scanner( input ); 
      this.GRID_SIZE = loadedBoard.nextInt(); // FIXME
      this.score = loadedBoard.nextInt();
      this.grid = new int[GRID_SIZE][GRID_SIZE];
      for ( int row = 0; row < GRID_SIZE; row++ )
      {
         for ( int column = 0; column < GRID_SIZE; column++ )
         {
            grid[row][column] = loadedBoard.nextInt(); 
         }
      } 
   }

   // TODO PA3
   // Saves the current board to a file
   /* Name: saveBoard()
    * Purpose: This method saves the board size, score and tiles (position
    *          and values inside) when a player quits the game. A PrintWriter
    *          object is used to write these components to a new File object.
    * Parameters: String object that will become the newly saved file 
    *             containing a game's board size, score and tiles. 
    * Return: None
    */
   public void saveBoard(String outputBoard) throws IOException
   {
      //Converts outputBoard to a new File object
      File saveFile = new File( outputBoard );
      PrintWriter newSave = new PrintWriter( saveFile );
      //Save (write) the size of the board 
      newSave.println( GRID_SIZE );
      //Save (write) the score 
      newSave.println( score );
      //Loop through the board and save (write) the tiles of the board 
      for ( int row = 0; row < GRID_SIZE; row++ )
      {
         for ( int column = 0; column < GRID_SIZE; column++ )
         {
            //Save (write) the tiles of a board 
            newSave.print( grid[row][column] + " " ); 
         }
         //After all tiles of a row are saved, move onto a new row and 
         //save (write) the tiles within that row 
         newSave.println(); 
      }
      newSave.close();
   }

   // TODO PA3
   // Adds a Random Tile (of value 2 or 4) to a 
   // Random Empty space on the board
   /* Name: addRandomTile
    * Purpose: This method adds a new tile containing either 2 or 4 to a 
    *          random open spot on the board. It loops through the board to
    *          find all open spots on the board. A random location on the 
    *          board is created. Then, it loops through the board again to 
    *          find the specific location to add the new tile. 
    * Parameters: None 
    * Return: None
    */ 
   public void addRandomTile()
   {
      int count = 0; 
      Random random = new Random();
      //Loop to count the number of available tiles 
      for ( int row = 0; row < GRID_SIZE; row++ )
      {
         for ( int column = 0; column < GRID_SIZE; column++ )
         {
            if ( grid[row][column] == 0 )
            {
               //If an open spot is found, increment the variable count
               count++;
            }
         }
      }
      //If no open spots are found, return without changing the board
      if ( count == 0 )
      { 
         return;
      }
      //Get a random open spot on the board
      int location = random.nextInt( count );
      //To determine whether 2 or 4 will be placed on the open spot
      int value = random.nextInt( 100 ); 
      int check = -1; 
      int two = 2;
      int four = 4;
      for ( int row = 0; row < this.GRID_SIZE; row++ )
      {
         for ( int column = 0; column < this.GRID_SIZE; column++ )
         {
            if( this.grid[row][column] == 0 )
             { 
               //If an open spot is found, increment the variable check 
               check++;
               if ( check == location )
               {
                  //If the value is less than the probability of placing a 2
                  if ( value < TWO_PROBABILITY )
                  {
                     //Set the open spot to 2
                     this.grid[row][column] = two;
                  }
                  else 
                  {
                     //Set the open spot to 4
                     this.grid[row][column] = four;
                  }
               }   
            }
         }   
      }
   }

   // TODO PA4
   // Check to see if we have a game over
   /* Name: isGameOver
    * Purpose: This method checks if the player has reached an instance
    *          in the 2048 game when he or she cannot move the tiles Up, 
    *          Down, Left or Right.
    * Parameters: None
    * Return: Either "true" if no moves can be executed or "false" if a move
    *         can still be executed
    */   
   public boolean isGameOver()
   {
      //If RIGHT, LEFT, UP and DOWN are all unpermitted
      if( canMove(Direction.RIGHT) == false && canMove(Direction.LEFT) == 
          false && canMove(Direction.UP) == false && canMove(Direction.DOWN)
          == false ) 
      {
         //The player has lost the 2048 game 
         return true;
      }
      return false;
   }

   // TODO PA4
   // Determine if we can move in a given direction
   /* Name: canMove 
    * Purpose: This method checks if a player's input move is permitted. 
    * Parameters: Direction Object indicating the input move. This will be
    *             determined as either permitted or unpermitted.
    * Return: Either "true" if the input move is permitted or "false" if the 
    *         input move is unpermitted. 
    */ 
   public boolean canMove(Direction direction)
   {
      //If input move is RIGHT
      if ( direction == Direction.RIGHT )
      {
         //Check if RIGHT is permitted
         if ( canMoveRight(direction) == true )
         {
            return true;
         }
      }
      //If input move is LEFT
      else if ( direction == Direction.LEFT )
      {
         //Check if LEFT is permitted
         if ( canMoveLeft(direction) == true )
         {
            return true;
         }
      }
      //If input move is UP
      else if ( direction == Direction.UP ) 
      {
         //Check if UP is permitted
         if ( canMoveUp(direction) == true )
         {
            return true;
         }
      }
      //If input move is DOWN
      else if ( direction == Direction.DOWN )
      { 
         //Check if DOWN is permitted
         if ( canMoveDown(direction) == true )
         {
            return true; 
         }
      }  
      //If RIGHT, LEFT, UP and DOWN are all unpermitted 
      return false; 
   }    
    
   /* Name: canMoveRight 
    * Purpose: This helper method checks if a player's input move is permitted,
    *          specifically the RIGHT move. 
    * Parameters: Direction Object indicating the input move. This will be
    *             determined as either permitted or unpermitted.
    * Return: Either "true" if the input move is permitted or "false" if the 
    *         input move is unpermitted. 
    */
   private boolean canMoveRight(Direction direction)
   {
      for ( int row = 0; row < this.GRID_SIZE; row++ )
      {  
          for ( int column = 0; column < this.GRID_SIZE-1; column++ )
          {
             int currentTile = this.grid[row][column];
             //If the tile that is right to the current tile goes off 
             //the grid
             if ( column+1 >= this.GRID_SIZE )
             {
                //Then the RIGHT move is unpermitted
                return false; 
             }
             //Get the tile that is right to the current tile
             int rightTile = this.grid[row][column+1];
             //If the current tile is greater than 0 and the tile to the 
             //right is 0 
             if ( currentTile > 0 && rightTile ==  0 )
             {  
                //Then the RIGHT move is permitted
                return true; 
             }
             //If both the current tile and the tile to the right are  
             //greater than 0
             else if ( currentTile > 0 && rightTile > 0 )
             {
                //If the current tile and the tile to the right are equal
                if ( currentTile == rightTile )
                {
                   //Then the RIGHT move is permitted 
                   return true; 
                } 
             }  
         } 
      }
      //In all other cases, the RIGHT move is not permitted 
      return false;  
   }
   
   /* Name: canMoveLeft 
    * Purpose: This helper method checks if a player's input move is permitted,
    *          specifically the LEFT move. 
    * Parameters: Direction Object indicating the input move. This will be
    *             determined as either permitted or unpermitted.
    * Return: Either "true" if the input move is permitted or "false" if the 
    *         input move is unpermitted. 
    */    
   private boolean canMoveLeft(Direction direction)
   {
      for ( int row = 0; row < this.GRID_SIZE; row++ )
      {
         for ( int column = this.GRID_SIZE-1; column > 0; column-- )
         {
            int currentTile = this.grid[row][column];
            //If the tile that is left to the current tile goes off the grid
            if ( column-1 >= this.GRID_SIZE )
            {
               //Then the LEFT move is unpermitted
               return false; 
            }
            //Get the tile that is left to the current tile 
            int leftTile = this.grid[row][column-1];
            //If the current tile is greater than 0 and the tile to the left
            //is 0
            if ( leftTile == 0 && currentTile > 0 )
            {
               //Then the LEFT move is permitted
               return true; 
            }
            //If both the current tile and the tile to the left are greater
            //than 0
            else if ( leftTile > 0 && currentTile > 0 )
            {
               //If the current tile and the tile to the left are equal
               if ( leftTile == currentTile )
               {
                  //Then the LEFT move is permitted 
                  return true; 
               }
            }
         }
      }
      //In all other cases, the LEFT move is unpermitted  
      return false; 
   }
   
   /* Name: canMoveUp 
    * Purpose: This helper method checks if a player's input move is allowed,
    *          specifically the UP move. 
    * Parameters: Direction Object indicating the input move. This will be
    *             determined as either valid or invalid.
    * Return: Either "true" if the input move is valid or "false" if the 
    *         input move is false. 
    */    
   private boolean canMoveUp(Direction direction)
   {
      for ( int column = 0; column < this.GRID_SIZE; column++ )
      {
         for ( int row = this.GRID_SIZE-1; row > 0; row-- )
         {
            int currentTile = this.grid[row][column];
            //If the tile that is above the current tile goes off the grid
            if ( row-1 >= this.GRID_SIZE )
            {
               //Then the UP move is unpermitted
               return false; 
            } 
            //Get the tile that is above the current tile
            int topTile = this.grid[row-1][column];
            //If the current tile is greater than 0 and the tile that is 
            //above is 0
            if ( topTile == 0 && currentTile > 0 )
            {
               //Then the UP move is permitted
               return true;  
            }
            //If both the current tile and the tile that is above are 0
            else if ( topTile > 0 && currentTile > 0 )
            {
               //If the current tile and the tile that is above are equal
               if ( topTile == currentTile )
               {
                  //Then the UP move is permitted
                  return true;
               } 
            } 
         }
      }
      //In all other cases, the UP move is unpermitted 
      return false; 
   }

   /* Name: canMoveDown 
    * Purpose: This helper method checks if a player's input move is allowed,
    *          specifically the DOWN move. 
    * Parameters: Direction Object indicating the input move. This will be
    *             determined as either valid or invalid.
    * Return: Either "true" if the input move is valid or "false" if the 
    *         input move is false. 
    */
   private boolean canMoveDown(Direction direction)
   {
      for ( int column = 0; column < this.GRID_SIZE; column++ )
      {
         for ( int row = 0; row < this.GRID_SIZE-1; row++ )
         {
            int currentTile = this.grid[row][column];
            //If the tile that is below the current tile goes off the grid
            if ( row+1 >= GRID_SIZE )
            {
               //Then the DOWN move is unpermitted 
               return false;
            } 
            //Get the tile that is below the current tile 
            int bottomTile = this.grid[row+1][column];
            //If the current tile is greater than 0 and the tile that is 
            //below is 0
            if ( currentTile > 0 && bottomTile == 0 )
            { 
               //Then the DOWN move is permitted
               return true; 
            }
            //If both the current tile and the tile that is below are 0
            else if ( currentTile > 0 && bottomTile > 0 )
            {
               //If the current tile and the tile that is below are equal
               if ( currentTile == bottomTile ) 
               {
                  //Then the DOWN move is permitted 
                  return true; 
               }
            }
         }
      }
      //In all other cases, the DOWN move is unpermitted 
      return false; 
   } 

   // TODO PA4
   // Perform a move Operation
   /* Name: move 
    * Purpose: This method performs a move operation with all the tiles on 
    * the board. It will move the tiles as far as possible to a certain side
    * on the board.   
    * Parameters: Direction Object indacting how the tiles will move. 
    * Return: Either "true" if the move was successful or "false" if the move 
    *         was unsuccessful 
    */         
   public boolean move(Direction direction)
   {   
      //If the input move is RIGHT
      if ( direction == Direction.RIGHT )
      {
         //Move the tiles to the RIGHT side of the board
         moveRight( direction ); 
         return true; 
      }
      //If the input move is LEFT
      if ( direction == Direction.LEFT )
      {
         //Move the tiles to the LEFT side of the board
         moveLeft( direction ); 
         return true; 
      }
      //If the input move is UP
      if ( direction == Direction.UP )
      {
         //Move the tiles to the TOP of the board
         moveUp( direction );
         return true;
      }  
      //If the input move is DOWN
      if ( direction == Direction.DOWN )
      {
         //Move the tiles to the BOTTOM of the board
         moveDown( direction );
         return true;
      }
      return false;
   }

   /* Name: moveRight
    * Purpose: This helper method for the move() method performs a RIGHT move 
    *          operation by moving the tiles to the RIGHT side of the board 
    *          as far as possible.   
    * Parameters: Direction Object indacting how the tiles will move. 
    * Return: "true" if the move was successful. Can only be "true" because 
    *         this helper method is only initiated when 
    *         direction == Direction.RIGHT;    
    */   
   private boolean moveRight(Direction direction) 
   { 
      for ( int row = 0; row < this.GRID_SIZE; row++ )
      {
         //Create an array list to hold tiles greater than 0 for all columns
         ArrayList<Integer> list = new ArrayList<Integer>(); 
         for ( int column = 0; column < GRID_SIZE; column++ )
         {
            int tile = this.grid[row][column];
            //If the current tile is greater than 0
            if ( tile > 0 )
            {
               //Add the tile to the array list
               list.add(tile);               
            }
         }         
         int numCombine = 0;       
         //Start from the end of the array list and iterate backwards           
         for ( int index = list.size()-1; index > 0; index-- )
         {
            //If the current tile is equal to the tile before it
	    if ( list.get(index).equals(list.get(index-1)) ) 
	    {
               //Combine the values of these tiles
	       numCombine = list.get(index) + list.get(index-1); 
               //Set the combined value to the tile that was positioned 
               //before the current tile
	       list.set(index-1, numCombine); 
               //Remove the current tile from the array list 
               list.remove( index ); 
               //Update the score of the game 
	       score = score + numCombine; 
	       index--;
	    }
         }  
         int counter = list.size(); 
         //Iterate through each column left to right 
         for ( int column = this.GRID_SIZE-1; column >= 0; column-- )
         {
            //If there are no more elements in the array list
            if ( counter-1 < 0 )
            {
               //Set the current tile to 0
               this.grid[row][column] = 0; 
            }
            //If there are elements in the array list 
            else if (counter >= 0 )
            {
               this.grid[row][column] = list.get(counter-1);
               counter--;
            } 
         } 
      }      
      return true; 
   }

   /* Name: moveLeft
    * Purpose: This helper method for the move() method performs a LEFT move 
    *          operation by moving the tiles to the LEFT side of the board  
    *          as far as possible.   
    * Parameters: Direction Object indacting how the tiles will move. 
    * Return: "true" if the move was successful. Can only be "true" because 
    *         this helper method is only initiated when 
    *         direction == direction.LEFT
    */ 
   private boolean moveLeft(Direction direction) 
   {
      for ( int row = 0; row < this.GRID_SIZE; row++ )
      {
         //Create an array list to hold tiles greater than 0 for all columns
         ArrayList<Integer> list = new ArrayList<Integer>(); 
         for ( int column = 0; column < this.GRID_SIZE; column++ )
         {
            int tile = this.grid[row][column];
            //If the current tile is greater than 0
            if ( tile > 0 )
            {
               //Add the tile to the array list
               list.add(tile);               
            }
         }         
         int numCombine = 0;                   
         //Start from the start of the array list and iterate forwards
         for ( int index = 0; index < list.size()-1; index++ )
         {
            //If the current tile is greater to the tile after it
	    if ( list.get(index).equals(list.get(index+1)) ) 
	    {
               //Combine the values of these tiles
	       numCombine = list.get(index) + list.get(index+1);
               //Set the combined value to the position of the current tile 
	       list.set(index, numCombine); 
	       list.remove( index+1 ); 
               //Update the score of the game
	       score = score + numCombine; 
            } 
         }  
         int counter = 0; 
         //Start from the beginning of the array list and iterate forwards
         for ( int column = 0; column < this.GRID_SIZE; column++ )
         {
            //If there are no more elements in the array list
            if ( counter >= list.size() )
            {
               this.grid[row][column] = 0; 
            }
            //If there are elements in the array list 
            else if (counter >= 0 && counter < list.size() )
            {
               this.grid[row][column] = list.get(counter);
               counter++;
            }
         }   
      }      
      return true; 
   }
   
   /* Name: moveUp
    * Purpose: This helper method for the move() method performs an UP move 
    *          operation by moving the tiles to the TOP of the board as far 
    *          as possible.   
    * Parameters: Direction Object indacting how the tiles will move. 
    * Return: "true" if the move was successful. Can only be "true" because
    *         this helper method is only initiated when 
    *         direction == Direction.UP
    */ 
   private boolean moveUp(Direction direction)
   {
      for ( int column = 0; column < this.GRID_SIZE; column++ )
      {
         //Create an array list to hold tiles greater than 0 for all rows
         ArrayList<Integer> list = new ArrayList<Integer>(); 
         for ( int row = 0; row < this.GRID_SIZE; row++ )
         {
            int tile = this.grid[row][column];
            //If the current tile is greater than 0
            if ( tile > 0 )
            {
               list.add(tile);               
            }
         }         
         int numCombine = 0;                   
         //Start from the start of the array list and iterate forwards
         for ( int index = 0; index < list.size()-1; index++ )
         {
            //If the current tile is equal to the tile below it
            if ( list.get(index).equals(list.get(index+1)) ) 
	    {
               //Combine the values of these tiles
	       numCombine = list.get(index) + list.get(index+1);
               //Set the combined value to the position of the current tile
	       list.set(index, numCombine); 
	       list.remove( index+1 ); 
               //Update the score of the game
	       score = score + numCombine; 
            } 
         }  
         int counter = 0; 
         //Iterate through each row top to bottom; 
         for ( int row = 0; row < this.GRID_SIZE; row++ )
         {
            //If there are no more elements in the array list
            if ( counter >= list.size() )
            {
               this.grid[row][column] = 0; 
            }
            //If there are elements in the array list 
            else if (counter >= 0 && counter < list.size() )
            {
               this.grid[row][column] = list.get(counter);
               counter++;
            }
         }   
      }  
      return true; 
   }

   /* Name: moveDown
    * Purpose: This helper method for the move() method performs an UP move 
    *          operation by moving the tiles to the BOTTOM of the board as 
    *          far as possible.   
    * Parameters: Direction Object indacting how the tiles will move. 
    * Return: "true" if the move was successful. Can only be "true" because
    *         this helper method is only initiated when 
    *         direction == Direction.DOWN 
    */ 
   private boolean moveDown(Direction direction)
   {
      for ( int column = 0; column < this.GRID_SIZE; column++ )
      {
         //Create an array list to hold tiles greater than 0 for all rows
         ArrayList<Integer> list = new ArrayList<Integer>(); 
         for ( int row = 0; row < GRID_SIZE; row++ )
         {
            int tile = this.grid[row][column];
            //If the current tile is greater than 0
            if ( tile > 0 )
            {
               //Add the tile to the array list
               list.add(tile);               
            }
         }         
         int numCombine = 0;       
         //Start from the end of the array list and iterate backwards           
         for ( int index = list.size()-1; index > 0; index-- )
         {
            //If the current tile is equal to the tile above it
	    if ( list.get(index).equals(list.get(index-1)) ) 
	    {
               //Combine the values of these tiles
	       numCombine = list.get(index) + list.get(index-1); 
               //Set the combined value to the tile that was positioned above
               //the current tile
	       list.set(index-1, numCombine); 
               //Remove the current tile from the array list 
	       list.remove( index ); 
               //Update the score of the game 
	       score = score + numCombine; 
	       index--;
	    }
         }  
         int counter = list.size(); 
         //Iterate through each row bottom to top 
         for ( int row = this.GRID_SIZE-1; row >= 0; row-- )
         {
            //If there are no more elements in the array list
            if ( counter-1 < 0 )
            {
               //Set the current tile to 0
               this.grid[row][column] = 0; 
            }
            //If there are elements in the array list 
            else if (counter >= 0 )
            {
               this.grid[row][column] = list.get(counter-1);
               counter--;
            }
         } 
      }      
      return true; 
   }
   
   // Return the reference to the 2048 Grid
   public int[][] getGrid()
   {
      return grid;
   }

   // Return the score
   public int getScore()
   {
      return score;
   }

   @Override
   public String toString()
   {
      StringBuilder outputString = new StringBuilder();
      outputString.append(String.format("Score: %d\n", score));
      for (int row = 0; row < GRID_SIZE; row++)
      {
          for (int column = 0; column < GRID_SIZE; column++)
              outputString.append(grid[row][column] == 0 ? "    -" :
                                  String.format("%5d", grid[row][column]));

              outputString.append("\n");
      }
      return outputString.toString();
   }
}

/** Gui2048.java */
/** PA8 Release */

/*
 * Name: Jim H. Lee
 * Login: cs8bann
 * Date: March 05, 2015
 * Due Date: March 05, 2015
 * File: Gui2048.java
 * Sources of Help: Tutors in the CSE lab
 * Program Description: This program generates a GUI for the 2048 game that
 *                      allows a user to play the game in a separate window
 *                      instead of in the terminal.
 */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

public class Gui2048 extends Application
{
   private String outputBoard; // The filename for where to save the Board
   private Board board; // The 2048 Game Board
   
   // Fill colors for each of the Tile values
   private static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
   private static final Color COLOR_2 = Color.rgb(238, 228, 218);
   private static final Color COLOR_4 = Color.rgb(237, 224, 200);
   private static final Color COLOR_8 = Color.rgb(242, 177, 121);
   private static final Color COLOR_16 = Color.rgb(245, 149, 99);
   private static final Color COLOR_32 = Color.rgb(246, 124, 95);
   private static final Color COLOR_64 = Color.rgb(246, 94, 59);
   private static final Color COLOR_128 = Color.rgb(237, 207, 114);
   private static final Color COLOR_256 = Color.rgb(237, 204, 97);
   private static final Color COLOR_512 = Color.rgb(237, 200, 80);
   private static final Color COLOR_1024 = Color.rgb(237, 197, 63);
   private static final Color COLOR_2048 = Color.rgb(237, 194, 46);
   private static final Color COLOR_OTHER = Color.BLACK;
   private static final Color COLOR_GAME_OVER = 
                              Color.rgb(238, 228, 218, 0.73);
   private static final Color COLOR_VALUE_LIGHT =
                              Color.rgb(249, 246, 242); // For tiles >= 8
   private static final Color COLOR_VALUE_DARK = 
                              Color.rgb(119, 110, 101); // For tiles < 8

   private GridPane gameBoard;
   private Text score2048; 
   private int score; 
   private int[][] intgrid; 
   private Tile[][] Tilegrid; 
   private String Gui2048 = "Gui2048"; 
   private String backgroundCol = "-fx-background-color: rgb(187, 173, 160)";
   private Insets insets = new Insets(11.5, 12.5, 13.5, 14.5);
   private int gap = 10;
   private int sizeforTitle = 50;
   private int sizeforScore = 28; 
   private double sizeofTile = 110.0; 
   private double size_transparent = 575.0; 
   private int font_GAMEOVER = 65; 
   private int singleDig = 50;
   private int doubleDig = 44;
   private int tripleDig = 35;   
   private int quadDig = 25; 
   private int num;
   private int num_2 = 2;
   private int num_4 = 4; 
   private int num_8 = 8;
   private int num_6 = 6; 
   private int num_16 = 16;
   private int num_32 = 32;
   private int num_64 = 64;
   private int num_128 = 128;
   private int num_256 = 256;
   private int num_512 = 512;
   private int num_1024 = 1024;
   private int num_2048 = 2048;

   @Override
   /*
    * Name: start
    * Purpose: Initiate the GUI for the 2048 game and load a saved game
    *          with its board and score
    * Parameters: Stage object
    * Return: None
    */  
   public void start(Stage primaryStage)
   {
      // Process Arguments and Initialize the Game Board
      processArgs(getParameters().getRaw().toArray(new String[0]));

      //Create the grid pane for the Game Board and set its properties 
      primaryStage.setTitle(Gui2048); 
      gameBoard = new GridPane();  
      gameBoard.setStyle(backgroundCol);
      gameBoard.setPadding(insets);
      gameBoard.setHgap(gap); 
      gameBoard.setVgap(gap);  
         
      //Create a Text object to disply the 2048 title in the grid pane
      Text txt2048 = new Text("" + num_2048);
      txt2048.setFont(Font.font("Arial",FontWeight.BOLD, sizeforTitle)); 
      gameBoard.add(txt2048, 0, 1, num_2, 1);  
       
      //Create a Text object to disply the score in the grid pane
      score2048 = new Text("Score: " + score);
      score2048.setFont(Font.font("Arial",FontWeight.BOLD, sizeforScore));
      gameBoard.add(score2048, num_2, 1, num_2, 1);

      intgrid = board.getGrid();
      //Initialize the Tile[][] instance variable   
      Tilegrid = new Tile[board.GRID_SIZE][board.GRID_SIZE];
      for(int row = 0; row < board.GRID_SIZE; row++)
      {
         for(int column = 0; column < board.GRID_SIZE; column++)
         {
            Tilegrid[row][column] = new Tile();   
            //Initialize the instance variables of the Tile class
            Tilegrid[row][column].rectangle = 
                                  new Rectangle(sizeofTile, sizeofTile);
            Tilegrid[row][column].txt = new Text("");
            if(intgrid[row][column] == 0)
            {         
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText(""); 
	       Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, singleDig));        
               Tilegrid[row][column].rectangle.setFill(COLOR_EMPTY);
	       GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER); 
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, 
                              column, row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
             }   
             else if(intgrid[row][column] == num_2)
             {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_2);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_DARK);
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_2);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
             }
             else if(intgrid[row][column] == num_4)
             {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_DARK);
               Tilegrid[row][column].txt.setText("" + num_4);
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_4);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, 
                             column, row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2); 
            }
            else if(intgrid[row][column] == num_8)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_8);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD,singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_8);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_16)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_16);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_16);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_32)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_32);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_32);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_64)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_64);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_64);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_128)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_128);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, tripleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_128);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_256)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_256);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial",
                                         FontWeight.BOLD, tripleDig));
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2); 
            }
            else if(intgrid[row][column] == num_512)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_512);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, tripleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_512);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_1024)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_1024);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_1024);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                              row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else if(intgrid[row][column] == num_2048)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_2048);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_2048);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
            else 
            {
               //Set the properties of the Rectangle and Text objects 
               num = intgrid[row][column]; 
               Tilegrid[row][column].txt.setText("" + num);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_OTHER);
               GridPane.setHalignment(Tilegrid[row][column].txt, 
                                      HPos.CENTER);
               GridPane.setValignment(Tilegrid[row][column].txt, 
                                      VPos.CENTER);
               //Add the the Rectangle and Text objects to the grid pane
               gameBoard.add(Tilegrid[row][column].rectangle, column, 
                             row+num_2);
               gameBoard.add(Tilegrid[row][column].txt, column, row+num_2);
            }
          }
          score = board.getScore();
          score2048.setText("Score: " + score); 
       }
 
       //Create a scene and place it in the stage
       Scene scene = new Scene(gameBoard);
       primaryStage.setScene(scene);
       scene.setOnKeyPressed(new KeyHandler()); 
       primaryStage.show(); 
   }

   /*
    * Class Purpose: This class allows other methods to access the
    *                Rectangle and Text objects of a Tile object. 
    */   
   private class Tile 
   {
      public Rectangle rectangle;
      public Text txt;      
   }
       
   /*
    * Name: updateBoard
    * Purpose: This method updates the colors, texts and positions of Tile
    *          objects in the grid pane when they are moved. These updates
    *          are then displayed on the GUI. 
    * Parameters: None
    * Return: None
    */   
   public void updateBoard() 
   {
      intgrid = board.getGrid(); 
      Tilegrid = this.getTileGrid();
      for(int row = 0; row < board.GRID_SIZE; row++)
      {
         for(int column = 0; column < board.GRID_SIZE; column++)
         {
            if(intgrid[row][column] == 0)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText(""); 
               Tilegrid[row][column].rectangle.setFill(COLOR_EMPTY); 
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }    
            else if(intgrid[row][column] == num_2)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_2);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_DARK);
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_2);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_4)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_4);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_DARK); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_4);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_8)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_8);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD,singleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_8);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_16)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_16);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_16);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_32)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_32);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_32);
               score = board.getScore();
               score2048.setText("Score: " + score); 
            }
            else if(intgrid[row][column] == num_64)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_64);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, doubleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_64);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_128)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_128);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, tripleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_128);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_256)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_256);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial",
                                         FontWeight.BOLD, tripleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_256);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_512)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_512);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, tripleDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_512);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_1024)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_1024);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_1024);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
            else if(intgrid[row][column] == num_2048)
            {
               //Set the properties of the Rectangle and Text objects 
               Tilegrid[row][column].txt.setText("" + num_2048);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_2048);
               score = board.getScore(); 
                score2048.setText("Score: " + score);
            }
            else 
            {
               //Set the properties of the Rectangle and Text objects 
               num = intgrid[row][column]; 
               Tilegrid[row][column].txt.setText("" + num);
               Tilegrid[row][column].txt.setFill(COLOR_VALUE_LIGHT); 
               Tilegrid[row][column].txt.setFont(Font.font("Arial", 
                                         FontWeight.BOLD, quadDig)); 
               Tilegrid[row][column].rectangle.setFill(COLOR_OTHER);
               score = board.getScore(); 
               score2048.setText("Score: " + score);
            }
         }
      }
      if(board.isGameOver() == true)
      {
         //Create a Rectangle and Text object that will display when there
         //are no more valid moves 
         Rectangle transparent = new Rectangle(size_transparent, 
                                               size_transparent);
         transparent.setFill(COLOR_GAME_OVER);
         Text GAMEOVER = new Text("Game Over!");
         GAMEOVER.setFill(COLOR_VALUE_DARK);
         GAMEOVER.setFont(Font.font("Arial", FontWeight.BOLD, 
                                     font_GAMEOVER));
         gameBoard.add(transparent, 0, 0, 6, 6);
         GridPane.setHalignment(transparent, HPos.CENTER);
         GridPane.setValignment(transparent, VPos.CENTER);
         gameBoard.add(GAMEOVER, 0 , 0, 6, 6); 
         GridPane.setHalignment(GAMEOVER, HPos.CENTER);
         GridPane.setValignment(GAMEOVER, VPos.CENTER);          
      } 
   }

   /*
    * Name: getTileGrid
    * Purpose: This method returns a reference to a two dimensional array of
    *          Tile objects.  
    * Parameters: None
    * Return: A two dimensional array of Tile objects
    */
   public Tile[][] getTileGrid()
   {
      return Tilegrid;
   } 
   
   private class KeyHandler implements EventHandler<KeyEvent>
   {
      /*
       * Name: handle
       * Purpose: This method updates the GUI with the new state of the board
       *          after a move has occurred. 
       * Parameters: KeyEvent object
       * Return: None
       */ 
      @Override 
      public void handle(KeyEvent e)
      {
         if(e.getCode() == KeyCode.UP)
         {
            if(board.canMove(Direction.UP) == true)
            {
               board.move(Direction.UP);
               board.addRandomTile();
               updateBoard();
               System.out.println("Moving UP"); 
            }
         }
         else if(e.getCode() == KeyCode.DOWN)
         {
            if(board.canMove(Direction.DOWN) == true)
            {
               board.move(Direction.DOWN);
               board.addRandomTile(); 
               updateBoard();
               System.out.println("Moving DOWN"); 
            }
         }
         else if(e.getCode() == KeyCode.RIGHT)
         {
            if(board.canMove(Direction.RIGHT) == true)
            {
               board.move(Direction.RIGHT);
               board.addRandomTile();
               updateBoard();
               System.out.println("Moving RIGHT"); 
            }
         }
         else if(e.getCode() == KeyCode.LEFT)
         {
            if(board.canMove(Direction.LEFT) == true)
            {
               board.move(Direction.LEFT);
               board.addRandomTile();
               updateBoard();
               System.out.println("Moving LEFT"); 
            }
         }
         //If the user presses the "S" key to save the game with its board 
         //and score
         else if(e.getCode() == KeyCode.S)
         {
            try
            {
               board.saveBoard(outputBoard); 
               System.out.println("Saving Board to " + outputBoard);
               System.exit(0);   
            }
            catch(IOException ex)
            {
               System.err.println("An IOException occured!");
               System.exit(0);   
            } 
         }
      }
   } 

    /** DO NOT EDIT BELOW */

    // The method used to process the command line arguments
    private void processArgs(String[] args)
    {
        String inputBoard = null;   // The filename for where to load the Board
        int boardSize = 0;          // The Size of the Board

        // Arguments must come in pairs
        if((args.length % 2) != 0)
        {
            printUsage();
            System.exit(-1);
        }

        // Process all the arguments 
        for(int i = 0; i < args.length; i += 2)
        {
            if(args[i].equals("-i"))
            {   // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            }
            else if(args[i].equals("-o"))
            {   // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            }
            else if(args[i].equals("-s"))
            {   // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            }
            else
            {   // Incorrect Argument 
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if(outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified or less than 2
        if(boardSize < 2)
            boardSize = 4;

        // Initialize the Game Board
        try{
            if(inputBoard != null)
                board = new Board(inputBoard, new Random());
            else
                board = new Board(boardSize, new Random());
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " was thrown while creating a " +
                               "Board from file " + inputBoard);
            System.out.println("Either your Board(String, Random) " +
                               "Constructor is broken or the file isn't " +
                               "formated correctly");
            System.exit(-1);
        }
    }

    // Print the Usage Message 
    private static void printUsage()
    {
        System.out.println("Gui2048");
        System.out.println("Usage:  Gui2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be used to save the 2048 board");
        System.out.println("                If none specified then the default \"2048.board\" file will be used");
        System.out.println("  -s [size]  -> Specifies the size of the 2048 board if an input file hasn't been");
        System.out.println("                specified.  If both -s and -i are used, then the size of the board");
        System.out.println("                will be determined by the input file. The default size is 4.");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class Tictactoegame extends Application {
    
        String player1name,player2name;
        TextField p1=new TextField();
        TextField p2=new TextField();
        GridPane grid=new GridPane();
        
         //array storing player data
         ArrayList<Integer> player1=new ArrayList<Integer>();
         ArrayList<Integer> player2=new ArrayList<Integer>();
         int ActivePlayer=1;
         Boolean win=false;
         Boolean PcMode=false;
        
         //buttons
        Button btn1=new Button();
        Button btn2=new Button();
        Button btn3=new Button();
        Button btn4=new Button();
        Button btn5=new Button();
        Button btn6=new Button();
        Button btn7=new Button();
        Button btn8=new Button();
        Button btn9=new Button();
        Button twoplayer=new Button("Two Player Game");
        Button pc=new Button("vs Computer");
        Button btn=new Button("Start Game"); 
        Button btnr=new Button("Replay");
        Button btne=new Button("Exit");
        
         //for name input
        Label name=new Label("Enter Player Name");
        Label pl1=new Label("Player 1: ");
        Label pl2=new Label("Player 2: ");
        
    @Override 
    public void start(Stage MyGame){
       
        //designing
        twoplayer.setId("twoplayermode");
        pc.setId("pcmode");
        btn.setId("startgame");
        
        //button actions for game mode
        pc.setOnAction(event->{
            vsPC();
        });
        twoplayer.setOnAction(event->{
            TwoPlayerGame();
        });
        
        //button actions(replay,exit)
         btne.setOnAction(event->{ System.exit(0);});
         btnr.setOnAction(event->{ reset();});
         
        //grid
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.add(twoplayer,1,0);
        grid.add(pc,1,1);
     
        
        //calling play
        btn1.setOnAction(event->{
        play(1,btn1);
        });
        btn2.setOnAction(event->{
        play(2,btn2);
        });
        btn3.setOnAction(event->{
        play(3,btn3);
        });
        btn4.setOnAction(event->{
        play(4,btn4);
        });
        btn5.setOnAction(event->{
        play(5,btn5);
        });
        btn6.setOnAction(event->{
        play(6,btn6);
        });
        btn7.setOnAction(event->{
        play(7,btn7);
        });
        btn8.setOnAction(event->{
        play(8,btn8);
        });
        btn9.setOnAction(event->{
        play(9,btn9);
        });
        
        //scene
        Scene scene =new Scene(grid,400,400,Color.GREEN);
        scene.getStylesheets().add(Tictactoegame.class.getResource("button.css").toExternalForm());
        
        //button action to start game
        btn.setOnAction(event->{
        grid.getChildren().removeAll(name,pl1,pl2,p1,p2,btn);
        grid.add(btn1, 0, 0);
        grid.add(btn2, 1, 0);
        grid.add(btn3, 2, 0);
        grid.add(btn4, 0, 1);
        grid.add(btn5, 1, 1);
        grid.add(btn6, 2, 1);
        grid.add(btn7, 0, 2);
        grid.add(btn8, 1, 2);
        grid.add(btn9, 2, 2);
        }) ;      
        
        //stage
        MyGame.setTitle("Tic Tac Toe Game");
        MyGame.setScene(scene);
      //  MyGame.setFullScreen(true);
        MyGame.show();
        
    }
    
    //method to manage two player game mode
   public void TwoPlayerGame(){
       grid.getChildren().removeAll(twoplayer,pc);
        grid.add(name,1,0);
        grid.add(pl1,0,1);
        grid.add(pl2,0,2);
        grid.add(btn,1,3);
        grid.add(p1,1,1);
        grid.add(p2,1,2);
   }
    
   //method to mange vs PC mode
public void vsPC(){
       grid.getChildren().removeAll(twoplayer,pc);
       PcMode=true;
       grid.add(name,1,0);
       grid.add(pl1,0,1);
       grid.add(p1, 1,1);
       grid.add(btn,1,2);
     
}

// how game proceeds
//player click button
//button->play->checkwinner->winner->alert->end screen
//                       |->no winner->end screen

    //method changing button text and switching player
    public void play(int index,Button pressed){
        if(ActivePlayer==1){
            player1.add(index);
            ActivePlayer=2;
            pressed.setText("X");
        }
        else{
            player2.add(index);
            ActivePlayer=1;
            pressed.setText("O");
        }
        pressed.setDisable(true);
        if(player1.size()>=3)
         CheckWinner();        
        if(win==false&&player1.size()+player2.size()==9)
            nowinner();
        if(PcMode==true&&ActivePlayer==2&&player1.size()+player2.size()!=9)
        playPC();
        
    }
    
    //method for pc to play(autoplay)
    public void playPC(){
        ArrayList<Integer> empty=new ArrayList<Integer>();
        for(int i=1;i<=9;++i){
            if(!(player1.contains(i)||player2.contains(i)))
                empty.add(i);
        }
        Random r=new Random();
        int x=r.nextInt(empty.size());
        int button_no=empty.get(x);
        switch(button_no)
        {
            case 1: play(1,btn1);break;
            case 2: play(2,btn2);break;
            case 3: play(3,btn3);break;
            case 4: play(4,btn4);break;
            case 5: play(5,btn5);break;
            case 6: play(6,btn6);break;
            case 7: play(7,btn7);break;
            case 8: play(8,btn8);break;
            case 9: play(9,btn9);break;
            default: play(1,btn1);
        }
        
    }
    
    //method to check winner
    public void CheckWinner(){
        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3))
            winner(1);
        if(player1.contains(4)&&player1.contains(5)&&player1.contains(6))
            winner(1);
        if(player1.contains(7)&&player1.contains(8)&&player1.contains(9))
            winner(1);
        if(player1.contains(1)&&player1.contains(4)&&player1.contains(7))
            winner(1);
        if(player1.contains(2)&&player1.contains(5)&&player1.contains(8))
            winner(1);
        if(player1.contains(3)&&player1.contains(6)&&player1.contains(9))
            winner(1);
         if(player1.contains(1)&&player1.contains(5)&&player1.contains(9))
            winner(1);
          if(player1.contains(3)&&player1.contains(5)&&player1.contains(7))
            winner(1);
        if(player2.contains(1)&&player2.contains(2)&&player2.contains(3))
            winner(2);
         if(player2.contains(4)&&player2.contains(5)&&player2.contains(6))
            winner(2);
          if(player2.contains(7)&&player2.contains(8)&&player2.contains(9))
            winner(2);
           if(player2.contains(1)&&player2.contains(4)&&player2.contains(7))
            winner(2);
            if(player2.contains(2)&&player2.contains(5)&&player2.contains(8))
            winner(2);
             if(player2.contains(3)&&player2.contains(6)&&player2.contains(9))
            winner(2);
              if(player2.contains(3)&&player2.contains(5)&&player2.contains(7))
            winner(2);
               if(player2.contains(1)&&player2.contains(5)&&player2.contains(9))
            winner(2);
               
         
    }
    
    //method to represent the winner
    public void winner(int n)    {
        win=true;
        String str;
        if(n==1){
        str=p1.getText().toString();
        }
        else{
            if(PcMode==true)
                str="Computer";
            else
                str=p2.getText().toString();
        }
        //alert dialog        
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        if(PcMode==true&&n==2)
            alert.setHeaderText("Bad Luck");
        else
            alert.setHeaderText("Congratulations");
        alert.setContentText("The Winner is : "+str);
        
        alert.show();
        grid.getChildren().removeAll(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        grid.add(btnr,1,0);
        grid.add(btne,1,1);
    }
    
     //method to hanlde case when there is no winner
    public void nowinner(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game info");
        alert.setHeaderText("No winner");
        alert.setContentText("Please restart the game");
        alert.show();
        grid.getChildren().removeAll(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        grid.add(btnr,1,0);
        grid.add(btne,1,1);
                
    }
    
    //method to reset game stats and variables
    public void reset(){
        ActivePlayer=1;
        win=false;
        btn1.setDisable(false);btn1.setText("");
        btn2.setDisable(false);btn2.setText("");
        btn3.setDisable(false);btn3.setText("");
        btn4.setDisable(false);btn4.setText("");
        btn5.setDisable(false);btn5.setText("");
        btn6.setDisable(false);btn6.setText("");
        btn7.setDisable(false);btn7.setText("");
        btn8.setDisable(false);btn8.setText("");
        btn9.setDisable(false);btn9.setText("");
        grid.getChildren().removeAll(btnr,btne);
        grid.add(btn1, 0, 0);
        grid.add(btn2, 1, 0);
        grid.add(btn3, 2, 0);
        grid.add(btn4, 0, 1);
        grid.add(btn5, 1, 1);
        grid.add(btn6, 2, 1);
        grid.add(btn7, 0, 2);
        grid.add(btn8, 1, 2);
        grid.add(btn9, 2, 2);
        player1.clear();
        player2.clear();
          
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

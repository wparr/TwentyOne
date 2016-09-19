/*
 * Simple blackjack game
Rules:
   
 */
package twentyone;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wparr
 */
public class TwentyOne extends Application implements EventHandler<ActionEvent> {
    static int UserNumofCards;
    static int DealerNumofCards;
    static String[] UserHand;
    static String[] DealerHand;
    static GridPane layout = new GridPane();
    Button hit = new Button();
    Button stay = new Button();
    Button deal = new Button();
    static int DealerHandValue;
    static int UserHandValue;
    static boolean wasItUser;
   
   static CardActions gameActions = new CardActions();
   
   
    public static void main(String[] args) {
        UserNumofCards = 1; //giving the user 2 cards to start
        DealerNumofCards = 1;
        DealCards();
        launch(args);
    }

   
    public static void DealCards(){
        UserHand = new String[5];
        DealerHand = new String[5];
        
        int i;
        wasItUser = true;
        for(i=0; i<=UserNumofCards; i++){
            UserHand[i]= gameActions.getCard(wasItUser); 
        }

         wasItUser = false;
         for(i=0; i<=DealerNumofCards; i++){
            DealerHand[i]= gameActions.getCard(wasItUser);
          }
    }
    /*
    If the user decides to hit.
    This is also called if the dealerhand is less than or equal to 17.
    */
    public static void Hit(){
        boolean User = wasItUser;
        DealerHand = new String[5];    
        UserHand = new String[5];  
    if(User == true){
            UserNumofCards = UserNumofCards + 1;
            UserHand[UserNumofCards] = gameActions.getCard(wasItUser);
            Image usercard = new Image("Cards/"+UserHand[UserNumofCards]+".png");
            ImageView UserCard = new ImageView(usercard);
            UserCard.setFitHeight(100);
            UserCard.setPreserveRatio(true);
            layout.add(UserCard, UserNumofCards + 15, 25);
        wasItUser = false;
    }
    else{
        if(DealerHandValue < 17){
            DealerNumofCards = DealerNumofCards + 1;
            DealerHand[DealerNumofCards] = gameActions.getCard(wasItUser);
            Image dealercard = new Image("Cards/"+DealerHand[DealerNumofCards]+".png");
            ImageView DealerCard = new ImageView(dealercard);
            DealerCard.setFitHeight(100);
            DealerCard.setPreserveRatio(true);
            layout.add(DealerCard, DealerNumofCards + 15, 5);
        wasItUser = false;
        }
    }
}
    
   public static void Stay(){
      wasItUser = false;
   }
   
   //ending the game and deciding the winner
   public static void GameOver(){
       boolean UserWon;
       Label winner = new Label();
       UserWon = gameActions.UserWinner();
       if(UserWon == true){
           winner.setText("YOU WON!"); 
           layout.add(winner, 30, 15);
       }
       else{
           winner.setText("Better luck next time.");
           layout.add(winner, 30, 15);
       }
   }

   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        int i;
      
       layout.setHgap(10);
       layout.setVgap(10);
            
       hit.setText("Hit");
       stay.setText("Stay");

       layout.add(hit, 1, 1);
       layout.add(stay, 2, 1);

       hit.setOnAction(this);
       stay.setOnAction(this);
       
        

        //the code below now works and adds both cards for the user
        for(i=0; i<=UserNumofCards; i++){
            Image usercard = new Image("Cards/"+UserHand[i]+".png");
            ImageView UserCard = new ImageView(usercard);
            UserCard.setFitHeight(100);
            UserCard.setPreserveRatio(true);
            layout.add(UserCard, i+15, 25);
        }
        
        for(i=0; i<=DealerNumofCards; i++){
            Image dealercard = new Image("Cards/"+DealerHand[i]+".png");
            ImageView DealerCard = new ImageView(dealercard);
            DealerCard.setFitHeight(100);
            DealerCard.setPreserveRatio(true);
            layout.add(DealerCard, i+15, 5);
        }
         Image firstcard  = new Image("Cards/BackOfCard.PNG");
          ImageView firstCard = new ImageView(firstcard);
          firstCard.setFitHeight(100);
          firstCard.setPreserveRatio(true);
          layout.add(firstCard, 15, 5);

        Scene scene = new Scene(layout, 800, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
        public void handle(ActionEvent event){
            if(event.getSource()==hit){
            wasItUser = true;
            Hit();
            }
            if(event.getSource()==stay){
                while(DealerHandValue < 17){
                 wasItUser = false;
                 Hit();
                }
                GameOver();
                wasItUser = false;
                Hit();
            }
        }
   
    

}

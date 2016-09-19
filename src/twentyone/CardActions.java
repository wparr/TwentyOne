/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyone;

/**
 *
 * @author wparr
 */
public class CardActions {
    
    
     public static String getCard(boolean wasItUser){
        boolean User;
        User = TwentyOne.wasItUser;
         
        
        String[] SuitHolder;
        String Suit;
        int Value;
        String Card;
        
        
        SuitHolder = new String[4];
        //0= diamonds, 1= hearts, 2=clubs, 3=spades
        SuitHolder[0]="d";
        SuitHolder[1]="h";
        SuitHolder[2]="c";
        SuitHolder[3]="s";
       
        Suit = SuitHolder[(int)(Math.random()*4)];
        Value = (int)(Math.random()*13+1);
        
        if(User == true){
            if(Value <= 10 && Value > 1){
                TwentyOne.UserHandValue = TwentyOne.UserHandValue + Value;
            }
            else if(Value == 1){
                TwentyOne.UserHandValue = TwentyOne.UserHandValue + 11;
            }
            else{
                TwentyOne.UserHandValue = TwentyOne.UserHandValue + 10;
            }
        }
        else{
            if(Value <= 10 && Value > 1){
                TwentyOne.DealerHandValue = TwentyOne.DealerHandValue + Value;
                
            }
            else if(Value == 1){
                TwentyOne.DealerHandValue = TwentyOne.DealerHandValue + 11;
            }
            else{
                TwentyOne.DealerHandValue = TwentyOne.DealerHandValue + 10;
            }
        }
        
        
        Card = Value+Suit;
        
        return Card;
    }
  //this decides who is the winner and is called from the gameover method
  public static boolean UserWinner(){
      boolean winner; //checks if the user won
      if((TwentyOne.UserHandValue > TwentyOne.DealerHandValue) && (TwentyOne.UserHandValue < 21) ){
          winner = true;
           return winner;
      }
      else if((TwentyOne.DealerHandValue > 21) && (TwentyOne.UserHandValue < 21)){
          winner = true;
           return winner;
      }
      else{
          winner = false;
           return winner;
      }
     
      }
}

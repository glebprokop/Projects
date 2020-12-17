import java.util.Scanner;
import java.security.SecureRandom;
import java.security.Key;
import java.security.MessageDigest;



public class Task3{
    
    public static void main(String[]args) throws Exception{
        //System.out.println("bla");
        String[]possibleWays = args;
        Scanner inputScanner;
        inputScanner = new Scanner(System.in);
        newGame ourArray = new newGame();
        ourArray.arr=possibleWays;
        ourArray.inputScanner=inputScanner;
        ourArray.checking();
}
}

class newGame{
    int comp_move = 0;
    int moveUser = 0;

    String []arr;
    Scanner inputScanner;

    void checking() throws Exception  {
        for(int i=0; i<arr.length; i++) {
            for (int j=i+1; j<arr.length; j++) {
               if(arr[i] == arr[j]) {
                  System.out.println("Sorry, your input is uncorrect! Please, restart the program!");
                  return;
                }
}
}
        if (arr.length%2!=1 || arr.length<3){
            System.out.println("Sorry, your input is uncorrect! Please, restart the program!");
            return;
}
else{
    this.newGame();    
}
}

    void newGame() throws Exception {
        //comp move
        comp_move = (int) (Math.random()*arr.length);
        

        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] values = new byte[20];
        random.nextBytes(values);
        StringBuilder sb = new StringBuilder();
        for (byte b : values) {sb.append(String.format("%02x", b));}
        
        String concaten_str = sb + arr[comp_move];
        byte[] concaten_byte = concaten_str.getBytes("UTF-8");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(concaten_byte);
        StringBuilder fgd = new StringBuilder();
        for (byte q : digest) {fgd.append(String.format("%02x", q));}
        System.out.println ("Calculate key for check:" + "  " + fgd);


        System.out.println("Available moves:");
        for (int q = 0; q < (arr.length); q++) {
            System.out.println((q+1)+ " - " + arr[q]);}
        System.out.println("0 - Exit");
        System.out.println("Enter your move:");
        moveUser=this.getMoveUser();
        
        if (moveUser==0){System.out.println("Exit");}
        

        else{
            moveUser= moveUser-1;
            int resulting = this.comparing();
            System.out.println("Your move:" + arr[moveUser]);
            System.out.println("Comp move:" + arr[comp_move]);


            switch (resulting){
                case 0:  System.out.println("Tie!");
                System.out.println ("The first key:" + "  " + sb);
                break;
                case 1:  System.out.println("You win!");
                System.out.println ("The first key:" + "  " + sb);
                break;
                case -1:  System.out.println("You lose!");
                System.out.println ("The first key:" + "  " + sb);
                break;
        }
    }
}

    int getMoveUser(){
        String userInput = inputScanner.nextLine();
        try{int rearm = Integer.parseInt (userInput);
            if (rearm<0 || rearm >arr.length){
                System.out.println("Incorrect input! Please, repeat!");
                return this.getMoveUser();}
            return(rearm);
        }
        catch (Exception e) {
            System.out.println("Incorrect input! Please, repeat!");
            return this.getMoveUser();
        }
}

    int comparing(){
        int koff = moveUser-comp_move;
        int len = arr.length/2;
       // System.out.println(comp_move + "  " + moveUser + "  " + koff + "  " + len);
        if (comp_move==moveUser){return 0;}
        else{
            if ((koff>0 && koff<=len)||(koff<0 && koff<len*-1)){return 1;}
            else{return -1;}            
        }
}
}

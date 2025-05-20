package ATMPROJECT;


import java.time.*;
import java.time.format.*;
import java.util.HashMap;
import java.util.Scanner;

class TransactionHistory{
    
    Node head;
    TransactionHistory(){
        head = null;
    }

    class Node{
        int amount;
        String type;
        String Date;
        double remainbalance;
        Node next;

        Node(int amt,String typ,double Rbalance){
            amount = amt;
            type = typ;
            Date = DatandTime();
            remainbalance = Rbalance;
            next = null;
        }

        String DatandTime(){

        LocalDateTime DT = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = DT.format(format);
        return formatted;
        }

    }

    }




class DB extends TransactionHistory{
       

        HashMap<Integer,Customers> hashMap= new HashMap<>();

        class Customers{
           
            public
            
            int accno;
            String name;
            String dob;
            int card_pin;
            long mobileNo;
            final int Pin_size;
           

            Customers(int a,String n,String d,long mob,int c){

                    accno = a;
                    name = n;
                    dob = d;
                    mobileNo = mob;
                    card_pin = c;
                    Pin_size = 4;
                   

            }

            
            int getPinfromDB(){
                return card_pin;
            }
            String getName(){
                return name;
            }
            int getAcc(){
                return accno;
            }
            String getDob(){
                return dob;
            }
            long getphone(){
                return mobileNo;
            }
        }

        DB(){
            hashMap.put(1234, new Customers(1111,"Yogesh", "21.07.2005", 701085092, 1234));
            hashMap.put(5678, new Customers(2222,"Harish", "22.01.2008", 801085092, 5678));
            hashMap.put(4321, new Customers(3333,"Naveen", "18.05.2005", 901085092, 4321));
            hashMap.put(8765, new Customers(1234,"Sathish", "05.03.2004", 601085092, 8765));
        }
    
}


class Bank  extends DB {

    TransactionHistory ls = new TransactionHistory();
    int card_no;
    int pin;
    boolean results;

    Bank(int c_no){
        card_no = c_no;
        results = true;
       
        
    }
    DB db = new DB();
    DB.Customers customers ;
    Scanner scan = new Scanner(System.in);
    
       
    double balance = 0;
    int tempamount = 0;

    
    void viewDetails(){
        System.out.println("*********************\n");
        System.out.println("ACCOUNT DETAILS\n");
        System.out.println("Account number : "+ customers.getAcc());
        System.out.println("Customer Name : " + customers.getName());
        System.out.println("DOB  : " + customers.getDob());
        System.out.println("PHONE NUMBER  : " + customers.getphone());
        System.out.println("\n*********************\n");
    }

    void viewBalance(){
        System.out.println("*********************");
        System.out.println("BALANCE PARUNGA ");
        System.out.println("AVAILABLE BALANCE : " + balance);
        System.out.println("*********************\n");
    }

    void deposit(int amount){
        System.out.println("*********************\n");
        if(amount<=0){
            System.out.println("Enter valid amount :)");
            return;
        }

        balance += amount;  
        System.out.println("Successfully credited Enjoy bro...\n");
        insertTransactions("deposit", amount ,balance);
        viewBalance();
    }

    void withdrawl(int amount){
        System.out.println("*********************\n");

        if(amount<=0 || balance<=0 || amount>balance){
            System.out.println("KASU ELLA BRO -> INSUFFICIENT MONEY");
            return;
        }

        balance -= amount;  
        System.out.println("\nCollect rs..." + amount);
        insertTransactions("Withdrawl", amount,balance);
        viewBalance();
    }

    void start(){

        if (Pincheck()) {
            return;
        }
        
        int choice;

       
        while(true){
          
          System.out.println("-----------TERV BANK UNGALAI ANBODU VARAVERKIRADHU :) !!--------");
          System.out.println(" ");
          System.out.println("PRESS 1 --> VIEW DETAILS");
          System.out.println("PRESS 2 --> VIEW BALANCE");
          System.out.println("PRESS 3 --> DEPOSIT");
          System.out.println("PRESS 4 --> WITHDRAWL");
          System.out.println("PRESS 5 --> VIEW ALL TRANSCATIONS");
          System.out.println("PRESS 6 --> DELETE LAST TRANSCATION");
        //System.out.println("PRESS 7 --> CHANGE PIN NUMBER");
          System.out.println("PRESS 7 --> EXIT");

          System.out.println();
          System.out.println("***************************************************************");
          
          System.out.println("Enter your choice : ");
          choice = scan.nextInt();

          switch(choice) {
            case 1:
                viewDetails();
                break;
          
            case 2:
                viewBalance();
                break;
          
            case 3:
                System.out.println("\nENter the Amount to deposite :");
                tempamount = scan.nextInt();
                if(tempamount>=0)
                     deposit(tempamount);
                else
                    System.out.println("Enter valid amount...");
                break;
          
            case 4:
                System.out.println("\nENter the Amount to withdrawl :");
                tempamount = scan.nextInt();
                if(tempamount>=0)
                     withdrawl(tempamount);
                else
                    System.out.println("Enter valid amount...");
                break;
                
            
          
            case 5:
                ViewAllTransactions();
                break;
            
            case 6:
                 deleteLastTransaction();
                 break;
            
            // case 7:
            //     changePin(card_no);
            //     break;
            
            case 7:
                System.exit(0);
                break;

            default:
                System.out.println("Enter valid choice :(");
                break;
          }
        }
       
    }

    //  void changePin(int c){
    //     int tempPin;
    //     int cofrimPin;
    //     int size;

    //     DB.Customers cPin = hashMap.get(card_no);
    //     System.out.println("Enter the new Pin : ");

    //     tempPin = scan.nextInt();
    //     System.out.println("Re-Enter the Pin : ");
    //     cofrimPin = scan.nextInt();

    //     size = String.valueOf(tempPin).length();
    //     if(tempPin == cofrimPin  && tempPin != cPin.card_pin && size == 4){
    //         cPin.card_pin = tempPin;
    //         System.out.println("Pin changed Successfully...");
    //         System.out.println("*************************");
    //         System.exit(0);
    //     }
       
    //     System.out.println(" Pin MisMatched");  
    //     System.out.println("*************************");
        
    //  }

// void loadFromFile() {
//     try {
//         BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
//         String line;
//         while ((line = reader.readLine()) != null) {
//             String[] data = line.split(",");
//             int acc = Integer.parseInt(data[0]);
//             String name = data[1];
//             String dob = data[2];
//             long mob = Long.parseLong(data[3]);
//             int pin = Integer.parseInt(data[4]);

//             hashMap.put(pin, new Customers(acc, name, dob, mob, pin));
//         }
//         reader.close();
//     } catch (IOException e) {
//         System.out.println("Error loading user file.");
//     }
// }


     void insertTransactions(String typ,int amt,double bal){
        Node newNode =  new Node(amt,typ,bal);
        if(head == null){
            head = newNode;
        }
        else{
            newNode.next = head;
            head = newNode;
        }
    }

    
    void ViewAllTransactions(){

        if(head == null){
            System.out.println("No Transaction History Bro!!");
            System.out.println("*************************");
        }

        else{
            Node temp = head;
            System.out.println("*****HISTORY*****\n");
            for(;temp!=null;temp= temp.next,System.out.println()){
                System.out.println("Type = " + temp.type + "\n" + "Amount = " + temp.amount + "\n" + "Balance = " + temp.remainbalance + "\n" + "Date = " + temp.Date);
            }
             System.out.println("*****THANK YOU*****\n");
        }

    }

    void deleteLastTransaction(){
        if(head == null){
            System.out.println("No Transaction History Bro!!");
            System.out.println("*************************");
        }

        else{
            Node temp = head;
            head = temp.next;
            temp.next = null;

            System.out.println("Last Transaction deleted..");
             System.out.println("*************************");
        }
    }
    
    // void getPin(){
    //     // Scanner scan = new Scanner(System.in);
       
    //     System.out.println("hello");
    // }

 boolean Pincheck(){
       
        if(hashMap.containsKey(card_no)){
             
             
             System.out.println("Enter the pin : ");
             pin = scan.nextInt();

            
            // Customers customer = db.hashMap.get(card_no);

            
           
           DB.Customers tempcustomers = db.hashMap.get(card_no);
            
            if(pin == tempcustomers.getPinfromDB()){
                customers = tempcustomers;
                return false;
            }

            return true;
            
        }

        else{
            System.out.println("********************");
            System.out.println("Card is invalid...");
            System.out.println("********************");
            return true;
        }
        

    }
    


}

public class TervBank {
   
    public static int getCard(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the card :");
        int card = scan.nextInt();
         
        return card;
    }

    public static void main(String[] args) {
            int card = getCard();
            Bank customer1 = new Bank(card);
            customer1.start();
        }

        /*
        VALID    CARDS AND PIN:
        1      1234    -    1234
        2      5678    -    5678  
        3      4321    -    4321
        4      8765   -     8765
            
        */
  
       
    }


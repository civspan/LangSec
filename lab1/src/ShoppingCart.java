import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;

public class ShoppingCart {
    

    
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Type "
                    + "\n0 to exit"
                    + "\n1 to run frontend with buggy API"
                    + "\n2 to run frontend with fixed API "
                    + "\n3 to reset resources: ");
            int choice = 0;
        
            try {
                choice = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Not an int!");
                e.printStackTrace();
            }
            Wallet w;
            Pocket p;
            switch(choice) {
                case 0: /* Type 0 to exit */
                        return;
                case 1: /* Type 1 to run frontend w. buggy API*/
                        try {
                            w = new Wallet();
                            p = new Pocket();
                            String prod;
                            int balance;
                            int price;

                            balance = w.getBalance();
                            System.out.println("Your balance: " + balance + " credits");
                            System.out.print(Store.asString());
                            System.out.print("What do you want to buy?: ");
                            prod = br.readLine();

                            if(! Store.products.containsKey(prod)) {
                                System.out.println("Product not found");
                                return;
                            }

                            price = Store.products.get(prod);
                            if( (balance-price) < 0 ) {
                                System.out.println("You cannot afford that product");
                                return;
                            }
                            System.out.println("Pausing after comparing price and balance, but before updating balance..");
                            br.readLine();
                            w.setBalance( (balance-price) );
                            p.addProduct( prod );
                            System.out.println("Your new balance is: " + (balance-price) + " credits");
                            w.close();      
                            p.close();
                        } catch(Exception e){
                            System.out.println("Error error!");
                            e.printStackTrace();
                        } 
                         break;
                case 2: /* Type 2 to run frontend w. secured API*/
                        try{
                            w = new Wallet();
                            p = new Pocket();
                            String prod;
                            int balance;
                            int price;
                            balance = w.getBalance();
                            System.out.println("Your balance: " + balance + " credits");
                            System.out.print(Store.asString());
                            System.out.print("What do you want to buy?: ");
                            prod = br.readLine();

                            if(! Store.products.containsKey(prod)) {
                                System.out.println("Product not found");
                                return;
                            }
                            price = Store.products.get(prod);
                            
                            /* call to safe withdraw method that locks wallet.txt */
                            w.safeWithdraw(price);
                            
                            balance = w.getBalance();
                            System.out.println("Your new balance is: " + (balance-price) + " credits");
                            p.safeAddProduct( prod );

                            w.close();      
                            p.close();
                        }catch (Exception e) {
                            System.out.println("IO error, stacktrace: ");
                            e.printStackTrace();
                            try {
                                sleep(100);
                            } catch (InterruptedException ex) {
                                System.out.println("Sleep interrupted");
                            }
                        }
                        break;
                case 3:    /* Type 3 to reset wallet and pocket */
                        try {
                            w = new Wallet();
                            p = new Pocket();
                            w.setBalance(30000);
                            p.resetPocket();
                            p.close();
                            w.close();
                        } catch (Exception e){
                            System.out.println("Error error!");
                            e.printStackTrace();
                            
                        }
                        break;
                default:
                        System.out.println("Not a valid choice");;
            }
        }
    }
}

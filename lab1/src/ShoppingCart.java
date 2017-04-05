import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShoppingCart {
    

    
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Type 0 to exit\n1 to run buggy frontend \n2 to reset credits and pocket: ");
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
                case 0: 
                        return;
                case 1: 

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
                          
                            w.safeWithdraw(price);
                         //   System.out.println("Your new balance is: " + (balance-price) + " credits");
                            
                            p.addProduct( prod );
                            
                            w.close();      
                            p.close();
                      } catch(Exception e){
                              System.out.println("Error error!");
                              e.printStackTrace();
                      } 
                        break;
                case 2:    
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
            }
        }
    }
}

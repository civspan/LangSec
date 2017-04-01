import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCart {
	
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Wallet w = new Wallet();
            Pocket p = new Pocket();
            String prod;
            int balance;
            int price;
        
            balance = w.getBalance();
            System.out.println("Your balance: " + balance + " credits");
       //     System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
            w.setBalance( (balance-price) );
            p.addProduct(prod + '\n');
            System.out.println("Your new balance is: " + (balance-price) + " credits");
            w.close();           
        } catch(Exception e){
            System.out.println("Error error!");
            e.printStackTrace();
        } 
    }
    
/*    private boolean checkInput(String p) {
        ArrayList l = new ArrayList();
    } */
}

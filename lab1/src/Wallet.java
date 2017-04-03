import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Wallet {
   /**
    * The RandomAccessFile of the wallet file
    */  
    private RandomAccessFile file;
   
    final Lock lock = new ReentrantLock();
   
    public void safeWithdraw(int valueToWithdraw) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int price = valueToWithdraw;
        lock.lock();
        try{           
            int balance = getBalance();
              if( (balance-price) < 0 ) {
                  System.out.println("You cannot afford that product. Current balance: " + balance);
              }else {
                  System.out.println("Pausing after comparing price and balance, but before updating balance..");
                  br.readLine();
                  setBalance( (balance-price) );
                  System.out.println("Your new balance is: " + (balance-price) + " credits");
                   
              }
        } catch(Exception e){
            System.out.println("Error error!");
            e.printStackTrace();
        } finally {
            close(); 
            lock.unlock();
        }
       
    }	

   /**
    * Creates a Wallet object
    *
    * A Wallet object interfaces with the wallet RandomAccessFile
    */
    public Wallet () throws Exception {
	this.file = new RandomAccessFile(new File("wallet.txt"), "rw");
    }

   /**
    * Gets the wallet balance. 
    *
    * @return                   The content of the wallet file as an integer
    */
    public int getBalance() throws IOException {
	this.file.seek(0);
	return Integer.parseInt(this.file.readLine());
    }

   /**
    * Sets a new balance in the wallet
    *
    * @param  newBalance          new balance to write in the wallet
    */
    public void setBalance(int newBalance) throws Exception {
	this.file.setLength(0);
	String str = new Integer(newBalance).toString()+'\n'; 
	this.file.writeBytes(str); 
    }

   /**
    * Closes the RandomAccessFile in this.file
    */
    public void close() throws Exception {
	this.file.close();
    }
}

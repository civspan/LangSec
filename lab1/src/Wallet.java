import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Wallet {
   /**
    * The RandomAccessFile of the wallet file
    */  
    private RandomAccessFile file;
    private FileLock lock;
    FileChannel channel;
   
    /**
    * Creates a Wallet object
    *
    * A Wallet object interfaces with the wallet RandomAccessFile
    */
    public Wallet () throws Exception {
	this.file = new RandomAccessFile(new File("wallet.txt"), "rw");
        this.channel = file.getChannel();
    }
    
    public void safeWithdraw(int valueToWithdraw) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int price = valueToWithdraw;
        this.lock  = channel.lock();
        int balance = getBalance();
                           
        if( balance < price ) {
            throw new Exception("Not enough schmeckles");
        }
        else {          
            System.out.println("Pausing after reading price and checking it against balance, but before updating balance..");
            br.readLine();
            setBalance( (balance-price) );
        }
        lock.release();
        close(); 
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

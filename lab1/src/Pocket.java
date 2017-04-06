import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


public class Pocket {
   /**
    * The RandomAccessFile of the pocket file
    */
   private RandomAccessFile file;
    private FileLock lock;
    FileChannel channel;


   /**
    * Creates a Pocket object
    * 
    * A Pocket object interfaces with the pocket RandomAccessFile.
    */
    public Pocket () throws Exception {
        this.file = new RandomAccessFile(new File("pocket.txt"), "rw");
        this.channel = file.getChannel();
    }

   /**
    * Adds a product to the pocket. 
    *
    * @param  product           product name to add to the pocket (e.g. "car")
    */
    public void addProduct(String product) throws Exception {
        this.file.seek(this.file.length());
        this.file.writeBytes(product+'\n'); 

    }
    public void safeAddProduct(String product) throws Exception {
        this.lock  = channel.lock();
        this.file.seek(this.file.length());
        this.file.writeBytes(product+'\n'); 
        lock.release();
        close(); 
    }
   /**
    * Resets the pocket. Written by group 31.
    */
    public void resetPocket() throws Exception {
        this.file.setLength(0);
    }
   /**
    * Closes the RandomAccessFile in this.file
    */
    public void close() throws Exception {
        this.file.close();
    }
}

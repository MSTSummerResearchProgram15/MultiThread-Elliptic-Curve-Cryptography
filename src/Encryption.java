import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Encryption implements Runnable{
	
	public void run(){
		ByteReaderWriter bytes = new ByteReaderWriter(); //create a new nBytes object
		File fin = new File("book.txt");
		long length = fin.length();
		int blockSize = 25; //modify the block size here
		long blocks = (long)Math.ceil((double)length/(double)blockSize); //calculates the total number of blocks needed
		InputStream in = new FileInputStream(fin);
		for(int i = 0; i < blocks; i++){
			array = new byte[blockSize];
			array = bytes.readFile(blockSize, in);
			encrypt.run();
		}
	}
	
}

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadManager {
	
	public static void main(String[] args) throws IOException{
		//Preprocessing - split file into chunks
		ByteReaderWriter rw = new ByteReaderWriter();
		File fin = new File("book.txt");
		long fileLength = fin.length();
		int fileSize = 50;
		InputStream in = new FileInputStream(fin);
		int i = 0;
		int eof;
		do{ //check to see if the end of the file has been reached
			
		}while(eof != -1);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++){
        	Runnable worker = new Encryption();
        	executor.execute(worker);
        }
        executor.shutdown();
	}
}

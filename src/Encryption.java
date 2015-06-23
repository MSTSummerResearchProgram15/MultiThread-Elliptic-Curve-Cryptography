import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Encryption implements Runnable{
	File fin, fout;
	byte[] array;
	OutputStream os;
	
	public Encryption(File fin, File fout){
		this.fin = fin;
		this.fout = fout;
	}
		
	public void run(){
		ByteReaderWriter bytes = new ByteReaderWriter(); //create a new nBytes object
		InputStream in = null;
		try {
			in = new FileInputStream(fin);
		} catch (FileNotFoundException e) {}
		int blockSize = 128;
		array = new byte[blockSize];
		try {
			array = bytes.readFile(blockSize, in);
		} catch (IOException e) {}
		try {
			os = new FileOutputStream(fout);
		} catch (FileNotFoundException e) {}
		
		try {
			bytes.writeFile(array, os);
		} catch (IOException e) {}
		
	}
	
}

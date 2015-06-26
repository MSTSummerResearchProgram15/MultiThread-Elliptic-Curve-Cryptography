import it.unisa.dia.gas.jpbc.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Encryption implements Runnable{
	File fin, fout;
	byte[] array;
	InputStream in;
	FileOutputStream out;
	Params params;
	public Element c1, c2, reencrypt;
	public Encryption(File fin, File fout, Params params){
		this.fin = fin;
		this.fout = fout;
		this.params = params;
	}
		
	public void run(){
		Element plaintext;
		ByteReaderWriter bytes = new ByteReaderWriter();
		long length = fin.length();
		byte[] result;
		try {
			in = new FileInputStream(fin);
		} catch (FileNotFoundException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		try {
			out = new FileOutputStream(fout, true);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		int blockSize = 128; //How many bytes of file to encrypt at a time
		long blocks = (long)Math.ceil((double)length/(double)blockSize); //How many blocks the file will be encrypted in
		for(int i = 0; i < blocks; i++){
			array = new byte[blockSize];
			try {
				array = bytes.readFile(blockSize, in); //Read the plaintext file
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	
			//encrypt here
			plaintext = params.getgt().newRandomElement();
			plaintext.setFromBytes(array);
			c1 = params.getOwnerPK().powZn(params.getk());
			c2 = params.getz_k().mul(plaintext);
			result = new byte[c1.getLengthInBytes() + c2.getLengthInBytes()]; //set the byte array size = size of both ciphertexts
			System.arraycopy(c1.toBytes(), 0, result, 0, c1.getLengthInBytes());
			System.arraycopy(c2.toBytes(), 0, result, c1.getLengthInBytes(), c2.getLengthInBytes()); //concatenate both ciphertexts into 1 array
			
			try {
				bytes.writeFile(result, out); //write the result to output file
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Files.delete(fin.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
			
	
}

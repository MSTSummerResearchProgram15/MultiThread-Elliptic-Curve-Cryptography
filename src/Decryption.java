import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import it.unisa.dia.gas.jpbc.Element;


public class Decryption implements Runnable{
	File fin, fout;
	Params params;
	byte[] cipher1, cipher2, result;
	Element decrypt, c1, c2;
	BufferedWriter bw;
	
	public Decryption(File fin, File fout, Params params){
		this.fin = fin;
		this.fout = fout;
		this.params = params;
	}

	public void run(){
		ByteReaderWriter bytes = new ByteReaderWriter();
		InputStream in = null;
		OutputStream os = null;
		String temp = null;
		
		long length = fin.length(); //get the length of the input file
		
		try {
			in = new FileInputStream(fin);
		} catch (FileNotFoundException e) {}
		
		int ciphertextSize = (int)length/2;
		cipher1 = new byte[ciphertextSize];
		cipher2 = new byte[ciphertextSize];
		
		try {
			cipher1 = bytes.readFile(ciphertextSize, in);
		} catch (IOException e) {}
		
		try {
			cipher2 = bytes.readFile(ciphertextSize, in);
		} catch (IOException e) {}
		
		try {
			in.close();
		} catch (IOException e) {}
		
		c1 = params.getg1().newRandomElement();
		c2 = params.getg1().newRandomElement();

		c1.setFromBytes(cipher1);
		c2.setFromBytes(cipher2);
		
		//Begin decryption here
		Element ialpha = params.getReEncryptionKey().powZn(params.getUserISK());
		decrypt = c2.div(ialpha);
		result = new byte[decrypt.getLengthInBytes()];
		result = decrypt.toBytes();
		
		try {
			temp = new String(result, "UTF-8");
		} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
		
		char[] charArray = temp.toCharArray();
		
		try {
			bw = new BufferedWriter(new FileWriter(fout));
		} catch (IOException e1) {e1.printStackTrace();}
		
		try {
			bytes.writeFile(charArray, bw);
		} catch (IOException e) {e.printStackTrace();}
		
		try {
			bw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}

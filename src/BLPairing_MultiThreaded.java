import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.jpbc.PairingPreProcessing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.util.math.BigIntegerUtils;


public class BLPairing_MultiThreaded{
	
	public static Element ciphertext;
	public static Element c1, c2, reencrypt;
	public static Element decrypt;
	public static Element decrypt_user1;
	public byte[] array;
	public static ArrayList<Element> ciphertext1, ciphertext2, reencrypttext;
	public static byte[] result;
	public static int x;
	public static ArrayList<String> decoded;
	BufferedWriter bw;
	ThreadedEncryption encrypt = new ThreadedEncryption();
	
	public void readFile() throws IOException{
		nBytes bytes = new nBytes(); //create a new nBytes object
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

//This class merges any number of input files into one output file
public class FileMerge {
	BufferedWriter bw;
	BufferedReader br;
	File fin, fout;
	String[] inputFiles;
	String outputFile;
	int numFiles;
	
	FileMerge(String[] inputFiles, String outputFile, int numFiles){
		this.outputFile = outputFile;
		this.numFiles = numFiles;
		this.inputFiles = inputFiles;
	}
	
	void mergeFiles() throws IOException{
		ByteReaderWriter rw = new ByteReaderWriter();
		fout = new File(outputFile);
		bw = new BufferedWriter(new FileWriter(fout, true)); //Set the FileWriter to append
		
		for(int i = 0; i < numFiles; i++){ //iterate through each input file
			fin = new File(inputFiles[i]);
			int fileSize = (int)fin.length();
			br = new BufferedReader(new FileReader(fin));
			char[] input = rw.readFile(fileSize, br); //read the input file
			rw.writeFile(input, bw); //write the file to the output file
			Files.delete(fin.toPath()); //delete the input file
			br.close();
		}
		bw.close();
	}
}

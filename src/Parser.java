import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	
	File file;
	Scanner in;
	
	public Parser(String filename) {
		file = new File(filename);
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean hasMoreCommands() {
		return true;
	}
	
	public void advance() {
		
	}
	
	public String commandType() {
		return "";
	}
	
	public String arg1() {
		return "";
	}
	
	public int arg2() {
		return 0;
	}
	
public static void main (String[] args) {
		
	}
}

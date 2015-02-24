import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {
	
	File file;
	Scanner in;
	String currentCommand;
	String commandType;
	String argument1;
	int argument2;
	StringTokenizer st;
	HashMap <String, String> allCommands;
	CodeWriter cw;
	
	
	public Parser(String filename) {
		file = new File(filename);
		try {
			in = new Scanner(file);
			cw = new CodeWriter(new File("out.asm"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allCommands = new HashMap <String, String>();
		allCommands.put("push", "C_PUSH");
		allCommands.put("pop", "C_POP");
		allCommands.put("add", "C_ARITHMETIC");
		allCommands.put("sub", "C_ARITHMETIC");
		allCommands.put("neg", "C_ARITHMETIC");
		allCommands.put("eq", "C_ARITHMETIC");
		allCommands.put("gt", "C_ARITHMETIC");
		allCommands.put("lt", "C_ARITHMETIC");
		allCommands.put("and", "C_ARITHMETIC");
		allCommands.put("or", "C_ARITHMETIC");
		allCommands.put("not", "C_ARITHMETIC");
		allCommands.put("label", "C_LABEL");
		allCommands.put("function", "C_FUNCTION");
		allCommands.put("return", "C_RETURN");
		allCommands.put("goto", "C_GOTO");
		allCommands.put("if-goto", "C_IF");
	}
	
	public boolean hasMoreCommands() {
		if (in.hasNext() && in.hasNextLine()) {
			return true;
		}
		return false;
	}
	
	public void advance() {
		if (hasMoreCommands()) {
			this.currentCommand = in.nextLine();
			//System.out.println(currentCommand);
		}

	}
	
	public String commandType() {
		st = new StringTokenizer(currentCommand);
		return allCommands.get(st.nextToken());
	}
	
	public String arg1() {
		st = new StringTokenizer(currentCommand);
		st.nextToken();
		return st.nextToken();
	}
	
	public int arg2() {
		st = new StringTokenizer(currentCommand);
		st.nextToken();
		st.nextToken();
		return Integer.parseInt(st.nextToken());
	}
	
	public void writeOutput() throws IOException {
		commandType = commandType();
		argument1 = arg1();
		if (commandType.equals("C_PUSH") || commandType.equals("C_POP")) {
			argument2 = arg2();
			cw.WritePushPop(commandType, argument1, argument2);
		}
	}
	
	public void close() throws IOException {
		cw.Close();
	}
	
	public static void main (String[] args) throws IOException {
		Parser p = new Parser("test.txt");
		while (p.hasMoreCommands()) {
			p.advance();
			p.writeOutput();
		}
		p.close();
	}
}

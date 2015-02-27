import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {
	
	String fileName;
	Scanner in;
	String currentCommand;
	String commandType;
	String commandKey;
	String nextLine;
	boolean validCommand;
	StringTokenizer st;
	HashMap <String, String> allCommands;
	
	
	public Parser(File file) {
		try {
			fileName = file.getName();
			in = new Scanner(file);
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
		while (in.hasNext() && in.hasNextLine()) {
			nextLine = in.nextLine();
			//System.out.println("next line is " + nextLine);
			if (isValidCommand(nextLine)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidCommand(String test) {
		CharSequence space = "//";
		if(test.contains(space)) {
			test = test.substring(0, nextLine.indexOf('/'));
		}
		if (test.trim().length() != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void advance() {
		this.currentCommand = nextLine;
		//System.out.println("current command is: " + currentCommand);
	}
	
	public String commandType() {
		st = new StringTokenizer(currentCommand);
		commandKey = st.nextToken();
		return allCommands.get(commandKey);
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

	public String getCommandKey() {
		return commandKey;
	}

	public String getFileName() {
		return fileName;
	}

}

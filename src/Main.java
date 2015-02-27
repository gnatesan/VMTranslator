import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	Parser p;
	CodeWriter cw;
	File[] allFiles;
	ArrayList<Parser> allParsers;
	boolean isDirectory;
	String commandType;
	String argument1;
	int argument2;
	String outFile;
	
	public Main(File fileName) throws IOException {
		isDirectory = false;
		if (fileName.isFile()) {
			System.out.println("true");
			p = new Parser(fileName);
			outFile = fileName.getName().replace(".vm", ".asm");
			//outFile = "out1.asm";
			cw = new CodeWriter(new File(outFile));
		}
		else if (fileName.isDirectory()) {
			isDirectory = true;
			allParsers = new ArrayList<Parser>();
			allFiles = fileName.listFiles();
			for (File f : allFiles) {
				if (f.getName().endsWith(".vm")){
					outFile = f.getName().replace(".vm", ".asm");
					cw = new CodeWriter(new File(outFile));
					allParsers.add(new Parser(f));
				}
			}
		}	
	}
	
	public void runProgram() throws IOException {
		if (!isDirectory) {
			while (p.hasMoreCommands()) {
				p.advance();
				writeOutput(p);
			}
			cw.Close();
		}
		else {
			for (Parser in : allParsers) {
				outFile = in.getFileName().replace(".vm", ".asm");
				cw.setFileName(outFile);
				while (in.hasMoreCommands()) {
					in.advance();
					writeOutput(in);
				}
				cw.Close();
			}
		}
	}
	
	public void writeOutput(Parser use) throws IOException {
		commandType = use.commandType();
		if (commandType.equals("C_PUSH") || commandType.equals("C_POP")) {
			argument1 = use.arg1();
			argument2 = use.arg2();
			cw.WritePushPop(commandType, argument1, argument2);
			//System.out.println("p");
		}
		else if (commandType.equals("C_ARITHMETIC")) {
			cw.writeArithmetic(use.getCommandKey());
			//System.out.println("a");
		}
	}
	
	public static void main(String[] args) {
		//File f = new File(args[0]);
		File f = new File("StaticTest.vm");
		try {
			Main m = new Main(f);
			m.runProgram();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}

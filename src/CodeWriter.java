import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
	FileWriter fw;
	
	public CodeWriter(File outfile) throws IOException {
		fw = new FileWriter(outfile);
	}

	public void setFileName(String fileName) {
		
	}
	
	public void writeArithmetic(String command) {
		
	}
	
	public void WritePushPop(String PushPop, String segment, int index) throws IOException {
		if (PushPop.equals("C_PUSH")) {
			pushCommand(segment, index);
		}
		else 
			popCommand(segment, index);
	}
	
	public void pushCommand(String segment, int index) throws IOException {
		if (segment.equals("constant")) {
			fw.write("@" + Integer.toString(index));
			fw.write(System.lineSeparator());
			fw.write("D = A");
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("A = M");
			fw.write(System.lineSeparator());
			fw.write("M = D");
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M + 1");
			fw.write(System.lineSeparator());
		}
	}
	
	public void popCommand(String segment, int index) throws IOException {
		
	}
	
	
	public void Close() throws IOException {
		fw.close();
	}
	
	/*public static void main (String[] args) {
		try {
			CodeWriter cw = new CodeWriter("out.asm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

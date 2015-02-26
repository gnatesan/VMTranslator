import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class CodeWriter {
	FileWriter fw;
	//Stack <Integer> s;
	int temp1;
	int temp2;
	String staticSubString;
	
	public CodeWriter(File outfile) throws IOException {
		fw = new FileWriter(outfile);
		staticSubString = outfile.getName().replace(".vm", ".");
	}

	public void setFileName(String fileName) throws IOException {
		fw = new FileWriter(new File(fileName));
		staticSubString = fileName.replace(".vm", "."); //update static substring as new file is being written to
	}
	
	public void writeArithmetic(String command) throws IOException {
		switch(command) {
		case("add"): {
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M"); //go to address of number on top of stack
			fw.write(System.lineSeparator());
			fw.write("D = M"); //make the d register contain that number
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M"); //go to address of next highest number on stack
			fw.write(System.lineSeparator());
			fw.write("M = M + D"); //make that address contain the number added to what is in d register
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M + 1"); //increment stack pointer by 1
			fw.write(System.lineSeparator());
			break;
		}
		case("sub"): {
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M"); //go to address of number on top of stack
			fw.write(System.lineSeparator());
			fw.write("D = M"); //make the d register contain that number
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M"); //go to address of next highest number on stack
			fw.write(System.lineSeparator());
			fw.write("M = M - D"); //make that address contain the number subtracted from what is in d register
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M + 1"); //increment stack pointer by 1
			fw.write(System.lineSeparator());
			break;
		}	
		case("eq"): {
			popCommand();
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D"); //store what's on top of stack in R13
			fw.write(System.lineSeparator());
			popCommand(); //store what's next in stack in D register
			fw.write("@R13"); 
			fw.write(System.lineSeparator());
			fw.write("D = D - M"); //what was popped off 2nd - what was popped off first, store in D register
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTEQ_N"); 
			fw.write(System.lineSeparator());
			fw.write("D;JNE"); //if D register is not equal to 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is equal to 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTEQ_END_N");
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTEQ_N)");
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't equal to 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTEQ_END_N)");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			break;
		}	
		case("lt"): {
			popCommand();
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D"); //store what's on top of stack in R13
			fw.write(System.lineSeparator());
			popCommand(); //store what's next in stack in D register
			fw.write("@R13"); 
			fw.write(System.lineSeparator());
			fw.write("D = D - M"); //what was popped off 2nd - what was popped off first, store in D register
			fw.write(System.lineSeparator());
			fw.write("@IF_LT_N"); 
			fw.write(System.lineSeparator());
			fw.write("D;JLT"); //if D register is less than 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't less than 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTLT_END_N");
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_LT_N)");
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is less than 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTLT_END_N)");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			break;
		}
		case("gt"): {
			popCommand();
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D"); //store what's on top of stack in R13
			fw.write(System.lineSeparator());
			popCommand(); //store what's next in stack in D register
			fw.write("@R13"); 
			fw.write(System.lineSeparator());
			fw.write("D = D - M"); //what was popped off 2nd - what was popped off first, store in D register
			fw.write(System.lineSeparator());
			fw.write("@IF_GT_N"); 
			fw.write(System.lineSeparator());
			fw.write("D:JGT"); //if D register is greater than 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't less than 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTGT_END_N");
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_GT_N)");
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is greater than 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTGT_END_N)");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			break;
		}	
		case("or"): {
			popCommand();
			
			break;
		}
		case("and"): {
			popCommand();
			break;
		}
		case("not"): {
			popCommand();
			break;
		}
		case("neg"): {
			popCommand();
			break;
		}
		}
	}
	
	public void WritePushPop(String PushPop, String segment, int index) throws IOException {
		if (PushPop.equals("C_PUSH")) {
			switch(segment) {
			case("constant"): {
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A");
				fw.write(System.lineSeparator());
			}
			case ("local"): { //push local offset
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@LCL"); //set address to LCL, contains a memory location in it
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[LCL], another memory location
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("argument"): { //push argument offset
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@ARG"); //set address to ARG, contains a memory location in it
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[ARG], another memory location
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("this"): { //push this offset
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@THIS"); //set address to THIS, contains a memory location in it
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[THIS], another memory location
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("that"): { //push that offset
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@THAT"); //set address to THAT, contains a memory location in it
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[THAT], another memory location
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("pointer"): { //CONFUSED ABOUT THIS, SHOULD I ADD MEM[THIS] + OFFSET OR ADD 3 + OFFSET, LOOK IN BOOK
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@THIS");
				fw.write(System.lineSeparator());
				fw.write("D = D + M");
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("temp"): {
				fw.write("@" + Integer.toString(index));
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains the offset
				fw.write(System.lineSeparator());
				fw.write("@R5"); //go to first address of temp segment
				fw.write(System.lineSeparator());
				fw.write("D = D + A"); //D register contains R5 + offset, another memory location
				fw.write(System.lineSeparator());
				fw.write("A = D"); //set address to what's in D register, which is memory location you will push from
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			case ("static"): { //push static 3
				staticSubString += Integer.toString(index);
				fw.write("@" + staticSubString); //go to static address of offset, A = SimpleAdd.3
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
			}
			}
			this.pushCommand(); //push value in D register onto stack
		}
		else {
			switch(segment) {
			
			}
		}
	}
	
	//push what's in register d onto stack
	public void pushCommand() throws IOException {
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
	
	//pop what's on top of stack and put in register d
	public void popCommand() throws IOException {
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M");
			fw.write(System.lineSeparator());
			fw.write("D = M");
			fw.write(System.lineSeparator());
	}
	
	
	public void Close() throws IOException {
		fw.close();
	}

}

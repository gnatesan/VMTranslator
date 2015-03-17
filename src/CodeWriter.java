import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class CodeWriter {
	FileWriter fw;
	int temp1;
	int temp2;
	String staticSubString = "";
	String funcName;
	String returnAddress = "return";
	int countEQ = 0;
	int countLT = 0;
	int countGT = 0;
	int countCall = 0;
	
	public CodeWriter(File outfile) throws IOException {
		fw = new FileWriter(outfile);
		//staticSubString = outfile.getName().replace(".asm", "."); //i.e. SimpleAdd.vm -> SimpleAdd.
	}

	public void setFileName(String fileName) throws IOException {
		fw = new FileWriter(new File(fileName));
		staticSubString = fileName.replace(".asm", "."); //update static substring as new file is being written to
	}
	
	public void writeArithmetic(String command) throws IOException {
		switch(command) {
		case("add"): {
			popCommand(); //pop what's on top of stack, put in D register
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M"); //go to address of next highest number on stack
			fw.write(System.lineSeparator());
			fw.write("M = M + D"); //make that address contain the number added to what is in D register
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M + 1"); //increment stack pointer by 1
			fw.write(System.lineSeparator());
			break;
		}
		case("sub"): {
			popCommand(); //pop what's on top of stack, put in D register
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D");
			fw.write(System.lineSeparator());
			popCommand();
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("D = D - M");
			fw.write(System.lineSeparator());
			pushCommand();
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
			fw.write("@IF_NOTEQ_" + Integer.toString(countEQ)); 
			fw.write(System.lineSeparator());
			fw.write("D;JNE"); //if D register is not equal to 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is equal to 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTEQ_END_" + Integer.toString(countEQ));
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTEQ_" + Integer.toString(countEQ) + ")");
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't equal to 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTEQ_END_" + Integer.toString(countEQ) + ")");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			++countEQ;
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
			fw.write("@IF_LT_" + Integer.toString(countLT)); 
			fw.write(System.lineSeparator());
			fw.write("D;JLT"); //if D register is less than 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't less than 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTLT_END_" + Integer.toString(countLT));
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_LT_" + Integer.toString(countLT) + ")");
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is less than 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTLT_END_" + Integer.toString(countLT) + ")");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			++countLT;
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
			fw.write("@IF_GT_" + Integer.toString(countGT)); 
			fw.write(System.lineSeparator());
			fw.write("D;JGT"); //if D register is greater than 0, jump to label
			fw.write(System.lineSeparator());
			fw.write("D = 0"); //since D register isn't less than 0, set to 0 because comparison is false
			fw.write(System.lineSeparator());
			fw.write("@IF_NOTGT_END_" + Integer.toString(countGT));
			fw.write(System.lineSeparator());
			fw.write("0;JMP"); //jump to label
			fw.write(System.lineSeparator());
			fw.write("(IF_GT_" + Integer.toString(countGT) + ")");
			fw.write(System.lineSeparator());
			fw.write("D = -1"); //since D register is greater than 0, set to -1 because comparison is true
			fw.write(System.lineSeparator());
			fw.write("(IF_NOTGT_END_" + Integer.toString(countGT) + ")");
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			++countGT;
			break;
		}	
		case("or"): {
			popCommand(); //pop what's on top of stack and put in D register
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D"); //store what's in D register into Mem[R13]
			fw.write(System.lineSeparator());
			popCommand(); //pop next highest item on stack and put in D register
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("D = D | M"); //complete the OR operation, store in D register
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			break;
		}
		case("and"): {
			popCommand();
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("M = D"); //store what's in D register into Mem[R13]
			fw.write(System.lineSeparator());
			popCommand(); //pop next highest item on stack and put in D register
			fw.write("@R13");
			fw.write(System.lineSeparator());
			fw.write("D = D & M"); //complete the AND operation, store in D register
			fw.write(System.lineSeparator());
			pushCommand(); //push what's in register D onto top of stack, increment stack pointer
			break;
		}
		case("not"): {
			popCommand(); //pop what's on top of stack and put in D register
			fw.write("D = !D"); //not value in D register
			fw.write(System.lineSeparator());
			pushCommand(); //push value in D register onto top of stack
			break;
		}
		case("neg"): { //do you modify the number you are negating, and then update stack pointer back up?
			/*fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M - 1");
			fw.write(System.lineSeparator());
			fw.write("A = M");
			fw.write(System.lineSeparator());
			fw.write("M = -M");
			fw.write(System.lineSeparator());
			fw.write("@SP");
			fw.write(System.lineSeparator());
			fw.write("M = M + 1");
			fw.write(System.lineSeparator());*/
			popCommand(); //pop what's on top of stack and put in D register
			fw.write("D = -D"); //negate value in D register
			fw.write(System.lineSeparator());
			pushCommand(); //push value in D register onto top of stack
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
				break;
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
				break;
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
				break;
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
				break;
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
				break;
			}
			case ("pointer"): { //push pointer offset
				if (index == 0) {
					fw.write("@THIS");
					fw.write(System.lineSeparator());
				}
				else {
					fw.write("@THAT");
					fw.write(System.lineSeparator());
				}
				//fw.write("A = M");
				//fw.write(System.lineSeparator());
				fw.write("D = M");
				fw.write(System.lineSeparator());
				//this.pushCommand();
				break;
			}
			case ("temp"): { //push temp 5
				fw.write("@R" + Integer.toString(index + 5));
				fw.write(System.lineSeparator());
				//fw.write("A = M");
				//fw.write(System.lineSeparator());
				fw.write("D = M");
				fw.write(System.lineSeparator());
				//this.pushCommand();
				break;
			}
			case ("static"): { //push static 3
				fw.write("@" + staticSubString + Integer.toString(index)); //go to static address of offset, A = SimpleAdd.3
				fw.write(System.lineSeparator());
				fw.write("D = M"); //set D register to what is contained in the memory location, which is what will be pushed on stack
				fw.write(System.lineSeparator());
				break;
			}
			}
			this.pushCommand(); //push value in D register onto stack
		}
		else {
			switch(segment) {
			case ("local"): { //pop local offset
				fw.write("@" + Integer.toString(index)); 
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains offset
				fw.write(System.lineSeparator());
				fw.write("@LCL");
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[LCL], another memory location
				fw.write(System.lineSeparator());
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("M = D"); //M[R13] = offset + Mem[LCL], memory location we will need to add popped item to
				fw.write(System.lineSeparator());
				this.popCommand(); //D register contains value on top of stack
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("A = M"); //A = offset + Mem[LCL]
				fw.write(System.lineSeparator());
				fw.write("M = D"); //memory contains what was popped off of stack
				fw.write(System.lineSeparator());
				break;
			}
			case ("argument"): { //pop argument offset
				fw.write("@" + Integer.toString(index)); 
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains offset
				fw.write(System.lineSeparator());
				fw.write("@ARG");
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[ARG], another memory location
				fw.write(System.lineSeparator());
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("M = D"); //M[R13] = offset + Mem[ARG], memory location we will need to add popped item to
				fw.write(System.lineSeparator());
				this.popCommand(); //D register contains value on top of stack
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("A = M"); //A = offset + Mem[ARG] 
				fw.write(System.lineSeparator());
				fw.write("M = D"); //memory contains what was popped off of stack
				fw.write(System.lineSeparator());
				break;
			}
			case ("this"): { //pop this offset
				fw.write("@" + Integer.toString(index)); 
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains offset
				fw.write(System.lineSeparator());
				fw.write("@THIS");
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[THIS], another memory location
				fw.write(System.lineSeparator());
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("M = D"); //M[R13] = offset + Mem[THIS], memory location we will need to add popped item to
				fw.write(System.lineSeparator());
				this.popCommand(); //D register contains value on top of stack
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("A = M"); //A = offset + Mem[THIS] 
				fw.write(System.lineSeparator());
				fw.write("M = D"); //memory contains what was popped off of stack
				fw.write(System.lineSeparator());
				break;
			}
			case ("that"): { //pop argument offset
				fw.write("@" + Integer.toString(index)); 
				fw.write(System.lineSeparator());
				fw.write("D = A"); //D register contains offset
				fw.write(System.lineSeparator());
				fw.write("@THAT");
				fw.write(System.lineSeparator());
				fw.write("D = D + M"); //D register contains offset + Mem[THAT], another memory location
				fw.write(System.lineSeparator());
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("M = D"); //M[R13] = offset + Mem[THAT], memory location we will need to add popped item to
				fw.write(System.lineSeparator());
				this.popCommand(); //D register contains value on top of stack
				fw.write("@R13");
				fw.write(System.lineSeparator());
				fw.write("A = M"); //A = offset + Mem[THAT] 
				fw.write(System.lineSeparator());
				fw.write("M = D"); //memory contains what was popped off of stack
				fw.write(System.lineSeparator());
				break;
			}
			case ("pointer"): {//pop pointer offset
				this.popCommand();
				if (index == 0) {
					//System.out.println("reached this");
					fw.write("@THIS");
					fw.write(System.lineSeparator());
				}
				else {
					fw.write("@THAT");
					fw.write(System.lineSeparator());
				}
				//fw.write("A = M");
				//fw.write(System.lineSeparator());
				fw.write("M = D");
				fw.write(System.lineSeparator());
				break;
			}
			case ("temp"): { //pop temp offset 
				popCommand();
				fw.write(System.lineSeparator());
				fw.write("@R" + Integer.toString(index + 5));
				fw.write(System.lineSeparator());
				fw.write("M = D");
				fw.write(System.lineSeparator());
				break;
			}
			case ("static"): { //pop static 2
				this.popCommand();
				fw.write("@" + staticSubString + Integer.toString(index)); //go to static address of offset, A = SimpleAdd.2
				fw.write(System.lineSeparator());
				fw.write("M = D");
				fw.write(System.lineSeparator());
				break;
			}
			}
		}
	}
	
	//bootstrap code, initialize SP to RAM[256] and call Sys.init
	public void writeInit() throws IOException {
		fw.write("@256");
		fw.write(System.lineSeparator());
		fw.write("D = A");
		fw.write(System.lineSeparator());
		fw.write("@SP");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		this.writeCall("Sys.init", 0);
	}
	
	//label name
	public void writeLabel(String label) throws IOException { 
		fw.write("(" + funcName + "$" + label + ")");
		fw.write(System.lineSeparator());
	}
	
	//goto label (unconditional jump)
	public void writeGoto(String label) throws IOException { 
		fw.write("@" + funcName + "$" + label);
		fw.write(System.lineSeparator());
		fw.write("0;JMP");
		fw.write(System.lineSeparator());
	}
	
	//if-goto label (conditional jump)
	public void writeIf(String label) throws IOException { 
		fw.write("@SP");
		fw.write(System.lineSeparator());
		fw.write("M = M - 1");
		fw.write(System.lineSeparator());
		fw.write("A = M");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@" + funcName + "$" + label);
		fw.write(System.lineSeparator());
		fw.write("D;JNE");
		fw.write(System.lineSeparator());
	}
	
	public void writeCall(String functionName, int numArgs) throws IOException {
		//push return address
		fw.write("@" + functionName + "return" + Integer.toString(countCall));
		fw.write(System.lineSeparator());
		fw.write("D = A");
		fw.write(System.lineSeparator());
		this.pushCommand();
		//push LCL
		fw.write("@LCL");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		this.pushCommand();
		//push ARG
		fw.write("@ARG");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		this.pushCommand();
		//push THIS
		fw.write("@THIS");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		this.pushCommand();
		//push THAT
		fw.write("@THAT");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		this.pushCommand();
		//Set ARG = SP - nargs - 5
		fw.write("@SP");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@" + Integer.toString(5 + numArgs));
		fw.write(System.lineSeparator());
		fw.write("D = D - A");
		fw.write(System.lineSeparator());
		fw.write("@ARG");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//Set LCL = SP
		fw.write("@SP");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@LCL");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//go to function
		fw.write("@" + functionName);
		fw.write(System.lineSeparator());
		fw.write("0;JMP");
		fw.write(System.lineSeparator());
		//set return address label
		fw.write("(" + functionName + "return" + Integer.toString(countCall) + ")");
		fw.write(System.lineSeparator());
		countCall++;
	}
	
	public void writeReturn() throws IOException {
		//store address in LCL to a temp location called FRAME
		fw.write("@LCL");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@FRAME");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		fw.write("@5");
		fw.write(System.lineSeparator());
		fw.write("D = D - A"); //D = FRAME - 5
		fw.write(System.lineSeparator());
		fw.write("A = D"); //A = FRAME - 5
		fw.write(System.lineSeparator());
		fw.write("D = M"); //D = Mem[FRAME - 5]
		fw.write(System.lineSeparator());
		fw.write("@RET");
		fw.write(System.lineSeparator());
		fw.write("M = D"); //Mem[RET] = Mem[FRAME - 5]
		fw.write(System.lineSeparator());
		this.popCommand(); //pops return value and stores in register D
		fw.write("@ARG");
		fw.write(System.lineSeparator());
		fw.write("A = M"); //A = Mem[ARG]
		fw.write(System.lineSeparator());
		fw.write("M = D"); //memory contains return value
		fw.write(System.lineSeparator());
		//set SP = ARG + 1
		fw.write("@ARG");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@SP");
		fw.write(System.lineSeparator());
		fw.write("M = D + 1");
		fw.write(System.lineSeparator());
		//restore THAT of caller
		fw.write("@FRAME");
		fw.write(System.lineSeparator());
		fw.write("M = M - 1");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("A = D");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@THAT");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//restore THIS of caller
		fw.write("@FRAME");
		fw.write(System.lineSeparator());
		fw.write("M = M - 1");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("A = D");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@THIS");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//restore ARG of caller
		fw.write("@FRAME");
		fw.write(System.lineSeparator());
		fw.write("M = M - 1");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("A = D");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@ARG");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//restore LCL of caller
		fw.write("@FRAME");
		fw.write(System.lineSeparator());
		fw.write("M = M - 1");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("A = D");
		fw.write(System.lineSeparator());
		fw.write("D = M");
		fw.write(System.lineSeparator());
		fw.write("@LCL");
		fw.write(System.lineSeparator());
		fw.write("M = D");
		fw.write(System.lineSeparator());
		//go to return address
		fw.write("@RET");
		fw.write(System.lineSeparator());
		fw.write("A = M");
		fw.write(System.lineSeparator());
		fw.write("0;JMP");
		fw.write(System.lineSeparator());
	}
	
	public void writeFunction(String functionName, int numLocals) throws IOException {
		funcName = functionName;
		//declare label for function entry
		fw.write("(" + funcName + ")"); 
		fw.write(System.lineSeparator());
		//check if there are any local variables, if so set D register to 0
		if (numLocals != 0) {
			fw.write("D = 0");
			fw.write(System.lineSeparator());
		}
		//if there are k local variables, push 0 onto stack k times
		for (int i = numLocals; i != 0; i--) {
			this.pushCommand();
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
	
	public void setStaticSubString(String test) {
		staticSubString = test.replace(".vm", ".");
	}

}

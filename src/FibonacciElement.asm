@256
D = A
@SP
M = D
@Sys.initreturn0
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@SP
D = M
@5
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Sys.init
0;JMP
(Sys.initreturn0)
(Main.fibonacci)
@0
D = A
@ARG
D = D + M
A = D
D = M
@SP
A = M
M = D
@SP
M = M + 1
@2
D = A
@SP
A = M
M = D
@SP
M = M + 1
@SP
M = M - 1
A = M
D = M
@R13
M = D
@SP
M = M - 1
A = M
D = M
@R13
D = D - M
@IF_LT_0
D;JLT
D = 0
@IF_NOTLT_END_0
0;JMP
(IF_LT_0)
D = -1
(IF_NOTLT_END_0)
@SP
A = M
M = D
@SP
M = M + 1
@SP
M = M - 1
A = M
D = M
@Main.fibonacci$IF_TRUE
D;JNE
@Main.fibonacci$IF_FALSE
0;JMP
(Main.fibonacci$IF_TRUE)
@0
D = A
@ARG
D = D + M
A = D
D = M
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@FRAME
M = D
@5
D = D - A
A = D
D = M
@RET
M = D
@SP
M = M - 1
A = M
D = M
@ARG
A = M
M = D
@ARG
D = M
@SP
M = D + 1
@FRAME
M = M - 1
D = M
A = D
D = M
@THAT
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@THIS
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@ARG
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@LCL
M = D
@RET
A = M
0;JMP
(Main.fibonacci$IF_FALSE)
@0
D = A
@ARG
D = D + M
A = D
D = M
@SP
A = M
M = D
@SP
M = M + 1
@2
D = A
@SP
A = M
M = D
@SP
M = M + 1
@SP
M = M - 1
A = M
D = M
@R13
M = D
@SP
M = M - 1
A = M
D = M
@R13
D = D - M
@SP
A = M
M = D
@SP
M = M + 1
@Main.fibonaccireturn1
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@SP
D = M
@6
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0;JMP
(Main.fibonaccireturn1)
@0
D = A
@ARG
D = D + M
A = D
D = M
@SP
A = M
M = D
@SP
M = M + 1
@1
D = A
@SP
A = M
M = D
@SP
M = M + 1
@SP
M = M - 1
A = M
D = M
@R13
M = D
@SP
M = M - 1
A = M
D = M
@R13
D = D - M
@SP
A = M
M = D
@SP
M = M + 1
@Main.fibonaccireturn2
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@SP
D = M
@6
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0;JMP
(Main.fibonaccireturn2)
@SP
M = M - 1
A = M
D = M
@SP
M = M - 1
A = M
M = M + D
@SP
M = M + 1
@LCL
D = M
@FRAME
M = D
@5
D = D - A
A = D
D = M
@RET
M = D
@SP
M = M - 1
A = M
D = M
@ARG
A = M
M = D
@ARG
D = M
@SP
M = D + 1
@FRAME
M = M - 1
D = M
A = D
D = M
@THAT
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@THIS
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@ARG
M = D
@FRAME
M = M - 1
D = M
A = D
D = M
@LCL
M = D
@RET
A = M
0;JMP
(Sys.init)
@4
D = A
@SP
A = M
M = D
@SP
M = M + 1
@Main.fibonaccireturn3
D = A
@SP
A = M
M = D
@SP
M = M + 1
@LCL
D = M
@SP
A = M
M = D
@SP
M = M + 1
@ARG
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THIS
D = M
@SP
A = M
M = D
@SP
M = M + 1
@THAT
D = M
@SP
A = M
M = D
@SP
M = M + 1
@SP
D = M
@6
D = D - A
@ARG
M = D
@SP
D = M
@LCL
M = D
@Main.fibonacci
0;JMP
(Main.fibonaccireturn3)
(Sys.init$WHILE)
@Sys.init$WHILE
0;JMP

@17
D = A
@SP
A = M
M = D
@SP
M = M + 1
@19
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
@IF_LT_N
D;JLT
D = 0
@IF_NOTLT_END_N
0;JMP
(IF_LT_N)
D = -1
(IF_NOTLT_END_N)
@SP
A = M
M = D
@SP
M = M + 1

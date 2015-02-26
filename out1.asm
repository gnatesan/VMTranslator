@15
D = A
@SP
A = M
M = D
@SP
M = M + 1
@7
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
@IF_GT_N
D:JGT
D = 0
@IF_NOTGT_END_N
0;JMP
(IF_GT_N)
D = -1
(IF_NOTGT_END_N)
@SP
A = M
M = D
@SP
M = M + 1

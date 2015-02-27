@17
D = A
@SP
A = M
M = D
@SP
M = M + 1
@17
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
@IF_NOTEQ_0
D;JNE
D = -1
@IF_NOTEQ_END_0
0;JMP
(IF_NOTEQ_0)
D = 0
(IF_NOTEQ_END_0)
@SP
A = M
M = D
@SP
M = M + 1
@17
D = A
@SP
A = M
M = D
@SP
M = M + 1
@16
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
@IF_NOTEQ_1
D;JNE
D = -1
@IF_NOTEQ_END_1
0;JMP
(IF_NOTEQ_1)
D = 0
(IF_NOTEQ_END_1)
@SP
A = M
M = D
@SP
M = M + 1
@16
D = A
@SP
A = M
M = D
@SP
M = M + 1
@17
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
@IF_NOTEQ_2
D;JNE
D = -1
@IF_NOTEQ_END_2
0;JMP
(IF_NOTEQ_2)
D = 0
(IF_NOTEQ_END_2)
@SP
A = M
M = D
@SP
M = M + 1
@892
D = A
@SP
A = M
M = D
@SP
M = M + 1
@891
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
@891
D = A
@SP
A = M
M = D
@SP
M = M + 1
@892
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
@IF_LT_1
D;JLT
D = 0
@IF_NOTLT_END_1
0;JMP
(IF_LT_1)
D = -1
(IF_NOTLT_END_1)
@SP
A = M
M = D
@SP
M = M + 1
@891
D = A
@SP
A = M
M = D
@SP
M = M + 1
@891
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
@IF_LT_2
D;JLT
D = 0
@IF_NOTLT_END_2
0;JMP
(IF_LT_2)
D = -1
(IF_NOTLT_END_2)
@SP
A = M
M = D
@SP
M = M + 1
@32767
D = A
@SP
A = M
M = D
@SP
M = M + 1
@32766
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
@IF_GT_0
D;JGT
D = 0
@IF_NOTGT_END_0
0;JMP
(IF_GT_0)
D = -1
(IF_NOTGT_END_0)
@SP
A = M
M = D
@SP
M = M + 1
@32766
D = A
@SP
A = M
M = D
@SP
M = M + 1
@32767
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
@IF_GT_1
D;JGT
D = 0
@IF_NOTGT_END_1
0;JMP
(IF_GT_1)
D = -1
(IF_NOTGT_END_1)
@SP
A = M
M = D
@SP
M = M + 1
@32766
D = A
@SP
A = M
M = D
@SP
M = M + 1
@32766
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
@IF_GT_2
D;JGT
D = 0
@IF_NOTGT_END_2
0;JMP
(IF_GT_2)
D = -1
(IF_NOTGT_END_2)
@SP
A = M
M = D
@SP
M = M + 1
@57
D = A
@SP
A = M
M = D
@SP
M = M + 1
@31
D = A
@SP
A = M
M = D
@SP
M = M + 1
@53
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
@SP
M = M - 1
A = M
M = M + D
@SP
M = M + 1
@112
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
@SP
M = M - 1
A = M
D = M
D = -D
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
D = D & M
@SP
A = M
M = D
@SP
M = M + 1
@82
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
D = D | M
@SP
A = M
M = D
@SP
M = M + 1
@SP
M = M - 1
A = M
D = M
D = !D
@SP
A = M
M = D
@SP
M = M + 1

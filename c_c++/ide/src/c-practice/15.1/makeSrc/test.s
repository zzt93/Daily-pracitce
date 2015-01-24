	.file	"test.c"
# GNU C (Ubuntu 4.8.2-19ubuntu1) version 4.8.2 (x86_64-linux-gnu)
#	compiled by GNU C version 4.8.2, GMP version 5.1.3, MPFR version 3.1.2-p3, MPC version 1.0.1
# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed:  -imultiarch x86_64-linux-gnu test.c -mtune=generic
# -march=x86-64 -fverbose-asm -fstack-protector -Wformat -Wformat-security
# options enabled:  -faggressive-loop-optimizations
# -fasynchronous-unwind-tables -fauto-inc-dec -fbranch-count-reg -fcommon
# -fdelete-null-pointer-checks -fdwarf2-cfi-asm -fearly-inlining
# -feliminate-unused-debug-types -ffunction-cse -fgcse-lm -fgnu-runtime
# -fident -finline-atomics -fira-hoist-pressure -fira-share-save-slots
# -fira-share-spill-slots -fivopts -fkeep-static-consts
# -fleading-underscore -fmath-errno -fmerge-debug-strings
# -fmove-loop-invariants -fpeephole -fprefetch-loop-arrays
# -freg-struct-return -fsched-critical-path-heuristic
# -fsched-dep-count-heuristic -fsched-group-heuristic -fsched-interblock
# -fsched-last-insn-heuristic -fsched-rank-heuristic -fsched-spec
# -fsched-spec-insn-heuristic -fsched-stalled-insns-dep -fshow-column
# -fsigned-zeros -fsplit-ivs-in-unroller -fstack-protector
# -fstrict-volatile-bitfields -fsync-libcalls -ftrapping-math
# -ftree-coalesce-vars -ftree-cselim -ftree-forwprop -ftree-loop-if-convert
# -ftree-loop-im -ftree-loop-ivcanon -ftree-loop-optimize
# -ftree-parallelize-loops= -ftree-phiprop -ftree-pta -ftree-reassoc
# -ftree-scev-cprop -ftree-slp-vectorize -ftree-vect-loop-version
# -funit-at-a-time -funwind-tables -fverbose-asm -fzero-initialized-in-bss
# -m128bit-long-double -m64 -m80387 -maccumulate-outgoing-args
# -malign-stringops -mfancy-math-387 -mfp-ret-in-387 -mfxsr -mglibc
# -mieee-fp -mlong-double-80 -mmmx -mno-sse4 -mpush-args -mred-zone -msse
# -msse2 -mtls-direct-seg-refs

	.globl	size
	.data
	.align 4
	.type	size, @object
	.size	size, 4
size:
	.long	100
	.comm	get,4,4
	.comm	i,4,4
	.section	.rodata
.LC0:
	.string	"%d"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	subq	$16, %rsp	#,
	movl	%edi, -4(%rbp)	# argc, argc
	movq	%rsi, -16(%rbp)	# argv, argv
	movl	$get, %esi	#,
	movl	$.LC0, %edi	#,
	movl	$0, %eax	#,
	call	__isoc99_scanf	#
	movl	$0, %eax	#,
	call	test1	#
	movl	$0, %eax	#,
	call	test2	#
	movl	i(%rip), %eax	# i, i.0
	movl	%eax, %esi	# i.0,
	movl	$.LC0, %edi	#,
	movl	$0, %eax	#,
	call	printf	#
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.globl	test1
	.type	test1, @function
test1:
.LFB1:
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	movl	$1, i(%rip)	#, i
	jmp	.L3	#
.L6:
	movl	get(%rip), %eax	# get, get.1
	testl	%eax, %eax	# get.1
	jle	.L4	#,
	jmp	.L2	#
.L4:
	movl	i(%rip), %eax	# i, i.2
	addl	$1, %eax	#, i.3
	movl	%eax, i(%rip)	# i.3, i
.L3:
	movl	i(%rip), %edx	# i, i.4
	movl	size(%rip), %eax	# size, size.5
	cmpl	%eax, %edx	# size.5, i.4
	jl	.L6	#,
.L2:
	popq	%rbp	#
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	test1, .-test1
	.globl	test2
	.type	test2, @function
test2:
.LFB2:
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	movl	$1, i(%rip)	#, i
	jmp	.L8	#
.L10:
	movl	i(%rip), %eax	# i, i.6
	addl	$1, %eax	#, i.7
	movl	%eax, i(%rip)	# i.7, i
.L8:
	movl	i(%rip), %edx	# i, i.8
	movl	size(%rip), %eax	# size, size.9
	cmpl	%eax, %edx	# size.9, i.8
	jge	.L7	#,
	movl	get(%rip), %eax	# get, get.10
	testl	%eax, %eax	# get.10
	jle	.L10	#,
.L7:
	popq	%rbp	#
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	test2, .-test2
	.ident	"GCC: (Ubuntu 4.8.2-19ubuntu1) 4.8.2"
	.section	.note.GNU-stack,"",@progbits

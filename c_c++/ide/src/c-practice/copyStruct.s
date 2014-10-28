	.file	"copyStruct.c"
	.comm	a,8,8
	.comm	b,8,8
	.section	.rodata
.LC0:
	.string	"abc"
.LC1:
	.string	"abcd"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	%edi, -4(%rbp)
	movq	%rsi, -16(%rbp)
	movq	a(%rip), %rax
	movl	$1, (%rax)
	movq	a(%rip), %rax
	movq	$.LC0, 8(%rax)
	movq	b(%rip), %rax
	movl	$2, (%rax)
	movq	b(%rip), %rax
	movq	$.LC1, 8(%rax)
	movl	$0, %eax
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Ubuntu/Linaro 4.7.3-1ubuntu1) 4.7.3"
	.section	.note.GNU-stack,"",@progbits

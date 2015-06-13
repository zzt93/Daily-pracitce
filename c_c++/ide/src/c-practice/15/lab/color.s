	.file	"color.c"
	.section	.rodata
.LC0:
	.string	"\033[31mThis text is RED!\033[0m"
.LC1:
	.string	"\033[32mThis text is GREEN!\033[0m"
.LC2:
	.string	"\033[33mThis text is YELLOW!\033[0m"
.LC3:
	.string	"\033[34mThis text is BLUE!\033[0m"
	.align 8
.LC4:
	.string	"\033[35mThis text is MAGENTA!\033[0m"
.LC5:
	.string	"\033[36mThis text is CYAN!\033[0m"
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
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movq	%rsi, -16(%rbp)
	movl	$.LC0, %edi
	call	puts
	movl	$.LC1, %edi
	call	puts
	movl	$.LC2, %edi
	call	puts
	movl	$.LC3, %edi
	call	puts
	movl	$.LC4, %edi
	call	puts
	movl	$.LC5, %edi
	call	puts
	movl	$0, %eax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.9.1-16ubuntu6) 4.9.1"
	.section	.note.GNU-stack,"",@progbits

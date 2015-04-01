	.file	"fib_test.c"
	.globl	color
	.section	.rodata
.LC0:
	.string	"\033[31m"
.LC1:
	.string	"\033[32m"
.LC2:
	.string	"\033[33m"
.LC3:
	.string	"\033[34m"
.LC4:
	.string	"\033[35m"
.LC5:
	.string	"\033[36m"
.LC6:
	.string	"\033[0m"
	.data
	.align 64
	.type	color, @object
	.size	color, 64
color:
	.quad	.LC0
	.quad	.LC1
	.quad	.LC1
	.quad	.LC2
	.quad	.LC3
	.quad	.LC4
	.quad	.LC5
	.quad	.LC6
	.globl	lcarry
	.bss
	.align 2
	.type	lcarry, @object
	.size	lcarry, 2
lcarry:
	.zero	2
	.globl	rcarry
	.align 2
	.type	rcarry, @object
	.size	rcarry, 2
rcarry:
	.zero	2
	.globl	map
	.data
	.align 32
	.type	map, @object
	.size	map, 48
map:
	.value	0
	.value	1
	.zero	44
	.text
	.globl	add_map
	.type	add_map, @function
add_map:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%rbx
	subq	$24, %rsp
	.cfi_offset 3, -24
	movl	%edi, %eax
	movw	%ax, -20(%rbp)
	cmpw	$24, -20(%rbp)
	ja	.L2
	movzwl	-20(%rbp), %eax
	cltq
	movzwl	map(%rax,%rax), %eax
	testw	%ax, %ax
	jne	.L2
	movzwl	-20(%rbp), %ebx
	movzwl	-20(%rbp), %eax
	movl	%eax, %edi
	call	fib
	movl	%eax, %edx
	movslq	%ebx, %rax
	movw	%dx, map(%rax,%rax)
.L2:
	cmpw	$24, -20(%rbp)
	jbe	.L3
	movzwl	-20(%rbp), %eax
	movl	%eax, %edi
	call	fib
	jmp	.L4
.L3:
	movzwl	-20(%rbp), %eax
	cltq
	movzwl	map(%rax,%rax), %eax
.L4:
	addq	$24, %rsp
	popq	%rbx
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	add_map, .-add_map
	.globl	fib
	.type	fib, @function
fib:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movl	%edi, %eax
	movw	%ax, -20(%rbp)
	cmpw	$0, -20(%rbp)
	jne	.L6
	movzwl	map(%rip), %eax
	jmp	.L7
.L6:
	cmpw	$1, -20(%rbp)
	jne	.L8
	movzwl	map+2(%rip), %eax
	jmp	.L7
.L8:
	movzwl	-20(%rbp), %eax
	subl	$1, %eax
	movzwl	%ax, %eax
	movl	%eax, %edi
	call	add_map
	movw	%ax, -6(%rbp)
	movzwl	-20(%rbp), %eax
	subl	$2, %eax
	movzwl	%ax, %eax
	movl	%eax, %edi
	call	add_map
	movw	%ax, -4(%rbp)
	movzwl	-6(%rbp), %edx
	movzwl	-4(%rbp), %eax
	addl	%edx, %eax
	movw	%ax, -2(%rbp)
	movzwl	-2(%rbp), %eax
.L7:
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	fib, .-fib
	.globl	f
	.type	f, @function
f:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	%edi, %eax
	movq	%rsi, -16(%rbp)
	movw	%ax, -4(%rbp)
	movzwl	-4(%rbp), %eax
	movl	%eax, %edi
	call	add_map
	movl	%eax, %edx
	movq	-16(%rbp), %rax
	movw	%dx, (%rax)
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	f, .-f
	.section	.rodata
	.align 8
.LC7:
	.string	"OS lab1, fib\nPlease Enter an aim"
.LC8:
	.string	"%s %d %s"
.LC9:
	.string	"%s 0x%x %s"
.LC10:
	.string	""
.LC11:
	.string	"%hd"
	.text
	.globl	main
	.type	main, @function
main:
.LFB3:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movq	%fs:40, %rax
	movq	%rax, -8(%rbp)
	xorl	%eax, %eax
	movl	$.LC7, %edi
	call	puts
	movw	$0, -16(%rbp)
	movl	$0, -12(%rbp)
	jmp	.L11
.L16:
	movl	$0, -12(%rbp)
	jmp	.L12
.L15:
	movl	-12(%rbp), %eax
	movzwl	%ax, %eax
	leaq	-14(%rbp), %rdx
	movq	%rdx, %rsi
	movl	%eax, %edi
	call	f
	movzwl	-16(%rbp), %eax
	cmpw	$24, %ax
	ja	.L13
	movq	color+56(%rip), %rcx
	movzwl	-14(%rbp), %eax
	movzwl	%ax, %edx
	movl	-12(%rbp), %eax
	andl	$7, %eax
	movq	color(,%rax,8), %rax
	movq	%rax, %rsi
	movl	$.LC8, %edi
	movl	$0, %eax
	call	printf
	jmp	.L14
.L13:
	movq	color+56(%rip), %rcx
	movzwl	-14(%rbp), %eax
	movzwl	%ax, %edx
	movl	-12(%rbp), %eax
	andl	$7, %eax
	movq	color(,%rax,8), %rax
	movq	%rax, %rsi
	movl	$.LC9, %edi
	movl	$0, %eax
	call	printf
.L14:
	addl	$1, -12(%rbp)
.L12:
	movzwl	-16(%rbp), %eax
	movzwl	%ax, %eax
	cmpl	-12(%rbp), %eax
	jae	.L15
	movl	$.LC10, %edi
	call	puts
.L11:
	leaq	-16(%rbp), %rax
	movq	%rax, %rsi
	movl	$.LC11, %edi
	movl	$0, %eax
	call	__isoc99_scanf
	cmpl	$-1, %eax
	jne	.L16
	movl	$0, %eax
	movq	-8(%rbp), %rsi
	xorq	%fs:40, %rsi
	je	.L18
	call	__stack_chk_fail
.L18:
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.9.1-16ubuntu6) 4.9.1"
	.section	.note.GNU-stack,"",@progbits

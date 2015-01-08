	.file	"inline_recursive.cpp"
	.section	.text._Z9factoriali,"axG",@progbits,_Z9factoriali,comdat
	.weak	_Z9factoriali
	.type	_Z9factoriali, @function
_Z9factoriali:
.LFB998:
	.cfi_startproc
	pushq	%r14
	.cfi_def_cfa_offset 16
	.cfi_offset 14, -16
	pushq	%r13
	.cfi_def_cfa_offset 24
	.cfi_offset 13, -24
	pushq	%r12
	.cfi_def_cfa_offset 32
	.cfi_offset 12, -32
	pushq	%rbp
	.cfi_def_cfa_offset 40
	.cfi_offset 6, -40
	pushq	%rbx
	.cfi_def_cfa_offset 48
	.cfi_offset 3, -48
	movl	%edi, %ebx
	movl	$1, %eax
	cmpl	$1, %edi
	je	.L2
	leal	-1(%rdi), %ebp
	cmpl	$1, %ebp
	je	.L3
	leal	-2(%rdi), %r12d
	cmpl	$1, %r12d
	je	.L4
	leal	-3(%rdi), %r13d
	cmpl	$1, %r13d
	je	.L5
	leal	-4(%rdi), %r14d
	cmpl	$1, %r14d
	je	.L6
	leal	-5(%rdi), %edi
	call	_Z9factoriali
	imull	%eax, %r14d
.L6:
	imull	%r14d, %r13d
.L5:
	imull	%r13d, %r12d
.L4:
	imull	%r12d, %ebp
.L3:
	movl	%ebx, %eax
	imull	%ebp, %eax
.L2:
	popq	%rbx
	.cfi_def_cfa_offset 40
	popq	%rbp
	.cfi_def_cfa_offset 32
	popq	%r12
	.cfi_def_cfa_offset 24
	popq	%r13
	.cfi_def_cfa_offset 16
	popq	%r14
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE998:
	.size	_Z9factoriali, .-_Z9factoriali
	.text
	.globl	main
	.type	main, @function
main:
.LFB999:
	.cfi_startproc
	subq	$8, %rsp
	.cfi_def_cfa_offset 16
	movl	$2, %edi
	call	_Z9factoriali
	leal	(%rax,%rax,2), %esi
	movl	$_ZSt4cout, %edi
	call	_ZNSolsEi
	movq	%rax, %rdi
	call	_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_
	movl	$0, %eax
	addq	$8, %rsp
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE999:
	.size	main, .-main
	.type	_GLOBAL__sub_I_main, @function
_GLOBAL__sub_I_main:
.LFB1006:
	.cfi_startproc
	subq	$8, %rsp
	.cfi_def_cfa_offset 16
	movl	$_ZStL8__ioinit, %edi
	call	_ZNSt8ios_base4InitC1Ev
	movl	$__dso_handle, %edx
	movl	$_ZStL8__ioinit, %esi
	movl	$_ZNSt8ios_base4InitD1Ev, %edi
	call	__cxa_atexit
	addq	$8, %rsp
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE1006:
	.size	_GLOBAL__sub_I_main, .-_GLOBAL__sub_I_main
	.section	.init_array,"aw"
	.align 8
	.quad	_GLOBAL__sub_I_main
	.local	_ZStL8__ioinit
	.comm	_ZStL8__ioinit,1,1
	.hidden	__dso_handle
	.ident	"GCC: (Ubuntu 4.8.2-19ubuntu1) 4.8.2"
	.section	.note.GNU-stack,"",@progbits

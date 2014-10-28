#include <stdio.h>

void maxArray(double *x, double *y);

int main (int argc, char *argv[]) {
	double x[65536], y[65536];
	
}

void maxArray(double *x, double *y) {
	for (i = 0; i < 65536; i++) {
        if (y[i] > x[i]) {
			x[i] = y[i];
		}
    }
}

/*
  -O0
  maxArray(double*, double*):
	pushq	%rbp
	movq	%rsp, %rbp
	movq	%rdi, -24(%rbp)
	movq	%rsi, -32(%rbp)
	movl	$0, -4(%rbp)
	jmp	.L2
.L5:
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,8), %rdx
	movq	-32(%rbp), %rax
	addq	%rdx, %rax
	movsd	(%rax), %xmm0
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,8), %rdx
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movsd	(%rax), %xmm1
	ucomisd	%xmm1, %xmm0
	jbe	.L3
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,8), %rdx
	movq	-24(%rbp), %rax
	addq	%rax, %rdx
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,8), %rcx
	movq	-32(%rbp), %rax
	addq	%rcx, %rax
	movq	(%rax), %rax
	movq	%rax, (%rdx)
.L3:
	addl	$1, -4(%rbp)
.L2:
	cmpl	$65535, -4(%rbp)
	jle	.L5
	popq	%rbp
	ret
*/


/*
  -O1
  maxArray(double*, double*):
	movl	$0, %eax
.L5:
	movsd	(%rsi,%rax), %xmm0
	ucomisd	(%rdi,%rax), %xmm0
	jbe	.L2
	movsd	%xmm0, (%rdi,%rax)
.L2:
	addq	$8, %rax
	cmpq	$524288, %rax
	jne	.L5
	rep; ret
*/
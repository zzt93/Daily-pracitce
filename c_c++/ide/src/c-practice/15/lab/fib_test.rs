
fib_test.out:     file format elf64-x86-64


Disassembly of section .init:

00000000004004d0 <_init>:
  4004d0:	48 83 ec 08          	sub    $0x8,%rsp
  4004d4:	48 8b 05 1d 0b 20 00 	mov    0x200b1d(%rip),%rax        # 600ff8 <_DYNAMIC+0x1d0>
  4004db:	48 85 c0             	test   %rax,%rax
  4004de:	74 05                	je     4004e5 <_init+0x15>
  4004e0:	e8 5b 00 00 00       	callq  400540 <__gmon_start__@plt>
  4004e5:	48 83 c4 08          	add    $0x8,%rsp
  4004e9:	c3                   	retq   

Disassembly of section .plt:

00000000004004f0 <puts@plt-0x10>:
  4004f0:	ff 35 12 0b 20 00    	pushq  0x200b12(%rip)        # 601008 <_GLOBAL_OFFSET_TABLE_+0x8>
  4004f6:	ff 25 14 0b 20 00    	jmpq   *0x200b14(%rip)        # 601010 <_GLOBAL_OFFSET_TABLE_+0x10>
  4004fc:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000400500 <puts@plt>:
  400500:	ff 25 12 0b 20 00    	jmpq   *0x200b12(%rip)        # 601018 <_GLOBAL_OFFSET_TABLE_+0x18>
  400506:	68 00 00 00 00       	pushq  $0x0
  40050b:	e9 e0 ff ff ff       	jmpq   4004f0 <_init+0x20>

0000000000400510 <__stack_chk_fail@plt>:
  400510:	ff 25 0a 0b 20 00    	jmpq   *0x200b0a(%rip)        # 601020 <_GLOBAL_OFFSET_TABLE_+0x20>
  400516:	68 01 00 00 00       	pushq  $0x1
  40051b:	e9 d0 ff ff ff       	jmpq   4004f0 <_init+0x20>

0000000000400520 <printf@plt>:
  400520:	ff 25 02 0b 20 00    	jmpq   *0x200b02(%rip)        # 601028 <_GLOBAL_OFFSET_TABLE_+0x28>
  400526:	68 02 00 00 00       	pushq  $0x2
  40052b:	e9 c0 ff ff ff       	jmpq   4004f0 <_init+0x20>

0000000000400530 <__libc_start_main@plt>:
  400530:	ff 25 fa 0a 20 00    	jmpq   *0x200afa(%rip)        # 601030 <_GLOBAL_OFFSET_TABLE_+0x30>
  400536:	68 03 00 00 00       	pushq  $0x3
  40053b:	e9 b0 ff ff ff       	jmpq   4004f0 <_init+0x20>

0000000000400540 <__gmon_start__@plt>:
  400540:	ff 25 f2 0a 20 00    	jmpq   *0x200af2(%rip)        # 601038 <_GLOBAL_OFFSET_TABLE_+0x38>
  400546:	68 04 00 00 00       	pushq  $0x4
  40054b:	e9 a0 ff ff ff       	jmpq   4004f0 <_init+0x20>

0000000000400550 <__isoc99_scanf@plt>:
  400550:	ff 25 ea 0a 20 00    	jmpq   *0x200aea(%rip)        # 601040 <_GLOBAL_OFFSET_TABLE_+0x40>
  400556:	68 05 00 00 00       	pushq  $0x5
  40055b:	e9 90 ff ff ff       	jmpq   4004f0 <_init+0x20>

Disassembly of section .text:

0000000000400560 <_start>:
  400560:	31 ed                	xor    %ebp,%ebp
  400562:	49 89 d1             	mov    %rdx,%r9
  400565:	5e                   	pop    %rsi
  400566:	48 89 e2             	mov    %rsp,%rdx
  400569:	48 83 e4 f0          	and    $0xfffffffffffffff0,%rsp
  40056d:	50                   	push   %rax
  40056e:	54                   	push   %rsp
  40056f:	49 c7 c0 20 09 40 00 	mov    $0x400920,%r8
  400576:	48 c7 c1 b0 08 40 00 	mov    $0x4008b0,%rcx
  40057d:	48 c7 c7 6c 07 40 00 	mov    $0x40076c,%rdi
  400584:	e8 a7 ff ff ff       	callq  400530 <__libc_start_main@plt>
  400589:	f4                   	hlt    
  40058a:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)

0000000000400590 <deregister_tm_clones>:
  400590:	b8 37 11 60 00       	mov    $0x601137,%eax
  400595:	55                   	push   %rbp
  400596:	48 2d 30 11 60 00    	sub    $0x601130,%rax
  40059c:	48 83 f8 0e          	cmp    $0xe,%rax
  4005a0:	48 89 e5             	mov    %rsp,%rbp
  4005a3:	76 1b                	jbe    4005c0 <deregister_tm_clones+0x30>
  4005a5:	b8 00 00 00 00       	mov    $0x0,%eax
  4005aa:	48 85 c0             	test   %rax,%rax
  4005ad:	74 11                	je     4005c0 <deregister_tm_clones+0x30>
  4005af:	5d                   	pop    %rbp
  4005b0:	bf 30 11 60 00       	mov    $0x601130,%edi
  4005b5:	ff e0                	jmpq   *%rax
  4005b7:	66 0f 1f 84 00 00 00 	nopw   0x0(%rax,%rax,1)
  4005be:	00 00 
  4005c0:	5d                   	pop    %rbp
  4005c1:	c3                   	retq   
  4005c2:	66 66 66 66 66 2e 0f 	data16 data16 data16 data16 nopw %cs:0x0(%rax,%rax,1)
  4005c9:	1f 84 00 00 00 00 00 

00000000004005d0 <register_tm_clones>:
  4005d0:	be 30 11 60 00       	mov    $0x601130,%esi
  4005d5:	55                   	push   %rbp
  4005d6:	48 81 ee 30 11 60 00 	sub    $0x601130,%rsi
  4005dd:	48 c1 fe 03          	sar    $0x3,%rsi
  4005e1:	48 89 e5             	mov    %rsp,%rbp
  4005e4:	48 89 f0             	mov    %rsi,%rax
  4005e7:	48 c1 e8 3f          	shr    $0x3f,%rax
  4005eb:	48 01 c6             	add    %rax,%rsi
  4005ee:	48 d1 fe             	sar    %rsi
  4005f1:	74 15                	je     400608 <register_tm_clones+0x38>
  4005f3:	b8 00 00 00 00       	mov    $0x0,%eax
  4005f8:	48 85 c0             	test   %rax,%rax
  4005fb:	74 0b                	je     400608 <register_tm_clones+0x38>
  4005fd:	5d                   	pop    %rbp
  4005fe:	bf 30 11 60 00       	mov    $0x601130,%edi
  400603:	ff e0                	jmpq   *%rax
  400605:	0f 1f 00             	nopl   (%rax)
  400608:	5d                   	pop    %rbp
  400609:	c3                   	retq   
  40060a:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)

0000000000400610 <__do_global_dtors_aux>:
  400610:	80 3d 19 0b 20 00 00 	cmpb   $0x0,0x200b19(%rip)        # 601130 <__TMC_END__>
  400617:	75 11                	jne    40062a <__do_global_dtors_aux+0x1a>
  400619:	55                   	push   %rbp
  40061a:	48 89 e5             	mov    %rsp,%rbp
  40061d:	e8 6e ff ff ff       	callq  400590 <deregister_tm_clones>
  400622:	5d                   	pop    %rbp
  400623:	c6 05 06 0b 20 00 01 	movb   $0x1,0x200b06(%rip)        # 601130 <__TMC_END__>
  40062a:	f3 c3                	repz retq 
  40062c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000400630 <frame_dummy>:
  400630:	bf 20 0e 60 00       	mov    $0x600e20,%edi
  400635:	48 83 3f 00          	cmpq   $0x0,(%rdi)
  400639:	75 05                	jne    400640 <frame_dummy+0x10>
  40063b:	eb 93                	jmp    4005d0 <register_tm_clones>
  40063d:	0f 1f 00             	nopl   (%rax)
  400640:	b8 00 00 00 00       	mov    $0x0,%eax
  400645:	48 85 c0             	test   %rax,%rax
  400648:	74 f1                	je     40063b <frame_dummy+0xb>
  40064a:	55                   	push   %rbp
  40064b:	48 89 e5             	mov    %rsp,%rbp
  40064e:	ff d0                	callq  *%rax
  400650:	5d                   	pop    %rbp
  400651:	e9 7a ff ff ff       	jmpq   4005d0 <register_tm_clones>

0000000000400656 <add_map>:
  400656:	55                   	push   %rbp
  400657:	48 89 e5             	mov    %rsp,%rbp
  40065a:	53                   	push   %rbx
  40065b:	48 83 ec 18          	sub    $0x18,%rsp
  40065f:	89 f8                	mov    %edi,%eax
  400661:	66 89 45 ec          	mov    %ax,-0x14(%rbp)
  400665:	0f b7 45 ec          	movzwl -0x14(%rbp),%eax
  400669:	48 98                	cltq   
  40066b:	0f b7 84 00 00 11 60 	movzwl 0x601100(%rax,%rax,1),%eax
  400672:	00 
  400673:	66 85 c0             	test   %ax,%ax
  400676:	75 1c                	jne    400694 <add_map+0x3e>
  400678:	0f b7 5d ec          	movzwl -0x14(%rbp),%ebx
  40067c:	0f b7 45 ec          	movzwl -0x14(%rbp),%eax
  400680:	89 c7                	mov    %eax,%edi
  400682:	e8 22 00 00 00       	callq  4006a9 <fib>
  400687:	89 c2                	mov    %eax,%edx
  400689:	48 63 c3             	movslq %ebx,%rax
  40068c:	66 89 94 00 00 11 60 	mov    %dx,0x601100(%rax,%rax,1)
  400693:	00 
  400694:	0f b7 45 ec          	movzwl -0x14(%rbp),%eax
  400698:	48 98                	cltq   
  40069a:	0f b7 84 00 00 11 60 	movzwl 0x601100(%rax,%rax,1),%eax
  4006a1:	00 
  4006a2:	48 83 c4 18          	add    $0x18,%rsp
  4006a6:	5b                   	pop    %rbx
  4006a7:	5d                   	pop    %rbp
  4006a8:	c3                   	retq   

00000000004006a9 <fib>:
  4006a9:	55                   	push   %rbp
  4006aa:	48 89 e5             	mov    %rsp,%rbp
  4006ad:	48 83 ec 20          	sub    $0x20,%rsp
  4006b1:	89 f8                	mov    %edi,%eax
  4006b3:	66 89 45 ec          	mov    %ax,-0x14(%rbp)
  4006b7:	66 83 7d ec 00       	cmpw   $0x0,-0x14(%rbp)
  4006bc:	75 09                	jne    4006c7 <fib+0x1e>
  4006be:	0f b7 05 3b 0a 20 00 	movzwl 0x200a3b(%rip),%eax        # 601100 <map>
  4006c5:	eb 7b                	jmp    400742 <fib+0x99>
  4006c7:	66 83 7d ec 01       	cmpw   $0x1,-0x14(%rbp)
  4006cc:	75 09                	jne    4006d7 <fib+0x2e>
  4006ce:	0f b7 05 2d 0a 20 00 	movzwl 0x200a2d(%rip),%eax        # 601102 <map+0x2>
  4006d5:	eb 6b                	jmp    400742 <fib+0x99>
  4006d7:	0f b7 45 ec          	movzwl -0x14(%rbp),%eax
  4006db:	83 e8 01             	sub    $0x1,%eax
  4006de:	0f b7 c0             	movzwl %ax,%eax
  4006e1:	89 c7                	mov    %eax,%edi
  4006e3:	e8 6e ff ff ff       	callq  400656 <add_map>
  4006e8:	66 89 45 fa          	mov    %ax,-0x6(%rbp)
  4006ec:	0f b7 45 ec          	movzwl -0x14(%rbp),%eax
  4006f0:	83 e8 02             	sub    $0x2,%eax
  4006f3:	0f b7 c0             	movzwl %ax,%eax
  4006f6:	89 c7                	mov    %eax,%edi
  4006f8:	e8 59 ff ff ff       	callq  400656 <add_map>
  4006fd:	66 89 45 fc          	mov    %ax,-0x4(%rbp)
  400701:	0f b7 55 fa          	movzwl -0x6(%rbp),%edx
  400705:	0f b7 45 fc          	movzwl -0x4(%rbp),%eax
  400709:	01 d0                	add    %edx,%eax
  40070b:	66 89 45 fe          	mov    %ax,-0x2(%rbp)
  40070f:	0f b7 45 fa          	movzwl -0x6(%rbp),%eax
  400713:	66 3b 45 fe          	cmp    -0x2(%rbp),%ax
  400717:	77 0a                	ja     400723 <fib+0x7a>
  400719:	0f b7 45 fc          	movzwl -0x4(%rbp),%eax
  40071d:	66 3b 45 fe          	cmp    -0x2(%rbp),%ax
  400721:	76 1b                	jbe    40073e <fib+0x95>
  400723:	bf 61 09 40 00       	mov    $0x400961,%edi
  400728:	e8 d3 fd ff ff       	callq  400500 <puts@plt>
  40072d:	0f b7 05 fe 09 20 00 	movzwl 0x2009fe(%rip),%eax        # 601132 <carry>
  400734:	83 c0 01             	add    $0x1,%eax
  400737:	66 89 05 f4 09 20 00 	mov    %ax,0x2009f4(%rip)        # 601132 <carry>
  40073e:	0f b7 45 fe          	movzwl -0x2(%rbp),%eax
  400742:	c9                   	leaveq 
  400743:	c3                   	retq   

0000000000400744 <f>:
  400744:	55                   	push   %rbp
  400745:	48 89 e5             	mov    %rsp,%rbp
  400748:	48 83 ec 10          	sub    $0x10,%rsp
  40074c:	89 f8                	mov    %edi,%eax
  40074e:	48 89 75 f0          	mov    %rsi,-0x10(%rbp)
  400752:	66 89 45 fc          	mov    %ax,-0x4(%rbp)
  400756:	0f b7 45 fc          	movzwl -0x4(%rbp),%eax
  40075a:	89 c7                	mov    %eax,%edi
  40075c:	e8 f5 fe ff ff       	callq  400656 <add_map>
  400761:	89 c2                	mov    %eax,%edx
  400763:	48 8b 45 f0          	mov    -0x10(%rbp),%rax
  400767:	66 89 10             	mov    %dx,(%rax)
  40076a:	c9                   	leaveq 
  40076b:	c3                   	retq   

000000000040076c <main>:
  40076c:	55                   	push   %rbp
  40076d:	48 89 e5             	mov    %rsp,%rbp
  400770:	48 83 ec 20          	sub    $0x20,%rsp
  400774:	89 7d ec             	mov    %edi,-0x14(%rbp)
  400777:	48 89 75 e0          	mov    %rsi,-0x20(%rbp)
  40077b:	64 48 8b 04 25 28 00 	mov    %fs:0x28,%rax
  400782:	00 00 
  400784:	48 89 45 f8          	mov    %rax,-0x8(%rbp)
  400788:	31 c0                	xor    %eax,%eax
  40078a:	bf 70 09 40 00       	mov    $0x400970,%edi
  40078f:	e8 6c fd ff ff       	callq  400500 <puts@plt>
  400794:	66 c7 45 f0 00 00    	movw   $0x0,-0x10(%rbp)
  40079a:	c7 45 f4 00 00 00 00 	movl   $0x0,-0xc(%rbp)
  4007a1:	e9 ce 00 00 00       	jmpq   400874 <main+0x108>
  4007a6:	c7 45 f4 00 00 00 00 	movl   $0x0,-0xc(%rbp)
  4007ad:	e9 a8 00 00 00       	jmpq   40085a <main+0xee>
  4007b2:	8b 45 f4             	mov    -0xc(%rbp),%eax
  4007b5:	0f b7 c0             	movzwl %ax,%eax
  4007b8:	48 8d 55 f2          	lea    -0xe(%rbp),%rdx
  4007bc:	48 89 d6             	mov    %rdx,%rsi
  4007bf:	89 c7                	mov    %eax,%edi
  4007c1:	e8 7e ff ff ff       	callq  400744 <f>
  4007c6:	0f b7 45 f0          	movzwl -0x10(%rbp),%eax
  4007ca:	66 83 f8 18          	cmp    $0x18,%ax
  4007ce:	77 30                	ja     400800 <main+0x94>
  4007d0:	48 8b 0d 21 09 20 00 	mov    0x200921(%rip),%rcx        # 6010f8 <color+0x38>
  4007d7:	0f b7 45 f2          	movzwl -0xe(%rbp),%eax
  4007db:	0f b7 d0             	movzwl %ax,%edx
  4007de:	8b 45 f4             	mov    -0xc(%rbp),%eax
  4007e1:	83 e0 07             	and    $0x7,%eax
  4007e4:	48 8b 04 c5 c0 10 60 	mov    0x6010c0(,%rax,8),%rax
  4007eb:	00 
  4007ec:	48 89 c6             	mov    %rax,%rsi
  4007ef:	bf 91 09 40 00       	mov    $0x400991,%edi
  4007f4:	b8 00 00 00 00       	mov    $0x0,%eax
  4007f9:	e8 22 fd ff ff       	callq  400520 <printf@plt>
  4007fe:	eb 56                	jmp    400856 <main+0xea>
  400800:	0f b7 05 2b 09 20 00 	movzwl 0x20092b(%rip),%eax        # 601132 <carry>
  400807:	0f b7 c0             	movzwl %ax,%eax
  40080a:	89 c6                	mov    %eax,%esi
  40080c:	bf 9a 09 40 00       	mov    $0x40099a,%edi
  400811:	b8 00 00 00 00       	mov    $0x0,%eax
  400816:	e8 05 fd ff ff       	callq  400520 <printf@plt>
  40081b:	48 8b 35 d6 08 20 00 	mov    0x2008d6(%rip),%rsi        # 6010f8 <color+0x38>
  400822:	0f b7 45 f2          	movzwl -0xe(%rbp),%eax
  400826:	0f b7 c8             	movzwl %ax,%ecx
  400829:	0f b7 05 02 09 20 00 	movzwl 0x200902(%rip),%eax        # 601132 <carry>
  400830:	0f b7 d0             	movzwl %ax,%edx
  400833:	8b 45 f4             	mov    -0xc(%rbp),%eax
  400836:	83 e0 07             	and    $0x7,%eax
  400839:	48 8b 04 c5 c0 10 60 	mov    0x6010c0(,%rax,8),%rax
  400840:	00 
  400841:	49 89 f0             	mov    %rsi,%r8
  400844:	48 89 c6             	mov    %rax,%rsi
  400847:	bf a6 09 40 00       	mov    $0x4009a6,%edi
  40084c:	b8 00 00 00 00       	mov    $0x0,%eax
  400851:	e8 ca fc ff ff       	callq  400520 <printf@plt>
  400856:	83 45 f4 01          	addl   $0x1,-0xc(%rbp)
  40085a:	0f b7 45 f0          	movzwl -0x10(%rbp),%eax
  40085e:	0f b7 c0             	movzwl %ax,%eax
  400861:	3b 45 f4             	cmp    -0xc(%rbp),%eax
  400864:	0f 83 48 ff ff ff    	jae    4007b2 <main+0x46>
  40086a:	bf b3 09 40 00       	mov    $0x4009b3,%edi
  40086f:	e8 8c fc ff ff       	callq  400500 <puts@plt>
  400874:	48 8d 45 f0          	lea    -0x10(%rbp),%rax
  400878:	48 89 c6             	mov    %rax,%rsi
  40087b:	bf b4 09 40 00       	mov    $0x4009b4,%edi
  400880:	b8 00 00 00 00       	mov    $0x0,%eax
  400885:	e8 c6 fc ff ff       	callq  400550 <__isoc99_scanf@plt>
  40088a:	83 f8 ff             	cmp    $0xffffffff,%eax
  40088d:	0f 85 13 ff ff ff    	jne    4007a6 <main+0x3a>
  400893:	b8 00 00 00 00       	mov    $0x0,%eax
  400898:	48 8b 4d f8          	mov    -0x8(%rbp),%rcx
  40089c:	64 48 33 0c 25 28 00 	xor    %fs:0x28,%rcx
  4008a3:	00 00 
  4008a5:	74 05                	je     4008ac <main+0x140>
  4008a7:	e8 64 fc ff ff       	callq  400510 <__stack_chk_fail@plt>
  4008ac:	c9                   	leaveq 
  4008ad:	c3                   	retq   
  4008ae:	66 90                	xchg   %ax,%ax

00000000004008b0 <__libc_csu_init>:
  4008b0:	41 57                	push   %r15
  4008b2:	41 89 ff             	mov    %edi,%r15d
  4008b5:	41 56                	push   %r14
  4008b7:	49 89 f6             	mov    %rsi,%r14
  4008ba:	41 55                	push   %r13
  4008bc:	49 89 d5             	mov    %rdx,%r13
  4008bf:	41 54                	push   %r12
  4008c1:	4c 8d 25 48 05 20 00 	lea    0x200548(%rip),%r12        # 600e10 <__frame_dummy_init_array_entry>
  4008c8:	55                   	push   %rbp
  4008c9:	48 8d 2d 48 05 20 00 	lea    0x200548(%rip),%rbp        # 600e18 <__init_array_end>
  4008d0:	53                   	push   %rbx
  4008d1:	4c 29 e5             	sub    %r12,%rbp
  4008d4:	31 db                	xor    %ebx,%ebx
  4008d6:	48 c1 fd 03          	sar    $0x3,%rbp
  4008da:	48 83 ec 08          	sub    $0x8,%rsp
  4008de:	e8 ed fb ff ff       	callq  4004d0 <_init>
  4008e3:	48 85 ed             	test   %rbp,%rbp
  4008e6:	74 1e                	je     400906 <__libc_csu_init+0x56>
  4008e8:	0f 1f 84 00 00 00 00 	nopl   0x0(%rax,%rax,1)
  4008ef:	00 
  4008f0:	4c 89 ea             	mov    %r13,%rdx
  4008f3:	4c 89 f6             	mov    %r14,%rsi
  4008f6:	44 89 ff             	mov    %r15d,%edi
  4008f9:	41 ff 14 dc          	callq  *(%r12,%rbx,8)
  4008fd:	48 83 c3 01          	add    $0x1,%rbx
  400901:	48 39 eb             	cmp    %rbp,%rbx
  400904:	75 ea                	jne    4008f0 <__libc_csu_init+0x40>
  400906:	48 83 c4 08          	add    $0x8,%rsp
  40090a:	5b                   	pop    %rbx
  40090b:	5d                   	pop    %rbp
  40090c:	41 5c                	pop    %r12
  40090e:	41 5d                	pop    %r13
  400910:	41 5e                	pop    %r14
  400912:	41 5f                	pop    %r15
  400914:	c3                   	retq   
  400915:	66 66 2e 0f 1f 84 00 	data16 nopw %cs:0x0(%rax,%rax,1)
  40091c:	00 00 00 00 

0000000000400920 <__libc_csu_fini>:
  400920:	f3 c3                	repz retq 

Disassembly of section .fini:

0000000000400924 <_fini>:
  400924:	48 83 ec 08          	sub    $0x8,%rsp
  400928:	48 83 c4 08          	add    $0x8,%rsp
  40092c:	c3                   	retq   

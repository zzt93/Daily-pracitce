#-*- coding:utf-8 -*-
import pdb

class Solution:
    def solve(self, A):
        self.qs(A, 0, len(A))
        
    def qs(self, A, b, e):
        print b, e
        if(b + 1 >= e):
            return
        
        mid = self.partition(A, b, e)
        self.qs(A, b, mid[0])
        self.qs(A, mid[1], e)
        return
    
    def partition(self, A, b, e):
        pivot = A[b]
        indices = []
        equ = b
        lar = b + 1
        i = b + 1
        while(i != e):
            if(A[i] < pivot):
                self.swap(A, i, equ)
                equ = equ + 1
                self.swap(A, i, lar)
                lar = lar + 1
            elif(A[i] == pivot):
                self.swap(A, i, lar)
                lar = lar + 1

            i = i + 1
            
        indices.append(equ)
        indices.append(lar)
        return indices

    def swap(self, A, i, j):
        t = A[i]
        A[i] = A[j]
        A[j] = t
        


s = Solution()
a = [1, 5, 3, 3, 3, 5, 1, 3, 5, 1]
#pdb.set_trace()
s.solve(a)
print a
#include <iostream>
#include <algorithm>

int main(){
}

template <class In>
bool equal(In b, In e, In b2){
    while(b != e){
        if(*b++ != *b2++){
            return false;
        }
    }
    return true;
}

template <class In>
bool search(In b, In e, In b2, In e2){
    while(b != e && !equal(b2, e2, b)){
        ++b;
    }
    return b;
}

template <class In, class X>
In find(In b, In e, X x){
    while(b != e){
        if(*b == x){
            return b;
        }
    }
}

template <class In, class F>
In find_if(In b, In e, F p){
    while(b != e && !p(*b)){
        ++b;
    }
    return b;
}

template <class In, class Out>
Out copy(In b, In e, Out d){
    while(b != e){
        *d = *b;
    }
    return ++d;
}

template <class In, class Out, class X>
Out remove_copy(In b, In e, Out d, X x){
    while(b != e){
        if(*b != x){
            *d = *b;
        }
    }
    return ++d;
}

template <class In, class Out, class P>
Out remove_copy_if(In b, In e, Out d, P p){
    while(b != e){
        if(!p(*b)){
            *d = *b;
        }
    }
}

template <class In, class Out, class F>
Out transform(In b, In e, Out d, F f){
    while(b != e){
        *d = f(*b);
    }
}

template <class Bi, class F, class T>
Bi partition(Bi b, Bi e, F f){
    while(b != e){
        if(!f(*b)){
            while(!f(*--e) && b != e){}
            if(b == e){
                return b;
            }else{
                T temp = *b;
                *b = *e;
                *e = temp;
            }
        }
        ++b;
    }
    return b;
}

template <class In, class T>
T accumulate(In b, In e, T t){
    T sum = t;
    while(b != e){
        sum += *b;
    }
}
#ifndef __MY_ALGO_HPP__
#define __MY_ALGO_HPP__

#include <algorithm>

template <class Ran, class T>
Ran binary_search_index(Ran b, Ran e, const T& v) {
    Ran res = e;
    while (b < e) {
        auto d = std::distance(b, e);
        auto cen = b + d/2;
        if (*cen > v) {
            e = cen;
        } else if (*cen < v){
            b = cen + 1;
        } else {
            return cen;
        }
    }
    return res;
}

template <class Ran, class T, class F>
Ran binary_search_index(Ran b, Ran e, const T& v, F f) {
    Ran res = e;
    while (b < e) {
        auto d = std::distance(b, e);
        auto cen = b + d/2;
        if (f(*cen, v)) {
            b = cen + 1;
        } else if (!f(*cen, v) && !f(v, *cen)){
            return cen;
        } else {
            e = cen;
        }
    }
    return res;
}

#endif /* __MY_ALGO_HPP__ */

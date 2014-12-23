#include <iostream>

class Digit
{
private:
    int m_nDigit;
public:
    Digit(int nDigit=0)
    {
        m_nDigit = nDigit;
    }
 
    Digit& operator++(); // prefix
    Digit& operator--(); // prefix
 
    Digit operator++(int); // postfix
    Digit operator--(int); // postfix
 
    int GetDigit() const { return m_nDigit; }
};
 
Digit& Digit::operator++()
{
    std::cout << "in operator++" << std::endl;
    // If our number is already at 9, wrap around to 0
    if (m_nDigit == 9)
        m_nDigit = 0;
    // otherwise just increment to next number
    else
        ++m_nDigit;
 
    return *this;
}
 
Digit& Digit::operator--()
{
    // If our number is already at 0, wrap around to 9
    if (m_nDigit == 0)
        m_nDigit = 9;
    // otherwise just decrement to next number
    else
        --m_nDigit;
 
    return *this;
}
 
Digit Digit::operator++(int)
{
    // Create a temporary variable with our current digit
    Digit cResult(m_nDigit);
 
    // Use prefix operator to increment this digit
    ++(*this);             // apply operator
 
    // return temporary result
    return cResult;       // return saved state
}
 
Digit Digit::operator--(int)
{
    // Create a temporary variable with our current digit
    Digit cResult(m_nDigit);

    // Use prefix operator to increment this digit
    --(*this);             // apply operator

    // return temporary result
    return cResult;       // return saved state
}

int main()
{
    Digit cDigit(5);
    ++cDigit; // calls Digit::operator++();
    //    cDigit++; // calls Digit::operator++(int);

    return 0;
}

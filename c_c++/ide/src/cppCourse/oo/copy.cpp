#include <iostream>

class Foo
{
public:
    Foo(void)
    {
    	std::cout << "In Foo::Foo()" << std::endl;
    }

    Foo(const Foo& rhs)
    {
    	std::cout << "In Foo::Foo(const Foo&)" << std::endl;
    }
};

class Bar
{
public:
    Bar(void)
    {
    	std::cout << "In Bar::Bar()" << std::endl;
    }

    Bar(const Bar& rhs)
    {
    	std::cout << "In Bar::Bar(const Bar&)" << std::endl;
    }
};

class Baz
{
public:
    Foo foo;
    Bar bar;

    Baz(void)
    {
    	std::cout << "In Baz::Baz()" << std::endl;
    }

    Baz(const Baz& rhs)
    {
    	std::cout << "In Baz::Baz(const Baz&)" << std::endl;
    }
    /* By comment the above copy constructor, it will call foo bar's copy constructor*/
};

int main(void)
{
    Baz baz1;
    Baz baz2(baz1);

    return 0;
}

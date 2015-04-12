#include <iostream>
#include <fstream>
#include <random>
#include <cstdlib>

using std::cout;
using std::endl;
using std::ofstream;


int main(int argc, char *argv[])
{
    if (argc == 1) {
        cout << "Usage: radom_generator type(int double) low high number" << endl;
    } else if(argc == 5){
        ofstream out("random numbers");
        double lower_bound = atof(argv[2]);
        double high_bound = atof(argv[3]);
        std::default_random_engine re;
        if (argv[1][0] == 'i') {
            std::uniform_int_distribution<int> unif(lower_bound, high_bound);
            for (int i = 0; i < atoi(argv[4]); i++) {
                out << unif(re) << endl;
            }
        } else {
            std::uniform_real_distribution<double> unif(lower_bound, high_bound);
            for (int i = 0; i < atoi(argv[4]); i++) {
                out << unif(re) << endl;
            }
        }
        out.close();

    }

    return 0;
}


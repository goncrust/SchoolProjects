#include <iostream>
#include <cmath>

int main()
{
    int a;
    int b;
    int c;

    int delta;

    int res1;
    int res2;

    bool donedelta = false;
    while (!donedelta)
    {

        bool donea = false;
        while (!donea)
        {
            std::cout << "a: ";
            std::cin >> a;

            if (a != 0)
            {
                donea = true;
            }
            else
            {
                std::cout << "Not 2nd grade";
            }
        }

        std::cout << "b: ";
        std::cin >> b;

        std::cout << "c: ";
        std::cin >> c;

        //delta
        delta = pow(b, 2) - (4 * a * c);

        if (delta < 0)
        {
            std::cout << "Imp, delta: " << delta << std::endl;
        }
        else
        {
            donedelta = true;
        }
    }

    if (delta == 0)
    {
        res1 = -b / (2 * a);

        std::cout << res1;
    }
    else
    {
        res1 = (-b + sqrt(delta)) / (2 * a);
        res2 = (-b - sqrt(delta)) / (2 * a);

        std::cout << res1 << std::endl;
        std::cout << res2;
    }
}

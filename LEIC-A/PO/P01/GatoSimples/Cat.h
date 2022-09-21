#ifndef __CAT_H__
#define __CAT_H__

typedef struct cat *Cat;

Cat newCat(const char *name, int age, double weight, int purrLevel, double fluffiness);
void destroyCat(Cat cat);

int equalsCat(Cat cat1, Cat cat2);
const char *getCatName(Cat cat);
int getCatAge(Cat cat);
double getCatWeight(Cat cat);
int getCatPurrLevel(Cat cat);
double getCatFluffiness(Cat cat);

void printCat(Cat cat);

#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Cat.h"

struct cat {
    char _name[16];
    int _age;
    double _weight; 
    int _purrLevel;
    double _fluffiness;
};

Cat newCat(const char *name, int age, double weight, int purrLevel, double fluffiness) {
    Cat cat = (Cat) malloc(sizeof(struct cat));
    if (cat != NULL) {
        strcpy(cat->_name, name);
        cat->_age = age;
        cat->_weight = weight;
        cat->_purrLevel = purrLevel;
        cat->_fluffiness = fluffiness;
    }

    return cat;
}

void destroyCat(Cat cat) {
    if (cat != NULL) {
        free(cat);
    }
}

const char *getCatName(Cat cat) {
    return cat->_name;
}

int getCatAge(Cat cat) {
    return cat->_age;
}

double getCatWeight(Cat cat) {
    return cat->_weight;
}

int getCatPurrLevel(Cat cat) {
    return cat->_purrLevel;
}

double getCatFluffiness(Cat cat) {
    return cat->_fluffiness;
}

int equalsCat(Cat cat1, Cat cat2) {
    if (cat1 == NULL || cat2 == NULL)
        return 0;

    if (strcmp(getCatName(cat1), getCatName(cat2)))
        return 0;

    return getCatAge(cat1) == getCatAge(cat2) &&
        getCatWeight(cat1) == getCatWeight(cat2) &&
        getCatPurrLevel(cat1) == getCatPurrLevel(cat2) &&
        getCatFluffiness(cat1) == getCatFluffiness(cat2);
}

void printCat(Cat cat) {
    printf("== Cat ==\n");
    printf("Name: %s\n", getCatName(cat));
    printf("Age: %d\n", getCatAge(cat));
    printf("Weight: %g\n", getCatWeight(cat));
    printf("PurrLevel: %d\n", getCatPurrLevel(cat));
    printf("Fluffiness: %g\n", getCatFluffiness(cat));
}

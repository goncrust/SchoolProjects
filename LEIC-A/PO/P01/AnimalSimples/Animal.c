#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Animal.h"

struct animal {
    char _name[16];
    int _age;
    double _weight;
};

Animal newAnimal(const char *name, int age, double weight) {
    Animal animal = (Animal) malloc(sizeof(struct animal));
    
    if (animal != NULL) {
        strcpy(animal->_name, name);
        animal->_age = age;
        animal->_weight = weight;
    }

    return animal;
}

void destroyAnimal(Animal animal) {
    if (animal != NULL) {
        free(animal);
    }
}

const char *getAnimalName(Animal animal) {
    return animal->_name;
}

int getAnimalAge(Animal animal) {
    return animal->_age;
}

double getAnimalWeight(Animal animal) {
    return animal->_weight;
}

int equalsAnimal(Animal animal1, Animal animal2) {
    if (animal1 == NULL || animal2 == NULL) 
        return 0;

    if (strcmp(animal1->_name, animal1->_name))
        return 0;
    if (animal1->_age != animal2->_age)
        return 0;
    if (animal1->_weight != animal2->_weight)
        return 0;

    return 1;
}

void printAnimal(Animal animal) {
    printf("== Animal ==\n");
    printf("Name: %s\n", getAnimalName(animal));
    printf("Age: %d\n", getAnimalAge(animal));
    printf("Weight: %g\n", getAnimalWeight(animal));
}

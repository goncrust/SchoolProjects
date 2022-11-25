#include <iostream>
#include <list>

std::list<int>* friends;
std::list<int>* persons_is_friend;

int num_persons, num_connections;

int* count1;
int* count2;
int biggest_count = 0;

int** matrix;

void readInput() {
    // read first line
    std::scanf("%d,%d", &num_persons, &num_connections);

    // initialize arrays
    friends = new std::list<int>[num_persons];
    persons_is_friend = new std::list<int>[num_persons];

    // read remaining lines
    int person;
    int conn;
    for (int i = 0; i < num_connections; i++) {
        std::scanf("%d %d", &person, &conn);

        // update arrays
        friends[person-1].push_back(conn-1);
        persons_is_friend[conn-1].push_back(person-1);
    }
}

void generateHistograms() {
    count1 = new int[num_connections+1];
    count2 = new int[num_connections+1];

    for (int i = 0; i < num_connections; i++) {
        count1[i] = 0;
        count2[i] = 0;
    }

    int curr_count;
    for (int i = 0; i < num_persons; i++) {
        curr_count = friends[i].size();
        if (curr_count > biggest_count)
            biggest_count = curr_count;
        count1[curr_count]++;
    }
    
    for (int i = 0; i < num_persons; i++) {
        curr_count = persons_is_friend[i].size();
        if (curr_count > biggest_count)
            biggest_count = curr_count;
        count2[curr_count]++;
    }

}

void printHistograms() {
    std::cout << "Histograma 1" << std::endl;

    for (int i = 0; i <= biggest_count; i++) {
        std::cout << count1[i] << std::endl;
    }

    std::cout << "Histograma 2" << std::endl;
    for (int i = 0; i <= biggest_count; i++) {
        std::cout << count2[i] << std::endl;
    }
}

void generateMatrix() {
    matrix = new int*[num_persons];
    
    for (int i = 0; i < num_persons; i++) {
        matrix[i] = new int[num_persons];
    }

    int count = 0;
    for (int x = 0; x < num_persons; x++) {
        for (int y = 0; y < num_persons; y++) {
            for (int i : friends[x]) {
                for (int y : friends[y]) {
                    if (i == y) {
                        count++;
                        break;
                    }
                }
            }
            matrix[x][y] = count;
            count = 0;
        }
    }
}

void printMatrix() {
    for (int x = 0; x < num_persons; x++) {
        for (int y = 0; y < num_persons; y++) {
            std::cout << matrix[x][y] << " ";
        }
        std::cout << std::endl;
    }
}

int main() {
    readInput();
    generateHistograms();
    printHistograms();
    std::cout << std::endl;
    generateMatrix();
    printMatrix();
}

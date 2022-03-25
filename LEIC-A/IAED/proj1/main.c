/* Commands:
 *   q - quit
 *   a - add new airport
 *   l - list airports
 *   v - add/list flights
 *   p - list flights with departure at 
 *   c - list flights with destination at
 *   t - advance the date
 *
 *
 * Start date: 1 january 2022
 * Max date: 31 december 2023
 * Date/time format: DD-MM-AAAA HH:MM
 * 
 * AirportID format: LLL COUNTRY(30 chars, no whitespaces) CITY(50 chars)
 * Max airports in the system: 40
 *
 * Flight format: LL[1-9999] Departure Destination DepartureDateAndTime Duration(<12h) MaxPassengers(10-100)
 * Max flights in the system: 30000
 */

/* qsort not allowed */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

/* Constants */

#define AIRPORTS_MAX 40
#define FLIGHTS_MAX 30000
#define AIRPORTID_SIZE 4
#define COUNTRY_SIZE 30
#define CITY_SIZE 50
#define FLIGHTID_SIZE 7

/* Structures */

typedef struct {
    int day;
    int month;
    int year;
} Date;

typedef struct {
    int hour;
    int minute;
} Time;

typedef struct {
    char id[AIRPORTID_SIZE];
    char country[COUNTRY_SIZE];
    char city[CITY_SIZE];
} Airport;

typedef struct {
    char id[FLIGHTID_SIZE];
    Airport departure;
    Airport destination;
    Date date;
    Time time;
    Time duration;
    int passengers_max;
} Flight;

int add_airport(Airport airports[], int airport_count) {
    int i, len;
    char space;
    Airport new_airport;

    scanf("%s%s%c", new_airport.id, new_airport.country, &space);
    fgets(new_airport.city, CITY_SIZE, stdin);

    /* Replace \n with \0 */
    len = strlen(new_airport.city);
    new_airport.city[len-1] = '\0';

    /* Errors */
    for (i = 0; i < AIRPORTID_SIZE-1; i++) {
        if (!isupper(new_airport.id[i])) {
            printf("invalid airport ID\n");
            return 1;
        }
    }

    if (airport_count >= AIRPORTS_MAX) {
        printf("too many airports\n");
        return 1;
    }

    for (i = 0; i < airport_count; i++) {
        if (strcmp(new_airport.id, airports[i].id) == 0) {
            printf("duplicate airport\n");
            return 1;
        }
    }

    strncpy(airports[airport_count].id, new_airport.id, AIRPORTID_SIZE);
    strncpy(airports[airport_count].country, new_airport.country, COUNTRY_SIZE);
    strncpy(airports[airport_count].city, new_airport.city, CITY_SIZE);

    printf("airport %s\n", airports[airport_count].id);

    return 0;
}

void print_airport_by_id(char id[], Airport airports[], int airport_count) {
    int i;
    for (i = 0; i < airport_count; i++) {
        if (strcmp(airports[i].id, id) == 0) {
            printf("%s %s %s\n", airports[i].id, airports[i].city, airports[i].country); 
            return;
        }
    }

    printf("no such airport ID\n");
}

int alpha_before(char w1[], char w2[], int word_size) {
    int i;
    for (i = 0; i < word_size; i++) {
        if (w1[i] < w2[i])
            return 1;
        else if (w1[i] > w2[i])
            return 0;
    }

    return 0;
}

int list_airports(Airport airports[], int airport_count) {
    int sort[AIRPORTS_MAX], i, j, aux;

    /* Reset sort vector */
    for (i = 0; i < airport_count; i++) sort[i] = i;

    for (i = airport_count-1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            if (alpha_before(airports[sort[j+1]].id,
                        airports[sort[j]].id, AIRPORTID_SIZE-1)) {
                aux = sort[j];
                sort[j] = sort[j+1];
                sort[j+1] = aux;
            }
        }
    }

    for (i = 0; i < airport_count; i++)
        printf("%s %s %s\n", airports[sort[i]].id, airports[sort[i]].city,
                airports[sort[i]].country); 

    return 0;
}

int list_airports_specified(Airport airports[], int airport_count) {
    char id[AIRPORTID_SIZE];
    int i = 0, end = 0;

    while (!end) {
        id[i] = getchar();
        if (id[i] == '\n')
            end = 1;

        if (i == 3) {
            id[3] = '\0';
            print_airport_by_id(id, airports, airport_count);
            i = 0;
            continue;
        }

        i++;
    }

    return 0;
}

int main() {
    Airport airports[AIRPORTS_MAX];
    int airport_count = 0;
    /* Flight flights[FLIGHTS_MAX]; */

    /* running - 0 if program termanted */
    /* error - used to store the return of functions (0 - success, 1 - error) */
    int running = 1, error;
    
    /* Main Loop */
    while (running) {

        /* Read command */
        char command, last_char;
        scanf("%c%c", &command, &last_char);

        switch (command) {
            case 'q':
                running = 0;
                break;
            case 'a':
                error = add_airport(airports, airport_count);
                if (!error)
                    airport_count++;
                break;
            case 'l':
                if (last_char == ' ')
                    list_airports_specified(airports, airport_count);
                else if (last_char == '\n')
                    list_airports(airports, airport_count);
                break;
            case 'v':
                break;
            case 'p':
                break;
            case 'c':
                break;
            case 't':
                break;
            default:
                break;
        }
    }

    return 0;
}

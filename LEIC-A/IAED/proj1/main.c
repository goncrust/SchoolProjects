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
#define AIRPORTID_SIZE 3
#define COUNTRY_SIZE 30
#define CITY_SIZE 50
#define FLIGHTID_SIZE 6

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

int main() {
    Airport airports[AIRPORTS_MAX];
    Flight flights[FLIGHTS_MAX];

    /* Main Loop */
    int running = 1;
    while (running) {

        /* Read command */
        char command;
        scanf("%c", &command);

        switch (command) {
            case 'd':
                running = 0;
                break;
            case 'a':
                break;
            case 'l':
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

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
#define FLIGHTID_LETTERS 2

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
    char departure_id[AIRPORTID_SIZE];
    char destination_id[AIRPORTID_SIZE];
    Date date;
    Time time;
    Time duration;
    int capacity;
} Flight;

/* Date functions */

int date_before(Date d1, Date d2) {
    if (d1.year < d2.year) return 1;
    else if (d1.year > d2.year) return 0;

    if (d1.month < d2.month) return 1;
    else if (d1.month > d2.month) return 0;

    if (d1.day < d2.day) return 1;

    return 0;
}

int one_year_ahead(Date d1, Date d2) {

    if (d1.year - d2.year > 1)
        return 1;
    else if (d1.year - d2.year == 1) {
        if (d1.month > d2.month)
            return 1;
        else if (d1.month == d2.month) {
            if (d1.day > d2.day)
                return 1;
        }
    }

    return 0;
}

void print_date(Date date) {
    if (date.day < 10)
        printf("0%d-", date.day);
    else
        printf("%d-", date.day);

    if(date.month < 10)
        printf("0%d-", date.month);
    else
        printf("%d-", date.day);

    printf("%d", date.year);
}

int invalid_date(Date date, Date curr_date) {
    if(date_before(date, curr_date) ||
            one_year_ahead(date, curr_date)) {

        printf("invalid date\n");
        return 1;
    }

    return 0;
}

/* Time functions */

void print_time(Time time) {
    if (time.hour < 10)
        printf("0%d:", time.hour);
    else
        printf("%d:", time.hour);

    if (time.minute < 10)
        printf("0%d", time.minute);
    else
        printf("%d", time.minute);
}

/* Auxiliar functions */

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

/* Command functions */

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
        if (new_airport.id[i] < 'A' || new_airport.id[i] > 'Z') {
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

    airports[airport_count] = new_airport;
    printf("airport %s\n", airports[airport_count].id);

    return 0;
}

void list_airports(Airport airports[], int airport_count) {
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

}

void list_airports_specified(Airport airports[], int airport_count) {
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
}

int add_flight(Flight flights[], int flight_count, Airport airports[],
        int airport_count, Date curr_date) {

    int i, found_departure = 0, found_destination = 0, error = 0;
    char space, dash, colon, new_line;
    Flight new_flight;

    /* Airports ID */
    scanf("%s%s%s%c", new_flight.id, new_flight.departure_id,
            new_flight.destination_id, &space);
    /* Departure date */
    scanf("%d%c%d%c%d%c", &new_flight.date.day, &dash, &new_flight.date.month,
            &dash, &new_flight.date.year, &space);
    /* Departure time */
    scanf("%d%c%d%c", &new_flight.time.hour, &colon, &new_flight.time.minute,
            &space);
    /* Duration */
    scanf("%d%c%d%c", &new_flight.duration.hour, &colon,
            &new_flight.duration.minute, &space);
    /* Capacity */
    scanf("%d%c", &new_flight.capacity, &new_line);

    /* Errors */
    i = 0;
    while (new_flight.id[i] != '\0') {
        if (i < FLIGHTID_LETTERS) {
            if (new_flight.id[i] < 'A' || new_flight.id[i] > 'Z') {
                error = 1;
                break;
            }
        } else {
            if (new_flight.id[i] == '0' && i == FLIGHTID_LETTERS) {
                error = 1;
                break;
            }

            if (new_flight.id[i] < '0' || new_flight.id[i] > '9') {
                error = 1;
                break;
            }
        }
        i++;
    }
    if (error || i < 3) {
        printf("invalid flight code\n");
        return 1;
    }

    for (i = 0; i < flight_count; i++) {
        if (strcmp(new_flight.id, flights[i].id) == 0) {
            printf("flight already exists\n");
            return 1;
        }
    }

    for (i = 0; i < airport_count; i++) {
        if (strcmp(new_flight.departure_id, airports[i].id) == 0) {
            found_departure = 1;
        }
        if(strcmp(new_flight.destination_id, airports[i].id) == 0) {
            found_destination = 1;
        }
    }
    if (!(found_departure && found_destination)) {
        printf("no such airport ID\n");
        return 1;
    }

    if (flight_count >= FLIGHTS_MAX) {
        printf("too many flights\n");
        return 1;
    }

    if (invalid_date(new_flight.date, curr_date))
        return 1;

    if (new_flight.duration.hour > 12 || 
            (new_flight.duration.hour == 12 && new_flight.duration.minute > 0)) {
        printf("invalid duration\n");
        return 1;
    }

    if (new_flight.capacity < 10 || new_flight.capacity > 100) {
        printf("invalid capacity\n");
        return 1;
    }

    flights[flight_count] = new_flight;

    return 0;
}

void list_flights(Flight flights[], int flight_count) {
    int i;
    for (i = 0; i < flight_count; i++) {
        printf("%s %s %s ", flights[i].id, flights[i].departure_id,
                flights[i].destination_id);

        print_date(flights[i].date);
        printf(" ");
        print_time(flights[i].time);
        printf("\n");
    }
}

Date change_date(Date curr_date) {
    char dash;
    Date new_date;

    scanf("%d%c%d%c%d%c", &new_date.day, &dash, &new_date.month, &dash,
            &new_date.year, &dash);

    if (invalid_date(new_date, curr_date))
        return curr_date;

    print_date(new_date);
    printf("\n");
    return new_date;
}

int main() {
    Airport airports[AIRPORTS_MAX];
    int airport_count = 0;
    Flight flights[FLIGHTS_MAX];
    int flight_count = 0;
    Date date = { 1, 1, 2022 };

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
                if (last_char == ' ') {
                    error = add_flight(flights, flight_count, airports,
                            airport_count, date);

                    if (!error)
                        flight_count++;
                } else if (last_char == '\n') {
                    list_flights(flights, flight_count);
                }
                break;
            case 'p':
                break;
            case 'c':
                break;
            case 't':
                date = change_date(date);
                break;
            default:
                break;
        }
    }

    return 0;
}

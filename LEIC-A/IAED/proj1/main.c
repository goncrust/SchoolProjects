/*
 * File: main.c
 * Author: goncrust
 * Description: First IAED project - Flight management system between airports.
 */
#include <stdio.h>
#include <string.h>

/* Constants */

/* Max number of airports and flights */
#define AIRPORTS_MAX 40
#define FLIGHTS_MAX 30000

/* Char array sizes */
#define AIRPORTID_SIZE 4
#define COUNTRY_SIZE 31
#define CITY_SIZE 51
#define FLIGHTID_SIZE 7
#define FLIGHTID_LETTERS 2

/* Error messages */
#define DATE_ERROR "invalid date\n"
#define AIRPORTID_NOTFOUND "%s: no such airport ID\n"
#define AIRPORTID_ERROR "invalid airport ID\n"
#define AIRPORTCOUNT_ERROR "too many airports\n"
#define AIRPORTDUP_ERROR "duplicate airport\n"
#define FLIGHTID_ERROR "invalid flight code\n"
#define FLIGHTDUP_ERROR "flight already exists\n"
#define FLIGHTCOUNT_ERROR "too many flights\n"
#define FLIGHTDURATION_ERROR "invalid duration\n"
#define FLIGHTCAP_ERROR "invalid capacity\n"

/* Date and time constants */
#define INITIAL_DAY 1
#define INITIAL_MONTH 1
#define INITIAL_YEAR 2022
#define FLIGHTDURATION_MAX 12

/* Other */
#define FLIGHTCAP_MIN 10
#define FLIGHTCAP_MAX 100
#define AIRPORTADD_MSG "airport %s\n"

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
    Date date;
    Time time;
} DateTime;

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

/* Returns 1 if date d1 is before date d2, otherwise returns 0 */
int date_before(Date d1, Date d2) {
    if (d1.year < d2.year) return 1;
    else if (d1.year > d2.year) return 0;

    if (d1.month < d2.month) return 1;
    else if (d1.month > d2.month) return 0;

    if (d1.day < d2.day) return 1;

    return 0;
}

/* Returns 1 if date d1 is more than 1 year ahead of date d2,
 * otherwise returns 0 
 */
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

/* Print given date to stdout, with dd-mm-yyyy format */
void print_date(Date date) {
    if (date.day < 10)
        printf("0%d-", date.day);
    else
        printf("%d-", date.day);

    if(date.month < 10)
        printf("0%d-", date.month);
    else
        printf("%d-", date.month);

    printf("%d", date.year);
}

/* Returns 1 if date is invalid, otherwise returns 0.
 * A given date is invalid if it is before or more than one year ahead
 * of the current date (curr_date).
 */
int invalid_date(Date date, Date curr_date) {
    if(date_before(date, curr_date) ||
            one_year_ahead(date, curr_date)) {

        printf(DATE_ERROR);
        return 1;
    }

    return 0;
}

/* Returns 1 if dates d1 and d2 are equal, otherwise returns 0. */
int equal_dates(Date d1, Date d2) {
    return (d1.day == d2.day && d1.month == d2.month && d1.year == d2.year);
}

/* Time functions */

/* Print given time to stdout, with hh:mm format */
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

/* Returns 1 if time t1 is before time t2, otherwise returns 0 */
int time_before(Time t1, Time t2) {
    if (t1.hour < t2.hour) return 1;
    else if (t1.hour > t2.hour) return 0;

    if (t1.minute < t2.minute) return 1;

    return 0;
}

/* DateTime functions */

/* Returns 1 if date d1 at time t1 is before date d2 at time t2,
 * otherwise returns 0. 
 */
int date_time_before(Date d1, Time t1, Date d2, Time t2) {
    if (d1.year < d2.year) return 1;
    else if (d1.year > d2.year) return 0;

    if (d1.month < d2.month) return 1;
    else if (d1.month > d2.month) return 0;

    if (d1.day < d2.day) return 1;
    else if (d1.day > d2.day) return 0;

    if (time_before(t1, t2)) return 1;

    return 0;
}

/* Given a date, time and duration of a flight, calculates the date and
 * time of the arrival and returns a structure composed by that values.
 */
DateTime calc_arrival(Date date, Time time, Time duration) {
    int month;
    DateTime arrival;

    time.minute += duration.minute;
    if (time.minute > 59) {
        time.minute -= 60;
        time.hour++;
    }

    time.hour += duration.hour;
    if (time.hour > 23) {
        time.hour -= 24;
        date.day++;
    }

    month = date.month;
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
            month == 10 || month == 12) {

        if (date.day > 31) {
            date.day = 1;
            date.month++;
        }
    } else if (month == 2) {
        if (date.day > 28) {
            date.day = 1;
            date.month = 3;
        }
    } else {
        if (date.day > 30) {
            date.day = 1;
            date.month++;
        }
    }

    if (date.month == 13) {
        date.month = 1;
        date.year++;
    }

    arrival.date = date;
    arrival.time = time;
    return arrival;
}

/* Auxiliar functions */

/* Returns 1 if word1 is alphabetically before word2, otherwise returns 0. */
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

/* Returns the number of flights with departure at the airport with given id. */
int airport_flight_count(char id[], Flight flights[], int flight_count) {
    int i, total = 0;
    for (i = 0; i < flight_count; i++) {
        if (strcmp(id, flights[i].departure_id) == 0)
            total++;
    }

    return total;
}

/* Prints to stdout the external representation of an airport, given its id. */
void print_airport_by_id(char id[], Airport airports[], int airport_count,
        Flight flights[], int flight_count) {

    int i;
    for (i = 0; i < airport_count; i++) {
        if (strcmp(airports[i].id, id) == 0) {
            printf("%s %s %s %d\n", airports[i].id, airports[i].city,
                    airports[i].country,
                    airport_flight_count(id, flights, flight_count)); 
            return;
        }
    }

    printf(AIRPORTID_NOTFOUND, id);
}

/* Return 1 if the given str is a valid flight code, otherwise returns 0. */
int valid_flightid(char str[]) {
    int i = 0, error = 0;
    while (str[i] != '\0') {
        if (i < FLIGHTID_LETTERS) {
            if (str[i] < 'A' || str[i] > 'Z') {
                error = 1;
                break;
            }
        } else {
            if (str[i] == '0' && i == FLIGHTID_LETTERS) {
                error = 1;
                break;
            }

            if (str[i] < '0' || str[i] > '9') {
                error = 1;
                break;
            }
        }
        i++;

        if (i >= FLIGHTID_SIZE) {
            error = 1;
            break;
        }
    }
    if (error || i < 3) {
        printf(FLIGHTID_ERROR);
        return 0;
    }

    return 1;
}

/* Given a departure and destination airport ids, returns 1 if they are
 * registered in the system. Otherwise returns 0.
 */
int flight_airport_notfound(Airport airports[], int airport_count,
        char departure_id[], char destination_id[]) {

    int i, found_departure = 0, found_destination = 0;
    for (i = 0; i < airport_count; i++) {
        if (strcmp(departure_id, airports[i].id) == 0) {
            found_departure = 1;
        }
        if(strcmp(destination_id, airports[i].id) == 0) {
            found_destination = 1;
        }
    }

    if (!(found_departure)) {
        printf(AIRPORTID_NOTFOUND, departure_id);
        return 1;
    }

    if (!(found_destination)) {
        printf(AIRPORTID_NOTFOUND, destination_id);
        return 1;
    }
    
    return 0;
}

/* Command functions */

/* Function for 'a' command (add airport to system) */
int add_airport(Airport airports[], int airport_count) {
    int i, len;
    char space;
    Airport new_airport;

    scanf("%s%s%c", new_airport.id, new_airport.country, &space);
    fgets(new_airport.city, CITY_SIZE+1, stdin);

    /* Replace \n with \0 */
    len = strlen(new_airport.city);
    new_airport.city[len-1] = '\0';

    /* Errors */
    for (i = 0; i < AIRPORTID_SIZE-1; i++) {
        if (new_airport.id[i] < 'A' || new_airport.id[i] > 'Z') {
            printf(AIRPORTID_ERROR);
            return 1;
        }
    }

    if (airport_count >= AIRPORTS_MAX) {
        printf(AIRPORTCOUNT_ERROR);
        return 1;
    }

    for (i = 0; i < airport_count; i++) {
        if (strcmp(new_airport.id, airports[i].id) == 0) {
            printf(AIRPORTDUP_ERROR);
            return 1;
        }
    }

    airports[airport_count] = new_airport;
    printf(AIRPORTADD_MSG, airports[airport_count].id);

    return 0;
}

/* Function for 'l' command (list airports in system) */
void list_airports(Airport airports[], int airport_count,
        Flight flights[], int flight_count) {
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
        printf("%s %s %s %d\n", airports[sort[i]].id, airports[sort[i]].city,
                airports[sort[i]].country,
                airport_flight_count(airports[sort[i]].id, flights, flight_count));

}

/* Function for 'l' command (list airports specified by the user) */
void list_airports_specified(Airport airports[], int airport_count,
        Flight flights[], int flight_count) {
    char id[AIRPORTID_SIZE];
    int i = 0, end = 0;

    while (!end) {
        id[i] = getchar();
        if (id[i] == '\n')
            end = 1;

        if (i == 3) {
            id[3] = '\0';
            print_airport_by_id(id, airports, airport_count,
                    flights, flight_count);
            i = 0;
            continue;
        }

        i++;
    }
}

/* Function for 'v' command (add flight to system) */
int add_flight(Flight flights[], int flight_count, Airport airports[],
        int airport_count, Date curr_date) {

    int i;
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
    if (!valid_flightid(new_flight.id))
        return 1;

    for (i = 0; i < flight_count; i++) {
        if (strcmp(new_flight.id, flights[i].id) == 0 &&
                equal_dates(new_flight.date, flights[i].date)) {
            printf(FLIGHTDUP_ERROR);
            return 1;
        }
    }

    if (flight_airport_notfound(airports, airport_count,
                new_flight.departure_id, new_flight.destination_id))
        return 1;

    if (flight_count >= FLIGHTS_MAX) {
        printf(FLIGHTCOUNT_ERROR);
        return 1;
    }

    if (invalid_date(new_flight.date, curr_date))
        return 1;

    if (new_flight.duration.hour > FLIGHTDURATION_MAX || 
            (new_flight.duration.hour == FLIGHTDURATION_MAX &&
             new_flight.duration.minute > 0)) {
        printf(FLIGHTDURATION_ERROR);
        return 1;
    }

    if (new_flight.capacity < FLIGHTCAP_MIN ||
            new_flight.capacity > FLIGHTCAP_MAX) {
        printf(FLIGHTCAP_ERROR);
        return 1;
    }

    flights[flight_count] = new_flight;

    return 0;
}

/* Function for 'v' command (list flights in system) */
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

/* Function for 'p' command (list flights in system, with given departure) */
void list_departures(Flight flights[], int flight_count,
        Airport airports[], int airport_count) {

    char id[AIRPORTID_SIZE], new_line;
    int selected_indexes[FLIGHTS_MAX], selected_indexes_count = 0, i, j,
        aux, found = 0;

    scanf("%s%c", id, &new_line);

    /* Errors */
    for (i = 0; i < airport_count; i++) {
        if (strcmp(airports[i].id, id) == 0) {
            found = 1;
            break;
        }
    }
    if (!found) {
        printf(AIRPORTID_NOTFOUND, id);
        return;
    }

    /* Select flights with desired departure id */
    for (i = 0; i < flight_count; i++) {
        if (strcmp(flights[i].departure_id, id) == 0) {
            selected_indexes[selected_indexes_count] = i;
            selected_indexes_count++;
        }
    }

    /* Sort by date */
    for (i = selected_indexes_count-1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            if (date_time_before(flights[selected_indexes[j+1]].date,
                        flights[selected_indexes[j+1]].time,
                        flights[selected_indexes[j]].date,
                        flights[selected_indexes[j]].time)) {

                aux = selected_indexes[j];
                selected_indexes[j] = selected_indexes[j+1];
                selected_indexes[j+1] = aux;
            }
        }
    }

    /* Print */
    for (i = 0; i < selected_indexes_count; i++) {
        printf("%s %s ", flights[selected_indexes[i]].id,
                flights[selected_indexes[i]].destination_id);
        print_date(flights[selected_indexes[i]].date);
        printf(" ");
        print_time(flights[selected_indexes[i]].time);
        printf("\n");
    }
}

/* Function for 'p' command (list flights in system, with given destination) */
void list_arrivals(Flight flights[], int flight_count,
        Airport airports[], int airport_count) {

    char id[AIRPORTID_SIZE], new_line;
    int selected_indexes[FLIGHTS_MAX], selected_indexes_count = 0, i, j,
        aux, found = 0;
    DateTime arrival_date_time[FLIGHTS_MAX], auxDT;

    scanf("%s%c", id, &new_line);

    /* Errors */
    for (i = 0; i < airport_count; i++) {
        if (strcmp(airports[i].id, id) == 0) {
            found = 1;
            break;
        }
    }
    if (!found) {
        printf(AIRPORTID_NOTFOUND, id);
        return;
    }

    /* Select flights with desired destination id */
    for (i = 0; i < flight_count; i++) {
        if (strcmp(flights[i].destination_id, id) == 0) {
            selected_indexes[selected_indexes_count] = i;
            selected_indexes_count++;
        }
    }

    for (i = 0; i < selected_indexes_count; i++) {
        arrival_date_time[i] = calc_arrival(flights[selected_indexes[i]].date,
                flights[selected_indexes[i]].time,
                flights[selected_indexes[i]].duration);
    }

    /* Sort by date */
    for (i = selected_indexes_count-1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            if (date_time_before(arrival_date_time[j+1].date,
                        arrival_date_time[j+1].time,
                        arrival_date_time[j].date,
                        arrival_date_time[j].time)) {

                aux = selected_indexes[j];
                selected_indexes[j] = selected_indexes[j+1];
                selected_indexes[j+1] = aux;

                auxDT = arrival_date_time[j];
                arrival_date_time[j] = arrival_date_time[j+1];
                arrival_date_time[j+1] = auxDT;
            }
        }
    }

    /* Print */
    for (i = 0; i < selected_indexes_count; i++) {
        printf("%s %s ", flights[selected_indexes[i]].id,
                flights[selected_indexes[i]].departure_id);
        print_date(arrival_date_time[i].date);
        printf(" ");
        print_time(arrival_date_time[i].time);
        printf("\n");
    }
}

/* Function for 't' command (advance date) */
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

/* Main function. Reads command and calls corresponding function. */
int main() {

    /* Airports in the system */
    Airport airports[AIRPORTS_MAX];
    int airport_count = 0;

    /* Flights in the system */
    Flight flights[FLIGHTS_MAX];
    int flight_count = 0;

    /* System date */
    Date date = { INITIAL_DAY, INITIAL_MONTH, INITIAL_YEAR };

    /* if running == 0 program termanted */
    /* error is used to store the return of functions (0-success, 1-error) */
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
                    list_airports_specified(airports, airport_count,
                            flights, flight_count);
                else if (last_char == '\n')
                    list_airports(airports, airport_count,
                            flights, flight_count);
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
                list_departures(flights, flight_count, airports, airport_count);
                break;
            case 'c':
                list_arrivals(flights, flight_count, airports, airport_count);
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

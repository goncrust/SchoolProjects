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
 * Flight format: LL[1-9999] Departure Destination DepartureDateAndTime(<12h) MaxPassengers(10-100)
 * Max flights in the system: 30000
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

/* qsort not allowed */

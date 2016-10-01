# Wildlife Tracker
![screenshot of project running](screenshot.png)

by [Karen Freeman-Smith](https://github.com/karenfreemansmith)
Version 0.0.0: September 30, 2016

## Description
Final Project for Week 4, Java at Epicodus. A program to track wildlife sightings using Java, PostgreSQL, and Spark with JUnit tests demonstrating abstract classes, interfaces, many-to-many database relationships and RESTful routing.

### Specifications
#### User Stories:
* As a supervisor, I can add/edit/delete animals.
* As a supervisor, I can add/edit/delete locations.
* As a supervisor, I can add/edit/delete rangers.
* As a supervisor, I can view a page that lists all sightings by a specified ranger.
* As a park ranger, I need to be able to enter sightings of wildlife that include the location and type of animal.
* As a park ranger, I will get an error if I don't include age or health for endangered animals.
* As a park ranger, I need to indicate age and health for endangered animals.
* As a park visitor, I can enter animal sightings.
* As a park visitor, I will get an error if I try to enter an endangered animal (or pokemon).
* As a pokemon trainer, I can enter pokemon sightings.
* As a pokemon trainer, I will get an error if I try to enter sighting for an animal other than pokemon.
* As a website user, I can view a page that lists all sightings sorted by most recent (default/home page)
* As a website user, I can view a page that lists all sightings in a certain location.
* As a website user, I can view a page that lists all sightings for a specified animal.

#### Technical Specifications:
| _Behavior_ | _Input_ | _Output_ |
|:---------------------------------------------------------------------:|:---------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------:|
| Store Animal Types | "Bear" | "Bear" |
| Store Optional Photo | "image.jpg" | "image.jpg" |
| Photo is optional | null | doesn't crash |
| Animal type | 1/2/3 | normal/endangered/pokemon |
| Store Animal Age if endangered | "adult" | "adult" |
| Store Animal Health if endangered | "sick" | "sick" |
| Store Animal CP if pokemon | 412 | 412 |
| Store Location | "near campsite" | "near campsite" |
| Store Optional Map Grid | 1,2 | 1,2 |
| Map grid is optional | null | doesn't crash |
| Store Ranger Name | "Ranger Smith", badge 2345, ranger@park.com, person(1) | "Ranger Smith", 2345, "ranger@park.com, "Joe Smith", 1234 Center St, Bolder, MT, 65432, 321-456-9876, joe@smith.com |
| Rangers get error if they forget to enter age of endangered animal | null | "This animal is endangered, you must include an age" |
| Rangers get error if they forget to enter health of endangered animal | null | "This animal is endangered, you must include a health status" |
| Allow Park Visitors to record sightings | "Joe Smith", 1234 Center St, Bolder, MT, 65432, 321-456-9876, joe@smith.com | "Joe Smith", 1234 Center St, Bolder, MT, 65432, 321-456-9876, joe@smith.com |
| Visitors get error if they sight an endangered animal | animal(2) | "This animal is endangered, please contact park ranger to verify sighting" |
| Visitors get error if they try to record a pokemon sighting | animal(3) | "That is not a real animal. If you are a pokemon trainer, please sign in again as a pokemon trainer. |
| Allow Pokemon Trainers to record pokemon sightings | "PicinicBasket", level 20, person(1) | "PicinicBasket", level 20, "Joe Smith", 1234 Center St, Bolder, MT, 65432, 321-456-9876, joe@smith.com |
| Pokemon trainers cannot enter non-pokemon animals. | animal(1) | "That is not a pokemon, please log out and report this sighting as a visitor." |
| Record Sightings with Timestamp | '2016-10-01 15:13' | '2016-10-01 15:13' |
| Sightings link to person | sighting(1) | person(2) |
| Sightings link to animal | sighting(1) | animal(1) |
| Sightings link to location | sighting(1) | location(1) |
| returns all sightings | n/a | list of sightings |
| returns sightings by location | location(1) | list of sightings |
| returns sightings by ranger | ranger(1) | list of sightings |
| returns sightings by animal | animal(1) | list of sightings |

#### Database Diagram:
![database diagram](database.png)

## Setup/Installation
* Clone directory
* Setup database in PSQL:
  * CREATE DATABASE wildlife_tracker;
  * \c wildlife_tracker
  * CREATE TABLE animals (id serial PRIMARY KEY, animalName varchar, photo varchar, type int, age int, health int, cp int);
  * CREATE TABLE sightings (id serial PRIMARY KEY, animalId int, personId int, locationId int, time timestamp);
  * CREATE TABLE people (id serial PRIMARY KEY, firstname varchar, lastname varchar, phonenumber varchar, address varchar, city varchar, state varchar, zip varchar, email varchar, badge int, workcontact varchar, trainerName varchar, level int);
  * CREATE TABLE locations (id serial PRIMARY KEY, description varchar, maprow int, mapcol int);
  * CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
* OR ... restore database from backup by running
  * CREATE DATABASE wildlife_tracker;
  * psql wildlife_tracker < db_backup.sql
  * CREATE DATABASE wildlife_tracker_test;
  * psql wildlife_tracker_test < db_backup.sql
* Type 'gradle run' inside the project directory
* Navigate to 'http://localhost:4567'

## Support & Contact
For questions, concerns, or suggestions please email karenfreemansmith@gmail.com

## Known Issues
* Sightings object refuses to even TRY to insert a new sighting
* Loading admin page throws an error about instantiating Person after first ranger is submited (ranger does end up int the database though)
* Missing some tests for "all()" functions
* Front-end UI not finished


## Technologies Used
Java, JUnit, Spark, PostgreSQL, Gradle

## Legal
*Licensed under the GNU General Public License v3.0*

Copyright (c) 2016 Copyright _Karen Freeman-Smith_ All Rights Reserved.

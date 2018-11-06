newman run collections/BuildingTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/building.txt
newman run collections/UserTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/user.txt
newman run collections/SubjectTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/subject.txt
newman run collections/SemesterTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/semester.txt
newman run collections/EventTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/event.txt
newman run collections/ClassroomTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/classroom.txt

newman run collections/ClassReservationTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/classReservation.txt
newman run collections/EventReservationTest.postman_collection.json  -e environments/TestEnvironment.postman_environment.json --insecure  > results/text/eventReservation.txt

read -p "A tesztelés lefutott..."
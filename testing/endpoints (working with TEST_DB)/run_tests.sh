newman run BuildingTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/building.txt
newman run UserTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/user.txt
newman run SubjectTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/subject.txt
newman run SemesterTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/semester.txt
newman run EventTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/event.txt
newman run ClassroomTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/classroom.txt

newman run ClassReservationTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/classReservation.txt
newman run EventReservationTest.postman_collection.json  -e TestEnvironment.postman_environment.json --insecure  > results/eventReservation.txt

read -p "A tesztelés lefutott..."
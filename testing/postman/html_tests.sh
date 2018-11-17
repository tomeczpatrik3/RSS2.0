newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/users.html collections/UserTest.postman_collection.json
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/buildings.html collections/BuildingTest.postman_collection.json
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/subjects.html collections/SubjectTest.postman_collection.json	
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/semesters.html collections/SemesterTest.postman_collection.json
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/events.html collections/EventTest.postman_collection.json	
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/classrooms.html collections/ClassroomTest.postman_collection.json	
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/messages.html collections/MesageTest.postman_collection.json	
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/class_reservations.html collections/ClassReservationTest.postman_collection.json	
newman run -e environments/TestEnvironment.postman_environment.json --reporters cli,html --reporter-html-template templates/template-custom.hbs --reporter-html-export results/html/event_reservations.html collections/EventReservationTest.postman_collection.json

read -p "A tesztelés lefutott..."
export class Routes {
    static USER: 							                      string = 'user';
    static USER_LOGIN: 							                      string = 'user/login';
    static USER_GET_NAMES: 						              string = 'user/getNames';
    static USER_FIND_BY_USERNAME:			                string = 'user/findByUsername';
    static USER_FIND_BY_NAME:			                    string = 'user/findByName';
    static USER_FIND_BY_ROLE: 					            string = 'user/findByRole';
    static USER_CREATE_USER: 					            string = 'user/createUser';
    static USER_DELETE_BY_USERNAME:				            string = 'user/deleteByUsername';
                                                  
    static SUBJECT: 		                                string = "subject";
    static SUBJECT_GET_SUBJECT_NAMES: 			            string = "subject/getSubjectNames";
    static SUBJECT_CREATE_SUBJECT: 		                    string = "subject/createSubject";
                                                  
    static RESERVATION: 		                            string = "reservation";
    static RESERVATION_CREATE_SIMPLE: 			                string = "reservation/createSimpleReservation";
    static RESERVATION_CREATE_SEMESTER: 			                string = "reservation/createSemesterReservation";
    static RESERVATION_CREATE_EVENT: 			                string = "reservation/createEventReservation";
    static RESERVATION_FIND_BY_USERNAME: 		            string = "reservation/findByUsername";
    static RESERVATION_FIND_BY_NAME: 		                string = "reservation/findByName";
    static RESERVATION_FIND_BY_ROOM_NAME: 		            string = "reservation/findByRoomName";
    static RESERVATION_FIND_BY_STATUS:                  string = "reservation/findByStatus";
    static RESERVATION_SET_STATUS:                      string = "reservation/setStatus";
                                                  
    static CLASSROOM: 			                            string = "classroom";
    static CLASSROOM_GET_NAMES: 			            string = "classroom/getNames";
    static CLASSROOM_FIND_BY_BUILDING_NAME: 			            string = "classroom/findByBuildingName";
    static CLASSROOM_FIND_BY_NAME: 			                string = "classroom/findByName";
    static CLASSROOM_FIND_BY_HAS_PC: 			            string = "classroom/findByHasPC";
    static CLASSROOM_FIND_BY_HAS_PROJECTOR: 			    string = "classroom/findByHasProjector";
    static CLASSROOM_FIND_BY_CHAIRS_LESS_THAN: 			    string = "classroom/findByChairsLessThan";
    static CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN: 			string = "classroom/findByChairsGreaterThan";
    static CLASSROOM_FIND_BY_CHAIRS_BETWEEN: 			    string = "classroom/findByChairsBetween";
    static CLASSROOM_DELETE_BY_ROOM_NAME: 			        string = "classroom/deleteByRoomName";
    static CLASSROOM_CREATE_CLASSROOM: 			            string = "classroom/createClassroom";
    static CLASSROOM_GET_BUILDINGS:                         string = "classroom/getBuildings";

    static BUILDING:  string = "building";
    static BUILDING_GET_NAMES: string = "building/getNames";
    static BUILDING_FIND_BY_ID: string = "building/findById";
    static BUILDING_FIND_BY_NAME: string = "building/findByName";
    static BUILDING_CREATE_BUILDING: string = "building/createBuilding";
    static BUILDING_DELETE_BY_NAME: string = "building/deleteByName";

    static SEMESTER: string = "semester";
    static SEMESTER_GET_NAMES: string = "semester/getSemesterNames";
    static SEMESTER_CREATE_SEMESTER: string ="semester/createSemester";
    static SEMESTER_DELETE_BY_NAME: string="semester/deleteByName";


    static getUrl(route: string): string {
      return `http://localhost:8080/api/${route}`;
    }
}
export class Routes {
    static USER: 							                string = 'user';
    static USER_LOGIN: 							                string = 'user/login';
    static USER_GET_NAMES: 						            string = 'user/getNames';
    static USER_FIND_BY_USERNAME:			                string = 'user/findByUsername';
    static USER_FIND_BY_NAME:			                    string = 'user/findByName';
    static USER_FIND_BY_ROLE: 					            string = 'user/findByRole';
    static USER_CREATE_USER: 					            string = 'user/createUser';
    static USER_DELETE_BY_USERNAME:				            string = 'user/deleteByUsername';
                                                  
    static SUBJECT: 		                                string = "subject";
    static SUBJECT_GET_SUBJECT_NAMES: 			            string = "subject/getSubjectNames";
    static SUBJECT_CREATE_SUBJECT: 		                    string = "subject/createSubject";
                                                  
    static RESERVATION: 		                            string = "reservation";
    static RESERVATION_CREATE_RES: 			                string = "reservation/createRes";
    static RESERVATION_FIND_BY_USERNAME: 		            string = "reservation/findByUsername";
    static RESERVATION_FIND_BY_NAME: 		                string = "reservation/findByName";
    static RESERVATION_FIND_BY_ROOM_NAME: 		            string = "reservation/findByRoomName";
                                                  
    static CLASSROOM: 			                            string = "classroom";
    static CLASSROOM_GET_ROOM_NAMES: 			            string = "classroom/getRoomNames";
    static CLASSROOM_FIND_BY_BUILDING: 			            string = "classroom/findByBuilding";
    static CLASSROOM_FIND_BY_NAME: 			                string = "classroom/findByName";
    static CLASSROOM_FIND_BY_HAS_PC: 			            string = "classroom/findByHasPC";
    static CLASSROOM_FIND_BY_HAS_PROJECTOR: 			    string = "classroom/findByHasProjector";
    static CLASSROOM_FIND_BY_CHAIRS_LESS_THAN: 			    string = "classroom/findByChairsLessThan";
    static CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN: 			string = "classroom/findByChairsGreaterThan";
    static CLASSROOM_FIND_BY_CHAIRS_BETWEEN: 			    string = "classroom/findByChairsBetween";
    static CLASSROOM_DELETE_BY_ROOM_NAME: 			        string = "classroom/deleteByRoomName";
    static CLASSROOM_CREATE_CLASSROOM: 			            string = "classroom/createClassroom";
    static CLASSROOM_GET_BUILDINGS:                         string = "classroom/getBuildings";
}

export class Server {
  private static address: 					          string = 'localhost';
  private static port: 						          string = '8080';
  private static prefix: 					          string = 'api';

  static routeTo(route: string) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}
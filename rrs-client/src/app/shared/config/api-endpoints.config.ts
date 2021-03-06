import { environment } from "../../../environments/environment";

/**
 * Az api végpontokat tartalmazó osztály
 */
export class ApiEndpoints {
  /*Felhasználók:*/
  static USER_LOGIN: string = "user/login";
  static USER_GET_ALL: string = "user/getAll";
  static USER_GET_NAMES: string = "user/getNames";
  static USER_GET_NAME_BY_USERNAME: string = "user/getName";
  static USER_FIND_BY_ID: string = "user/findById";
  static USER_FIND_BY_USERNAME: string = "user/findByUsername";
  static USER_FIND_BY_NAME: string = "user/findByName";
  static USER_CREATE_USER: string = "user/createUser";
  static USER_DELETE_BY_USERNAME: string = "user/deleteByUsername";
  static USER_EXISTS_BY_ID: string = "user/existsById";
  static USER_EXISTS_BY_USERNAME: string = "user/existsByUsername";
  static USER_EXISTS_BY_EMAIL: string = "user/existsByEmail";
  static USER_UPDATE: string = "user/update";

  /*Tantárgyak:*/
  static SUBJECT_GET_ALL: string = "subject/getAll";
  static SUBJECT_GET_SUBJECT_NAMES: string = "subject/getSubjectNames";
  static SUBJECT_GET_SUBJECT_NAME: string = "subject/getSubjectName";
  static SUBJECT_FIND_BY_ID: string = "subject/findById";
  static SUBJECT_CREATE_SUBJECT: string = "subject/createSubject";
  static SUBJECT_DELETE_BY_CODE: string = "subject/deleteByCode";
  static SUBJECT_EXISTS_BY_ID: string = "subject/existsById";
  static SUBJECT_EXISTS_BY_CODE: string = "subject/existsByCode";
  static SUBJECT_UPDATE: string = "subject/update";

  /*Órára vonatkozó foglalások*/
  static CLASS_RESERVATION_GET_ACCEPTED: string =
    "classReservation/getAccepted";
  static CLASS_RESERVATION_GET_PENDING: string = "classReservation/getPending";
  static CLASS_RESERVATION_FIND_BY_ID: string = "classReservation/findById";
  static CLASS_RESERVATION_FIND_BY_USERNAME: string =
    "classReservation/findByUsername";
  static CLASS_RESERVATION_FIND_BY_STATUS: string =
    "classReservation/findByStatus";
  static CLASS_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM: string =
    "classReservation/findByBuildingAndClassroom";
  static CLASS_RESERVATION_FIND_BY_SUBJECT_CODE: string =
    "classReservation/findBySubjectCode";
  static CLASS_RESERVATION_FIND_BY_SEMESTER: string =
    "classReservation/findBySemester";
  static CLASS_RESERVATION_SET_STATUS: string = "classReservation/setStatus";
  static CLASS_RESERVATION_CREATE_RESERVATION: string =
    "classReservation/createReservation";
  static CLASS_RESERVATION_DELETE_BY_ID: string = "classReservation/deleteById";
  static CLASS_RESERVATION_DELETE_BY_USERNAME: string =
    "classReservation/deleteByUsername";
  static CLASS_RESERVATION_DELETE_BY_BUILDING_AND_CLASSROOM: string =
    "classReservation/deleteByBuildingAndClassroom";
  static CLASS_RESERVATION_DELETE_BY_SUBJECT_CODE: string =
    "classReservation/deleteBySubjectCode";
  static CLASS_RESERVATION_DELETE_BY_SEMESTER: string =
    "classReservation/deleteBySemester";
  static CLASS_RESERVATION_DELETE_BY_STATUS: string =
    "classReservation/deleteByStatus";
  static CLASS_RESERVATION_EXISTS_BY_ID: string = "classReservation/existsById";
  static CLASS_RESERVATION_UPDATE: string = "classReservation/update";
  static CLASS_RESERVATION_UPDATE_OWN_BY_ID: string = "classReservation/updateOwnById";

  /*Eseményre vonatkozó foglalások*/
  static EVENT_RESERVATION_GET_ACCEPTED: string =
    "eventReservation/getAccepted";
  static EVENT_RESERVATION_GET_PENDING: string = "eventReservation/getPending";
  static EVENT_RESERVATION_GET_NAMES: string = "eventReservation/getNames";
  static EVENT_RESERVATION_FIND_BY_ID: string = "eventReservation/findById";
  static EVENT_RESERVATION_FIND_BY_USERNAME: string =
    "eventReservation/findByUsername";
  static EVENT_RESERVATION_FIND_BY_STATUS: string =
    "eventReservation/findByStatus";
  static EVENT_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM: string =
    "eventReservation/findByBuildingAndClassroom";
  static EVENT_RESERVATION_FIND_BY_NAME: string = "eventReservation/findByName";
  static EVENT_RESERVATION_SET_STATUS: string = "eventReservation/setStatus";
  static EVENT_RESERVATION_CREATE_RESERVATION: string =
    "eventReservation/createReservation";
  static EVENT_RESERVATION_DELETE_BY_ID: string = "eventReservation/deleteById";
  static EVENT_RESERVATION_DELETE_BY_USERNAME: string =
    "eventReservation/deleteByUsername";
  static EVENT_RESERVATION_DELETE_BY_BUILDING_AND_CLASSROOM: string =
    "eventReservation/deleteByBuildingAndClassroom";
  static EVENT_RESERVATION_DELETE_BY_STATUS: string =
    "eventReservation/deleteByStatus";
  static EVENT_RESERVATION_DELETE_BY_NAME: string =
    "eventReservation/deleteByName";
  static EVENT_RESERVATION_EXISTS_BY_ID: string = "eventReservation/existsById";
  static EVENT_RESERVATION_EXISTS_BY_NAME: string =
    "eventReservation/existsByName";
  static EVENT_RESERVATION_UPDATE: string = "eventReservation/update";
  static EVENT_RESERVATION_UPDATE_OWN_BY_ID: string = "eventReservation/updateOwnById";

  /*Tantermek:*/
  static CLASSROOM_GET_ALL: string = "classroom/getAll";
  static CLASSROOM_GET_NAMES_BY_BUILDING: string =
    "classroom/getNamesByBuilding";
  static CLASSROOM_FIND_BY_ID: string = "classroom/findById";
  static CLASSROOM_FIND_BY_NAME: string = "classroom/findByName";
  static CLASSROOM_FIND_BY_BUILDING_NAME: string =
    "classroom/findByBuildingName";
  static CLASSROOM_FIND_BY_NAME_AND_BUILDING_NAME: string =
    "classroom/findByNameAndBuildingName";
  static CLASSROOM_FIND_BY_HAS_PC: string = "classroom/findByHasPC";
  static CLASSROOM_FIND_BY_HAS_PROJECTOR: string =
    "classroom/findByHasProjector";
  static CLASSROOM_FIND_BY_CHAIRS_LESS_THAN: string =
    "classroom/findByChairsLessThan";
  static CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN: string =
    "classroom/findByChairsGreaterThan";
  static CLASSROOM_FIND_BY_CHAIRS_BETWEEN: string =
    "classroom/findByChairsBetween";
  static CLASSROOM_CREATE_CLASSROOM: string = "classroom/createClassroom";
  static CLASSROOM_DELETE_BY_NAME_AND_BUILDING_NAME: string =
    "classroom/deleteByNameAndBuildingName";
  static CLASSROOM_EXISTS_BY_ID: string = "classroom/existsById";
  static CLASSROOM_EXISTS_BY_NAME_AND_BUILDING: string =
    "classroom/existsByNameAndBuilding";
  static CLASSROOM_UPDATE: string = "classroom/update";

  /*Épületek:*/
  static BUILDING_GET_ALL: string = "building/getAll";
  static BUILDING_GET_NAMES: string = "building/getNames";
  static BUILDING_FIND_BY_ID: string = "building/findById";
  static BUILDING_FIND_BY_NAME: string = "building/findByName";
  static BUILDING_CREATE_BUILDING: string = "building/createBuilding";
  static BUILDING_DELETE_BY_NAME: string = "building/deleteByName";
  static BUILDING_EXISTS_BY_ID: string = "building/existsById";
  static BUILDING_EXISTS_BY_NAME: string = "building/existsByName";
  static BUILDING_UPDATE: string = "building/update";

  /*Szemeszterek*/
  static SEMESTER_GET_ALL: string = "semester/getAll";
  static SEMESTER_GET_OPENED: string = "semester/getOpened";
  static SEMESTER_GET_OPENED_NAMES: string = "semester/getOpenedSemesterNames";
  static SEMESTER_FIND_BY_ID: string = "semester/findById";
  static SEMESTER_FIND_BY_NAME: string = "semester/findByName";
  static SEMESTER_CREATE_SEMESTER: string = "semester/createSemester";
  static SEMESTER_DELETE_BY_NAME: string = "semester/deleteByName";
  static SEMESTER_EXISTS_BY_ID: string = "semester/existsById";
  static SEMESTER_EXISTS_BY_NAME: string = "semester/existsByName";
  static SEMESTER_UPDATE: string = "semester/update";

  /*Kalendár*/
  static EVENT_GET_EVENTS: string = "event/getEvents";
  static EVENT_FIND_BY_USER_NAME: string = "event/findByUserName";
  static EVENT_FIND_BY_BUILDING_NAME: string = "event/findByBuildingName";
  static EVENT_FIND_BY_CLASSROOM_AND_BUILDING: string =
    "event/findByClassroomAndBuilding";
  static EVENT_FIND_BY_EVENT_NAME: string = "event/findByEventName";
  static EVENT_FIND_BY_SUBJECT_NAME: string = "event/findBySubjectName";
  static EVENT_FIND_BY_SEMESTER_NAME: string = "event/findBySemesterName";

  /*Üzenetek*/
  static MESSAGE_FIND_OWN_BY_ID: string = "message/findOwnById";
  static MESSAGE_FIND_BY_SENDER: string = "message/findBySender";
  static MESSAGE_FIND_BY_RECIPIENT: string = "message/findByRecipient";
  static MESSAGES_FIND_OWN_MESSAGES: string = "message/findOwnMessages";
  static MESSAGE_SEND_MESSAGE: string = "message/sendMessage";
  static MESSAGE_GENERATE_SYSTEM_MESSAGE: string =
    "message/generateSystemMessage";
  static MESSAGE_MARK_OWN_AS_READ: string = "message/markOwnAsRead";
  static MESSAGE_MARK_OWN_AS_UNREAD: string = "message/markOwnAsUnread";
  static MESSAGE_DELETE_OWN_BY_ID: string = "message/deleteOwnById";

  /**
   * Az adott route-hoz tartozó url-t előállító függvény
   * @param route A route
   */
  static getUrl(route: string): string {
    return environment.apiUrl + `${route}`;
  }
}

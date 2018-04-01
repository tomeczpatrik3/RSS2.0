package RoomReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A különböző foglalás típusok ősosztálya
 * (Tartalmazza azon adattagokat amelyeket, a foglalás típusától függően, minden foglalás használ)
 * @author Tomecz Patrik
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseReservationDTO {
    private String username;            /*A felhasználó aki foglal*/
    private String buildingName;        /*Az épület amelyre a foglalás vonatkozik*/
    private String roomName;            /*A terem amelyre a foglalás vonatkozik*/
    private String note;                /*A foglaláshoz tartozó megjegyzés*/
}

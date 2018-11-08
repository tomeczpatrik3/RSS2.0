package RoomReservationSystem.enums;

public enum ReservationType {
    CLASS("CLASS"),
    EVENT("EVENT");
    
    private final String type;
    
    ReservationType(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return this.type;
    }
}

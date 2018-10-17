package RoomReservationSystem.enums;

public enum Type {
    CLASS("CLASS"),
    EVENT("EVENT");
    
    private final String type;
    
    Type(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return this.type;
    }
}

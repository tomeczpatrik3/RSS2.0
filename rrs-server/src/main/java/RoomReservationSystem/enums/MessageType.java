package RoomReservationSystem.enums;

public enum MessageType {
    ACCEPT_MSG("ACCEPT_MSG"),
    DECLINE_MSG("DECLINE_MSG");
    
    private final String type;
    
    MessageType(final String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return this.type;
    }
}
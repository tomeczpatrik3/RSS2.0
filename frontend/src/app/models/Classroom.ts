export class Classroom {
    building: string;
    roomName: string;
    hasPC: boolean;
    hasProjector: boolean;
    chairs: number;

    public constructor (building: string, roomName: string, hasPC: boolean, hasProjector: boolean, chairs: number) {
        this.building = building;
        this.roomName = roomName;
        this.hasPC = hasPC;
        this.hasProjector = hasProjector;
        this.chairs = chairs;
    }
}
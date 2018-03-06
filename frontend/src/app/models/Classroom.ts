export class Classroom {
    building: string;
    name: string;
    hasPC: boolean;
    hasProjector: boolean;
    chairs: number;

    public constructor (building?: string, name?: string, hasPC?: boolean, hasProjector?: boolean, chairs?: number) {
        this.building = building;
        this.name = name;
        this.hasPC = hasPC;
        this.hasProjector = hasProjector;
        this.chairs = chairs;
    }
}
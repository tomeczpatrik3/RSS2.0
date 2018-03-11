export class Classroom {
    name: string;
    hasPC: boolean;
    hasProjector: boolean;
    chairs: number;
    building: string;

    public constructor (
        name?: string, 
        hasPC?: boolean, 
        hasProjector?: boolean, 
        chairs?: number,
        building?: string
    ) {
        this.name = name || "";
        this.hasPC = hasPC || false;
        this.hasProjector = hasProjector || false;
        this.chairs = chairs || 0;
        this.building = building || "";
    }
}
export class Classroom {
    id: number;
    building: string;
    name: string;
    hasPC: boolean;
    hasProjector: boolean;
    chairs: number;

    public constructor (id?:number, building?: string, name?: string, hasPC?: boolean, hasProjector?: boolean, chairs?: number) {
        this.id = id;
        this.building = building;
        this.name = name;
        this.hasPC = hasPC;
        this.hasProjector = hasProjector;
        this.chairs = chairs;
    }
}
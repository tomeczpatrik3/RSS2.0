export class BaseEntity {
    protected id: number;

    constructor(id?: number) {
        this.id = id || 666;
    }
}
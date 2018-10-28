/**
 * Az alapvető entitás
 */
export class BaseEntity {
    public id: number;

    constructor(id?: number) {
        this.id = id || 666;
    }
}
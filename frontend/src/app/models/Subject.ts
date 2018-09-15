export class Subject {
    name: string;
    code: string;
    
    public constructor (name?: string, code?: string) {
        this.name = name || "";
        this.code = code || "";
    }
}
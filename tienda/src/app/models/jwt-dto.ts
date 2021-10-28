export class JwtDTO {
    user(user: any): any {
      throw new Error('Method not implemented.');
    }
    token: string;

    constructor(token: string) {
        this.token = token;
    }
}

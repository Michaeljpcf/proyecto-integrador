import { Product } from "./product";

export class User {
    idUser?: number;
    name?: string;
    lastName?: string;
    userName?: string;
    email?: string;
    password?: string;
    picture?: string;
    address?: string;
    phone?: string;
    createAt?: Date;
    products?: Product[] = [];


    constructor() {
      this.idUser = 0;
      this.name = '';
      this.userName = '';
      this.email = '';
      this.password = '';
    }
}

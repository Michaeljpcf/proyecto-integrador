import { Product } from "./product";

export class User {
    idUser: number;
    name: string;
    userName: string;
    email: string;
    password: string;
    picture: string;
    country: string;
    city: string;
    address: string;
    phone: string;
    createAt: Date;
    product: Product[] = [];


    constructor() {
      this.idUser = 0;
      this.name = '';
      this.userName = '';
      this.email = '';
      this.password = '';
  }
}

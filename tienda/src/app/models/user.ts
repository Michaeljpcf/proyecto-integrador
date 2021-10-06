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
    createAt: string;
    product: Product[] = [];
}

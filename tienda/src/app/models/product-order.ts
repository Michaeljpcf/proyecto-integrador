import { Product } from "./product";

export class ProductOrder {
  id:number;
  nameUser:string;
  lastNameUser:string;
  phoneContact:string;
  emailUser:string;
  address1:string;
  address2:string;
  deliveryDate: Date;

  creditName:string;
  creditCarNumber:string;
  expirationCardDate:Date;
  cvv:string;

  product: Product;
}

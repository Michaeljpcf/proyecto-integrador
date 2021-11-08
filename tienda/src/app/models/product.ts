import { Category } from "./category";
import { SubCategory } from "./sub-category";
import { User } from "./user";
export class Product {
    id?: number;
    name?: string;
    url?: string;
    price?: number;
    description?: string;
    conditionProduct?: string;
    stock?: number;
    lstProductImages?: string[];
    image?: string;
    gallery?: string;
    createAt?: string;
    subCategory?: SubCategory;
    category?: Category
    user?: User;
    deliveryRangeDate?:Date;
}

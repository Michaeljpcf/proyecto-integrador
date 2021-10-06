import { SubCategory } from "./sub-category";
import { User } from "./user";
export class Product {
    id: number;
    name: string;
    url: string;
    price: string;
    description: string;
    image: string;
    gallery: string;    
    createAt: string;
    subCategory: SubCategory;
    user: User;    
}

import { Category } from "./category";
import { Product } from "./product";

export class SubCategory {
    id: number;
    name: string;
    url: string;
    category: Category;
    product: Product[] = [];
}

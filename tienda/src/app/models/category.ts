import { SubCategory } from "./sub-category";

export class Category {
    id: number;
    name: string;
    url: string;
    subcategories: SubCategory[] = []
}

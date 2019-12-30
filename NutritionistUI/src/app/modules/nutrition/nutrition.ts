export interface SearchResponse {
    list: SearchResponseList
  };
 export interface SearchResponseList {
    q: string;
    sr: string;
    ds: string;
    start: number;
    end: number;
    total: number;
    group: string;
    sort: string;
    item: SearchResponseItem[];
  };
export interface SearchResponseItem {
    offset: number;
    group: string;
    name: string;
    ndbno: string;
    ds: string;
  };
export interface SearchResult {
    start: number;
    end: number;
    total: number;
    items: FoodItem[];
  };
  
export interface FoodItem {
    foodGroup: string;
    name: string;
    ndbno: string;
  }
export interface ReportResponse {
    foods: ReportResponseFoodContainer[];
    count: number;
    notfound: number;
    api: number
  }
  
export interface ReportResponseFoodContainer {
    food: ReportResponseFood;
  }
  
export interface ReportResponseFood {
    sr: string;
    type: string;
    desc: ReportResponseFoodDesc;
    nutrients: ReportResponseFoodNutrient[];
    footnotes: {idv: string, desc: string}[];
  }
  
export interface ReportResponseFoodDesc {
    ndbno: string;
    name: string;
    ds: string;
    manu: string;
    ru: string;
  }
  
export interface ReportResponseFoodNutrient {
    nutrient_id: string;
    name: string;
    derivation: string;
    group: string;
    unit: string;
    value: string;
    measures: ReportResponseFoodNutrientMeasure[]
  }
  
export interface ReportResponseFoodNutrientMeasure {
    label: string;
    eqv: number;
    eunit: string;
    qty: number;
    value: string;
  }

export  interface PageDetail {
    pageNumber: number;
    topItemOffset: number;
  }
  
export interface FoodReport {
    ndbno: string;
    name: string;
    foodNutrients: FoodNutrient[];
  }
  
export interface FoodNutrient {
    nutrientId: string;
    name: string;
    group: string;
    unit: string;
    value: string;
  }
export interface PageDetail {
    pageNumber: number;
    topItemOffset: number;
  }
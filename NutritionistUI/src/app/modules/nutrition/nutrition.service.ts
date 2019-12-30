import { Injectable } from '@angular/core';
//import { HttpClient } from '@angular/common/http';
import { map, retry } from 'rxjs/operators';
import {SearchResponse, FoodItem, FoodReport, ReportResponse} from './nutrition';
import {SearchResponseItem} from './nutrition';
import{SearchResult} from './nutrition';
import { Observable } from 'rxjs/Observable';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class NutritionService {

  
  apiKey: string;
  page: string;
  foodEndpoint: string;
  format: string;
  foodSearchUrl: string;
  foodReportUrl: string;
  paramApiKey: string;
  paramFormat: string;
  paramQuery: string;
  paramMax: string;
  paramOffset: string;
  paramNDBNO: string;
  paramType: string;
  httpClient: any;


  constructor(private http: HttpClient) { 
    this.page = '1';
    this.foodEndpoint='http://localhost:8082/api/v1/food';
    this.apiKey = '0eSJ5Bwjti3Ck1Yvj3Nk9xkwuQtuaaK7xcw9zYv1';
    this.format = 'json';
    this.foodSearchUrl= 'https://api.nal.usda.gov/ndb/search';
    this.foodReportUrl='https://api.nal.usda.gov/ndb/V2/reports';
    this.paramApiKey = 'api_key';
    this.paramFormat= 'format';
    this.paramQuery= 'q';
    this.paramMax ='max';
    this.paramOffset= 'offset';
    this.paramNDBNO= 'ndbno';
    this.paramType= 'type';

   }
  getFoodList = (searchQuery: string, offset: number, max: number): Observable<SearchResult> => {

    let params = new HttpParams();
    params = params.append(this.paramApiKey, this.apiKey);
    params = params.append(this.paramFormat, this.format);
    params = params.append(this.paramQuery, searchQuery);
    params = params.append(this.paramMax, max.toString());
    params = params.append(this.paramOffset, offset.toString());

    return this.http.get(this.foodSearchUrl, {
      params: params
    }).pipe(map((res: SearchResponse) => {

      return {
        start: res.list.start,
        end: res.list.end,
        total: res.list.total,
        items: itemsMap(res.list.item)
      };
  function itemsMap(inItems: SearchResponseItem[]) {

    return inItems.map(inItem => {

      return {
        foodGroup: inItem.group,
        name: inItem.name,
        ndbno: inItem.ndbno
      }
    });
  }
}));
}
  addFoodToFavourites(item) {
    return this.http.post(this.foodEndpoint,item);
  }

  getFoodFromFavourites(): Observable<Array<FoodItem>> {
    return this.http.get<Array<FoodItem>>(this.foodEndpoint);
  }

  deleteFoodFromFavourites(item: FoodItem){
    const deleteEndpoint = `${this.foodEndpoint}/${item.ndbno}`;
    console.log(item.ndbno);
    return this.http.delete(deleteEndpoint,{responseType: 'text'});
  }
  getFoodDetails = (ndbnoList: string): Observable<FoodReport[]> => {

    let params = new HttpParams();
    params = params.append(this.paramApiKey, this.apiKey);
    params = params.append(this.paramFormat, this.format);
    params = params.append(this.paramType, 'b');
    params =  params.append(this.paramNDBNO,ndbnoList);

    return this.http.get(this.foodReportUrl, {
      params: params
    }).pipe(map((inItem: ReportResponse) => {

      return inItem.foods.map(value => {

        let foodReport: FoodReport = {
          ndbno: value.food.desc.ndbno,
          name: value.food.desc.name,
          foodNutrients: null // added below
        };

        foodReport.foodNutrients = value.food.nutrients.map(nutrient => {

          return {
            nutrientId: nutrient.nutrient_id,
            name: nutrient.name,
            group: nutrient.group,
            unit: nutrient.unit,
            value: nutrient.value
          }
        });

        return foodReport;
      });
    }));
  }
  
}

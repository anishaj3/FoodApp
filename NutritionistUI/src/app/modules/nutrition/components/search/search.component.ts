import { Component, OnInit,EventEmitter ,Output} from '@angular/core';
import {FoodItem} from '../../nutrition';
import { NutritionService } from '../../nutrition.service';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';


@Component({
  selector: 'food-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  
  
  header: string;
  searchText = '';
  items: FoodItem[];
  // Subject to be passed to paginated list so the list knows when
  //  the total number of items has changed and what the new value is
  totalItems$ = new BehaviorSubject<number>(0);
  pageSize = 15;
  constructor(private nutritionService: NutritionService,private route: ActivatedRoute) {
    this.route.data.subscribe(data => {   
      this.header = data.header;
    });
   }

  ngOnInit() {
  }
  onSearch(searchText: string) {
    this.searchText = searchText;
    this.performFoodSearch();
  }
  private performFoodSearch() {
    this.nutritionService.getFoodList(this.searchText, 0, this.pageSize).subscribe(result => {
      this.items = result.items;
      console.log(this.items);
      this.totalItems$.next(result.total);
    });
  }
}

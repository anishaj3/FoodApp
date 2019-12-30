import { Component, OnInit } from '@angular/core';
import { NutritionService} from './../../nutrition.service';
import { ActivatedRoute } from '@angular/router';
import { FoodItem , FoodReport} from '../../nutrition';

@Component({
  selector: 'food-favourite',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  
  items: Array<FoodItem>;
  header: string;
  watchlist = true;
  foodReport: FoodReport;

  constructor(private nutritionService: NutritionService, private route: ActivatedRoute) { 
    this.items = [];
    this.route.data.subscribe(data => {   
     this.header = data.header;
    });
  }

  ngOnInit() {
    this.nutritionService.getFoodFromFavourites()
     .subscribe((items) => {
       this.items.push(...items);
     });
    }
  }
  
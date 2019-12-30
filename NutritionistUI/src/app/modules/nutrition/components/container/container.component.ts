import { Component, OnInit, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NutritionService } from './../../nutrition.service';
import{ FoodItem, FoodReport, FoodNutrient } from '../../nutrition';
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'food-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})

export class ContainerComponent implements OnInit {


  @Input()items: Array<FoodItem>;
  @Input()food: Array<FoodNutrient>;


  @Input()
  header: string;

  @Input()
  watchlist: boolean;

  foodReport: FoodReport;

  constructor(private nutritionService: NutritionService, private snackBar: MatSnackBar,private route: ActivatedRoute) {
  }

  ngOnInit() {
  }

  addToFavourites(items) {
    this.nutritionService.addFoodToFavourites(items)
    .subscribe((data) => {
      this.snackBar.open("Food Added to favourites","",{
        duration:2000
      })
    });
  }

  itemButtonClicked(items){
     this.nutritionService.getFoodDetails(items
      ).subscribe(foodReports => {
        this.foodReport = foodReports[0];
      });
  }

  deleteFromFavourites(item) {
    let foodIndex = this.items.findIndex((mv) => {
      return mv.ndbno === item.ndbno;
    });

    this.items.splice(foodIndex,1);

    this.nutritionService.deleteFoodFromFavourites(item)
    .subscribe((data) => {
      this.snackBar.open("Food deleted from favourites","",{
        duration:2000
      })
    });
  }

}


import { Component, OnInit } from '@angular/core';
import {NutritionService} from "../../nutrition.service";
import {ActivatedRoute} from "@angular/router";
import { MatDialog } from '@angular/material';
import {FoodNutrient} from "../../nutrition";

@Component({
  selector: 'food-search',
  templateUrl: 'details-page.component.html',
  styleUrls: [
    'details-page.component.css'
  ]
})
export class DetailsPageComponent implements OnInit {

  foodNutrients: FoodNutrient[];

  constructor(private nutritionService: NutritionService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {

      this.nutritionService.getFoodDetails(
        params[this.nutritionService.paramNDBNO]
      ).subscribe(foodReports => {

        this.foodNutrients = foodReports[0].foodNutrients; // only one on details page
      });
    });
  }
}



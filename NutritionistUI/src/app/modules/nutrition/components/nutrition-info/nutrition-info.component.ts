import {Component, Input, OnInit} from '@angular/core';
import { FoodNutrient } from '../../nutrition';

@Component({
  selector: 'nutrient-thumbnail',
  templateUrl: 'nutrition-info.component.html',
  styleUrls: [
    'nutrition-info.component.css'
  ]
})
export class NutritionInfoComponent implements OnInit {

  @Input() foodNutrients: FoodNutrient[];

  constructor() { }

  ngOnInit() {

    console.log('test', this.foodNutrients);
  }

}

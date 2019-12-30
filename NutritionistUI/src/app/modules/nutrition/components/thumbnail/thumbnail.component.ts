import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FoodItem, ReportResponseFoodDesc, ReportResponseFoodNutrient, FoodNutrient } from '../../nutrition';

@Component({
  selector: 'food-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})

export class ThumbnailComponent implements OnInit {

  @Input()
  item: FoodItem;

  @Input()
  food: FoodNutrient;

  @Input()
  watchlist: boolean;

  @Output()
  addFood = new EventEmitter;

  @Output()
  deleteFood = new EventEmitter;

  @Output()
  showDetail= new EventEmitter;

  @Input()buttonLabel: string;

  @Output()
  showDetails = new EventEmitter;

  comments: string;

  constructor(public dialog: MatDialog) { }

  ngOnInit() { }

  addToFavourites() {
    console.log(this.item);
    this.addFood.emit(this.item);
  }

  deleteFromFavourites() {
    this.deleteFood.emit(this.item);
  }

  itemButtonClicked(item: FoodItem) {
    console.log(this.item.ndbno);
    this.showDetails.emit(this.item.ndbno);
  }

}

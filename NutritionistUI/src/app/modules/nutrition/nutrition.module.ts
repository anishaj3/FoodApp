import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NutritionService } from './nutrition.service';
import { ContainerComponent } from './components/container/container.component';
import { NutritionRouterModule } from './nutrition.router.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './interceptor.service';
import { NutritionInfoComponent } from './components/nutrition-info/nutrition-info.component';
import { DetailsPageComponent } from './components/details-page/details-page.component';
import { MatSelectModule, MatFormFieldModule } from '@angular/material';
import {FavouritesComponent} from './components/favourites/favourites.component';
//import { DetailsPageComponent} from './components/details-page/details-page.component';



@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    NutritionRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    CommonModule
  ],
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    FavouritesComponent,
    SearchComponent,
    NutritionInfoComponent,
    DetailsPageComponent,
  ],
  exports: [
    ThumbnailComponent,
    NutritionRouterModule,   
    SearchComponent,
    DetailsPageComponent,
    NutritionInfoComponent,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [
    NutritionService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
})
export class NutritionModule { }

import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuard } from '../../auth.guard.service';
//import { DetailsPageComponent } from './components/details-page/details-page.component';
import { DetailsPageComponent } from './components/details-page/details-page.component';

const foodRoutes: Routes = [
    {
        path: 'food',
        children: [
            {
                path: 'favourite',
                component: FavouritesComponent,
                canActivate: [AuthGuard],
                data: {
                    foodType: 'watchlist',
                    header: 'Favourite'
                }
            },
            {
                path: 'search',
                component: SearchComponent,
                canActivate: [AuthGuard],
                data: {
                    foodType: 'search',
                    header: 'Search Food'
                }
            },
             {
                path: 'search/food-details/:ndbno',
               component: DetailsPageComponent,
                canActivate: [AuthGuard],
                data: {
                    foodType: 'search',
                    header: 'Search Food'
                }
            }
        ] 
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(foodRoutes),      
    ],
    exports: [
        RouterModule
    ]
})

export class NutritionRouterModule { }
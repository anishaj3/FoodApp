import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { NutritionModule } from './modules/nutrition/nutrition.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { AuthGuard } from './auth.guard.service';
import { MatFormFieldModule, MatSelectModule } from '@angular/material';
import { CommonModule } from '@angular/common';

const appRoutes: Routes = [ 
  {
      path: '',
      redirectTo: 'user',
      pathMatch: 'full'
  } 
];


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NutritionModule,    
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    FormsModule,
    MatInputModule,
    AuthenticationModule,
    MatDialogModule,
    MatFormFieldModule,
    CommonModule 
  ],
  providers: [
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

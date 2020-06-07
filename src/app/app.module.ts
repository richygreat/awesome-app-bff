import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { BuyerRegistrationComponent } from './buyer/registration/buyer-registration.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatListModule } from '@angular/material/list';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatRadioModule } from '@angular/material/radio';

import { MAT_DATE_LOCALE } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    BuyerRegistrationComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      { path: 'buyer-registration', component: BuyerRegistrationComponent }
    ]),
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatSnackBarModule,
    MatListModule,
    MatChipsModule,
    MatIconModule,
    MatDividerModule,
    MatTableModule,
    MatTooltipModule,
    MatSelectModule,
    MatDatepickerModule,
    MatMomentDateModule,
    MatRadioModule
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'en-US' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatNativeDateModule,MatSnackBarModule,MatIconModule,MatDialogModule, MatButtonModule, MatTableModule,
  MatPaginatorModule , MatSortModule, MatCheckboxModule, MatCard, MatCardModule, MatFormField,
  MatFormFieldModule, MatProgressSpinnerModule, MatInputModule, MatSidenavModule, MAT_DATE_LOCALE } from  '@angular/material';
import {MatDatepickerModule} from  '@angular/material/datepicker';
import {MatRadioModule} from  '@angular/material/radio';
import {MatSelectModule} from  '@angular/material/select';
import {MatSliderModule} from  '@angular/material/slider';
import {MatDividerModule} from  '@angular/material/divider';
import {MatStepperModule} from '@angular/material/stepper';
import { AppComponent } from './app.component';
import { StronaGlownaComponent } from './strona-glowna/strona-glowna.component';
import { ONasComponent } from './o-nas/o-nas.component';
import { SpecjalisciComponent } from './specjalisci/specjalisci.component';
import { PodstawaPrawnaComponent } from './podstawa-prawna/podstawa-prawna.component';
import { UslugiComponent } from './uslugi/uslugi.component';
import { KontaktComponent } from './kontakt/kontakt.component';
import { routes } from './app.routes';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { from } from 'rxjs';
import { PortalHomeComponent } from './portal-home/portal-home.component';
import { PortalRezerwacjaComponent } from './portal-rezerwacja/portal-rezerwacja.component';
import { PortalUstawieniaComponent } from './portal-ustawienia/portal-ustawienia.component';
import {CookieService} from 'ngx-cookie-service';
import { PortalArchiwumComponent } from './portal-archiwum/portal-archiwum.component';
import {MatChipsModule} from '@angular/material/chips';
import {MatToolbarModule} from '@angular/material/toolbar';
import { PortalGlownaComponent } from './portal-glowna/portal-glowna.component';
import { ModalComponent } from './portal-rezerwacja/modal/modal.component';
import { PrivacyModalComponent } from './strona-glowna/privacy-modal/privacy-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    StronaGlownaComponent,
    ONasComponent,
    SpecjalisciComponent,
    PodstawaPrawnaComponent,
    UslugiComponent,
    KontaktComponent,
    LayoutComponent,
    LoginComponent,
    RegisterComponent,
    PortalHomeComponent,
    PortalRezerwacjaComponent,
    PortalUstawieniaComponent,
    PortalArchiwumComponent,
    PortalGlownaComponent,
    ModalComponent,
    PrivacyModalComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes, {useHash: true}),
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatIconModule,
    MatDialogModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatStepperModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatDatepickerModule,
    MatRadioModule,
    MatSelectModule,
    MatSliderModule,
    MatDividerModule  ,
    MatSidenavModule,
    MatChipsModule,
  ],
  exports: [MatTableModule,MatDividerModule,
    MatSliderModule,MatSelectModule,MatRadioModule,MatNativeDateModule,MatDatepickerModule,MatSnackBarModule,MatIconModule,
    MatDialogModule,MatProgressSpinnerModule,MatButtonModule,MatSortModule, MatCheckboxModule, MatToolbarModule,
    MatCardModule, MatFormFieldModule, MatProgressSpinnerModule, MatInputModule, MatChipsModule,
    MatPaginatorModule],
  providers: [ CookieService,
    {provide: MAT_DATE_LOCALE, useValue: 'pl'}
  ],
  bootstrap: [AppComponent],
  entryComponents: [ModalComponent, PrivacyModalComponent]
})
export class AppModule { }

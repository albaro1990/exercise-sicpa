import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { Error400Component } from './error/error-400/error-400.component';
import { Error404Component } from './error/error-404/error-404.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { PagesModule } from './pages/pages.module';

@NgModule({
  declarations: [AppComponent, Error404Component, Error400Component],
  imports: [BrowserModule, AppRoutingModule, PagesModule, AuthModule],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

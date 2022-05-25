import {Injectable} from '@angular/core';

import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import {Observable} from 'rxjs';
import Swal from 'sweetalert2';
import {AuthService} from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    const role = next.data['role'] as string[];
    console.log(role);
    for (const r of role) {
      if (this.authService.hasRole(r)) {
        return true;
      }
    }
    Swal.fire(
      'Acceso denegado',
      `Hola ${this.authService.getUsuario.username} no tienes acceso a este recurso`,
      'warning'
    );
    this.router.navigate(['/']);
    return false;
  }
}

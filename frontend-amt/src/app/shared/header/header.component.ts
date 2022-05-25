import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  public logout(): void {
    const usuario = this.authService.getUsuario;
    this.authService.logout();
    Swal.fire(
      'Logout',
      `Adiós ${usuario.username} has cerrado sesión con éxito`,
      'success'
    );
    this.router.navigate(['/login']);
  }
}

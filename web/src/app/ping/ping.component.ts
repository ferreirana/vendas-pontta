import { Component, OnInit, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

/**
 * Componente TEMPORÁRIO: testa a conexão com a API.
 * Remover na hora do desafio, quando as telas reais existirem.
 */
@Component({
  selector: 'app-ping',
  standalone: true,
  template: `<p>API: {{ status() }}</p>`
})
export class PingComponent implements OnInit {
  private http = inject(HttpClient);
  status = signal('verificando...');

  ngOnInit() {
    this.http.get<{ status: string }>(`${environment.apiUrl}/ping`).subscribe({
      next: r => this.status.set(r.status),
      error: () => this.status.set('sem conexão')
    });
  }
}
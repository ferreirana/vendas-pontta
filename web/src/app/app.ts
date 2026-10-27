import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PingComponent } from './ping/ping.component';

@Component({
  imports: [RouterOutlet, PingComponent],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('web');
}
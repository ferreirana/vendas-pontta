import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CustomerService } from '../core/services/customer.service';
import { Customer } from '../core/models/customer';

@Component({
  imports: [RouterLink],
  selector: 'app-customer-list',
  styleUrl: './customer.css',
  templateUrl: './customer-list.component.html',
})
export class CustomerListComponent implements OnInit {
    private service = inject(CustomerService);
    customers = signal<Customer[]>([]);
    error = signal('');
    private search = '';

    ngOnInit(): {
      this.load();
    }

    load() {
      this.service.list(this.search).subscribe(data => this.customer.set(data));
    }

    onSearch (event: Event){
      this.search = (event?.target as HTMLInputElement).value;
      this.load();
    }

    remove (c: Customer) {
      if (!confirm('Excluir ${c.name}?'))
        return;  
      this.error.set('');
      
      this.service.delete(c.id!).subscribe({
        next: () => this.load(),
        error: e => this.error.set(e.error?.message ?? 'Erro ao excluir') 
      });
    }
}


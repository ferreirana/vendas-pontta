import { HttpClient, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";
import { Customer } from "../../customer/customer-list.component";

@Injectable({ providedIn: 'root'})
export class CustomerService{
    private http = inject(HttpClient);
    private url = `${environment.apiUrl}/customers`;

    list(name?: string) {
        const params = name ? new HttpParams().set('name', name) : undefined;
        return this.http.get<Customer[]> (this.url, {params});
    }

    get(id: number) {
        return this.http.get<Customer> (`{this.url}/${id}`);
    }

    create(c: Customer){
        return this.http.post<Customer> (this.url, c);
    }

    update(id: number, c: Customer){
        return this.http.put<Customer> (`{this.url}/${id}`, c);
    }

    delete(id: number) {
        return this.http.delete<void> (`{this.url}/${id}`);
    }
}
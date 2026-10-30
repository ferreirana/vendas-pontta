import { Component, inject, OnInit, signal } from "@angular/core";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { CustomerService } from "../core/services/customer.service";
import { ActivatedRoute, Router } from "@angular/router";
import { email } from "@angular/forms/signals";

@Component({
    selector: 'app-customer-form',
    imports: [ReactiveFormsModule],
    templateUrl: './customer-form.component.html'
})

export class CustomerFormComponent implements OnInit {
    private fb = inject(FormBuilder);
    private service = inject(CustomerService);
    private route = inject(ActivatedRoute);
    private router = inject(Router);

    id?: number;
    serverError = signal('');

    form = this.fb.nonNullable.group([
        name, ['', [Validators.required, Validators.maxLength(120)]],
        email, ['', [Validators.required, Validators.email]]
    ])

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id){
            this.id = +id;
            this.service.get(this.id).subscribe(c => this.form.patchValue(c));
        }
    }

    save() {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }
        const value = this.form.getRawValue();
        const request = this.id ? this.service.update(this.id, value) : this.service.create(value);
        request.subscribe({
            next: () => this.router.navigate(['/customers']), 
            error: e 
        })
    }
}

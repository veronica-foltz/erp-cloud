import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ProductService } from './product.service';
import { Product } from './product.model';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, CurrencyPipe],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  saving = false;

  // create form
  form!: FormGroup;

  // edit state
  editingId: number | null = null;
  editForm!: FormGroup;

  constructor(private fb: FormBuilder, private svc: ProductService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      sku: ['', Validators.required],
      name: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      active: [true],
      stockQty: [0, [Validators.required, Validators.min(0)]],
    });
    this.reload();
  }

  reload(): void {
    this.loading = true;
    this.svc.list().subscribe({
      next: rows => { this.products = rows; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  // CREATE
  add(): void {
    if (this.form.invalid) return;
    this.saving = true;
    const v = this.form.value;
    const p: Product = {
      sku: v.sku!, name: v.name!,
      price: Number(v.price), active: !!v.active,
      stockQty: Number(v.stockQty)
    };
    this.svc.create(p).subscribe({
      next: () => { this.form.reset({ active: true, price: 0, stockQty: 0 }); this.saving = false; this.reload(); },
      error: () => { this.saving = false; }
    });
  }

  // EDIT
  startEdit(row: Product): void {
    this.editingId = row.id!;
    this.editForm = this.fb.group({
      sku: [row.sku, Validators.required],
      name: [row.name, Validators.required],
      price: [row.price, [Validators.required, Validators.min(0)]],
      active: [row.active],
      stockQty: [row.stockQty, [Validators.required, Validators.min(0)]],
    });
  }

  cancelEdit(): void {
    this.editingId = null;
  }

  saveEdit(id: number): void {
    if (!this.editForm || this.editForm.invalid) return;
    const v = this.editForm.value;
    const payload: Product = {
      id,
      sku: v.sku!, name: v.name!,
      price: Number(v.price), active: !!v.active,
      stockQty: Number(v.stockQty)
    };
    this.svc.update(id, payload).subscribe(() => { this.editingId = null; this.reload(); });
  }

  // DELETE
  remove(id?: number): void {
    if (id == null) return;
    this.svc.remove(id).subscribe(() => this.reload());
  }

  trackById = (_: number, p: Product) => p.id ?? p.sku;
}
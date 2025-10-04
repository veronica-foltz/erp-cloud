import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product.model';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private baseUrl = '/api/products';
  constructor(private http: HttpClient) {}

  list(): Observable<Product[]> { return this.http.get<Product[]>(this.baseUrl); }
  create(p: Product): Observable<Product> { return this.http.post<Product>(this.baseUrl, p); }
  update(id: number, p: Product): Observable<Product> { return this.http.put<Product>(`${this.baseUrl}/${id}`, p); }
  remove(id: number): Observable<void> { return this.http.delete<void>(`${this.baseUrl}/${id}`); }
}
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html'
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
  this.productService.getAll().subscribe({
    next: (data) => {
      console.log("DATA:", data);
      this.products = data;
    },
    error: (err) => {
      console.error("ERROR:", err);
    }
  });
}

  deleteProduct(id: number) {
    this.productService.delete(id).subscribe(() => {
      this.loadProducts();
    });
  }
}
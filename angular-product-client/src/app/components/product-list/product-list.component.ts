import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from 'src/app/common/product';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Observable<Product[]>;

  constructor(private productService: ProductServiceService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.products = this.productService.getProductList();
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  productDetails(id: number){
    this.router.navigate(["details", id]);
  }

  updateProduct(id: number){
    this.router.navigate(["update", id]);
  }

}

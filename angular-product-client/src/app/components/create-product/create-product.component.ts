import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/common/product';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  product: Product = new Product();
  submitted = false;

  constructor(private employeeService: ProductServiceService,
    private router: Router) { }

  ngOnInit() {
  }

  newProduct(): void {
    this.submitted = false;
    this.product = new Product();
  }

  save() {
    this.employeeService
    .createProduct(this.product).subscribe(data => {
      console.log(data)
      this.product = new Product();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(["/products"]);
  }

}

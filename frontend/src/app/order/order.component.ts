import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ShopComponent } from '../model/component.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  apiHostUrl = 'http://localhost:8080/shop';

  @Input()
  orders: ShopComponent[] = [];

  @Output()
  orderPlaced: EventEmitter<void> = new EventEmitter();

  showLoader = false;

  showError = false;

  showSuccess = false;

  errors: string[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    console.log(this.orders);
  }

  public removeOrderFromCart(id: number) {
    let index = this.orders.findIndex(o => o.id === id);
    if (index > -1) {
      this.orders.splice(index, 1);
    }
  }

  public placeOrder(): void {
    this.showLoader = true;
    console.log('here');
    this.httpClient
      .post(
        this.apiHostUrl + '/order',
        this.orders
      )
      .subscribe(
        (response) => {
          this.showLoader = false;
          this.showSuccess = true;
          setTimeout(() => {
            this.orderPlaced.emit();
          }, 2000);
        },
        (error) => {
          this.errors = error.error;
          this.showError = true
          this.showLoader = false;
        }
      );
  }
}

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
    console.log('here');
    this.httpClient
      .post(
        this.apiHostUrl + '/order',
        this.orders
      )
      .subscribe(
        (response) => {
          this.orderPlaced.emit();
        },
        (error) => {
          // Todo

        }
      );
  }
}

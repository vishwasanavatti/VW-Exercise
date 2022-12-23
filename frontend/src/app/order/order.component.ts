import { Component, Input, OnInit } from '@angular/core';
import { ShopComponent } from '../model/component.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input()
  orders: ShopComponent[] = [];

  constructor() { }

  ngOnInit(): void {
    console.log(this.orders);
  }

  public removeOrderFromCart(id: number) {
    let index = this.orders.findIndex(o => o.id === id);
    if (index > -1) {
      this.orders.splice(index, 1);
    }
  }
}

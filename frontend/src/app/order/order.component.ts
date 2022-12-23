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

}

/**
 * @description
 * This component renders the orders in the cart. Once user clicks on `Add to Cart` 
 * then the added component information is rendered in this component.
 */
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

  // boolean value to show loader when order is placed
  showLoader = false;

  // boolean value to show error when order is placed
  showError = false;

  // boolean value to show success when order is placed
  showSuccess = false;

  // contains list of errors which received when order is placed
  errors: string[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {

  }

  /**
   * This method is used to remove the order from cart
   * @param id 
   */
  public removeOrderFromCart(id: number) {
    let index = this.orders.findIndex(o => o.id === id);
    if (index > -1) {
      this.orders.splice(index, 1);
    }
  }

  /**
   * This method is used place the order .The api 'http://localhost:8080/shop/order' will be called with 
   * payload containg the orders. 
   * The success message is shown if order placed successfully else if there are errors during order, a list of errors is captured from the 
   * response and are displayed.
   * @param id 
   */
  public placeOrder(): void {
    this.showLoader = true;

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
          console.log("log", error);
          this.errors = error.error as string[];
          this.showError = true
          this.showLoader = false;
        }
      );
  }
}

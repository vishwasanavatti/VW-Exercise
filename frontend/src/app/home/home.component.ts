/**
 * @description
 * This component renders the home page information and contains actions 
 * to display the components and orders.
 */
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ShopComponent } from '../model/component.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  apiHostUrl = 'http://localhost:8080';

  // boolean value to display the components or not
  showComponent = false;

  // boolean value to display the orders or not
  showOrder = false;

  // contains list of components information
  components: ShopComponent[] = [];

  // contains list of components that are in the cart for ordering
  orderComponents: ShopComponent[] = [];

  // boolean value to display the loader while waiting for response from backend
  showLoader = false;

  constructor(private httpClient: HttpClient) { }

  /**
   * form froup to validate the input field for model
   */
  public form = new FormGroup({
    model: new FormControl('', Validators.compose([
      Validators.minLength(1),
      Validators.pattern("^[0-9]*$"),
      Validators.required
    ]),
    )
  });

  /**
   * returns model from form with validations
   */
  public get model() { return this.form.get('model'); }

  /**
   * returns boolean to display compoents or not
   */
  public get showComponents(): boolean {
    return this.showComponent;
  }

  /**
   * returns boolean to display orders or not
   */
  public get showOrders(): boolean {
    return this.orderComponents.length > 0;
  }

  /**
   * when "search" button is clicked. The backend api 'http://localhost:8080/shop/stock/model/{modelId}'
   * is called.
   */
  public onClick(): void {
    this.showLoader = true;
    const modelId = +this.model.value;
    this.httpClient
      .get(
        this.apiHostUrl + '/shop/stock/model/' + modelId
      )
      .subscribe(
        (response) => {
          this.showComponent = true;
          this.components = response as ShopComponent[];
          this.model.setValue(''); // reset value after search
          this.showLoader = false;
          this.form.get('model').setErrors(null); // do not show errors after search is success
        },
        (error) => {
          // Todo
          this.showLoader = false;
          if (error.status !== 404) {
            // Show the message that no component is available
            this.showComponent = false;
          }
          this.components = [];
        }
      );
  }

  /**
   * This method is used to enable the search button based on the input value 
   */
  public get isDisabled(): boolean {
    return this.form.get('model').invalid;
  }


  /**
   * This method updates the orders list that are added to the cart.
   * @param order 
   */
  public updateOrders(order: ShopComponent) {
    let comp = this.orderComponents.find(c => c.id === order.id);
    if (comp) {
      comp.quantity += order.quantity;
    }
    else {
      this.orderComponents.push(order);
    }

  }

  /**
   * This method reset order list after the order is placed successfully and also hides the components shown
   */
  public updatePlacedOrders(): void {
    this.orderComponents = [];
    this.showComponent = false;
  }

}

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

  showComponent = false;

  showOrder = false;

  components: ShopComponent[] = [];

  orderComponents: ShopComponent[] = [];

  showLoader = false;

  constructor(private httpClient: HttpClient) { }

  public form = new FormGroup({
    model: new FormControl('', Validators.compose([
      Validators.minLength(1),
      Validators.pattern("^[0-9]*$"),
      Validators.required
    ]),
    )
  });

  public get model() { return this.form.get('model'); }

  public get showComponents(): boolean {
    return this.showComponent;
  }

  public get showOrders(): boolean {
    return this.orderComponents.length > 0;
  }

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
          this.model.setValue('');
          this.showLoader = false;
          this.form.get('model').setErrors(null);
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

  public get isDisabled(): boolean {
    return this.form.get('model').invalid;
  }

  public updateOrders(order: ShopComponent) {
    let comp = this.orderComponents.find(c => c.id === order.id);
    if (comp) {
      comp.quantity += order.quantity;
    }
    else {
      this.orderComponents.push(order);
    }

  }

  public updatePlacedOrders(): void {
    this.orderComponents = [];
    this.showComponent = false;
  }

}

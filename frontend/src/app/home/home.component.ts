import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
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

  public get hasOtherValuesThanNumber(): boolean {
    return Boolean(this.modelError['required'])
  }

  public get showComponents(): boolean {
    return this.showComponent;
  }

  public get showOrders(): boolean {
    return this.showOrder;
  }

  public onClick(): void {
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
        },
        (error) => {
          // Todo
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

  private modelError(): ValidationErrors {
    return this.hasControlError(this.model);
  }

  private hasControlError(control: AbstractControl) {
    if (!control || control.untouched) {
      return {};
    }

    return control.errors ?? {};
  }

}

import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {

  }

  public form = new FormGroup({
    model: new FormControl('', Validators.compose([
      Validators.minLength(1),
      Validators.pattern("^[0-9]*$"),
      Validators.required
    ]),
    )
  });

  ngOnInit(): void {

  }

  public get model() { return this.form.get('model'); }

  public get hasOtherValuesThanNumber(): boolean {
    return Boolean(this.modelError['required'])
  }

  public onClick(): void { }

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

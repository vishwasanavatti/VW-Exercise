/**
 * @description
 * This component renders the compoents for the model and displays the message if components not found.
 */
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { ShopComponent } from '../model/component.model';

@Component({
  selector: 'app-components',
  templateUrl: './components.component.html',
  styleUrls: ['./components.component.css']
})
export class ComponentsComponent implements OnInit {

  @Input()
  components: ShopComponent[] = [];

  @Output()
  cartComponent: EventEmitter<ShopComponent> = new EventEmitter();

  orderComponents: ShopComponent[] = [];

  constructor() { }

  ngOnInit(): void { }

  /**
   * This method creates an array to select quantity for the components
   * @param n
   * @returns array of numbers
   */
  public quantityRange(n: number): number[] {
    return Array.from(Array(n).keys());
  }

  /**
   * This method emtis the component added to cart to the parent component which is then 
   * showed in the Cart column to place the order.
   * @param compId 
   */
  public add(compId: number): void {
    const comp = this.orderComponents.find(c => c.id === compId);

    this.cartComponent.emit(comp);
  }

  /**
   * This method updates the quantity seelcted for the component.
   * @param qty 
   * @param id 
   */
  public updateQuantity(qty: number, id: number): void {
    let comp = Object.assign({}, this.components.find(c => c.id === id));
    comp.quantity = qty;
    let index = this.orderComponents.findIndex(c => c.id === id);
    if (index === -1) {
      this.orderComponents.push(comp);
    } else {
      this.orderComponents[index] = comp;
    }

  }

  /**
   * This method is used to disable the "Add to Cart" button if "Qty" is not selected.
   * @param id 
   * @returns boolean
   */
  public isDisabled(id: number): boolean {
    let index = this.orderComponents.findIndex(c => c.id === id);
    if (index > -1) {
      return false;
    }
    return true;
  }

}

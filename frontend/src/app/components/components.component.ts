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

  public quantityRange(n: number): number[] {
    return Array.from(Array(n).keys());
  }

  public add(compId: number): void {
    const comp = this.orderComponents.find(c => c.id === compId);

    this.cartComponent.emit(comp);
  }

  public updateQuantity(qty: number, id: number): void {
    let comp = Object.assign({}, this.components.find(c => c.id === id));
    comp.quantity = qty;

    this.orderComponents.push(comp);
  }

}

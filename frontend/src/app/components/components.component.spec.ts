import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShopComponent } from '../model/component.model';

import { ComponentsComponent } from './components.component';

/**
 * Tests are added which checks the functions defined in the compoent.
 */
describe('ComponentsComponent', () => {
  let component: ComponentsComponent;
  let fixture: ComponentFixture<ComponentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ComponentsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('quantityRange should return array from 0 to given minus 1 value', () => {
    expect(component.quantityRange(5)).toEqual([0, 1, 2, 3, 4]);
  });

  it('add to cart button is disabled', () => {
    component.components = [createComponent()];
    fixture.detectChanges();

    expect(component.isDisabled(1)).toBeTrue();
  });

  it('add to cart button is not disabled', () => {
    const comp = createComponent();
    component.components = [comp];
    component.orderComponents = [comp];
    fixture.detectChanges();

    expect(component.isDisabled(1)).toBeFalse();
  });

  it('updateQuantity adds the compoent to orderComponents array', () => {
    const comp = createComponent();
    component.components = [comp];
    component.updateQuantity(5, 1);
    fixture.detectChanges();

    expect(component.orderComponents).toEqual([{
      id: 1,
      name: 'component',
      price: 10,
      quantity: 5
    }])
  });

  it('updateQuantity updates the quantity of the compoent if present in orderComponents', () => {
    const comp = createComponent();
    component.orderComponents = [comp];
    component.components = [comp];
    component.updateQuantity(25, 1);
    fixture.detectChanges();

    expect(component.orderComponents).toEqual([{
      id: 1,
      name: 'component',
      price: 10,
      quantity: 25
    }])
  });

  it('add method emits the component', () => {
    const action = spyOn(component.cartComponent, 'emit');
    const comp = createComponent();
    component.orderComponents = [comp];
    component.add(1);
    fixture.detectChanges();

    expect(action).toHaveBeenCalledWith(comp);
  });

  function createComponent(): ShopComponent {
    return {
      id: 1,
      name: 'component',
      price: 10,
      quantity: 10
    }
  }
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { HomeComponent } from './home.component';
import { By } from '@angular/platform-browser';

/**
 * Tests are added which checks the functions defined in the component and API call 
 * made to get the compoents list
 */
describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [HomeComponent]
    })
      .compileComponents();
    httpMock = TestBed.inject(HttpTestingController);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should make a get request on button click', () => {
    component.model.setValue(1);
    fixture.detectChanges();

    // search button
    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();

    // mock url call
    const req = httpMock.expectOne('http://localhost:8080/shop/stock/model/1');
    expect(req.request.method).toEqual('GET');
  });

  it('should set components attribute if get is successful', () => {
    component.model.setValue(1);
    fixture.detectChanges();

    const mockData = [{
      id: 1,
      name: 'component',
      price: 10,
      quantity: 10
    }];

    // search button
    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();

    // mock get call
    const req = httpMock.expectOne('http://localhost:8080/shop/stock/model/1');
    req.flush(mockData);

    // checks if get returns the components list
    expect(component.components).toEqual(mockData);
  });

  it('showComponent set to false if error thrown on get request', () => {
    component.model.setValue(1);
    fixture.detectChanges();

    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();

    const mockErrorResponse = { status: 404, statusText: 'Not Found' };
    const req = httpMock.expectOne('http://localhost:8080/shop/stock/model/1');
    req.flush({}, mockErrorResponse);

    // checks if compoents is not shown when there is error in the GET API
    expect(component.showComponent).toEqual(false);
  });
});

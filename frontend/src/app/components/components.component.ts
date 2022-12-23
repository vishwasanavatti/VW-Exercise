import { Component, Input, OnInit } from '@angular/core';
import { ShopComponent } from '../model/component.model';

@Component({
  selector: 'app-components',
  templateUrl: './components.component.html',
  styleUrls: ['./components.component.css']
})
export class ComponentsComponent implements OnInit {

  @Input()
  components: ShopComponent[] = [];

  constructor() { }

  ngOnInit(): void { }

}

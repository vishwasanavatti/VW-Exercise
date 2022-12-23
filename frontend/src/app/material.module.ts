import { ModuleWithProviders, NgModule } from "@angular/core";
import { MatIconRegistry } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormField } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';

@NgModule({
    imports: [
        MatCardModule,
        MatDividerModule
    ],
    exports: [
        MatCardModule,
        MatDividerModule
    ],
    providers: [
    ]
})
export class MaterialModule {
    constructor(public matIconRegistry: MatIconRegistry) {
        // matIconRegistry.registerFontClassAlias('fontawesome', 'fa');
    }

    static forRoot(): ModuleWithProviders<MaterialModule> {
        return {
            ngModule: MaterialModule,
            providers: [MatIconRegistry, MatFormField]
        };
    }
}
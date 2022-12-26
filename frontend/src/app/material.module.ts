/**
 * @description
 * This module import and exports the material components used in this application.
 */
import { ModuleWithProviders, NgModule } from "@angular/core";
import { MatIconRegistry } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
    imports: [
        MatCardModule,
        MatDividerModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatButtonModule
    ],
    exports: [
        MatCardModule,
        MatDividerModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatButtonModule
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
            providers: [MatIconRegistry]
        };
    }
}
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/search.component';
import { ListComponent } from './components/list.component';

const routes: Routes = [
  { path: '', component: SearchComponent },
  { path: 'movie', component: ListComponent },
  { path: '**', redirectTo: '', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

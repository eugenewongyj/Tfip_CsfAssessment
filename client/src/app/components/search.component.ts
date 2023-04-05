import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  form!: FormGroup

  constructor(private formBuilder: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  search(): void {
    const movieName = this.form.value['movieName']
    const queryParams: Params = { movieName: movieName };
    this.router.navigate(['/movie'], {queryParams : queryParams})
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      movieName: this.formBuilder.control<string>('', [ Validators.required, Validators.minLength(2) ])
    })
  }

}

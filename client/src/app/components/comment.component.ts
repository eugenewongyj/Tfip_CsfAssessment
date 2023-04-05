import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit, OnDestroy{

  form!: FormGroup
  param$!: Subscription
  title: string = ''

  constructor(private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute, private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
      (params) => {
        this.title = params['title']
        console.log(this.title)
      }
    )
    this.form = this.createForm();
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }

  submit(): void {
    const comment = this.form.value
    this.movieService.postComment(this.title, comment)
    console.log('passed to service')
    // Return to previous search
    const savedSearch: string = this.movieService.savedSearch
    const queryParams: Params = { movieName: savedSearch };
    this.router.navigate(['/movie'], {queryParams : queryParams})
  }

  back(): void {
    // Return to previous search
    const savedSearch: string = this.movieService.savedSearch
    const queryParams: Params = { movieName: savedSearch };
    this.router.navigate(['/movie'], {queryParams : queryParams})
  }

  private createForm(): FormGroup {
    return this.formBuilder.group({
      userName: this.formBuilder.control<string>('', [ Validators.minLength(3) ]),
      rating: this.formBuilder.control<number>(5, [ Validators.min(1), Validators.max(5)]),
      commentText: this.formBuilder.control<string>('', [ Validators.required ])
    })
  }

  isControlInvalid(ctrlName: string): boolean {
    const ctrl = this.form.get(ctrlName) as FormControl
    return ctrl.invalid && (!ctrl.pristine)
  }
  

}

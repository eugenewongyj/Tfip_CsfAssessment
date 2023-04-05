import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { Subscription } from 'rxjs';
import { Movie } from '../models/movie';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit, OnDestroy {

  queryParam$!: Subscription
  movieArray: Movie[] = []
  replacementPicture: string = "/assets/placeholder.jpg"

  constructor(private activatedRoute: ActivatedRoute, private movieService: MovieService) {}

  ngOnInit(): void {
    this.queryParam$ = this.activatedRoute.queryParams.subscribe(
      (queryParams) => {
        const movieName = queryParams['movieName']
        this.movieService.getMovies(movieName)
        .then(
          (result) => {
            this.movieArray = result
          }
        )
      }
    )
  }
  
  ngOnDestroy(): void {
    this.queryParam$.unsubscribe()
  }

}

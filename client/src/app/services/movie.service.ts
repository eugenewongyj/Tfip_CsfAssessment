import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../models/movie';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private GET_MOVIES_API: string = "/api/search";

  constructor(private httpClient: HttpClient) { }

  getMovies(movieName: string): Promise<Movie[]> {
    const params = new HttpParams()
                .set("query", movieName)

    return lastValueFrom(this.httpClient
        .get<Movie[]>(this.GET_MOVIES_API, {params: params}));
  }
}

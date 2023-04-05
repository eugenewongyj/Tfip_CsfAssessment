import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../models/movie';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private GET_MOVIES_API: string = "/api/search";

  private POST_COMMENT_API: string = "/api/comment";

  constructor(private httpClient: HttpClient) { }

  getMovies(movieName: string): Promise<Movie[]> {
    const params = new HttpParams()
                .set("query", movieName)

    return lastValueFrom(this.httpClient
        .get<Movie[]>(this.GET_MOVIES_API, {params: params}));
  }

  postComment(title: string, form: any): void {
    const comment = new HttpParams()
      .set('title', "The Black Godfather")
      .set('userName', "123")
      .set('rating', 5)
      .set('commentText', "123")

    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')

    this.httpClient.post<any>(this.POST_COMMENT_API, comment.toString(), { headers: headers })
    console.log('posted!')
  }
}

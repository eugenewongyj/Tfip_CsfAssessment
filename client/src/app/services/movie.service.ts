import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../models/movie';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  savedSearch: string = ''

  private GET_MOVIES_API: string = "/api/search";

  private POST_COMMENT_API: string = "/api/comment";

  constructor(private httpClient: HttpClient) { }

  getMovies(movieName: string): Promise<Movie[]> {
    this.savedSearch = movieName
    const params = new HttpParams()
                .set("query", movieName)

    return lastValueFrom(this.httpClient
        .get<Movie[]>(this.GET_MOVIES_API, {params: params}));
  }

  postComment(title: string, form: any): Promise<any> {
    const comment = new HttpParams()
      .set('title', title)
      .set('userName',form['userName'])
      .set('rating', form['rating'])
      .set('commentText', form['commentText'])

    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')

    return lastValueFrom(this.httpClient.post<any>(this.POST_COMMENT_API, comment.toString(), { headers: headers }))
    
  }
}

import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Task } from './task';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

const putHttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Operation': 'Undefined' })
};

@Injectable({ 
  providedIn: 'root'
})
export class TaskService {

  private tasksUrl:string = 'http://localhost:8080/api/tasks';
  
  getTasks(): Observable<Task[]> {
    const url = `${this.tasksUrl}`;
    return this.http.get<Task[]>(url)
      .pipe(
        tap(_ => this.log(`Fetched tasks`)),
        catchError(this.handleError<Task[]>(`getTasks`, []))
      );
  } 
  
  getTask(id: number): Observable<Task> {
    const url = `${this.tasksUrl}/${id}`;
    return this.http.get<Task>(url)
      .pipe(
        tap(_ => this.log(`Fetched task id = ${id}`)),
        catchError(this.handleError<Task>(`getTask id = ${id}`))
      );
  }   

  updateTask(task: Task): Observable<any> {
    const url = `${this.tasksUrl}/${task.id}`;
    var customHttpOptions = putHttpOptions;
    customHttpOptions.headers = customHttpOptions.headers.set('Operation', 'Update');
    return this.http.put(url, task, customHttpOptions).pipe(
      tap(_ => this.log(`Updated task id=${task.id}`)),
      catchError(this.handleError<any>('updateTask'))
    ); 
  }  

  deleteTask(task: Task): Observable<any> {
    const url = `${this.tasksUrl}/${task.id}`;
    var customHttpOptions = putHttpOptions;
    customHttpOptions.headers = customHttpOptions.headers.set('Operation', 'Delete');
    return this.http.put(url, task, customHttpOptions).pipe(
      tap(_ => this.log(`Deleted task id=${task.id}`)),
      catchError(this.handleError<any>('deleteTask'))
    ); 
  }  

  toggleTask(task: Task): Observable<any> {
    const url = `${this.tasksUrl}/${task.id}`;
    var customHttpOptions = putHttpOptions;
    customHttpOptions.headers = customHttpOptions.headers.set('Operation', 'Toggle');
    return this.http.put(url, task, customHttpOptions).pipe(
      tap(_ => this.log(`Toggled task id=${task.id}`)),
      catchError(this.handleError<any>('toggleTask'))
    ); 
  }  


  createTask(task: Task): Observable<any> {
    const url = `${this.tasksUrl}`;
    return this.http.post(url, task, httpOptions).pipe(
      tap(_ => this.log(`Created new task ${task.text}`)),
      catchError(this.handleError<any>('createTask'))
    ); 
  }

  private log(message: string) {
    this.messageService.add(`TaskService: ${message}`);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);  
      return of(result as T);
    }    
  }

  constructor( private http: HttpClient,
    private messageService: MessageService) { }
}

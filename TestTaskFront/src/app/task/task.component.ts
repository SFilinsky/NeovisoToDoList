import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorageService } from '../token-storage.service';
import { Task } from '../task';
import { TaskService } from '../task.service';
  
@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks: Task[];
  doneTasks: Task[]; 
  undoneTasks: Task[]; 

  constructor(
    private taskService: TaskService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {  
    if (!this.tokenStorage.getToken()) {
      this.router.navigateByUrl("welcome");   
    }
    this.getTasks();
  }

  getTasks(): void {
    this.taskService.getTasks().subscribe(
      tasks => this.saveTasks(tasks)
    );          
  }

  saveTasks(tasks: Task[]): void {
    this.tasks = tasks;
    this.doneTasks = [] as Task[];
    this.undoneTasks = [] as Task[];
    for (let i = 0; i<this.tasks.length; i++) {
      if (this.tasks[i].isDone) this.doneTasks.push(this.tasks[i]);
      if (!this.tasks[i].isDone) this.undoneTasks.push(this.tasks[i]);
    }
  }

}

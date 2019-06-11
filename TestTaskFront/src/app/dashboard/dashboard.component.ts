import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorageService } from '../token-storage.service';
import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tasks: Task[] = [];

  constructor(
    private taskService: TaskService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    if (!this.tokenStorage.getToken()) {
      this.router.navigateByUrl("welcome");   
    }
    this.getHeroes();    
  }

  getHeroes(): void {
    this.taskService.getTasks().subscribe(tasks => this.tasks = tasks.slice(1,5));
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { Location } from '@angular/common';

import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-create',
  templateUrl: './task-create.component.html',
  styleUrls: ['./task-create.component.css']
})
export class TaskCreateComponent implements OnInit {

  @Input() task: Task;

  constructor(
    private taskService: TaskService,
    private location: Location    
  ) { }

  ngOnInit() {
    this.task = new Task();    
  }

  save(): void {
    this.taskService.createTask(this.task)
      .subscribe(() => this.goBack()); 
  }

  goBack(): void {
    this.location.back();
  }

}

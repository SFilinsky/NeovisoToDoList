import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TaskComponent } from './task/task.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TaskCreateComponent } from './task-create/task-create.component';
import { StartPageComponent } from './start-page/start-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'tasks', component: TaskComponent },
  { path: 'tasks/:id', component: TaskDetailComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create', component: TaskCreateComponent },
  { path: 'welcome', component: StartPageComponent} 
] 

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
# todo-scala

## What's todo-scala
todo-scala is an API for task management apps. 
Created for scala, postgreSQL, Play Framework, Docker practice. 

## How to run your local
```
docker-compose up -d
```  

```

TODO: Write DB initialization processing OR support evolutions etc.  

CREATE DATABASE todo;
CREATE TABLE Tasks (id SMALLSERIAL,title VARCHAR(256) NOT NULL,deadline DATE,progress VARCHAR(16),memo TEXT,PRIMARY KEY(id));

```

## How to use
The task is operated with the curl command.
The format of the task to register is as follows.  

| Key Value | Details | 
|---|---|
| id(any) |  Number assigned to the task. If not specified, DB will automatically allocate. |  
| title | The title of the task. |  
|  deadline(any) | Specify the deadline. |  
| progress(any) | Specify the task status. You can specify Doing, Done, Invalid, etc. |  
| memo(any) | You can write a memo on the task. |  

#### Example of use  
`	
{"title":"hogehoge","deadline":2099-01-01,"memo":"fugafuga"}
`      

### Example of curl command 

#### Get all tasks
```
curl -w '\n' http://localhost:9000/todo/tasks
```
#### Get one task
```
curl -w '¥n' -X POST 'http://localhost:9000/todo/tasks'  -H "Content-Type:application/json" -d '{"title":"be master of todo-scala","memo":"Just do it"}'
```
#### Register one task
```
curl -w '\n' http://localhost:9000/todo/tasks/1
```
#### Update one task
```
curl -w '\n' -X PUT 'http://localhost:9000/todo/tasks/update' -H "Content-Type:application/json" -d '{"id":3,"title":"make todo task app","deadline":"2019-08-31","memo":"Try first"}'
```
#### Delete one task
```
curl -w '\n' http://localhost:9000/todo/tasks/delete/9
```


\:shrimp:

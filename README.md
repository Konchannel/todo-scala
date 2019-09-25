# todo-scala

## what's todo-scala
todo-scalaはタスク管理アプリのためのAPIです。
scala,postgreSQL,Play Framework,Dockerの練習用に作成しました。  

## How to run your local
```
docker-compose up -d
```  

```

TODO: DBの初期化処理を書く or evolutionsなどに対応する

```

## how to use
curlコマンドでタスクの操作を行います。  
登録するタスクのフォーマットは以下です。  

| id(任意) | タスクに振られた番号です。指定が無ければDBが自動で割り振ります |  
| title | タスクのタイトルです。 |  
| deadline(任意) | 締め切りを指定します。 |  
| progress(任意) | タスクの状態を指定します。Doing,Done,Invalidなど指定できます |  
| memo(任意) | そのタスクにメモをつけることができます。 |  

使用例  
`	
{"title":"たいとる","deadline":2099-01-01,"memo":"めも"}
`    
以下curlコマンドの例  

#### タスクの全件取得
```
curl -w '\n' http://localhost:9000/todo/tasks
```
#### タスクの1件取得
```
curl -w '¥n' -X POST 'http://localhost:9000/todo/tasks'  -H "Content-Type:application/json" -d '{"title":"be master of todo-scala","memo":"Just do it"}'
```
#### タスク1件を登録
```
curl -w '\n' http://localhost:9000/todo/tasks/1
```
#### タスク1件を更新
```
curl -w '\n' -X PUT 'http://localhost:9000/todo/tasks/update' -H "Content-Type:application/json" -d '{"id":3,"title":"make todo task app","deadline":"2019-08-31","memo":"Try first"}'
```
#### タスク1件を削除
```
curl -w '\n' http://localhost:9000/todo/tasks/delete/9
```


:ebi:

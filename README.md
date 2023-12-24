# clever_backend_task_10_servlet

## Задача

1. Взять за основу проект из лекции паттернов
2. Написать CRUD для всех таблиц
3. Для метода .findAll() сделать пагинацию (по умолчанию 20 элементов на странице, если pagesize не задан)
4. Сделать GET метод, для генерации чека в формате pdf (если товара не существует, тогда генерируем ошибку)
5. Прикрутить возможность инициализации бд и наполнения её данными с помощью параметра в application.yml файле, т.е.
   чтобы при подъеме приложения, приложение создавало схему, таблицы и наполняло таблицы данными
5. Фильтры
6. UI НЕ нужен

___
___

## Запуск

При первом запуске приложения, для активации миграции необходимо в файле src/main/resources/application.yml установить
параметр enabled как true, это создаст таблицу cats в уже существующей базе данных и наполнит её данными.

## Реализация:

### CatController

GET-запрос по id:

```http request
http://localhost:8080/cats?id=1
```

Результат (code 200):

```json
{
   "id": 1,
   "name": "name1",
   "breed": "breed1",
   "color": "color1",
   "age": 1
}
```

Cat не найден (code 404):

```text
Cat not found
```

___
GET-запрос по page и count:

```http request
http://localhost:8080/cats?page=2&count=3
```

Результат (code 200):

```json
[
   {
      "id": 4,
      "name": "name4",
      "breed": "breed2",
      "color": "color2",
      "age": 5
   },
   {
      "id": 5,
      "name": "name5",
      "breed": "breed1",
      "color": "color5",
      "age": 5
   },
   {
      "id": 6,
      "name": "name6",
      "breed": "breed2",
      "color": "color4",
      "age": 3
   }
]
```

Ошибка выполнения (code 500):

```text
Error retrieving cats list
```

___
GET-запрос по page:

```http request
http://localhost:8080/cats?page=1
```

Результат (code 200):

```json
[
   {
      "id": 1,
      "name": "name",
      "breed": "breed",
      "color": "color",
      "age": 2
   },
   {
      "id": 2,
      "name": "name2",
      "breed": "breed2",
      "color": "color1",
      "age": 2
   },
   {
      "id": 3,
      "name": "name3",
      "breed": "breed3",
      "color": "color3",
      "age": 3
   },
   {
      "id": 4,
      "name": "name4",
      "breed": "breed2",
      "color": "color2",
      "age": 5
   },
   {
      "id": 5,
      "name": "name5",
      "breed": "breed1",
      "color": "color5",
      "age": 5
   },
   {
      "id": 6,
      "name": "name6",
      "breed": "breed2",
      "color": "color4",
      "age": 3
   },
   {
      "id": 7,
      "name": "name7",
      "breed": "breed3",
      "color": "color3",
      "age": 2
   },
   {
      "id": 8,
      "name": "name8",
      "breed": "breed1",
      "color": "color2",
      "age": 2
   },
   {
      "id": 9,
      "name": "name9",
      "breed": "breed1",
      "color": "color1",
      "age": 1
   },
   {
      "id": 10,
      "name": "name10",
      "breed": "breed2",
      "color": "color1",
      "age": 2
   },
   {
      "id": 11,
      "name": "name11",
      "breed": "breed3",
      "color": "color3",
      "age": 3
   },
   {
      "id": 12,
      "name": "name12",
      "breed": "breed2",
      "color": "color6",
      "age": 5
   },
   {
      "id": 13,
      "name": "name13",
      "breed": "breed1",
      "color": "color5",
      "age": 5
   },
   {
      "id": 14,
      "name": "name14",
      "breed": "breed2",
      "color": "color4",
      "age": 3
   },
   {
      "id": 15,
      "name": "name15",
      "breed": "breed3",
      "color": "color3",
      "age": 2
   },
   {
      "id": 16,
      "name": "name16",
      "breed": "breed3",
      "color": "color3",
      "age": 2
   },
   {
      "id": 17,
      "name": "name17",
      "breed": "breed4",
      "color": "color5",
      "age": 0
   },
   {
      "id": 18,
      "name": "name18",
      "breed": "breed1",
      "color": "color3",
      "age": 4
   },
   {
      "id": 19,
      "name": "name19",
      "breed": "breed2",
      "color": "color7",
      "age": 1
   },
   {
      "id": 20,
      "name": "name20",
      "breed": "breed3",
      "color": "color3",
      "age": 3
   }
]
```

Ошибка выполнения (code 500):

```text
Error retrieving cats list
```

___
GET-запрос по count:

```http request
http://localhost:8080/cats?count=3
```

Результат (code 200):

```json
[
   {
      "id": 1,
      "name": "name",
      "breed": "breed",
      "color": "color",
      "age": 2
   },
   {
      "id": 2,
      "name": "name2",
      "breed": "breed2",
      "color": "color1",
      "age": 2
   },
   {
      "id": 3,
      "name": "name3",
      "breed": "breed3",
      "color": "color3",
      "age": 3
   }
]
```

Ошибка выполнения (code 500):

```text
Error retrieving cats list
```

Неверный запрос (code 400):

```text
Invalid request parameters
```

___
POST-запрос:

```http request
 http://localhost:8080/cats
```

Body:

```json
{
   "name": "name22",
   "breed": "breed3",
   "color": "color2",
   "age": 2
}
```

Результат (code 200):

```json
{
   "id": 22,
   "name": "name22",
   "breed": "breed3",
   "color": "color3",
   "age": 2
}
```

Ошибка выполнения (code 500):

```text
Cat created error
```

___
PUT-запрос:

```http request
http://localhost:8080/cats
```

Body:

```json
{
   "id": 1,
   "name": "name",
   "breed": "breed",
   "color": "color",
   "age": 2
}
```

Результат (code 200):

```json
{
   "id": 1,
   "name": "name",
   "breed": "breed",
   "color": "color",
   "age": 2
}
```

Ошибка выполнения (code 500):

```text
Cat updated error
```

___
DELETE-запрос (code 200):

```http request
http://localhost:8080/cats?id=22
```

Результат:

```text
Cat is deleted
```

Ошибка выполнения (500):

```text
Cat deleted error
```

___
___

### CheckController

GET-запрос по id:

```http request
http://localhost:8080/cats/check?id=1
```

Результат (code 200):

```text
Check was created
```

Чек будет сгенерирован в папке check в корне проекта

Ошибка выполнения (code 404):

```text
Cat not found
```



# Category

## Display all category
```bash
{{base_url}}/category
```

<details>
  <summary>Scheme</summary>
```json
{
    "status": boolean,
    "message": string,
    "data": [
        {
            "id": integer,
            "name": string,
            "description": string,
            "image": string
        },
        ...
    ]
}
```
</details>

## Product per category
```bash
{{base_url}}/v2/product/category/{category_id}
```

| query      | desc                                   |
|------------|----------------------------------------|
| `page`     | display products in page               |
| `per_page` | display products per page (default 10) |

<details>
  <summary>Scheme</summary>
```json
{
  "status": boolean,
  "message": string,
  "data": {
    "per_page": integer,
    "has_next_page": boolean,
    "page": integer,
    "data": [
      {
        "id": integer,
        "name": string,
        "price": float,
        "category": {
          "id": integer,
          "name": string
        },
        "brand": {
          "id": integer,
          "name": string,
          "logo": string
        },
        "image": string,
        "promoted": boolean
      },
      ...
    ]
  }
}
```
</details>
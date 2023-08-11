# Product By Id

```bash
{{base_url}}/product/{product_id}
```

<details>
  <summary>Scheme</summary>
```json
{
    "status": boolean,
    "message": string,
    "data": {
        "id": integer,
        "name": string,
        "description": string,
        "category": {
            "id": integer,
            "name": string
        },
        "price": float,
        "images": [
            string,
            ...
        ],
        "brand": {
            "id": integer,
            "name": string,
            "logo": string
        },
        "promoted": boolean
    }
}
```
</details>
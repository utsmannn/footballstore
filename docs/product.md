# Product List

## Display all product

```bash
{{base_url}}/v2/product?page={page}
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

## Search product

```bash
{{base_url}}/v2/product/search?q={query}&page={page}&brand={brandId}&category={categoryId}
```

| query      | desc                                   | mandatory |
|------------|----------------------------------------|-----------|
| `q`        | filtered by product name contain query | true      |
| `page`     | display products in page               | false     |
| `brand`    | filtered by brand Id                   | false     |
| `category` | filtered by category Id                | false     |
| `per_page` | display products per page (default 10) | false     |

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

## Featured product

```bash
{{base_url}/v2/product/featured
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


## Top product

```bash
{{base_url}}/v2/product/top
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
```
</details>


## Curated product

```bash
{{base_url}}/v2/product/curated
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
```
</details>

## Thumbnail product
Get product list by product id request

```bash
# get product for id 1, 4, and 5
{{base_url}}/api/v2/product/thumbnail?id=1,4,5
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
```
</details>
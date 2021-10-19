# Practice For Java Spring

SQL files in /database folder , please import it before using api.

## API Spec

### Register a new user

- HTTP Method: ```POST```
- Path: ```/member/register```

**Request Body**

```
{
    "account": "account",
    "password": "PASSWORD",
    "name": "newMember"
    "phone": 0912345678
}
```

**Response Body**
```
{
    "Msg": "",
    "Data": {
        "update_time": "2021-10-19 14:09:44",
        "create_time": "2021-10-19 14:09:44",
        "phone": "0912345678",
        "name": "newMember",
        "id": 1,
        "account": "account"
    },
    "Code": 0
}
```

---

### Login

- HTTP Method: ```Get```
- Path: ```/member/login```

**Query Params**

- account
- password

**Response Body**
```
{
    "Msg": "",
    "Data": {
        "update_time": "2021-10-19 14:09:44",
        "create_time": "2021-10-19 14:09:44",
        "phone": "0912345678",
        "name": "newMember",
        "id": 1,
        "account": "account"
    },
    "Code": 0
}
```
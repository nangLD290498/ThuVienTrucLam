ssh -i "C:\Users\nangl\Downloads\TPBANK-BUILD-DOCKER-2.pem" ubuntu@54.169.49.14

# REST API
The REST API to the example app is described below.

## Save book full flow
### Request

curl --location --request POST 'http://localhost:8080/pdf/saveBookFullFlow?bookString=ewogICAgIm5hbWUiOiAiTmd14buTbiBhbiBs4bqhYyIsCiAgICAiYXV0aG9yIjogIlRow61jaCBUaGFuaCBU4burIiwKICAgICJwdWJsaXNoZXIiOiAiTkJYIHTDtG4gZ2nDoW8gVk4iLAogICAgInB1Ymxpc2hlZFllYXIiOiAiMTk5MCIsCiAgICAicmVtYXJrcyI6ICIiLAogICAgImNhdGVnb3J5IjogewogICAgICAgICJuYW1lIjogIktp4bq/biB0aOG7qWMiLAogICAgICAgICJyZW1hcmtzIjogIiIKICAgIH0KfQ==&tableContent=WwogICAgICAgIHsKICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4gQ2hhcHRlciAxIiwKICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgInRvUGFnZSI6IDk5LAogICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICJpZCI6IG51bGwsCiAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLiBDaGFwdGVyIDEuMSIsCiAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgICAgICAgICAidG9QYWdlIjogMjAsCiAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMS4xIEFTREZBU0RGQVNERiIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYSBBU0RGQVNERiIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbXQogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjEuMS5iIFdFUlFXRVIiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogW10KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYyBRV1RRUkVURVJUIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjIgRVlURVlFUlRZRVJUWSIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjIuYSBFUlRVWVRVUlRJUlVJIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMTAsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiAxNSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB7fQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMi4gQ2hhcHRlciAxLjIiLAogICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDIxLAogICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiA5OSwKICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4yLjEgU0RGR1NERkdTREZHIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjIuMWEgUUVXUldRRVJUIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgfQogICAgICAgICAgICBdCiAgICAgICAgfSwKICAgICAgICB7CiAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjIuIENoYXB0ZXIgMiIsCiAgICAgICAgICAgICJmcm9tUGFnZSI6IDEwMCwKICAgICAgICAgICAgInRvUGFnZSI6IDIwMCwKICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjIuMS4gQ2hhcHRlciAyLjEiLAogICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDEsCiAgICAgICAgICAgICAgICAgICAgInRvUGFnZSI6IDIwCiAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgIF0KICAgICAgICB9CiAgICBd' \
--header 'Accept: application/json' \
--form 'file=@"/D:/TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"'

### Response

{
"status": "OK",
"message": "book saved.",
"content": {
"id": 3798,
"name": "Nguồn an lạc",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"remarks": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"category": {
"id": 3799,
"name": "Kiến thức",
"remarks": "",
"books": null
},
"tableContents": null
}
}

## get file pdf
### Request

curl --location --request GET 'http://localhost:8080/pdf/get/2708' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "pdf found.",
"content": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"
}

## get book details

### Request

curl --location --request GET 'http://localhost:8080/book/3253' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "book found.",
"content": {
"tableContent": [
{
"headerContent": "1. Chapter 1",
"fromPage": 1,
"childs": [
{
"headerContent": "1.1. Chapter 1.1",
"fromPage": 1,
"childs": [
{
"headerContent": "1.1.1 ASDFASDFASDF",
"fromPage": null,
"childs": [
{
"headerContent": "1.1.1.a ASDFASDF",
"fromPage": null,
"childs": [],
"toPage": null
},
{
"headerContent": "1.1.1.b WERQWER",
"fromPage": null,
"childs": [],
"toPage": null
},
{
"headerContent": "1.1.1.c QWTQRETERT",
"fromPage": null,
"childs": [],
"toPage": null
}
],
"toPage": null
},
{
"headerContent": "1.1.2 EYTEYERTYERTY",
"fromPage": null,
"childs": [
{
"headerContent": "1.1.2.a ERTUYTURTIRUI",
"fromPage": 10,
"childs": [],
"toPage": 15
}
],
"toPage": null
}
],
"toPage": 20
},
{
"headerContent": "1.2. Chapter 1.2",
"fromPage": 21,
"childs": [
{
"headerContent": "1.2.1 SDFGSDFGSDFG",
"fromPage": null,
"childs": [
{
"headerContent": "1.2.1a QEWRWQERT",
"fromPage": null,
"childs": [],
"toPage": null
}
],
"toPage": null
}
],
"toPage": 99
}
],
"toPage": 99
},
{
"headerContent": "2. Chapter 2",
"fromPage": 100,
"childs": [
{
"headerContent": "2.1. Chapter 2.1",
"fromPage": 1,
"childs": [],
"toPage": 20
}
],
"toPage": 200
}
],
"bookDetails": {
"id": 3253,
"name": "Nguồn an lạc 4",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"remarks": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"category": {
"id": 3254,
"name": "Kiến thức",
"remarks": "",
"books": null
},
"tableContents": null
}
}
}

## get book list

### Request

curl --location --request GET 'http://localhost:8080/book/getList/1/10/ASC/name?category=name&search=Ngu%E1%BB%93n%20an%20l%E1%BA%A1c' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "book found.",
"content": {
"isFirst": true,
"numberOfElements": 1,
"isLast": true,
"elements": [
{
"tableContent": [
{
"headerContent": "1. Chapter 1",
"fromPage": 1,
"childs": [
{
"headerContent": "1.1. Chapter 1.1",
"fromPage": 1,
"childs": [
{
"headerContent": "1.1.1 ASDFASDFASDF",
"fromPage": null,
"childs": [
{
"headerContent": "1.1.1.a ASDFASDF",
"fromPage": null,
"childs": [],
"toPage": null
},
{
"headerContent": "1.1.1.b WERQWER",
"fromPage": null,
"childs": [],
"toPage": null
},
{
"headerContent": "1.1.1.c QWTQRETERT",
"fromPage": null,
"childs": [],
"toPage": null
}
],
"toPage": null
},
{
"headerContent": "1.1.2 EYTEYERTYERTY",
"fromPage": null,
"childs": [
{
"headerContent": "1.1.2.a ERTUYTURTIRUI",
"fromPage": 10,
"childs": [],
"toPage": 15
}
],
"toPage": null
}
],
"toPage": 20
},
{
"headerContent": "1.2. Chapter 1.2",
"fromPage": 21,
"childs": [
{
"headerContent": "1.2.1 SDFGSDFGSDFG",
"fromPage": null,
"childs": [
{
"headerContent": "1.2.1a QEWRWQERT",
"fromPage": null,
"childs": [],
"toPage": null
}
],
"toPage": null
}
],
"toPage": 99
}
],
"toPage": 99
},
{
"headerContent": "2. Chapter 2",
"fromPage": 100,
"childs": [
{
"headerContent": "2.1. Chapter 2.1",
"fromPage": 1,
"childs": [],
"toPage": 20
}
],
"toPage": 200
}
],
"bookDetails": {
"id": 3798,
"name": "Nguồn an lạc",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"remarks": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"category": {
"id": 3799,
"name": "Kiến thức",
"remarks": "",
"books": null
},
"tableContents": null
}
}
],
"totalPages": 1,
"pageSize": 10,
"page": 1,
"totalElements": 1
}
}




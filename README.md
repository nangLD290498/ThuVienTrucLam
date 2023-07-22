# ThuVienTrucLam
step 1: Save book general information

curl --location --request POST 'http://localhost:8080/pdf/saveBookInfor' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Nguồn an lạc",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"remarks": "",
"category": {
"name": "Kiến thức",
"remarks": ""
}
}'
===========================================================================
step 2: Upload file pdf to resource folder of project

curl --location --request POST 'http://localhost:8080/pdf/upload' \
--header 'Accept: application/json' \
--form 'file=@"/D:/TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"'
===========================================================================
step 3: Save mục lục và text vào db

save text -----------------------------
curl --location --request POST 'http://localhost:8080/pdf/savePdfByPage' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw ' {
"pdfName": "TONG MON CANH HUAN 2022 - TAP 1 CK",
"bookName": "Nguon An Lac"
}'
save muc luc ----------------------------
curl --location --request POST 'http://localhost:8080/pdf/saveContentTable' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"bookName": "Nguồn an lạc",
"contentTable": [
{
"headerContent": "1. Chapter 1",
"fromPage": 1,
"toPage": 99,
"childs": [
{
"id": null,
"headerContent": "1.1. Chapter 1.1",
"fromPage": 1,
"toPage": 20,
"childs": [
{
"id": null,
"headerContent": "1.1.1 ASDFASDFASDF",
"childs": [
{
"id": null,
"headerContent": "1.1.1.a ASDFASDF",
"childs": []
},
{
"id": null,
"headerContent": "1.1.1.b WERQWER",
"childs": []
},
{
"id": null,
"headerContent": "1.1.1.c QWTQRETERT",
"childs": []
}
]
},
{
"id": null,
"headerContent": "1.1.2 EYTEYERTYERTY",
"childs": [
{
"id": null,
"headerContent": "1.1.2.a ERTUYTURTIRUI",
"fromPage": 10,
"toPage": 15,
"childs": []
},
{}
]
}
]
},
{
"id": null,
"headerContent": "1.2. Chapter 1.2",
"fromPage": 21,
"toPage": 99,
"childs": [
{
"id": null,
"headerContent": "1.2.1 SDFGSDFGSDFG",
"childs": [
{
"id": null,
"headerContent": "1.2.1a QEWRWQERT",
"childs": []
}
]
}
]
}
]
},
{
"headerContent": "2. Chapter 2",
"fromPage": 100,
"toPage": 200,
"childs": [
{
"id": null,
"headerContent": "2.1. Chapter 2.1",
"fromPage": 1,
"toPage": 20
}
]
}
]
}'
===================================================================
get book details
curl --location --request GET 'http://localhost:8080/book/4213' \
--header 'Accept: application/json' \
--data-raw ''
============================================================================
saveBookFullFlow

curl --location --request POST 'http://localhost:8080/pdf/saveBookFullFlow?bookString=ewogICAgIm5hbWUiOiAiTmd14buTbiBhbiBs4bqhYyIsCiAgICAiYXV0aG9yIjogIlRow61jaCBUaGFuaCBU4burIiwKICAgICJwdWJsaXNoZXIiOiAiTkJYIHTDtG4gZ2nDoW8gVk4iLAogICAgInB1Ymxpc2hlZFllYXIiOiAiMTk5MCIsCiAgICAicmVtYXJrcyI6ICIiLAogICAgImNhdGVnb3J5IjogewogICAgICAgICJuYW1lIjogIktp4bq/biB0aOG7qWMiLAogICAgICAgICJyZW1hcmtzIjogIiIKICAgIH0KfQ==&tableContent=WwogICAgICAgIHsKICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4gQ2hhcHRlciAxIiwKICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgInRvUGFnZSI6IDk5LAogICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICJpZCI6IG51bGwsCiAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLiBDaGFwdGVyIDEuMSIsCiAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgICAgICAgICAidG9QYWdlIjogMjAsCiAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMS4xIEFTREZBU0RGQVNERiIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYSBBU0RGQVNERiIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbXQogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjEuMS5iIFdFUlFXRVIiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogW10KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYyBRV1RRUkVURVJUIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjIgRVlURVlFUlRZRVJUWSIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImlkIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjIuYSBFUlRVWVRVUlRJUlVJIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMTAsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiAxNSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB7fQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMi4gQ2hhcHRlciAxLjIiLAogICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDIxLAogICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiA5OSwKICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogWwogICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4yLjEgU0RGR1NERkdTREZHIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjIuMWEgUUVXUldRRVJUIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfQogICAgICAgICAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgXQogICAgICAgICAgICAgICAgfQogICAgICAgICAgICBdCiAgICAgICAgfSwKICAgICAgICB7CiAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjIuIENoYXB0ZXIgMiIsCiAgICAgICAgICAgICJmcm9tUGFnZSI6IDEwMCwKICAgICAgICAgICAgInRvUGFnZSI6IDIwMCwKICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAiaWQiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjIuMS4gQ2hhcHRlciAyLjEiLAogICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDEsCiAgICAgICAgICAgICAgICAgICAgInRvUGFnZSI6IDIwCiAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgIF0KICAgICAgICB9CiAgICBd' \
--header 'Accept: application/json' \
--form 'file=@"/D:/TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"'

==================================================================================
getPDF

curl --location --request GET 'http://localhost:8080/pdf/get/2708' \
--header 'Accept: application/json' \
--data-raw ''
==================================================================================
get book list

curl --location --request GET 'http://localhost:8080/book/getList/1/10/ASC/name?category=name&search=Ngu%E1%BB%93n%20an%20l%E1%BA%A1c' \
--header 'Accept: application/json' \
--data-raw ''
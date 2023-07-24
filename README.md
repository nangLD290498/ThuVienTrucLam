ssh -i "C:\Users\nangl\Downloads\TPBANK-BUILD-DOCKER-2.pem" ubuntu@54.169.49.14

# REST API
The REST API to the example app is described below.

## Save book full flow
### Request

curl --location --request POST 'http://localhost:8080/pdf/saveBookFullFlow?bookString=ewogICAgICAgICAgICAiaWQiOiAzMjUzLAogICAgICAgICAgICAibmFtZSI6ICJOZ3Xhu5NuIGFuIGzhuqFjIDQiLAogICAgICAgICAgICAiYXV0aG9yIjogIlRow61jaCBUaGFuaCBU4burIiwKICAgICAgICAgICAgInB1Ymxpc2hlciI6ICJOQlggdMO0biBnacOhbyBWTiIsCiAgICAgICAgICAgICJwdWJsaXNoZWRZZWFyIjogIjE5OTAiLAogICAgICAgICAgICAicmVtYXJrcyI6ICIiLAogICAgICAgICAgICAiY2F0ZWdvcnkiOiB7CiAgICAgICAgICAgICAgICAiaWQiOiAzMjU0LAogICAgICAgICAgICAgICAgIm5hbWUiOiAiS2nhur9uIHRo4bupYyIsCiAgICAgICAgICAgICAgICAicmVtYXJrcyI6ICIiLAogICAgICAgICAgICAgICAgImJvb2tzIjogbnVsbAogICAgICAgICAgICB9LAogICAgICAgICAgICAidGFibGVDb250ZW50cyI6IG51bGwKICAgICAgICB9&tableContent=WwogICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLiBDaGFwdGVyIDEiLAogICAgICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjEuIENoYXB0ZXIgMS4xIiwKICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjEuMSBBU0RGQVNERkFTREYiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IG51bGwsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYSBBU0RGQVNERiIsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiZnJvbVBhZ2UiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInRvUGFnZSI6IG51bGwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgfSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjEuYiBXRVJRV0VSIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IG51bGwsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiY2hpbGRzIjogW10sCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAidG9QYWdlIjogbnVsbAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjEuMS5jIFFXVFFSRVRFUlQiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbXSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiBudWxsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBdLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiBudWxsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICB9LAogICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMS4yIEVZVEVZRVJUWUVSVFkiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IG51bGwsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4xLjIuYSBFUlRVWVRVUlRJUlVJIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDEwLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInRvUGFnZSI6IDE1CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBdLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiBudWxsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIF0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiAyMAogICAgICAgICAgICAgICAgICAgIH0sCiAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIxLjIuIENoYXB0ZXIgMS4yIiwKICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMjEsCiAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgICAgICAgICB7CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMS4yLjEgU0RGR1NERkdTREZHIiwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAiZnJvbVBhZ2UiOiBudWxsLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIHsKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJoZWFkZXJDb250ZW50IjogIjEuMi4xYSBRRVdSV1FFUlQiLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogbnVsbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbXSwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiBudWxsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBdLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiBudWxsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICAgICAgICAgIF0sCiAgICAgICAgICAgICAgICAgICAgICAgICJ0b1BhZ2UiOiA5OQogICAgICAgICAgICAgICAgICAgIH0KICAgICAgICAgICAgICAgIF0sCiAgICAgICAgICAgICAgICAidG9QYWdlIjogOTkKICAgICAgICAgICAgfSwKICAgICAgICAgICAgewogICAgICAgICAgICAgICAgImhlYWRlckNvbnRlbnQiOiAiMi4gQ2hhcHRlciAyIiwKICAgICAgICAgICAgICAgICJmcm9tUGFnZSI6IDEwMCwKICAgICAgICAgICAgICAgICJjaGlsZHMiOiBbCiAgICAgICAgICAgICAgICAgICAgewogICAgICAgICAgICAgICAgICAgICAgICAiaGVhZGVyQ29udGVudCI6ICIyLjEuIENoYXB0ZXIgMi4xIiwKICAgICAgICAgICAgICAgICAgICAgICAgImZyb21QYWdlIjogMSwKICAgICAgICAgICAgICAgICAgICAgICAgImNoaWxkcyI6IFtdLAogICAgICAgICAgICAgICAgICAgICAgICAidG9QYWdlIjogMjAKICAgICAgICAgICAgICAgICAgICB9CiAgICAgICAgICAgICAgICBdLAogICAgICAgICAgICAgICAgInRvUGFnZSI6IDIwMAogICAgICAgICAgICB9CiAgICAgICAgXQ==' \
--header 'Accept: application/json' \
--form 'file=@"/D:/TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"' \
--form 'thumbnailPic=@"/F:/picture (1)/Nang and EFIS/PMAI8077.JPG"'

### Response

{
"status": "OK",
"message": "book saved.",
"content": {
"id": 549,
"name": "Nguồn an lạc 4",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"thumbnailUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\549\\images\\PMAI8077.JPG",
"pdfUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\549\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"remarks": "",
"category": {
"id": 548,
"name": "Kiến thức",
"remarks": "",
"books": null
},
"tableContents": null
}
}

## get file pdf
### Request

curl --location --request GET 'http://localhost:8080/pdf/get/2' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "pdf found.",
"content": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\2\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf"
}

## get book details

### Request

curl --location --request GET 'http://localhost:8080/books/2' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "book found.",
"content": {
"id": 2,
"name": "Nguồn an lạc 5",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"thumbnailUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\2\\images\\PMAI8077.JPG",
"pdfUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\2\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"remarks": "",
"category": {
"id": 1,
"name": "Kiến thức 2",
"remarks": "",
"books": null
},
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
]
}
}

## get book list

### Request

curl --location --request GET 'http://localhost:8080/books?page=1' \
--header 'Accept: application/json' \
--data-raw ''

### Response

{
"status": "OK",
"message": "book found.",
"content": {
"isFirst": true,
"numberOfElements": 2,
"isLast": true,
"elements": [
{
"id": 2,
"name": "Nguồn an lạc 5",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"thumbnailUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\2\\images\\PMAI8077.JPG",
"pdfUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\2\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"remarks": "",
"category": {
"id": 1,
"name": "Kiến thức 2",
"remarks": "",
"books": null
},
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
]
},
{
"id": 549,
"name": "Nguồn an lạc 4",
"author": "Thích Thanh Từ",
"publisher": "NBX tôn giáo VN",
"publishedYear": "1990",
"thumbnailUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\549\\images\\PMAI8077.JPG",
"pdfUrl": "E:\\eclipseStore\\ThuVienTrucLam\\truc_lam_library\\src\\main\\resources\\549\\pdf\\TONG MON CANH HUAN 2022 - TAP 1 CK.pdf",
"remarks": "",
"category": {
"id": 548,
"name": "Kiến thức",
"remarks": "",
"books": null
},
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
]
}
],
"totalPages": 1,
"pageSize": 10,
"page": 1,
"totalElements": 2
}
}




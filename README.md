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
--data-raw '[
{
"headerContent" : "1. Chapter 1",
"fromPage": 1,
"toPage": 99,
"childs" : [
{
"id": null,
"headerContent" : "1.1. Chapter 1.1",
"fromPage": 1,
"toPage": 20,
"childs" : [
{
"id": null,
"headerContent" : "1.1.1 ASDFASDFASDF",
"childs" : [
{
"id": null,
"headerContent" : "1.1.1.a ASDFASDF",
"childs" : []
},
{
"id": null,
"headerContent" : "1.1.1.b WERQWER",
"childs" : []
},
{
"id": null,
"headerContent" : "1.1.1.c QWTQRETERT",
"childs" : []
}
]
},
{
"id": null,
"headerContent" : "1.1.2 EYTEYERTYERTY",
"childs" : [
{
"id": null,
"headerContent" : "1.1.2.a ERTUYTURTIRUI",
"fromPage": 10,
"toPage": 15,
"childs" : []
},
{

							}
						]
					}
				]
			},
			{
				"id": null,
				"headerContent" : "1.2. Chapter 1.2",
                "fromPage": 21,
                "toPage": 99,
				"childs" : [
					{
						"id": null,
						"headerContent" : "1.2.1 SDFGSDFGSDFG",
						"childs" : [
							{
								"id": null,
								"headerContent" : "1.2.1a QEWRWQERT",
								"childs" : [
                                    
                                ]
							}
						]
					}
				]
			}
		]
	},
	{
		"headerContent" : "2. Chapter 2",
        "fromPage": 100,
        "toPage": 200,
		"childs" : [
            {
                "id": null,
				"headerContent" : "2.1. Chapter 2.1",
                "fromPage": 1,
                "toPage": 20
            }
			
		]
	}
]'
===================================================================
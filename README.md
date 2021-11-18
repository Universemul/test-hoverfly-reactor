# How to test

1. Import the project in your favorite IDE
2. Launch the only tets in the code called `testHoverfly`.

It will generate a `test.json` file in the directory `test/resources/stubs`

It Hoverfly can capture the request/response from the `WebClient`, you will see an entry with the `request.path` at `https://docs.openaq.org`.

If not you will see :
```json
{
  "data" : {
    "pairs" : [ ],
    "globalActions" : {
      "delays" : [ ]
    }
  },
  "meta" : {
    "schemaVersion" : "v5.1",
    "hoverflyVersion" : "v1.3.3",
    "timeExported" : "2021-11-18T16:11:46+01:00"
  }
}
```

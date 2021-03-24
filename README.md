# rna_microservice

You can send any file with jq:
```
jq -n --rawfile input refMrna.fa.corrected.txt '{"id":"myId", $input}' | curl -v 'localhost' -H 'Content-Type: application/json' -d@-
```
# rna_microservice

You can send any file with jq:
```
jq -n --rawfile data refMrna.fa.corrected.txt '{"id":"myId", $data}' | curl -v 'localhost:8080/decode' -H 'Content-Type: application/json' -d@- &
```
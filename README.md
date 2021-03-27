# mRNA Microservice

Going from the code in DNA to the final proteins is a very complicated process (Protein Biosynthesis). 
The genetic code in DNA is copied to *messengerRNA* (**mRNA**) that then travels out of the nucleus of the cell into the cytoplasm of the cell. 

Sequences of mRNA are broken into a series of three-nucleotide units known as **codons** (called A, U, G, and C).

Genes are defined by a sequence of what could be many hundreds of codons. Each gene contains a 'start codon' which indicates where it begins and one or more 'stop codon' which defines where the gene ends. Start codons can vary according to the species but there are commonly three possible stop codons: ***UAG**, **UGA** and **UAA***.

## Use cases

Given a single or multiline string of codons that can contain:
* Any of the possible nucleotide representation (A / U / G / C) or any other characters
* White spaces
* Comments - prefixed with `>`
* Line breaks - `\n`

It is cleansed (Remove Comments to the end of the line, Line Breaks, White spaces and extra noise Stop Codons)

It returns a LIST of Gens defined by a sequence of Codons.

It returns a LIST of Errors indicating the line where they were thrown (*Invalid Character* or *Unexpected end of sequence*).

### Event-driven/Streaming Multi-chunk implementation

The mRNA microservice decodes Gens and Codons from an input string in a line-by-line way (splits by line breaks). 
* Gens contain multiple Codons that can be described in *one or mone than one* **lines** of the input string. 
* Long Gens can be decoded with *multiple requests* and each request can have *one or more than one* lines. 
* The system stores intermediate Gens until END Codons are found.  

#### API Endpoint

[**POST**] -> `/decode`

If you send a POST request to the endpoint with an input string to decode:
* You will get a response containing a list of thrown exceptions
* You will get a response containing a list of complete Gens
* Your remaining codons will be stored until you send an end Codon
* You will receive the full Gen on the last response (the one containing the END Codon)

**Response body**:
```json
{
    "results": [
        {
            "gen": {
                "codons": [
                    {
                        "code": 234,
                        "identifier": "AUG",
                        "nucleotide1": "A",
                        "nucleotide2": "U",
                        "nucleotide3": "G"
                    },
                    {
                        "code": 345,
                        "identifier": "UGC",
                        "nucleotide1": "U",
                        "nucleotide2": "G",
                        "nucleotide3": "C"
                    },
                    {
                        "code": 424,
                        "identifier": "GAG",
                        "nucleotide1": "G",
                        "nucleotide2": "A",
                        "nucleotide3": "G"
                    },
                    {
                        "code": 324,
                        "identifier": "UAG",
                        "nucleotide1": "U",
                        "nucleotide2": "A",
                        "nucleotide3": "G"
                    }
                ],
                "identifier": "AUGUGCGAGUAG",
                "code": 234345424324
            }
        }
    ],
    "exceptions": []
}
```

#### Kafka Streams

### Parallel Scalable Multi-threading solution

Due to the nature of the Spring framework, each Request will be handled by a different Thread.

Event streams are handled in a similar way. Each message is handled by a thread in a way that events from different clients can be processed on a same single topic.

Due to the sequential processing nature of the Codons to Gens (an ordered sequence), threads with chunks of data from a **Gen that is already being processed** by another thread will **wait for the first one to finish**. 

Parallelism is achieved using different threads for handling each of the clients requests. If the machine threading (or memory) capacity is exceeded a shared persistence layer should be used in order to maintain order.

An extra parameter `id` is used to identify and group subsequent input strings into the corresponding Gen and check for pending nucleotides from the last execution. 

*WARNING: Change the id only when you have finished sending a full Gen or sending a different sample.* 

**Body**:

```json
{
    "id": "yourId",
    "data": ">NM_0002\naugugcgag gacugcuga >NM_0003 \naugugcgaguag" // <- Example string
}
```

## Running the application

### Dependencies
Dependecies are automatically managed by **Maven** and **Spring**.

### Running on local
Start the process with the command:
```shell script
mvn clean compile exec:java  
```
Or with (once you have compiled and installed deps):
```shell script
mvn exec:java
```

### Running on Docker - 👀 Quick OnBoarding 🤖

```shell script
mvn spring-boot:build-image
```
Docker image deploys mRNA microservice running on `:8080/decode` & Kafka + Zookeeper (preconfigured) running on port `:9092` [Kafka] and `:2181` [Zookeeper]

```shell script
docker-compose up --build 
```

### Run Tests
You can run all test scenarios using:
```shell script
mvn test
```

## Repository Setup

- `app`: Main entrypoint for Spring Boot Application.
- `controllers`: Define the exposed API calls.
- `models`: Implemented the DAO and logic to generate the service data.
    * `models.exceptions`: Custom exceptions.
    * `models.requests`: Request types.
    * `models.responses`: Response types.
- `service`: Implementation of the main functionalities on the Service layer.
    * `service.ParserService`: Main parsing logic.
    * `service.StoreService`: Handles gen and codons storage.
- `resources/application.properties`: properties used by the System.

# Simulator

# Working with files

You can send any file with jq:
```shell script
jq -n --rawfile data refMrna.fa.corrected.txt '{"id":"myId", $data}' | curl -v 'localhost:8080/decode' -H 'Content-Type: application/json' -d@-
```
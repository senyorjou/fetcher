# Fetcher

Play with concurrency


## Usage using serial calls

Comment `getConcurrent` func and run `go run .`

```go
func main() {
	capitals := readJSON()
	getSerial(capitals)
	//getConcurrent(capitals)
}
```

## Usage using concurrent calls

Comment `getSerial` func and run `go run .`

```go
func main() {
	capitals := readJSON()
	//getSerial(capitals)
	getConcurrent(capitals)
}
```



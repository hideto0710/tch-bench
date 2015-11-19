# tch-bench

## Usage

### target

```
val dates = (0 to 34).toList.map(i =>
    now.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
)
```

### code

```
val bench = TchBench((s: String) => logger.info(s))
    .start("bench started")
    .finish((d) => s"""bench finished: duration -> $d""")

val dates = bench.execute(
    (0 to 34).toList.map(i =>
        now.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    )
)
```

### result

```
08:43:42 INFO  [main] App - bench started:
08:43:43 INFO  [main] App - bench finished: duration -> 0:00:00.101
```
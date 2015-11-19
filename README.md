# tch-bench

## Usage

```
val dates = (0 to 34).toList.map(i =>
    now.minusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
)
```

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

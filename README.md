# Typst4K — Kotlin bindings for Typst

## Quick introduction

This library allows 

- Manipulation Typst values:

```kt
val pattern = TPattern(
    size = TArray(30.pt, 30.pt),
    body = TSequence(
        TPlace(body = TLine(start = TArray(0.pc, 0.pc), end = TArray(100.pc, 100.pc))),
        TPlace(body = TLine(start = TArray(0.pc, 100.pc), end = TArray(100.pc, 0.pc))),
    )
)
```
(example from [official documentation on patterns](https://typst.app/docs/reference/visualize/pattern/))

- Converting them to Typst code:

```kt
pattern.repr()
```
converts to 
```typ
pattern(size: (0.0em + 30.0pt, 0.0em + 30.0pt), { place(line(start: (0.0%, 0.0%), end: (100.0%, 100.0%))); place(line(start: (0.0%, 100.0%), end: (100.0%, 0.0%))); })
```

(Which evaluates to the same.
Cosmetic improvements are planned, but not the first priority)

- Accessing Typst compiler:

(with custom executable)

```kt
val typst = Typst("/home/user/.cargo/bin/typst")
typst.compile(Path.of("test.typ"), format = OutputFormat.SVG, ppi = 1440)
```

When no path for the typst compiler is provided, default is used.

```kt
val typst = Typst()
typst.query<TMetadata<TArray<TInt>>>(Path.of("test.typ"), "label")
```

Queries have to be typed (`TValue` is the most general type). When you call a function with a selector rather than a label, the type can be inferred on its own.

When you query with a selector instead of label, the type can be inferred on its own:

```kt
typst.query(Path.of("test.typ"), heading.where(level = 2.t))
// Inferred type is THeading
```

Note that `query<T>` returns `TypstCompilerResult<TArray<T>>`.  
You may handle the errors yourself or use `.orElseThrow()` to get the result.

Besides that, you'll need [the customized version of Typst compiler](https://github.com/LDemetrios/typst-less-dynamic-values) to make queries.
`compile` requests work fine with standard one.

## Installation 

This library is yet in beta testing stage.
To use it, first install it to your local maven repo:

```bash
git clone https://github.com/LDemetrios/LDemetriosCommons.git
cd LDemetriosCommons 
gradle publish
cd ..

git clone https://github.com/LDemetrios/Typst4k
cd Typst4k
gradle publish
cd ..
```

(Or use an appropriate `gradlew` if you haven't installed gradle)

Then include it to your project:

### Maven

```xml
<dependency>
    <groupId>org.ldemetrios</groupId>
    <artifactId>typst4k</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Gradle 
Kotlin DSL:
```kt
implementation("org.ldemetrios:typst4k:0.1.0")
```
Groovy DSL:
```groovy
implementation 'org.ldemetrios:typst4k:0.1.0'
```

## More details

I plan on creating more detailed manual later.

## Plans

- [ ] Improve type checking during deserialization
- [ ] Allow functions, which take primitive arguments (`int`, `str` etc) also accept corresponding Kotlin values (`Int`, `String`). 
- [ ] Beautify `repr` (make more human-readable)

## Contacts

If you experience bugs or have proposal for improvements, feel free to open issues. 
PRs are also welcome, feel free to ask questions about internal structure of the project.

tg: @LDemetrios

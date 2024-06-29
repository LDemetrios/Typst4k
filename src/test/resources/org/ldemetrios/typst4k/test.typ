#let reify(..args) = args

#metadata(
    (
        nones: none,
        autos: auto,
        bools: true,
        ints: 10,
        floats: 2.71828,
        lengths: 1pt + 2em,
        angles: 1rad,
        ratios: 50%,
        relatives: 1pt + 2em + 50%,
        fractions: 1fr,
        colors: (
            luma(50),
            oklab(50%, 50%, 50%),
            oklch(50%, 50%, 5deg),
            rgb(50, 50, 50, 50),
            color.linear-rgb(50, 50, 50, 50),
            cmyk(50%, 50%, 50%, 50%),
            color.hsl(50deg, 50, 50, 50),
            color.hsv(50deg, 50, 50, 50),
        ),
           gradients: (
               gradient.linear(yellow, blue),
               gradient.radial(yellow, blue, focal-center: (10%, 40%), focal-radius: 5%),
               gradient.conic(yellow, blue, center: (20%, 30%)),
           ),
        patterns: pattern(size: (30pt, 30pt))[
            #place(line(start: (0%, 0%), end: (100%, 100%)))
            #place(line(start: (0%, 100%), end: (100%, 0%)))
        ],
        symbols: math.arrow.l,
        versions: version(1, (2, 3)),
        strs: "abc",
        byte-arrs: (bytes((123, 160, 22, 0)), bytes("Hello 😃"),),
        labels: <lbl>,
        datetimes: datetime(year: 2020, month: 10, day: 4),
        durations: datetime.today() - datetime(year: 2020, month: 10, day: 4),
        contents: ([*Hi* there], [
            #place(line(start: (0%, 0%), end: (100%, 100%)))
            #place(line(start: (0%, 100%), end: (100%, 0%)))
        ]),
        arrays: (1, "hi", 12cm),
        dicts: (a: 1, b: "hi"),
        funcs: (it) => it,
        arguments: reify(1, b: 2),
        types: (int, str, dictionary),
        modules: sys,
        aligns: (top, left, center + horizon,),
        dirs: ltr,
        counters: counter(heading),
        selectors: heading.where(level: 1).or(heading.where(level: 2)),
        regexes: regex("[a-z]+"),
        states: state("a" , 0),
        strokes: 2pt + red
    ),
) <full>


= aaa <a>
== bbb
=== ccc <c>
==== ddd <d>

#[
    aaa <bbb>
] <ccc>

#metadata(
    (
        a : 1,
        b : "aaaa",
    )
) <sample>

#metadata(heading.where(level:1)) <selector>

#[
- a
- b
] <list>







#type(1pt + blue)


#stroke()

#metadata(
    (1, "2")
)<s>

#metadata(
    ((1, "2") , (3, "2"))
)<sss>


$a/b^c$ <eq>


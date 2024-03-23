#metadata(
    (
        "none" : none,
        "auto" : auto,
        angles : (
            degrees : 2deg,
            radians : 2rad,
        ),
        array : (1, 2, 3),
        bool : true,
        bytes : bytes("abc"),
        datetime : datetime.today(),
        dictionary : (
            b : 1,
            a : 2,
        ),
        duration : datetime.today() - datetime(year:1970, month:1, day:1),
        floats : (1.0, -0.0, 1e4, calc.nan, calc.inf),
        fractions : (1fr, 1.5fr),
        ints : (1, 2, 9223372036854775807),
        label : <b>,
        // regex : regex("\r\n|\r|\n"),
        str : "str",
        version : version((1, 2), 3),
        relatives : (
            100% + 50pt - 2em,
            2cm + 50pt - 50%,
            2cm + 3cm + 50%,
            2cm + 3mm + 50%,
            7mm + 3mm - 25%,
        ),
        lengths : (
            2cm + 2em,
            2cm - 2mm,
            1pt + 1em + 1mm + 1in,
        ),
        ratio : 100%,
        alignments : (left, left + top),
        colors : (
            rgb(11,22,33),
            oklab(50%, .5, 50%, 50%),
            oklab(50%, .5, 50%),
            oklch(50%, 1.0, 1deg, 2%),
            oklch(50%, 1.0, 1deg),
            color.linear-rgb(100%, 127, 127, 50%),
            color.linear-rgb(100%, 127, 127),
            luma(127),
            cmyk(33%, 50%, 1%, 3%),
            color.hsl(2rad, 2%, 1, 5%),
            color.hsl(2rad, 2%, 1),
            color.hsv(2deg, 2%, 2, 2%),
            color.hsv(2deg, 2%, 2),
        ),
        stroke: (blue + 1pt),
        pat:pattern(size:auto, spacing:(20pt, 20pt), relative:"self", [aaa]),
        grad:gradient.linear(red, blue),
    )
) <lbl>

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


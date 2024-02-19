

bbb


= aaaaaa


== b _b_ <x> bb

aa "bb" cc

$a /b$
a <l>

/*#exec(
  ("test.sh": "ls -Ali\n"),
  (("bash", "test.sh"),),
  (text) => eval(text.at(0).output.replace("\n", "\n\n"), mode: "markup"),
)*/

#set math.equation(numbering: "(1)")


#list(indent:2em, [a], [b])<x>
#enum(indent:2em, number-align: top+end, spacing:100% - 50pt, [a])<x>

#metadata((a:1, b:2)) <c>

#[
a
#parbreak()
#line(length: 100pt)
b
]<fe>

#metadata((a:1, b:2))

$a/b$
- 1
- 2
  - 3
  - 4
- 5

+ 1
+ 3
  + 5
+ 6


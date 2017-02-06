SRC = Lex.ml Scanner.ml Ast.ml Syntax.ml Semantics.ml Main.ml

all: compute

Scanner.ml : Scanner.mll
	ocamllex Scanner.mll

compute: $(SRC)
	ocamlc -o compute $(SRC)
run:
	./compute ex/fact.mml
clean:
	rm -f *.cm* compute Scanner.ml


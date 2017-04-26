all: dag.cma
dag.cmi: dag.mli
	@ocamlc -I /usr/lib/ocaml/ocamlgraph graph.cma dag.mli
dag.cma: dag.cmi dag.ml
	@ocamlc -a -I /usr/lib/ocaml/ocamlgraph graph.cma dag.ml -o dag.cma
	@rm a.out
clean:
	@rm -f *.cm*
	@rm -f a.out

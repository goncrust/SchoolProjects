# Copyright (C) 2021, Pedro Reis dos Santos
.SUFFIXES: .in .out .diff
MAKEFLAGS += --no-print-directory # No entering and leaving messages
OK="\e[1;32mtest $< PASSED\e[0m"
KO="\e[1;31mtest $< FAILED\e[0m"
EXE=../proj2

all:: clean # run regression tests
	@$(MAKE) $(MFLAGS) `ls *.in | sed -e "s/in/diff/"`

.in.diff:
	@-$(EXE) < $< | diff - $*.out > $@
	@if [ `wc -l < $@` -eq 0 ]; then echo $(OK); else echo $(KO); fi;

.in.out:
	$(EXE) < $< > $@

out::
	@for i in `ls test*.in | sed -e "s/in/out/"`; do $(MAKE) $(MFLAGS) $$i; done

clean::
	rm -f *.diff

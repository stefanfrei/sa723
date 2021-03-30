```flow
st=>start: Start
e=>end: End
op1=>operation: Write article
cond1=>condition: Pass article review?
sub1=>subroutine: Work post review
op2=>operation: Translate
cond2=>condition: Needs translation?
cond3=>condition: Pass translation review?
sub2=>subroutine: Work post review
op3=>operation: Publish

st->op1->cond1
cond1(yes)->cond2
cond1(no)->sub1(right)->cond1
cond2(yes)->op2->cond3
cond2(no)->op3
cond3(yes)->op3->e
cond3(no)->sub2(right)->cond3
```

```flow
st=>start: Start
e=>end: Publish
op1=>operation: Write article
cond1=>condition: Pass article review
sub1=>subroutine: Work post review
op2=>operation: Translate
cond2=>condition: Pass translation review
sub2=>subroutine: Work post review

st->op1->cond1
cond1(yes)->cond2
cond1(no)->sub1(right)->op1
cond2(yes)->e
cond2(no)->sub2(right)->op2
```

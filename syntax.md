# Syntax

```
digit excluding zero = ? 1-9 ? ;

digit = "0" | digit excluding zero ;

number = [ "+" | "-" ] , digit excluding zero , { digit } ;

white space = ? one or more white space characters ? ;

alphabetic character = ? A-Za-z ? ;

identifier = alphabetic character , { alphabetic character | digit } ;

factor =
    number
    | "(" , [ white space ] , expr , [ white space ] , ")" ;

term =
    factor ,
    {
        ( [ white space ] , "*" , [ white space ] , factor )
        | ( [ white space ] , "/" , [ white space ] , factor )
    } ;

expr =
    term ,
    {
        ( [ white space ] , "+" , [ white space ] term )
        | ( [ white space ] , "-" , term )
    } ;

let declaration =
    'let' , white space , identifier , [ white space ] , "=" , [ white space ] ,
    expr , [ white space ] , ";"

program = [ white space ] , { let declaration } , [ white space ] ;
```

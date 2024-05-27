%%
%public
%class Analex
%unicode
%cup
%line
%column

/* Caracteres */
WHITESPACE = [ \t\r\n]
DIGIT = [0-9]
LETTER = [a-zA-Z]

%% 

{WHITESPACE}    { /* ignorar espacios en blanco */ }
"main"          { return sym.main; }
"int"           { return sym.INT; }
{LETTER}({LETTER}|{DIGIT})* { return sym.IDENTIFIER; }
"="             { return sym.EQ; }
";"             { return sym.SEMICOLON; }
"("             { return sym.LPAREN; }
")"             { return sym.RPAREN; }
"{"             { return sym.LBRACE; }
"}"             { return sym.RBRACE; }
{DIGIT}+        { return sym.NUMBER; }

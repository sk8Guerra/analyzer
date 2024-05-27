package analyzer;
import static analyzer.ParserTokens.*;
%%
%class Parser
%type ParserTokens

MAIN="main"
PARENTI="()"
LLAVEI= "{"
VAR_TYPE="int"
VAR_NAME=[a-zA-Z]
VAR_VAL=[0-9]
LLAVED= "}"
SPACE=" "
ASSIGN="="
PUNTO_COMA=";"




WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%


{WHITE} {/* ignore */}
"//".* {/* ignore */}

{MAIN}{PARENTI}{SPACE}{LLAVEI} {lexeme=yytext(); return LINEA1_CORRECTO;}
{VAR_TYPE}{SPACE}{VAR_NAME}{ASSIGN}{VAR_VAL}{PUNTO_COMA} {lexeme=yytext(); return LINEA2_CORRECTO;}
{LLAVED} {lexeme=yytext(); return LINEA3_CORRECTO;}

. {return ERROR;}

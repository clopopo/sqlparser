package com.clopopo.sqlparser.lexer

class SQLLexer(input: String) extends Lexer(input) {

  def isLETTER(): Boolean = c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'
  def isNUM(): Boolean = c >= '0' && c <= '9'
  def isWS(): Boolean = c == ' ' || c == '\t' || c == '\r' || c == '\n'
  def isIDPrefix(): Boolean = isLETTER || c == '_' || c == '$'

  def WS(): Unit = {
    while (isWS) {
      consume()
    }
  }

  def getID(): Token = {
    val text = new StringBuilder
    do {
      text + c
      consume
    } while (isIDPrefix || isNUM)
    NAME(text.toString)
  }

  def getNum(): Token = {
    val text = new StringBuilder
    do {
      text + c
      consume
    } while (isNUM)
    NUM(text.toString)
  }

  def nextToken(): Token = {
    c match {
      case ' ' | '\t' | '\n' | '\r' => {
        WS()
        nextToken()
      }
      case COMMA.code      => mkToken(COMMA)
      case LBRACK.code     => mkToken(LBRACK)
      case RBRACK.code     => mkToken(RBRACK)
      case ASTERISK.code   => mkToken(ASTERISK)
      case DIVIDE.code     => mkToken(DIVIDE)
      case MOD.code        => mkToken(MOD)
      case BITAND.code     => mkToken(BITAND)
      case VERTBAR.code    => mkToken(VERTBAR)
      case SEMI.code       => mkToken(SEMI)
      case COLON.code      => mkToken(COLON)
      case DOT.code        => mkToken(DOT)
      case RPAREN.code     => mkToken(RPAREN)
      case LPAREN.code     => mkToken(LPAREN)
      case PLUS.code       => mkToken(PLUS)
      case MINUS.code      => mkToken(MINUS)
      case NEGATION.code   => mkToken(NEGATION)
      case POWER_OP.code   => mkToken(POWER_OP)
      case GTH.code        => mkToken(GTH)
      case LTH.code        => mkToken(LTH)
      case EQUALS.code     => mkToken(EQUALS)
      case QUOTE.code      => mkToken(QUOTE)
      case UNDER_LINE.code => mkToken(UNDER_LINE)
      case DOLLAR.code     => mkToken(DOLLAR)
      case EOF.code        => EOF
      case _ => {
        if (isIDPrefix) {
          getID
        } else if (isNUM) {
          getNum
        } else {
          throw new Error("can not match [" + c + "]")
        }
      }

    }
  }

  def mkToken(token: Token): Token = {
    consume
    token
  }

}
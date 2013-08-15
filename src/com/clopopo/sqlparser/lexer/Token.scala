package com.clopopo.sqlparser.lexer

trait Token {
  def code: Char
  def name: String

  override def toString = "<" + name + ">"

}
//
case class NAME(name: String) extends Token {
  val code = 0 toChar
}

case object COMMA extends Token {
  val code = ','
  val name = "COMMA"

}

case object EOF extends Token {
  val code = -1 toChar
  val name = "EOF"
}

case object LBRACK extends Token {
  val code = '['
  val name = "LBRACK"
}

case object RBRACK extends Token {
  val code = ']'
  val name = "RBRACK"
}

case object ASTERISK extends Token {
  val code = '*'
  val name = "ASTERISK"
}

case object DIVIDE extends Token {
  val code = '/'
  val name = "DIVIDE"
}

case object MOD extends Token {
  val code = '%'
  val name = "MOD"
}

case object BITAND extends Token {
  val code = '&'
  val name = "AND"
}

case object VERTBAR extends Token {
  val code = '|'
  val name = "VERTBAR"
}

case object SEMI extends Token {
  val code = ';'
  val name = "SEMI"
}

case object COLON extends Token {
  val code = ':'
  val name = "COLON"
}

case object DOT extends Token {
  val code = '.'
  val name = "DOT"
}

case object RPAREN extends Token {
  val code = ')'
  val name = "RPAREN"
}

case object LPAREN extends Token {
  val code = '('
  val name = "LPAREN"
}

case object PLUS extends Token {
  val code = '+'
  val name = "PLUS"
}

case object MINUS extends Token {
  val code = '-'
  val name = "MINUS"
}

case object NEGATION extends Token {
  val code = '~'
  val name = "NEGATION"
}

case object POWER_OP extends Token {
  val code = '^'
  val name = "POWER_OP"
}

case object GTH extends Token {
  val code = '>'
  val name = "GTH"
}

case object LTH extends Token {
  val code = '<'
  val name = "LTH"
}

case object EQUALS extends Token {
  val code = '='
  val name = "EQUALS"
}

case object QUOTE extends Token {
  val code = '\''
  val name = "QUOTE"
}

case object UNDER_LINE extends Token {
  val code = '_'
  val name = "UNDER_LINE"
}

case object DOLLAR extends Token {
  val code = '$'
  val name = "DOLLAR"
}




















 
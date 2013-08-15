package com.clopopo.sqlparser.lexer
object test {
  def main(args: Array[String]) {
    val lexer = new SQLLexer("select a.b,b.c c.* fomr a,b,c where a.b = 212")
    var token = lexer.nextToken
    while (token != EOF) {
      println(token)
      token = lexer.nextToken
    }
  }
}

abstract class Lexer(input: String) {
  var p = 0
  var c: Char = input.charAt(p)

  def consume(): Unit = {
    p += 1
    if (p >= input.length) {
      c = EOF.code
    } else {
      c = input.charAt(p)
    }
  }

  def match_(x: Char): Unit = {
    if (c == x) {
      consume()
    } else {
      throw new Error("expecting " + x + "; found " + c)
    }
  }

  def nextToken(): Token

}



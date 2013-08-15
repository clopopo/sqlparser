package com.clopopo.sqlparser.lexer

import com.clopopo.sqlparser.praser.MySQLParser

object Test2 {
	def main(args: Array[String]) {
		val lexer = new SQLLexer("select")
		val parser = new MySQLParser(lexer)
		parser.selectExpression

	}

}
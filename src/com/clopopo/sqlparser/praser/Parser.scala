package com.clopopo.sqlparser.praser

import scala.collection.mutable.ListBuffer
import com.clopopo.sqlparser.lexer.Lexer
import com.clopopo.sqlparser.lexer.Token
import com.clopopo.sqlparser.praser.Keyword.Keyword
import com.clopopo.sqlparser.lexer.NAME
import com.clopopo.sqlparser.lexer.EOF

abstract class Parser(input: Lexer) {
	val markers = ListBuffer[Int]()
	val lookahead = ListBuffer[Token]()
	var p = 0
	sync(1)

	def tokenMatch(x: Token, log: Boolean = true): Unit = {
		if (LT(1) == x) {
			consume
			if (log) {
				println("token  : " + x)
			}

		} else throw new MismatchedTokenException("expecting " + x + "; found " + LT(1))
	}

	def keywordMatch(x: Keyword, log: Boolean = true): Unit = {
		val token = LT(1)
		token match {
			case NAME(text) if text.equalsIgnoreCase(x.toString) => {
				consume
				if (log) {
					println("匹配关键字:" + text)
				}
			}
			case _ => throw new MismatchedKeywordException("expecting " + x.toString() + "; found " + token)
		}
	}

	def tryMatch(x: Keyword): Boolean = {
		val succ = tryDo(keywordMatch, x, false)
		println("尝试匹配" + x + " " + succ)
		succ
	}

	def tryMatch(x: Token): Boolean = {
		tryDo(tokenMatch, x, false)
	}

	def isEOF(): Boolean = {
		LT(1) == EOF
	}

	def isID(): Boolean = {
		val token = LT(1)
		token match {
			case NAME(text) => true
			case _ => false
		}
	}

	def LT(i: Int): Token = {
		sync(i)
		lookahead(p + i - 1)
	}

	def consume(): Unit = {
		p += 1
		if (p == lookahead.size && !isSpeculating) {
			p = 0
			lookahead.clear
		}
		sync(1)
	}

	private def isSpeculating(): Boolean = markers.size > 0

	private def sync(i: Int): Unit = {
		if (p + i > lookahead.size) {
			val n = (p + i) - (lookahead.size)
			fill(n)
		}
	}

	private def fill(n: Int): Unit = {
		if (n > 0) {
			lookahead.append(input.nextToken)
			fill(n - 1)
		}
	}

	def mark(): Int = {
		markers.append(p)
		p
	}

	def release(): Unit = {
		p = markers.last
		markers.remove(markers.size - 1)
	}

	private def tryDo[T](f: ((T, Boolean) => Unit), param: T, log: Boolean): Boolean = {
		var succ = true
		mark
		try {
			f(param, log)
		} catch {
			case e: Exception => {
				println(e.getMessage())
				succ = false
			}
		}
		release
		succ
	}

}
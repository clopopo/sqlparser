package com.clopopo.sqlparser.praser

import com.clopopo.sqlparser.lexer._

import Keyword._
class MySQLParser(input: Lexer) extends Parser(input) {
	def selectExpression(): Unit = {
	    keywordMatch(SELECT)
//		selectPrefix
		selectPrefix
	}

	def selectPrefix: Unit = {
		?(() => { keywordMatch(DISTINCT) }) || ?(() => { keywordMatch(ALL) }) || ?(() => { keywordMatch(ALL) })
	}

	def selectList(): Unit = {
		?(displayedColumn, () => { *(() => { tryMatch(COMMA) }, displayedColumn) }) || $(() => { tokenMatch(ASTERISK) })

	}

	def displayedColumn(): Unit = {
		(?(tableSpec, () => { tokenMatch(DOT) }, () => { tokenMatch(ASTERISK) })) ||
			(?(columnSpec, () => { ?(alias) })) ||
			(?(bitExpr, () => { ?(alias) }))

	}

	def alias(): Unit = {
		?(() => { keywordMatch(AS) })
		aliasName
	}

	def tableSpec(): Unit = {
		(?(schemaName, () => { tokenMatch(DOT) }))
		tableName
	}

	def columnSpec(): Unit = {
		?(() => {
			?(schemaName, () => { tokenMatch(DOT) }, tableName, () => { tokenMatch(DOT) })
			tableName
			tokenMatch(DOT)
		})
		columnName
	}

	def bitExpr(): Unit = {

	}
	// identifiers
	def schemaName(): Unit = id
	def tableName(): Unit = id
	def columnName(): Unit = id
	def aliasName(): Unit = id

	def id(): Unit = {
		if (isID) {
			println("ID " + LT(1))
			consume
		} else {
			throw new MismatchedIDException("expecting an ID , found " + LT(1))
		}
	}

	//匹配0次或者1次
	def ?(fs: () => Unit*): Boolean = {
		if (isEOF) false
		else {
			var succ = true
			mark
			try {
				fs.foreach(_())

			} catch {
				case e: Exception => {
					println(e.getMessage())
					succ = false
					release
				}
			}
			succ
		}
	}
	//匹配0次或多次
	def *(fs: () => Unit*): Boolean = {
		var succ = ?(fs: _*)
		if (succ) { while (?(fs: _*)) {println("--")} }
		succ
	}
	//至少匹配1次
	def +(fs: () => Unit*): Boolean = {
		fs.foreach(_())
		*(fs: _*) || true
	}

	//有且只能匹配一次
	def $(fs: () => Unit*): Boolean = {
		fs.foreach(_())
		true
	}
}



package com.clopopo.sqlparser.praser

import com.clopopo.sqlparser.lexer._

import Keyword._
class MySQLParser(input: Lexer) extends Parser(input) {
	def selectExpression(): Unit = {
		//		keywordMatch(SELECT)
		//		//		selectPrefix
		//		selectPrefix
		keywordMatch(SELECT)
		selectList
 	}

	//correct
	def selectPrefix: Unit = {
		?(() => { keywordMatch(DISTINCT) }) ||
			?(() => { keywordMatch(ALL) }) ||
			?(() => { keywordMatch(ALL) })
	}

	def selectList(): Unit = {
		orElse(?(() => {
			displayedColumn
			*(() => {
				tokenMatch(COMMA)
				displayedColumn
			})
		}) ||
			$(() => { tokenMatch(ASTERISK) }), "no selectList match")

	}

	def displayedColumn(): Unit = {
		orElse((?(() => {
			tableSpec
			tokenMatch(DOT)
			tokenMatch(ASTERISK)
			println("匹配 table.*")
		})) ||
			(?(() => {
				columnSpec
				?(alias)
			})) ||
			(?(() => {
				bitExpr
				?(alias)
			})), "no displayedColumn match")
	}

	//correct
	def alias(): Unit = {
		?(() => {
			keywordMatch(AS)
		})
		aliasName
	}

	//TODO 如果？里正確，外面錯誤，需要一起回滾
	def tableSpec(): Unit = {
		?(() => {
			schemaName
			tokenMatch(DOT)
		})
		tableName
	}

	def columnSpec(): Unit = {
		?(() => {
			tableSpec
			tokenMatch(DOT)
		})
		columnName
	}

	def bitExpr(): Unit = {
		throw new MismatchedIDException("not supported , found " + LT(1))

	}

	//from
	def from(): Unit = {

		keywordMatch(FROM)

	}

	def tableReferences(): Unit = {
		tableReference
		*(() => {
			tokenMatch(COMMA)
			tableReference
		})
	}

	def tableReference(): Unit = {
		orElse(?(tableFactor1) || ?(tableAtom), "无法匹配tableReference")
	}

	def tableFactor1(): Unit = {
		tableFactor2
		?(() => { keywordMatch(INNER)})||
		?(() => {keywordMatch(CROSS)})
		keywordMatch(JOIN)
		tableAtom
		?(joinCondition)
	}

	def joinCondition(): Unit = {}

	def tableFactor2(): Unit = {

	}

	def tableFactor3(): Unit = {

	}

	def tableFactor4(): Unit = {

	}

	def tableAtom(): Unit = {

	}

	// identifiers
	def schemaName(): Unit = id("schemea")
	def tableName(): Unit = id("tableName")
	def columnName(): Unit = id("columnName")
	def aliasName(): Unit = id("aliasName")

	def id(id: String = ""): Unit = {
		if (isID) {
			println("ID " + LT(1))
			consume
		} else {
			throw new MismatchedIDException("expecting an " + id + ", found " + LT(1))

		}
	}

	def orElse(succ: Boolean, error: String): Unit = {
		if (!succ) {
			throw new Exception(error)
		}
	}

}



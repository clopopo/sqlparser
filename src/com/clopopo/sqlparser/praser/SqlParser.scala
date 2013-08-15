package com.clopopo.sqlparser.praser

import com.clopopo.sqlparser.lexer.ASTERISK
import com.clopopo.sqlparser.lexer.COMMA
import com.clopopo.sqlparser.lexer.Lexer
import com.clopopo.sqlparser.lexer.Token
import com.clopopo.sqlparser.praser.Keyword.Keyword

//class SqlParser(input: Lexer) extends Parser(input) {
//
//  def select_expression(): Unit = {
//    SELECT()
//    select_list()
//  }
//
//  trait Exp {
//    def ok(): Boolean
//    def apply(): Unit
//    //zero or more
//    def *(): Exp = {
//      val self = this
//      new Exp {
//        def ok(): Boolean = {
//          self ok
//        }
//        def apply(): Unit = {
//          while (!isEOF && ok) {
//            self apply
//          }
//        }
//      }
//    }
//    // zero or one
//    def ?(): Exp = {
//      val self = this
//      new Exp {
//        def ok(): Boolean = {
//          self ok
//        }
//        def apply(): Unit = {
//          if (!isEOF && ok) {
//            self apply
//          }
//        }
//      }
//    }
//
//    // one or more
//    def +(): Exp = {
//      val self = this
//      new Exp {
//        def ok(): Boolean = {
//          self ok
//        }
//        def apply(): Unit = {
//          self();
//          while (!isEOF && ok) {
//            self()
//          }
//        }
//      }
//    }
//    //only one
//    def $(): Unit = {
//      apply
//    }
//  }
//
//  trait KeywordExp extends Exp {
//    val keyword: Keyword
//    def ok(): Boolean = {
//      tryMatchKeyword(keyword)
//    }
//    def apply(): Unit = {
//      matchKeyword(keyword)
//    }
//  }
//
//  trait TokenExp extends Exp {
//    val token: Token
//    def ok(): Boolean = {
//      tryMatchToken(token)
//    }
//    def apply(): Unit = {
//      matchToken(token)
//
//    }
//  }
//
//  trait AndExp extends Exp {
//    var succ = true
//    val exps: List[Exp]
//    def ok(): Boolean = {
//      mark
//      try {
//        exps.foreach(_())
//      } catch {
//        case e: Exception => { succ = false }
//      }
//      release
//      succ
//    }
//    def apply(): Unit = {
//      if (succ)
//        exps.foreach(_())
//    }
//  }
//
//  trait ComplexExp extends Exp {
//    val exp: Exp
//    def ok(): Boolean = {
//      exp.ok
//    }
//    def apply(): Unit = {
//      exp()
//    }
//  }
//
//  trait OrExp extends Exp {
//    val exps: List[Exp]
//    var r: Option[Exp] = None
//    def ok(): Boolean = {
//      r = exps.find(_.ok)
//      r != None
//    }
//    def apply(): Unit = {
//      if (r != None) {
//        r.get.apply
//      } else {
//        throw new MismatchedKeywordException("")
//      }
//    }
//  }
//
//  case class And(exps: List[Exp]) extends AndExp {}
//  case class Or(exps: List[Exp]) extends OrExp {}
//
//  object SELECT extends KeywordExp {
//    val keyword = Keyword.SELECT
//  }
//  object ALL extends KeywordExp {
//    val keyword = Keyword.ALL
//  }
//  object DISTINCT extends KeywordExp {
//    val keyword = Keyword.DISTINCT
//  }
//  object DISTINCTROW extends KeywordExp {
//    val keyword = Keyword.DISTINCTROW
//  }
//
//  object ASTERISK_ extends TokenExp {
//    val token = ASTERISK
//  }
//
//  object COMMA_ extends TokenExp {
//    val token = COMMA
//  }
//
//  object select_list extends ComplexExp {
//    val exp = And(List(displayed_column, (And(List(COMMA_, displayed_column)))*))
//  }
//
//  object displayed_column extends ComplexExp {
//    val exp = DISTINCT
//  }
//
//  def and(rs: Exp*): Exp = {
//    new Exp {
//      var succ = true
//      def ok(): Boolean = {
//        mark
//        try {
//          rs.foreach(_())
//        } catch {
//          case e: Exception => { succ = false }
//        }
//        release
//        succ
//      }
//      def apply(): Unit = {
//        if (succ)
//          rs.foreach(_())
//      }
//    }
//  }
//
//  def or(rs: Exp*): Exp = {
//    new Exp {
//      var r: Option[Exp] = None
//      def ok(): Boolean = {
//        r = rs.find(_.ok)
//        r != None
//      }
//
//      def apply(): Unit = {
//        if (r != None) {
//          r.get.apply
//        } else {
//          throw new MismatchedKeywordException("")
//        }
//      }
//    }
//  }
//}
//  

 
 
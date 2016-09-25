package org

import org.repo.{Bank, BankRepository}

/**
  * @author Humayun
  */
object Demo {
  def main(args: Array[String]): Unit = {
    BankRepository.createTable
    val bankId =  BankRepository.insert(Bank("IFIC Bank"))

    BankRepository.getAll.foreach(println)

  }
}

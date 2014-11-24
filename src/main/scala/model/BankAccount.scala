package main.scala.model

import javax.validation.constraints._
import scala.beans.BeanProperty

class BankAccount {
//  ba_id (System generated field) - {integer}
//account_name (Optional) - {string}
//routing_number (Required) - {string}
//account_number (Required) - {string}
	@NotNull
	@BeanProperty
	var ba_id : String = ""
	  
	@BeanProperty
	var account_name: String = ""
	
	@NotNull
	@BeanProperty
	var routing_number: String = ""
	  
	@NotNull
	@BeanProperty
	var account_number: String = ""
}
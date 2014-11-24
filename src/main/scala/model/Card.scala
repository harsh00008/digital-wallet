package main.scala.model

import javax.validation.constraints._
import scala.beans.BeanProperty

class Card {
	@NotNull
	@BeanProperty
	var card_id: String = ""
	
	@NotNull
	@BeanProperty
	var card_name: String = ""
	@NotNull
	@BeanProperty
	var card_number: Int = 0
	
	@NotNull
	@BeanProperty
	var expiration_date: String =""
	
}
package main.scala.model

import javax.validation.constraints._
import scala.beans.BeanProperty

class WebLogin {
	@BeanProperty
	var login_id: String = ""
	
	@BeanProperty
	var login: String = ""
	  
	@BeanProperty
	var url: String = ""
	  
	@BeanProperty
	var password: String = ""
	  
}
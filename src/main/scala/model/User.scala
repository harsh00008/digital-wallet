package main.scala.model

import scala.beans.BeanProperty
import java.util.Calendar
import javax.validation.constraints._
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

class User {
  
  @NotNull(message="User id cannot be null")
  @BeanProperty
  var user_id: String = ""
  
  @Size(min=2, max=30, message = "Name should be 2 - 30 chracters") 
  @BeanProperty
  var name: String = ""
  
  @NotEmpty(message="Email cannot be left blank") @Email(message="Invalid Email")
  @BeanProperty
  var email: String = ""
    
  @Size(min=2, max=30, message="Password should be 2 - 30 characters") @NotNull 
  @BeanProperty
  var password: String = ""
  
  @BeanProperty
  var created_at = Calendar.getInstance().getTime().toString()
  
  @BeanProperty
  var updated_at = Calendar.getInstance().getTime().toString()
  
}

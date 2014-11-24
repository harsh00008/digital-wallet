package wallet

import main.scala.model.User
import main.scala.model.Card
import main.scala.model.WebLogin
import main.scala.model.BankAccount
import main.scala.service.WebService
import scala.collection.mutable.Map
import java.util.HashMap
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation._
import org.springframework.stereotype.Controller
import javax.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

/**
 * Configuring and mapping controller for Digital Wallet
 * @author harsh00008
 * @since 1.0
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
class WalletConfig {
  var webService : WebService = new WebService()
  var nullUser: User = new User()
  var nullCard: Card = new Card()
  var nullWebLogin: WebLogin = new WebLogin()
  var nullBankAccount: BankAccount = new BankAccount()
  var nullCardMap: HashMap[String, Card] = new HashMap() 
  var nullWebLoginMap: HashMap[String, WebLogin] = new HashMap()
  var nullBankAccountMap: HashMap[ String, BankAccount] = new HashMap()
  nullUser = null
  nullCard = null
  nullWebLogin = null
  nullBankAccount = null
  nullCardMap = null
  nullWebLoginMap = null
  nullBankAccountMap = null
  
  //Welcome Applicaion
  @RequestMapping(value = Array("/"))
  @ResponseBody
  def welcome(): String  = {
    return "Welcome to Wallet Application"
  }

  //1] add a user
  @RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def createUser(@RequestBody @Valid user: User ) : ResponseEntity[User] = {
	  return new ResponseEntity[User](webService.addUser(user), HttpStatus.CREATED)
  }
  
  //2] get a user by userid
  @RequestMapping(value = Array("/users/{userId}"), method=Array(RequestMethod.GET), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def getUser(@PathVariable userId : String): ResponseEntity[User] = {
	  if( webService.isUserExists(userId) ){
	     var resultUser = webService.getUser(userId)
		 return new ResponseEntity[User](resultUser, HttpStatus.OK)
	  }
	  return new ResponseEntity[User](nullUser, HttpStatus.BAD_REQUEST)
  }
  
  //3] update user
  @RequestMapping(value = Array("/users/{userId}"), method=Array(RequestMethod.PUT), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def updateUser(@PathVariable userId : String, @RequestBody @Valid user: User): ResponseEntity[User] = {
      if( webService.isUserExists(userId) ){
    	  return new ResponseEntity[User](webService.updateUser(userId, user), HttpStatus.CREATED)
      }
      return new ResponseEntity[User](nullUser, HttpStatus.BAD_REQUEST)
  }
  
  //4] add a Card
  @RequestMapping(value = Array("/users/{userId}/idcards"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def createCard(@PathVariable userId: String, @RequestBody @Valid card: Card ): ResponseEntity[Card] = {
	  if( webService.isUserExists(userId) ){
		  return new ResponseEntity[Card](webService.addCard(userId,card), HttpStatus.CREATED)
	  }
	  return new ResponseEntity[Card](nullCard, HttpStatus.BAD_REQUEST)
  }
  
  //5] Get All Cards
  @RequestMapping(value = Array("/users/{userId}/idcards"), method=Array(RequestMethod.GET), produces = Array("application/json"))
  @ResponseBody
  def getAllCard(@PathVariable userId : String): ResponseEntity[HashMap[String, Card]] = {
       if( webService.isUserExists(userId) ){
          var allCards = webService.getAllCard(userId)
	      if( allCards.size() == 0 ){
	          return new ResponseEntity[HashMap[String, Card]](nullCardMap, HttpStatus.NO_CONTENT)
	      }
	 	  return new ResponseEntity[HashMap[String, Card]](allCards, HttpStatus.OK)
       }
       return new ResponseEntity[HashMap[String, Card]](nullCardMap, HttpStatus.BAD_REQUEST)
  }
  
  //6] Delete a card
  @RequestMapping(value = Array("/users/{userId}/idcards/{cardId}"), method=Array(RequestMethod.DELETE), produces = Array("application/json"))
  @ResponseBody
  def removeCard(@PathVariable userId : String, @PathVariable cardId : String): ResponseEntity[String] = {
	  if( webService.deleteCard(userId, cardId) ){
	       return new ResponseEntity[String]("Card Removed Successfully", HttpStatus.NO_CONTENT)
	  }
	  return new ResponseEntity[String]("Invalid User ID or Card", HttpStatus.BAD_REQUEST)
  }

  //7] add a Web Login
  @RequestMapping(value = Array("/users/{userId}/weblogins"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def createWebLogin(@PathVariable userId: String, @RequestBody @Valid webLogin: WebLogin) :ResponseEntity[WebLogin] = {
	  if( webService.isUserExists(userId) ){
		  return new ResponseEntity[WebLogin](webService.addWebLogin(userId, webLogin), HttpStatus.CREATED)
	  }
	  return new ResponseEntity[WebLogin](nullWebLogin, HttpStatus.BAD_REQUEST)
  }
  
  //8] Get all web logins
  @RequestMapping(value = Array("/users/{userId}/weblogins"), method=Array(RequestMethod.GET), produces = Array("application/json"))
  @ResponseBody
  def getAllWebLogin(@PathVariable userId : String): ResponseEntity[HashMap[String, WebLogin]] = {
      if(webService.isUserExists(userId)){
    	  var allWebLogins = webService.getAllWebLogin(userId)
	      if( allWebLogins.size() == 0 ){
	        return new ResponseEntity[HashMap[String, WebLogin]](nullWebLoginMap, HttpStatus.NO_CONTENT)
	      }
		  return new ResponseEntity[HashMap[String, WebLogin]](allWebLogins, HttpStatus.OK)
      }
      return new ResponseEntity[HashMap[String, WebLogin]](nullWebLoginMap, HttpStatus.BAD_REQUEST)
  }
  
  //9] Delete a Web Login
  @RequestMapping(value = Array("/users/{userId}/weblogins/{loginId}"), method=Array(RequestMethod.DELETE), produces = Array("application/json"))
  @ResponseBody
  def removeLogin(@PathVariable userId : String, @PathVariable loginId : String): ResponseEntity[String] = {
	  if( webService.deleteWebLogin(userId, loginId) ){
	       return new ResponseEntity[String]("Web Login Removed Successfully", HttpStatus.NO_CONTENT)
	  }
	  return new ResponseEntity[String]("Invalid User ID or Web Login", HttpStatus.BAD_REQUEST)
  }
  
  //10] add a Bank Account
  @RequestMapping(value = Array("/users/{userId}/bankaccounts"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
  @ResponseBody
  def createBankAccount(@PathVariable userId: String, @Valid @RequestBody bankAccount: BankAccount ): ResponseEntity[BankAccount] = {
	  if(webService.isUserExists(userId)){
	    return new ResponseEntity[BankAccount](webService.addBankAccount(userId, bankAccount), HttpStatus.CREATED)
	  }
	  return new ResponseEntity[BankAccount](nullBankAccount, HttpStatus.BAD_REQUEST)
  }
  
  //11] Get all bank accounts
  @RequestMapping(value = Array("/users/{userId}/bankaccounts"), method=Array(RequestMethod.GET), produces = Array("application/json"))
  @ResponseBody
  def getAllBankAccount(@PathVariable userId : String): ResponseEntity[HashMap[String, BankAccount]] = {
	if( webService.isUserExists(userId) ){
		  var allBankAccounts = webService.getAllBankAccount(userId)
	      if( allBankAccounts.size() == 0 ){
	        return new ResponseEntity[HashMap[String, BankAccount]](nullBankAccountMap, HttpStatus.NO_CONTENT)
	      }
		  return new ResponseEntity[HashMap[String, BankAccount]](allBankAccounts, HttpStatus.OK)
	}  
    	return new ResponseEntity[HashMap[String, BankAccount]](nullBankAccountMap, HttpStatus.BAD_REQUEST)
  }
  
  //12] Delete a bank account
  @RequestMapping(value = Array("/users/{userId}/bankaccounts/{baId}"), method=Array(RequestMethod.DELETE), produces = Array("application/json"))
  @ResponseBody
  def removeBankAccount(@PathVariable userId : String, @PathVariable baId : String): ResponseEntity[String] = {
	  if( webService.deleteBankAccount(userId, baId) ){
	       return new ResponseEntity[String]("Bank Account Removed Successfully", HttpStatus.NO_CONTENT)
	  }
	  return new ResponseEntity[String]("Invalid User ID or Bank Account", HttpStatus.BAD_REQUEST)
  }
  
}